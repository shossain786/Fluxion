package com.fluxion.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

/**
 * A thread-safe library for common Selenium actions.
 */
public class SeleniumActions {

    private static final Logger logger = LogManager.getLogger(SeleniumActions.class);

    private final ThreadLocal<WebDriver> driver;
    private final WebDriverWait wait;
    private final SoftAssert softAssert;

    public SeleniumActions(ThreadLocal<WebDriver> driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver.get(), Duration.ofSeconds(timeoutInSeconds));
        this.softAssert = new SoftAssert();
    }

    /**
     * Clicks on a web element.
     */
    public void click(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked on element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to click on element: " + locator, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends keys to a web element.
     */
    public void sendKeys(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
            logger.info("Sent keys to element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to send keys to element: " + locator, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Selects an option from a dropdown by visible text.
     */
    public void selectByVisibleText(By locator, String visibleText) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(visibleText);
            logger.info("Selected option '" + visibleText + "' from dropdown: " + locator);
        } catch (Exception e) {
            logger.error("Failed to select option from dropdown: " + locator, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks or unchecks a checkbox or radio button.
     */
    public void setCheckboxOrRadio(By locator, boolean check) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            if (element.isSelected() != check) {
                element.click();
                logger.info("Set checkbox/radio button state to: " + check);
            }
        } catch (Exception e) {
            logger.error("Failed to set checkbox/radio button state: " + locator, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Switches to an alert and accepts it.
     */
    public void acceptAlert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            logger.info("Accepted alert");
        } catch (Exception e) {
            logger.error("Failed to accept alert", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Switches to an alert and dismisses it.
     */
    public void dismissAlert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.dismiss();
            logger.info("Dismissed alert");
        } catch (Exception e) {
            logger.error("Failed to dismiss alert", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Switches to a specific window by title.
     */
    public void switchToWindow(String title) {
        try {
            for (String handle : driver.get().getWindowHandles()) {
                driver.get().switchTo().window(handle);
                if (driver.get().getTitle().equals(title)) {
                    logger.info("Switched to window: " + title);
                    return;
                }
            }
            throw new NoSuchElementException("Window with title not found: " + title);
        } catch (Exception e) {
            logger.error("Failed to switch to window: " + title, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Switches to a frame by locator.
     */
    public void switchToFrame(By locator) {
        try {
            WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            driver.get().switchTo().frame(frame);
            logger.info("Switched to frame: " + locator);
        } catch (Exception e) {
            logger.error("Failed to switch to frame: " + locator, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifies if an element is displayed with SoftAssert.
     */
    public void verifyElementDisplayed(By locator) {
        try {
            boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
            softAssert.assertTrue(isDisplayed, "Element is not displayed: " + locator);
            logger.info("Verified element is displayed: " + locator);
        } catch (Exception e) {
            logger.error("Failed to verify element is displayed: " + locator, e);
            softAssert.fail("Exception while verifying element displayed: " + e.getMessage());
        }
    }

    /**
     * Gets the text of a web element.
     */
    public String getElementText(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String text = element.getText();
            logger.info("Text of element " + locator + ": " + text);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text of element: " + locator, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Validates if a field contains the expected text.
     */
    public void validateFieldText(By locator, String expectedText) {
        try {
            String actualText = getElementText(locator);
            softAssert.assertEquals(actualText, expectedText, "Field text does not match.");
            logger.info("Field text validation for " + locator + ": Expected = " + expectedText + ", Actual = " + actualText);
        } catch (Exception e) {
            logger.error("Failed to validate field text: " + locator, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Validates the state of an element.
     */
    public void validateElementState(By locator, boolean expectedState) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            boolean actualState = element.isEnabled();
            softAssert.assertEquals(actualState, expectedState, "Element state does not match.");
            logger.info("Element state validation for " + locator + ": Expected = " + expectedState + ", Actual = " + actualState);
        } catch (Exception e) {
            logger.error("Failed to validate element state: " + locator, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Asserts all SoftAssert validations.
     */
    public void assertAll() {
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            logger.error("Assertion errors found:", e);
            throw e;
        }
    }
}
