import java.util.ArrayList;
import javax.swing.ImageIcon;

public interface UserInterface {
    String getPassword();
    String getUsername();
    String getFirstName();
    String getLastName();
    String getName();
    ImageIcon getProfilePicture();
    ArrayList<User> getFriends();
    ArrayList<User> getBlocked();
    void setUsername(String username);
    void setFriends(ArrayList<User> friends);
    void setBlocked(ArrayList<User> blocked);
    void setProfilePicure(ImageIcon profilePicture);
    boolean equals(Object other);
    boolean setPassword(String oldPass, String newPass);
    void addFriend(User f);
    void blockUser(User b);
    void unblock(User b);
    void removeFriend(User f);
    String toString();
    ArrayList<Post> displayFeed(ArrayList<Post> posts);
    void writeFile();
}
