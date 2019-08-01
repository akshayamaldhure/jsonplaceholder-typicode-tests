package config;

import io.restassured.RestAssured;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.SkipException;

public class MyTestListener implements ISuiteListener {

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
}
