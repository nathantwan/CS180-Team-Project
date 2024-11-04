import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class UserTest {

    User testUser1;
    User testUser2;
    User testUser3;
    ImageIcon testImageIcon;

    @Before //Creates initialized variables that are used for all following tests
    public void setUp() throws Exception {
        testImageIcon = new ImageIcon("s");
        testUser1 = new User("Sarah", "Leitzel", "SarahL", "password123", testImageIcon);
        testUser2 = new User("William", "Smith", "WilliamS", "IamCool45", testImageIcon);
        testUser3 = new User("Eduardo", "Garcia", "EduardoG", "superCool14", testImageIcon);
    }

    @After //Delete the text file created in a JUnit test after it is done testing
    public void cleanUp() {
        File file = new File("SarahL.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    //Tests the getters to make sure they work properly
    @Test
    public void getPassword() {
        assertEquals("password123", testUser1.getPassword());
        assertEquals("IamCool45", testUser2.getPassword());
        assertEquals("superCool14", testUser3.getPassword());
    }

    @Test
    public void getUsername() {
        assertEquals("SarahL", testUser1.getUsername());
        assertEquals("WilliamS", testUser2.getUsername());
        assertEquals("EduardoG", testUser3.getUsername());
    }

    @Test
    public void getFirstName() {
        assertEquals("Sarah", testUser1.getFirstName());
        assertEquals("William", testUser2.getFirstName());
        assertEquals("Eduardo", testUser3.getFirstName());
    }

    @Test
    public void getLastName() {
        assertEquals("Leitzel", testUser1.getLastName());
        assertEquals("Smith", testUser2.getLastName());
        assertEquals("Garcia", testUser3.getLastName());
    }

    @Test
    public void getName() {
        assertEquals("Sarah Leitzel", testUser1.getName());
        assertEquals("William Smith", testUser2.getName());
        assertEquals("Eduardo Garcia", testUser3.getName());
    }

    @Test
    public void getProfilePicture() {
        assertEquals("s", testUser1.getProfilePicture().getDescription());
    }

    @Test
    public void getFriends() {
        assertEquals(0, testUser1.getFriends().size());
    }

    @Test
    public void getBlocked() {
        assertEquals(0, testUser1.getBlocked().size());
    }

    //Tests the setters to make sure they work properly
    @Test
    public void setUsername() {
        testUser1.setUsername("NewSarah");
        assertEquals("NewSarah", testUser1.getUsername());
    }
       
    @Test
    public void setPassword() {
        assertTrue(testUser1.setPassword("password123", "password321"));
        assertEquals("password321", testUser1.getPassword());
        assertFalse(testUser1.setPassword("wrongPassword", "password321"));
    }

    @Test
    public void setFriends() {
        ArrayList<User> newFriends = new ArrayList<>();
        newFriends.add(testUser2);
        newFriends.add(testUser3);
        testUser1.setFriends(newFriends);
        assertEquals(2, testUser1.getFriends().size());
        assertEquals(testUser2, testUser1.getFriends().get(0));
    }

    @Test
    public void setBlocked() {
        ArrayList<User> blockedUsers = new ArrayList<>();
        blockedUsers.add(testUser2);
        testUser1.setBlocked(blockedUsers);
        assertEquals(1, testUser1.getBlocked().size());
        assertEquals(testUser2, testUser1.getBlocked().get(0));
    }

    @Test
    public void setProfilePicture() {
        ImageIcon newProfilePic = new ImageIcon("newPath");
        testUser1.setProfilePicure(newProfilePic);
        assertEquals("newPath", testUser1.getProfilePicture().getDescription());
    }

    //Creates a new user that is identical to testUser1 and ensures that the method returns true when compared to one another
    @Test
    public void testEquals() {
        User newUser = new User("Sarah", "Leitzel", "SarahL", "password123", testImageIcon);
        assertTrue(testUser1.equals(newUser));
        assertFalse(testUser1.equals(testUser2));
    }

    //Tests that ensure adding, unadding, blocking, and unblocking methods function properly
    @Test
    public void addFriend() {
        testUser1.addFriend(testUser2);
        assertEquals(1, testUser1.getFriends().size());
        assertEquals(testUser2, testUser1.getFriends().get(0));
        assertTrue(testUser1.getFriends().contains(testUser2));
    }

    @Test
    public void removeFriend() {
        testUser1.addFriend(testUser2);
        testUser1.removeFriend(testUser2);
        assertFalse(testUser1.getFriends().contains(testUser2));
    }

    @Test
    public void blockUser() {
        testUser1.blockUser(testUser2);
        assertEquals(1, testUser1.getBlocked().size());
        assertEquals(testUser2, testUser1.getBlocked().get(0));
        assertTrue(testUser1.getBlocked().contains(testUser2));
        assertFalse(testUser1.getFriends().contains(testUser2));
    }

    @Test
    public void unblock() {
        testUser1.blockUser(testUser2);
        assertTrue(testUser1.getBlocked().contains(testUser2));

        testUser1.unblock(testUser2);
        assertFalse(testUser1.getBlocked().contains(testUser2));
    }

    //Tests that ensures the Users information is properly converted into a string format
    @Test
    public void testToString() {

        String expected = "------------\n" +
                "Name: Sarah Leitzel\n" +
                "Username: SarahL\n" +
                "Profile Picture: s\n" +
                "Friends: None\n" +
                "------------";
        assertEquals(expected, testUser1.toString());
    }
    
    @Test
    public void testToStringWithFriendsAndBlocked() {
        testUser1.addFriend(testUser2);
        testUser1.blockUser(testUser3);

        String expected = "------------\n" 
        + "Name: Sarah Leitzel\n" 
        + "Username: SarahL\n" 
        + "Profile Picture: s\n" 
        + "Friends: William Smith\n" 
        + "------------";

    assertEquals(expected, testUser1.toString());
    }

    //Ensures that the displayFeed method properly handles media feed from friends and blocked users
    @Test
    public void displayFeed() throws InvalidPostException {
        ArrayList<Post> posts = new ArrayList<>();
        Post post1 = new Post("Hello World", testImageIcon, testUser2); 
        Post post2 = new Post("This is a post", testImageIcon, testUser3); 
        posts.add(post1);
        posts.add(post2);

        testUser1.addFriend(testUser2);
        ArrayList<Post> feed = testUser1.displayFeed(posts);

        assertEquals(1, feed.size());
        assertTrue(feed.contains(post1));
        assertFalse(feed.contains(post2));
    }

    //Tests that ensure a users data is properly written and stored in a text file
    @Test
    public void writeFile() throws FileNotFoundException {
        testUser1.addFriend(testUser2);
        testUser1.blockUser(testUser3);

        testUser1.writeFile();

        File file = new File("SarahL.txt");
        assertTrue(file.exists());

        Scanner scanner = new Scanner(file);
        assertEquals("SarahL", scanner.nextLine());
        assertEquals("FRIENDS", scanner.nextLine());
        assertEquals("WilliamS", scanner.nextLine());
        assertEquals("BLOCKED", scanner.nextLine());
        assertEquals("EduardoG", scanner.nextLine());

        scanner.close();
    }

    @Test
    public void writeFileAndVerifyContent() throws FileNotFoundException {
        testUser1.addFriend(testUser2);
        testUser1.blockUser(testUser3);
        testUser1.writeFile();

        File file = new File("SarahL.txt");
        assertTrue(file.exists());

        Scanner scanner = new Scanner(file);
        assertEquals("SarahL", scanner.nextLine());
        assertEquals("FRIENDS", scanner.nextLine());
        assertEquals("WilliamS", scanner.nextLine());
        assertEquals("BLOCKED", scanner.nextLine());
        assertEquals("EduardoG", scanner.nextLine()); 

        scanner.close();
    }
}
