package common;

import config.Environment;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.Logger;


public class Spec {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification successResponseSpec;
    private Logger log = MyLogger.log;

    public Spec() {
        log.info("Initializing request and response specifications");
        Spec.requestSpec = new RequestSpecBuilder()
                .setBaseUri(Environment.baseUrl)
                .addHeader("Content-Type", "application/json")
                .build();
        Spec.successResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}
