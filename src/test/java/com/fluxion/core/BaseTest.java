package com.fluxion.core;

import com.fluxion.config.ConfigLoader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void setUp() {
        String browser = ConfigLoader.getProperty("browser");
        com.fluxion.core.DriverManager.initializeDriver(browser);
    }

    @AfterMethod
    public void tearDown() {
        com.fluxion.core.DriverManager.quitDriver();
    }
}
