package tests;

import com.jayway.jsonpath.JsonPath;
import config.Environment;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.Comment;
import models.Post;
import org.junit.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class PostTests {

    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    Environment environment;
    Response allUsersResponse;
    Response allPostsByUserResponse;
    Response allCommentsOnUserPostsResponse;
    int userId;
    List<Post> allPostsByUser;
    List<Comment> allCommentsOnUserPosts;

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
        allUsersResponse = given()
                .spec(requestSpec)
                .get(environment.usersEndpoint);
        allUsersResponse
                .then()
                .assertThat()
                .spec(responseSpec);
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

    @Test(dependsOnMethods = "verifyGetUserIdForUser")
    public void verifyGetPostsByUserId() {
        allPostsByUserResponse = given()
                .spec(requestSpec)
                .param("userId", userId)
                .get(environment.postsEndpoint);
        allPostsByUserResponse
                .then()
                .assertThat()
                .spec(responseSpec);
        allPostsByUser = Arrays.asList(allPostsByUserResponse.getBody().as(Post[].class));
        allPostsByUser.forEach(post -> Assert.assertEquals(post.getUserId(), userId));
    }

    @Test(dependsOnMethods = "verifyGetPostsByUserId")
    public void verifyPostComments() {
        allPostsByUser.forEach(post -> {
            int postId = post.getId();
            allCommentsOnUserPostsResponse = given()
                    .spec(requestSpec)
                    .param("postId", postId)
                    .get(environment.commentsEndpoint);
            allCommentsOnUserPostsResponse
                    .then()
                    .assertThat()
                    .spec(responseSpec);
            allCommentsOnUserPosts = Arrays.asList(allCommentsOnUserPostsResponse.getBody().as(Comment[].class));
            String emailRegex = "^(.+)@(.+)$";
            allCommentsOnUserPosts.forEach(comment -> {
                Assert.assertTrue(comment.getEmail().matches(emailRegex));
            });
        });
    }
}
