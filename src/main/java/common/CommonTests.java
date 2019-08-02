package common;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CommonTests {

    public static boolean isAppBackendUp() {
        Response response = given().get();
        return response.statusCode() == 200;
    }
}
