package tests;

import com.jayway.jsonpath.JsonPath;
import config.Environment;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class PostTests {

    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    Environment environment;
    Response allUsersResponse;
    int userId;

    @BeforeSuite
    public void setupRequestResponseSpecs() {
        environment = new Environment();
        System.out.println("Setting up the request and response specifications");
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(environment.baseUrl)
                .addHeader("Content-Type", "application/json")
                .build();
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    @Test
    public void verifyGetAllUsers() {
        allUsersResponse = given().spec(requestSpec)
                .get(environment.usersEndpoint);
        allUsersResponse
                .then()
                .assertThat()
                .body("size()", greaterThan(0));
    }

    @Test(dependsOnMethods = "verifyGetAllUsers")
    @Parameters({"userName"}) // get the userName from testng.xml
    public void verifyGetUserIdForUser(String userName) {
        List<Integer> users = JsonPath.read(allUsersResponse.getBody().asString(), "$.[?(@.username=='" + userName + "')].id");
        if (users.size() > 0) {
            userId = users.get(0);
            System.out.println("userId for the username " + userName + " = " + userId);
        } else
            Assert.fail("No user found with username = " + userName);
    }
}
