import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserTest {

    User testUser1;
    User testUser2;
    User testUser3;
    ImageIcon testImageIcon;

    /*
    * Add more thorough case that access multiple methods at once if needed
     */

    @org.junit.Before //Creates initialized variables that are used for all following tests
    public void setUp() throws Exception {
        testImageIcon = new ImageIcon("s");
        testUser1 = new User("Sarah", "Leitzel", "SarahL", "password123", testImageIcon);
        testUser2 = new User("William", "Smith", "WilliamS", "IamCool45", testImageIcon);
        testUser3 = new User("Eduardo", "Garcia", "EduardoG", "superCool14", testImageIcon);
    }

    @org.junit.Test
    public void getPassword() {
        assertEquals("password123", testUser1.getPassword());
        assertEquals("IamCool45", testUser2.getPassword());
        assertEquals("superCool14", testUser3.getPassword());
    }

    @org.junit.Test
    public void getUsername() {
        assertEquals("SarahL", testUser1.getUsername());
        assertEquals("WilliamS", testUser2.getUsername());
        assertEquals("EduardoG", testUser3.getUsername());
    }

    @org.junit.Test
    public void getFirstName() {
        assertEquals("Sarah", testUser1.getFirstName());
        assertEquals("William", testUser2.getFirstName());
        assertEquals("Eduardo", testUser3.getFirstName());
    }

    @org.junit.Test
    public void getLastName() {
        assertEquals("Leitzel", testUser1.getLastName());
        assertEquals("Smith", testUser2.getLastName());
        assertEquals("Garcia", testUser3.getLastName());
    }

    @org.junit.Test
    public void getName() {
        assertEquals("Sarah Leitzel", testUser1.getName());
        assertEquals("William Smith", testUser2.getName());
        assertEquals("Eduardo Garcia", testUser3.getName());
    }

    @org.junit.Test
    public void getProfilePicture() {
        assertEquals("s", testUser1.getProfilePicture().getDescription());
    }

    @org.junit.Test
    public void getFriends() {
        assertEquals(0, testUser1.getFriends().size());
    }

    @org.junit.Test
    public void getBlocked() {
        assertEquals(0, testUser1.getBlocked().size());
    }

    @org.junit.Test
    public void setUsername() {
        testUser1.setUsername("NewSarah");
        assertEquals("NewSarah", testUser1.getUsername());
    }

    @org.junit.Test
    public void setFriends() {
        ArrayList<User> newFriends = new ArrayList<>();
        newFriends.add(testUser2);
        newFriends.add(testUser3);
        testUser1.setFriends(newFriends);
        assertEquals(2, testUser1.getFriends().size());
        assertEquals(testUser2, testUser1.getFriends().get(0));
    }

    @org.junit.Test
    public void setBlocked() {
        ArrayList<User> blockedUsers = new ArrayList<>();
        blockedUsers.add(testUser2);
        testUser1.setBlocked(blockedUsers);
        assertEquals(1, testUser1.getBlocked().size());
        assertEquals(testUser2, testUser1.getBlocked().get(0));
    }

    @org.junit.Test
    public void setProfilePicture() {
        ImageIcon newProfilePic = new ImageIcon("newPath");
        testUser1.setProfilePicure(newProfilePic);
        assertEquals("newPath", testUser1.getProfilePicture().getDescription());
    }

    @org.junit.Test
    public void testEquals() {
        User newUser = new User("Sarah", "Leitzel", "SarahL", "password123", testImageIcon);
        assertTrue(testUser1.equals(newUser));
        assertFalse(testUser1.equals(testUser2));
    }

    @org.junit.Test
    public void setPassword() {
        assertTrue(testUser1.setPassword("password123", "password321"));
        assertEquals("password321", testUser1.getPassword());
        assertFalse(testUser1.setPassword("wrongPassword", "password321"));
    }

    @org.junit.Test
    public void addFriend() {
        testUser1.addFriend(testUser2);
        assertEquals(1, testUser1.getFriends().size());
        assertEquals(testUser2, testUser1.getFriends().get(0));
        assertTrue(testUser1.getFriends().contains(testUser2));
    }

    @org.junit.Test
    public void blockUser() {
        //Add a section that adds a person to the friends list and assertsTrue
        testUser1.blockUser(testUser2);
        assertEquals(1, testUser1.getBlocked().size());
        assertEquals(testUser2, testUser1.getBlocked().get(0));
        assertTrue(testUser1.getBlocked().contains(testUser2));
        assertFalse(testUser1.getFriends().contains(testUser2));
    }

    @org.junit.Test
    public void unblock() {
        testUser1.blockUser(testUser2);
        testUser1.unblock(testUser2);
        assertFalse(testUser1.getBlocked().contains(testUser2));
    }

    @org.junit.Test
    public void removeFriend() {
        testUser1.addFriend(testUser2);
        testUser1.removeFriend(testUser2);
        assertFalse(testUser1.getFriends().contains(testUser2));
    }

    @org.junit.Test
    public void testToString() {
        //Add a

        String expected = "------------\n" +
                "Name: Sarah Leitzel\n" +
                "Username: SarahL\n" +
                "Profile Picture: testPath\n" +
                "Friends: None\n" +
                "------------";
        assertEquals(expected, testUser1.toString());
    }
}
