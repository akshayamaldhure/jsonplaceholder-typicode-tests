package tests;

import components.CommentComponent;
import components.PostComponent;
import components.UserComponent;
import models.Comment;
import models.Post;
import models.User;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class PostNegativeTests {

    @Test
    @Parameters({"invalidUserName"}) // get the userName from testng.xml
    public void verifyGetUserWithInvalidNameReturnsAnEmptyArray(String invalidUserName) {
        List<User> users = Arrays.asList(UserComponent.getUser(invalidUserName).getBody().as(User[].class));
        Assert.assertEquals("One or more users found with name " + invalidUserName, users.size(), 0);
    }

    @Test
    @Parameters({"invalidUserId"}) // get the userId from testng.xml
    public void verifyGetPostsByInvalidUserIdReturnsAnEmptyArray(String invalidUserId) {
        int invalidUserIdInt = Integer.parseInt(invalidUserId);
        List<Post> posts = Arrays.asList(PostComponent.getPosts(invalidUserIdInt).getBody().as(Post[].class));
        Assert.assertEquals("One or more posts found by user with userId " + invalidUserIdInt, posts.size(), 0);
    }

    @Test
    @Parameters({"invalidPostId"}) // get the postId from testng.xml
    public void verifyGetPostCommentsByInvalidPostIdReturnsAnEmptyArray(String invalidPostId) {
        int invalidPostIdInt = Integer.parseInt(invalidPostId);
        List<Comment> comments = Arrays.asList(CommentComponent.getCommentsOnPost(invalidPostIdInt).getBody().as(Comment[].class));
        Assert.assertEquals("One or more comments found on the post with postId " + invalidPostId, comments.size(), 0);
    }
}
