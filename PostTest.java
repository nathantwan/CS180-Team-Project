import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class PostTest {

    @Test
    public void testCreateValidPost() throws InvalidPostException {
        User user = new User("John", "Doe", "johndoe", "password123", new ImageIcon("avatar.png"));
        Post post = new Post("My first post", "image.png", user);
        assertEquals("My first post", post.getCaption());
        assertEquals("image.png", post.getImage());
        assertEquals(user, post.getUser());
        assertEquals(0, post.getUpvote());
        assertEquals(0, post.getDownvote());
    }

    @Test(expected = InvalidPostException.class)
    public void testCreateInvalidPostWithNullUser() throws InvalidPostException {
        new Post("Invalid post", "image.png", null);
    }

    @Test(expected = InvalidPostException.class)
    public void testCreateInvalidPostWithEmptyCaption() throws InvalidPostException {
        User user = new User("John", "Doe", "johndoe", "password123", new ImageIcon("avatar.png"));
        new Post("", "image.png", user);
    }

    @Test
    public void testEditPost() throws InvalidPostException {
        User user = new User("John", "Doe", "johndoe", "password123", new ImageIcon("avatar.png"));
        Post post = new Post("My first post", "image.png", user);
        post.editPost("Updated caption");
        assertEquals("Updated caption", post.getCaption());
    }

    @Test
    public void testVoteFunctions() throws InvalidPostException {
        User user = new User("John", "Doe", "johndoe", "password123", new ImageIcon("avatar.png"));
        Post post = new Post("My first post", "image.png", user);
        post.incrementUpvote();
        post.incrementUpvote();
        assertEquals(2, post.getUpvote());
        post.incrementDownvote();
        assertEquals(1, post.getDownvote());
    }

    @Test
    public void testAddAndDeleteComment() throws InvalidPostException, InvalidCommentException {
        User user1 = new User("John", "Doe", "johndoe", "password123", new ImageIcon("avatar1.png"));
        User user2 = new User("Jane", "Doe", "janedoe", "password456", new ImageIcon("avatar2.png"));
        Post post = new Post("My first post", "image.png", user1);
        Comment comment = new Comment("Great post!", user1, user2, post);
        post.addComment(comment);
        assertEquals(1, post.comments.size());

        // Test delete comment by postOwner
        post.deleteComment(comment, user1);
        assertEquals(0, post.comments.size());

        // Test delete comment by commenter
        post.addComment(comment);
        post.deleteComment(comment, user2);
        assertEquals(0, post.comments.size());

        // Test delete comment by unauthorized user
        User user3 = new User("Mike", "Smith", "mikesmith", "password789", new ImageIcon("avatar3.png"));
        post.addComment(comment);
        try {
            post.deleteComment(comment, user3);
            fail("Expected an InvalidCommentException to be thrown");
        } catch (InvalidCommentException e) {
            assertEquals("You do not have permission!", e.getMessage());
        }
        assertEquals(1, post.comments.size());
    }
}