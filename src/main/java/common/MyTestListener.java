package common;

import config.Environment;
import io.restassured.RestAssured;
import org.testng.*;

public class MyTestListener implements ISuiteListener, ITestListener {

    public void onStart(ISuite iSuite) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        Environment environment = new Environment();
        RestAssured.baseURI = environment.baseUrl; // set the app baseURI "globally"
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

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

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
