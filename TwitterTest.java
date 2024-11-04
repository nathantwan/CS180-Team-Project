import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TwitterTest {

    @Test
    public void readFile() {
        String usernameFile = "testUsers.txt";
        ArrayList<String> userFiles = new ArrayList<>();
        ArrayList<String> postFiles = new ArrayList<>();

        try {
            PrintWriter writer = new PrintWriter(usernameFile);
            writer.println("John Doe, testUser, testPass, null");
            writer.close();
        } catch (Exception e) {
            fail("Setup for readFile test failed.");
        }

        Twitter twitter = new Twitter(usernameFile, userFiles, postFiles);

        User retrievedUser = twitter.getUser("testUser");
        assertNotNull(retrievedUser);
        assertEquals("testUser", retrievedUser.getUsername());

        new File(usernameFile).delete();
    }

    @Test
    public void run() {

        Twitter twitter = new Twitter();

        Thread twitterThread = new Thread(twitter);
        twitterThread.start();

        assertTrue(twitterThread.isAlive());

        twitterThread.interrupt();
    }

    @Test
    public void main() {
        Twitter.main(new String[]{});
        assertTrue(true);
    }
}
