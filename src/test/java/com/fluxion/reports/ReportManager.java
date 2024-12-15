package com.fluxion.reports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {

    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    // Initialize the report
    public static void initializeReport() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        // Use ExtentSparkReporter to generate the HTML report
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/TestReport_" + timestamp + ".html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    // Start a new test case in the report
    public static ExtentTest startTest(String testName) {
        extentTest = extentReports.createTest(testName);
        return extentTest;
    }

    // Log steps to the test case
    public static void log(String message) {
        if (extentTest != null) {
            extentTest.info(message);
        }
    }

    // End the test and generate the report
    public static void endTest() {
        if (extentReports != null) {
            extentReports.flush(); // This will flush the report and write it to the output file.
        }
    }

    // Add a screenshot to the report
    public static void addScreenshot(String screenshotPath) {
        if (extentTest != null) {
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }
    }
}
