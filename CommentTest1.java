import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Comment Test class
 * <p>
 * Purdue University -- CS18000 -- Team Project
 *
 * @author Rafael Fletes
 * @version Nov 3, 2024
 */

public class CommentTest1 {

    Comment testComment1;
    Comment testComment2;

    User postOwner;
    User commentOwner;
    User commentOwner2;
    Post testPost;
    ImageIcon testImageIcon;

    @Before //Sets variables that will be used in all following test cases
    public void setUp() throws Exception {
        testImageIcon = new ImageIcon("testImageIcon.png");
        postOwner = new User("Sarah", "Leitzel", "SarahL", "password123", testImageIcon);
        commentOwner = new User("William", "Smith", "WilliamL", "IamCool45", testImageIcon);
        commentOwner2 = new User("Eduardo", "Garcia", "EduardoG", "awesome1234", testImageIcon);
        testPost = new Post("This is a caption", testImageIcon, postOwner);

        testComment1 = new Comment("This is Comment 1", postOwner, commentOwner, testPost);
        testComment2 = new Comment("This is Comment 2", postOwner, commentOwner2, testPost);
    }

    //Tests that ensure the getter methods properly return data
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

    //Test that ensures the setComment method properly changes or sets the comment text
    @Test
    public void setComment() {
        assertEquals("This is Comment 1", testComment1.getText());

        testComment1.setComment("This is a new comment");
        assertEquals("This is a new comment", testComment1.getText());
    }

    //Test that ensures null or empty text throws an exception
    @Test(expected = InvalidCommentException.class)
    public void constructorShouldThrowExceptionForEmptyText() throws InvalidCommentException {
        new Comment("", postOwner, commentOwner, testPost);
    }

    //Test that creates a new comment and compares it to testComment1 to ensure that the equal method works properly
    @Test
    public void testEquals() throws InvalidCommentException {
        Comment newComment = new Comment("This is Comment 1", postOwner, commentOwner, testPost);

        assertTrue(testComment1.getText().equals(newComment.getText()));
        assertTrue(testComment1.getPostOwner().equals(newComment.getPostOwner()));
        assertTrue(testComment1.getCommenter().equals(newComment.getCommenter()));
        assertTrue(testComment1.getPost().equals(newComment.getPost()));
        assertFalse(testComment1.equals(testComment2));
    }
}
