package tests;

import com.jayway.jsonpath.JsonPath;
import components.CommentComponent;
import components.PostComponent;
import components.UserComponent;
import io.restassured.response.Response;
import models.Comment;
import models.Post;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;

public class PostTests {

    private Response allUsersResponse;
    private Response allCommentsOnUserPostsResponse;
    private int userId;
    private List<Post> allPostsByUser;
    private  List<Comment> allCommentsOnUserPosts;

    @Test
    public void verifyGetAllUsers() {
        allUsersResponse = UserComponent.getAllUsers();
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
        allPostsByUser = Arrays.asList(PostComponent.getPosts(userId).getBody().as(Post[].class));
        allPostsByUser.forEach(post -> Assert.assertEquals(post.getUserId(), userId));
    }

    @Test(dependsOnMethods = "verifyGetPostsByUserId")
    public void verifyPostComments() {
        allPostsByUser.forEach(post -> {
            int postId = post.getId();
            allCommentsOnUserPostsResponse = CommentComponent.getCommentsOnPost(postId);
            allCommentsOnUserPosts = Arrays.asList(allCommentsOnUserPostsResponse.getBody().as(Comment[].class));
            String emailRegex = "^(.+)@(.+)$";
            allCommentsOnUserPosts.forEach(comment -> {
                Assert.assertTrue(comment.getEmail().matches(emailRegex));
            });
        });
    }
}
