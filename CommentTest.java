import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class CommentTest {

    @Test
    public void testCreateValidComment() throws InvalidCommentException {
        User user1 = new User("John", "Doe", "johndoe", "password123", new ImageIcon("avatar1.png"));
        User user2 = new User("Jane", "Doe", "janedoe", "password456", new ImageIcon("avatar2.png"));
        Post post = new Post("My first post", "image.png", user1);
        Comment comment = new Comment("Great post!", user1, user2, post);
        assertEquals("Great post!", comment.getText());
        assertEquals(user1, comment.getPostOwner());
        assertEquals(user2, comment.getCommenter());
        assertEquals(post, comment.getPost());
    }

    @Test(expected = InvalidCommentException.class)
    public void testCreateInvalidCommentEmptyText() throws InvalidCommentException {
        User user1 = new User("John", "Doe", "johndoe", "password123", new ImageIcon("avatar1.png"));
        User user2 = new User("Jane", "Doe", "janedoe", "password456", new ImageIcon("avatar2.png"));
        Post post = new Post("My first post", "image.png", user1);
        new Comment("", user1, user2, post);
    }

    @Test
    public void testEditComment() throws InvalidCommentException {
        User user1 = new User("John", "Doe", "johndoe", "password123", new ImageIcon("avatar1.png"));
        User user2 = new User("Jane", "Doe", "janedoe", "password456", new ImageIcon("avatar2.png"));
        Post post = new Post("My first post", "image.png", user1);
        Comment comment = new Comment("Great post!", user1, user2, post);
        comment.editComment("Updated comment.");
        assertEquals("Updated comment.", comment.getText());
    }

    @Test
    public void testCommentEquals() throws InvalidCommentException {
        User user1 = new User("John", "Doe", "johndoe", "password123", new ImageIcon("avatar1.png"));
        User user2 = new User("Jane", "Doe", "janedoe", "password456", new ImageIcon("avatar2.png"));
        Post post = new Post("My first post", "image.png", user1);
        Comment comment1 = new Comment("Great post!", user1, user2, post);
        Comment comment2 = new Comment("Great post!", user1, user2, post);
        assertTrue(comment1.equals(comment2));
        comment2.editComment("Different comment");
        assertFalse(comment1.equals(comment2));
    }
}