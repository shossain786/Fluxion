package com.fluxion.core;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void setUp() {
        String browser = ConfigManager.getProperty("browser");
        com.fluxion.core.DriverManager.initializeDriver(browser);
    }

    @AfterMethod
    public void tearDown() {
        com.fluxion.core.DriverManager.quitDriver();
    }
}
