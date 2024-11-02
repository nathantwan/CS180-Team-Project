import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class CommentTest1 {

    Comment testComment1;
    Comment testComment2;

    User postOwner;
    User commentOwner;
    User commentOwner2;
    Post testPost;
    ImageIcon testImageIcon;

    @Before
    public void setUp() throws Exception {
        testImageIcon = new ImageIcon("testImageIcon.png");
        postOwner = new User("Sarah", "Leitzel", "SarahL", "password123", testImageIcon);
        commentOwner = new User("William", "Smith", "WilliamL", "IamCool45", testImageIcon);
        commentOwner2 = new User("Eduardo", "Garcia", "EduardoG", "awesome1234", testImageIcon);
        testPost = new Post("This is a caption", testImageIcon, postOwner);

        testComment1 = new Comment("This is Comment 1", postOwner, commentOwner, testPost);
        testComment2 = new Comment("This is Comment 2", postOwner, commentOwner2, testPost);
    }

    @Test
    public void setComment() {
        testComment1.setComment("This is a new comment");
        assertEquals("This is a new comment", testComment1.getText());
    }

    @Test
    public void getText() {
        assertEquals("This is Comment 1", testComment1.getText());
    }

    @Test
    public void getPostOwner() {
        assertEquals(postOwner, testComment1.getPostOwner());
    }

    @Test
    public void getCommenter() {
        assertEquals(commentOwner, testComment1.getCommenter());
    }

    @Test
    public void getPost() {
        assertEquals(testPost, testComment1.getPost());
    }

    @Test
    public void testEquals() throws InvalidCommentException {
        Comment newComment = new Comment("This is Comment 1", postOwner, commentOwner, testPost);

        assertTrue(testComment1.equals(newComment));
        assertFalse(testComment1.equals(testComment2));
    }
}
