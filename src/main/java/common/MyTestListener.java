package common;

import config.Environment;
import io.restassured.RestAssured;
import org.testng.*;

public class MyTestListener implements ISuiteListener, ITestListener {

    public void onStart(ISuite iSuite) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        new Environment();
        new Spec();
        if (!CommonTests.isAppBackendUp()) // check if the service under test is up before running any tests
            throw new SkipException("Skipping tests as the application backend is down.");
    }

    public void onFinish(ISuite iSuite) {
        System.out.println("Finished running all the tests.");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Starting test " + iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test " + iTestResult.getName() + " PASSED");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test " + iTestResult.getName() + "  FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test " + iTestResult.getName() + " SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
