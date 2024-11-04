import java.util.*;

public interface TwitterInterface {
    public User createNewUser(Scanner s);
    public User getUser(String username);
    public User login(Scanner s);
    public void writeFile();
    public void readFile(String usernameFile, ArrayList<String> userFiles, ArrayList<String> postFiles);
    public void run();
}
