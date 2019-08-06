package components;

import common.Spec;
import config.Environment;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

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

    private static String getCreatePostPayload(String postTitle, String postBody, int userId) {
        JSONObject createPostJsonObject = new JSONObject();
        createPostJsonObject.put("title", postTitle);
        createPostJsonObject.put("body", postBody);
        createPostJsonObject.put("userId", userId);
        return createPostJsonObject.toString();
    }

    private static String getUpdatePostPayload(String key, String value) {
        JSONObject updatePostJsonObject = new JSONObject();
        updatePostJsonObject.put(key, value);
        return updatePostJsonObject.toString();
    }

    public static Response createPost(String postTitle, String postBody, int userId) {
        Response createPostResponse = given()
                .spec(Spec.requestSpec)
                .body(getCreatePostPayload(postTitle, postBody, userId))
                .post(Environment.postsEndpoint);
        createPostResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return createPostResponse;
    }

    public static Response updatePost(String updateAttributeKey, String updateAttributeValue, String postId) {
        Response updatePostResponse = given()
                .spec(Spec.requestSpec)
                .body(getUpdatePostPayload(updateAttributeKey, updateAttributeValue))
                .put(Environment.postsEndpoint + "/" + postId);
        updatePostResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return updatePostResponse;
    }

    public static Response deletePost(String postId) {
        Response deletePostResponse = given()
                .spec(Spec.requestSpec)
                .delete(Environment.postsEndpoint + "/" + postId);
        deletePostResponse
                .then()
                .assertThat()
                .spec(Spec.successResponseSpec);
        return deletePostResponse;
    }
}
