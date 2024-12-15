package com.fluxion.helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

public class ScreenshotHelper {

    private WebDriver driver;

    public ScreenshotHelper(WebDriver driver) {
        this.driver = driver;
    }

    // Method to capture screenshot
    public void captureScreenshot(String screenshotName) throws IOException {
        // Capture screenshot as a file
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the destination path for the screenshot
        File destination = new File("screenshots/" + screenshotName + ".png");

        // Copy the screenshot to the destination
        FileUtils.copyFile(screenshot, destination);
    }
}
