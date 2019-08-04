package common;

import config.Environment;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class Spec {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification successResponseSpec;

    public Spec() {
        System.out.println("Initializing request and response specifications");
        Spec.requestSpec = new RequestSpecBuilder()
                .setBaseUri(Environment.baseUrl)
                .addHeader("Content-Type", "application/json")
                .build();
        Spec.successResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}
