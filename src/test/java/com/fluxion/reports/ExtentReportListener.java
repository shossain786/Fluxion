package com.fluxion.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ExtentReportListener extends TestListenerAdapter implements ITestListener {

    private static ExtentReports extentReports;
    private static ExtentTest extentTest;
    private static ExtentSparkReporter sparkReporter;

    // Initialize the report
    public static void initializeReport() {
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
        sparkReporter = new ExtentSparkReporter("reports/TestReport_" + timestamp + ".html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        // Optional: Customize the report
        sparkReporter.config().setDocumentTitle("Test Report");
        sparkReporter.config().setReportName("Test Execution Report");
    }

    // Called before a test starts
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
        extentTest = extentReports.createTest(result.getName());  // Create a test with the name of the test case
    }

    // Called when a test is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
        extentTest.log(Status.SKIP, "Test skipped");  // Log skip status
    }

    // Called when a test is passed
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
        extentTest.log(Status.PASS, "Test passed");  // Log pass status
    }

    // Called when a test fails
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        extentTest.log(Status.FAIL, "Test failed");  // Log fail status

        // Optionally add a screenshot (if you capture one)
        // extentTest.addScreenCaptureFromPath("path_to_screenshot");
    }

    /*// Called when a test is finished
    @Override
    public void onTestFinish(ITestResult result) {
        System.out.println("Test finished: " + result.getName());
        extentReports.flush();  // Flush the report at the end of the test
    }*/

    // Called when the test suite finishes
    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();  // Flush the report at the end of the suite
    }

    // Optionally add a listener to the suite (TestNG specific)
    public static void setUp() {
        initializeReport();
    }

    public static void tearDown() {
        extentReports.flush();
    }
}
