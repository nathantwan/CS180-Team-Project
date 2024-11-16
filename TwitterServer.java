import java.io.*;
import java.util.*;
import javax.swing.ImageIcon;
import java.net.*;

public class TwitterServer implements Runnable {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Post> posts = new ArrayList<Post>();
    private final Object obj = new Object();

    public User getUser(String username) {
        User u = null;
        synchronized (obj) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    u = user;
                    break;
                }
            }
        }
        return u;
    }

    public String option1(String friendUsername) {
        User friend = getUser(friendUsername);
        if (friend == null) {
            return "Error: Invalid username";
        } else {
            user.addFriend(friend);
            return "Friend added!";
        }
    }

    public void option2(String friendUsername) {
        
    }
    
}
