package common;

import static io.restassured.RestAssured.given;

public class CommonTests {

    public static boolean isAppBackendUp() {
        return given()
                .spec(Spec.requestSpec)
                .get().statusCode() == 200;

    }
}