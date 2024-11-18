import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.ImageIcon;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TwitterServerTest {
    private TwitterServer server;
    private User testUser;
    private ImageIcon testImageIcon;
    private User blocked;

    @BeforeEach
    void setUp() {
        Socket mockSocket = new Socket();
        server = new TwitterServer(mockSocket);
        testImageIcon = new ImageIcon("testImageIcon.png");

        testUser = new User("John", "Doe", "johndoe", "password123", testImageIcon);
        blocked = new User("Jane", "Smith", "janesmith", "password123", testImageIcon);
        server.users.add(testUser);
        server.users.add(blocked);
    }

    @Test
    void testWriteFile() {
        assertDoesNotThrow(() -> server.writeFile());
    }

    @Test
    void testReadFile() {
        assertDoesNotThrow(() -> server.readFile("mockUsernames.txt", new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    void testGetUser() {
        assertEquals(testUser, server.getUser("johndoe"));

        assertNull(server.getUser("janedoe"));
    }

    @Test
    void testOption1AddFriend() {
        User friend = new User("Jane", "Smith", "janesmith", "password123", null);
        server.users.add(friend);

        String result = server.option1("janesmith", testUser);
        assertEquals("Friend added!", result);

        assertTrue(testUser.getFriends().contains(friend));
    }

    @Test
    void testOption2RemoveFriend() {
        User friend = new User("Jane", "Smith", "janesmith", "password123", null);
        testUser.addFriend(friend);

        String result = server.option2("janesmith", testUser);
        assertEquals("Friend removed.", result);

        assertFalse(testUser.getFriends().contains(friend));
    }

    @Test
    void testOption3BlockUser() {
        User blocked = new User("Jane", "Smith", "janesmith", "password123", null);
        server.users.add(blocked);

        String result = server.option3("janesmith", testUser);
        assertEquals("User blocked.", result);

        assertTrue(testUser.getBlocked().contains(blocked));
    }

    @Test
    void testOption4UnblockUser() {
        testUser.blockUser(blocked);

        String result = server.option4("janesmith", testUser);
        assertEquals("User unblocked.", result);

        assertFalse(testUser.getBlocked().contains(blocked));
    }

    @Test
    void testOption5ViewProfile() {
        String result = server.option5("johndoe");
        assertTrue(result.contains(testUser.toString()));

        String invalidResult = server.option5("janedoe");
        assertEquals("Error: Invalid username\nstop", invalidResult);
    }

    @Test
    void testOption6DisplayFeed() throws InvalidPostException {
        Post post = new Post("Hello World!", testImageIcon, testUser);
        server.posts.add(post);

        server.posts.clear();
        String emptyResult = server.option6(testUser);
        assertEquals("There are no posts in your feed.\nstop", emptyResult);
    }

    @Test
    void testOption7CreatePost() {
        assertDoesNotThrow(() -> server.option7(testUser, "New Post!", null));
        assertEquals(1, server.posts.size());
    }

    @Test
    void testOption8DeletePost() throws InvalidPostException {
        Post post = new Post("Hello World!", testImageIcon, testUser);
        server.posts.add(post);

        String result = server.option8(0, testUser);
        assertEquals("Post deleted", result);

        assertTrue(server.posts.isEmpty());
    }

    @Test
    void testOption9EditPost() throws InvalidPostException {
        Post post = new Post("Hello World!", null, testUser);
        server.posts.add(post);

        String result = server.option9(0, "Edited Post", testUser);
        assertEquals("Post edited", result);

        assertEquals("Edited Post", server.posts.get(0).getCaption());
    }

    @Test
    void testOption10CreateComment() throws InvalidPostException {
        Post post = new Post("Hello World!", null, testUser);
        server.posts.add(post);

        String result = server.option10(0, "Nice post!", testUser);
        assertEquals("Comment created", result);

        assertEquals(1, post.getComments().size());
    }

    @Test
    void testOption11DeleteComment() throws InvalidPostException {
        Post post = new Post("Hello World!", testImageIcon, testUser);
        post.addComment("Nice post!", testUser, testUser, post);
        server.posts.add(post);

        assertFalse(post.getComments().isEmpty(), "Comment should be added initially");

        String result = server.option11(0, 0, testUser);

        assertEquals("Comment deleted", result, "The deletion message should match");
        assertTrue(post.getComments().isEmpty(), "Comments list should be empty after deletion");
    }


    @Test
    void testOption12EditComment() throws InvalidPostException, InvalidCommentException {
        Post post = new Post("Hello World!", null, testUser);
        post.addComment("Nice post!", testUser, testUser, post);
        server.posts.add(post);

        assertEquals("Nice post!", post.getComments().get(0).getText(), "Initial comment text should match");

        String result = server.option12(0, 0, "Edited comment", testUser);

        assertEquals("Comment edited", result, "The edit message should match");
        assertEquals("Edited comment", post.getComments().get(0).getText(), "Comment text should be updated");
    }


    @Test
    void testOption13UpvotePost() throws InvalidPostException {
        Post post = new Post("Hello World!", null, testUser);
        server.posts.add(post);

        String result = server.option13(0);
        assertEquals("Post upvoted", result);

        assertEquals(1, post.getUpvote());
    }

    @Test
    void testOption14DownvotePost() throws InvalidPostException {
        Post post = new Post("Hello World!", null, testUser);
        server.posts.add(post);

        String result = server.option14(0);
        assertEquals("Post downvoted", result);

        assertEquals(1, post.getDownvote());
    }

    @Test
    void testOption15ChangePassword() {
        String result = server.option15("password123", "newpassword123", testUser);
        assertEquals("Password changed", result);

        assertEquals("newpassword123", testUser.getPassword());
    }

    @Test
    void testOption16EndRun() {
        assertDoesNotThrow(() -> server.option16());
    }
}
