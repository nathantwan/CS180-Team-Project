import java.util.ArrayList;
import javax.swing.ImageIcon;

public interface UserInterface {
    public String getPassword();
    public String getUsername();
    public String getFirstName();
    public String getLastName();
    public String getName();
    public ImageIcon getProfilePicture();
    public ArrayList<User> getFriends();
    public ArrayList<User> getBlocked();
    public void setUsername(String username);
    public void setFriends(ArrayList<User> friends);
    public void setBlocked(ArrayList<User> blocked);
    public void setProfilePicure(ImageIcon profilePicture);
    public boolean equals(Object other);
    public boolean setPassword(String oldPass, String newPass);
    public void addFriend(User f);
    public void blockUser(User b);
    public void unblock(User b);
    public void removeFriend(User f);
    public String toString();

}
