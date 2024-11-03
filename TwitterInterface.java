import java.util.ArrayList;

public interface TwitterInterface {
    public User createNewUser();
    public User getUser(String username);
    public User login();
    public void writeFile();
    public void readFile(String usernameFile, ArrayList<String> userFiles, ArrayList<String> postFiles);
    public void run();
}
