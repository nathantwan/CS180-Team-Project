import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.Files;

/**
 * Post Class Tests
 * <p>
 * Purdue University -- CS18000 -- Team Project
 *
 * @author Rafael Fletes
 * @version Nov 3, 2024
 */

public class PostTest1 {

    Post testPost1;
    User testUser1;
    User testUser2;
    File testPostFile;

    ImageIcon testImage;

    @Before //Sets variables to be used in all following tests
    public void setUp() throws Exception {
        testImage = new ImageIcon("testPath.png");
        testUser1 = new User("Sarah", "Leitzel", "SarahL", "password123", testImage);
        testUser2 = new User("William", "Smith", "WilliamL", "IamCool45", testImage);
        testPost1 = new Post("I am a caption", testImage, testUser1);

        testPostFile = new File("Post" + testPost1.getPostNumber() + ".txt");
    }

    @After //Deletes the files created from the tests
    public void tearDown() throws IOException {
        Files.deleteIfExists(testPostFile.toPath());
    }

    //Getter tests
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
    public void getComments() {
        assertEquals(0, testPost1.getComments().size());
    }

    //Setter tests
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
        testPost1.incrementDownvote();
        assertEquals(1, testPost1.getDownvote());
    }

    @Test
    public void setPost() {
        testPost1.setPost("This a new caption", testImage);
        assertEquals("This a new caption", testPost1.getCaption());
        assertEquals("testPath.png", testPost1.getImage().getDescription());
    }

     //Checks that a post with no caption throws an exception
    @Test(expected = InvalidPostException.class)
    public void testEmptyCaption() throws InvalidPostException {
        new Post("", testImage, testUser1);
    }

    //Ensures that the addComment method works
    @Test
    public void addComment() throws InvalidCommentException {
        testPost1.addComment("This is text", testUser1, testUser2, testPost1);
        assertEquals(1, testPost1.getComments().size());
        assertEquals("This is text", testPost1.getComments().get(0).getText());
    }

    //Ensures that the deleteComment method works
    @Test
    public void deleteComment() throws InvalidCommentException {
        testPost1.addComment("This is text", testUser1, testUser2, testPost1);
        assertEquals(1, testPost1.getComments().size());

        testPost1.deleteComment(testPost1.getComments().get(0), testUser2);
        assertEquals(0, testPost1.getComments().size());
    }

    //Ensures that the editComment method works
    @Test
    public void editComment() throws InvalidCommentException {
        testPost1.addComment("This is text", testUser1, testUser2, testPost1);

        testPost1.editComment("This is new text", testPost1.getComments().get(0), testUser2);
        assertEquals("This is new text", testPost1.getComments().get(0).getText());
    }

    //Tests if two Post objects are equal 
    @Test
    public void testEquals() throws InvalidPostException {
        Post testPost2 = new Post("I am a caption", testImage, testUser1);
        assertTrue(testPost1.getCaption().equals(testPost2.getCaption()));
        assertTrue(testPost1.getImage().equals(testPost2.getImage()));
        assertTrue(testPost1.getUser().equals(testPost2.getUser()));

        Post testPost3 = new Post("Different caption", testImage, testUser1);
        assertFalse(testPost1.equals(testPost3));
    }

    //Ensures that the toString method properly converts test input into a String
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

    //Ensures that a post is successfully written to a file using test input
    @Test
    public void testWritePost() {
        testPost1.writePost();

        try (BufferedReader reader = new BufferedReader(new FileReader(testPostFile))) {
            String expectedFirstLine = "I am a caption, testPath.png, 0, 0, SarahL, " + testPost1.getPostNumber();

            assertEquals(expectedFirstLine, reader.readLine());
            
            if (!testPost1.getComments().isEmpty()) {
                for (Comment comment : testPost1.getComments()) {
                    String expectedCommentLine = comment.getText() + ", " + comment.getPostOwner().getUsername() 
                         + ", " + comment.getCommenter().getUsername();

                    assertEquals(expectedCommentLine, reader.readLine());
                }
            }
        } catch (IOException e) {
            fail("IOException occurred while reading the post file: " + e.getMessage());
        }
    }
    
    //Ensures that a post is successfully written to a file using test input if comments exist
    @Test
    public void testWritePostWithComments() throws InvalidCommentException {
        testPost1.addComment("Comment1", testUser1, testUser2, testPost1);
        testPost1.addComment("Comment2", testUser1, testUser2, testPost1);
        testPost1.writePost();

        try (BufferedReader reader = new BufferedReader(new FileReader(testPostFile))) {
            reader.readLine(); 
            assertEquals("Comment1, SarahL, WilliamL", reader.readLine());
            assertEquals("Comment2, SarahL, WilliamL", reader.readLine());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

}

