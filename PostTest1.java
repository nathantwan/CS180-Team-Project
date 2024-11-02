import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.font.ImageGraphicAttribute;

import static org.junit.Assert.*;

public class PostTest1 {

    Post testPost1;
    User testUser1;
    User testUser2;

    ImageIcon testImage;

    @Before
    public void setUp() throws Exception {
        testImage = new ImageIcon("testPath.png");
        testUser1 = new User("Sarah", "Leitzel", "SarahL", "password123", testImage);
        testUser2 = new User("William", "Smith", "WilliamL", "IamCool45", testImage);
        testPost1 = new Post("I am a caption", testImage, testUser1);

    }

    @Test
    public void getCaption() {
        assertEquals("I am a caption", testPost1.getCaption());
    }

    @Test
    public void getImage() {
        assertEquals("testPath.png", testPost1.getImage().getDescription());
    }

    @Test
    public void getUpvote() {
        assertEquals(0, testPost1.getUpvote());
    }

    @Test
    public void getDownvote() {
        assertEquals(0, testPost1.getDownvote());
    }

    @Test
    public void getUser() {
        assertEquals(testUser1, testPost1.getUser());
    }

    @Test
    public void editPost() {
        testPost1.editPost("This is a new caption");
        assertEquals("This is a new caption", testPost1.getCaption());
    }

    @Test
    public void incrementUpvote() {
        testPost1.incrementUpvote();
        assertEquals(1, testPost1.getUpvote());
    }

    @Test
    public void incrementDownvote() {
        testPost1.incrementUpvote();
        testPost1.incrementDownvote();
        assertEquals(0, testPost1.getDownvote());
    }

    @Test
    public void setPost() {
        testPost1.setPost("This a new caption", testImage);
        assertEquals("This a new caption", testPost1.getCaption());
        assertEquals("testPath.png", testPost1.getImage().getDescription());
    }


    @Test
    public void addComment() throws InvalidCommentException {
        testPost1.addComment("This is text", testUser1, testUser2, testPost1);
        assertEquals(1, testPost1.getComments().size());
        assertEquals("This is text", testPost1.getComments().get(0).getText());
    }

    @Test
    public void deleteComment() throws InvalidCommentException {
        Comment newComment = new Comment("This is text", testUser1, testUser2, testPost1);
        testPost1.addComment("This is text", testUser1, testUser2, testPost1);
        assertEquals(1, testPost1.getComments().size());

        testPost1.deleteComment(newComment, testUser2);
        assertEquals(0, testPost1.getComments().size());
    }

    @Test
    public void editComment() throws InvalidCommentException {
        Comment oldComment = new Comment("This is text", testUser1, testUser2, testPost1);
        testPost1.addComment("This is text", testUser1, testUser2, testPost1);

        testPost1.editComment("This is new text", oldComment, testUser2);
        assertEquals("This is new text", testPost1.getComments().get(0).getText());
    }

    @Test
    public void testEquals() throws InvalidPostException {
        Post testPost2 = new Post("I am a caption", testImage, testUser1);
        assertTrue(testPost1.equals(testPost2));

        Post testPost3 = new Post("Different caption", testImage, testUser1);
        assertFalse(testPost1.equals(testPost3));
    }

    @Test
    public void testToString() {
        String expected = "------------\n" +
                testUser1.toString() +
                "Caption: I am a caption\n" +
                "Image: testPath.png\n" +
                "Comments: None\n" +
                "------------";
        assertEquals(expected, testPost1.toString());
    }
}
