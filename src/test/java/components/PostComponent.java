package components;

import common.Spec;
import config.Environment;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostComponent { // each method in a component would return a REST-assured Response object

    public static Response getPosts(int userId) {
        Response allPostsResponse = given()
                .spec(Spec.requestSpec)
                .param("userId", userId)
                .get(Environment.postsEndpoint);
        allPostsResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allPostsResponse;
    }

    public static Response getPosts() {
        Response allPostsResponse = given()
                .spec(Spec.requestSpec)
                .get(Environment.postsEndpoint);
        allPostsResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return allPostsResponse;
    }

}
