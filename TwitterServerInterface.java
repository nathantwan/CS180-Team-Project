/**
 * Twitter Server Interface
 * <p>
 * Purdue University -- CS18000 -- Team Project
 *
 * @author Yajushi
 * @version Nov 14, 2024
 */

public interface TwitterServerInterface {
    void writeFile();
    void readFile(String usernameFile, ArrayList<String> userFiles, ArrayList<String> postFiles);
    User getUser(String username);
    String option1(String friendUsername, User user);
    String option2(String friendUsername, User user);
    String option3(String blockedUsername, User user);
    String option4(String blockedUsername, User user);
    String option5(String userprofile);
    String option6(User user);
    void option7(User user, String caption, String path) throws InvalidPostException;
    String option8(int postNum, User user);
    String option9(int postNum, String caption, User user);
    String option10(int postNum, String comment, User user);
    String option11(int postNum, int commentNum, User user);
    String option12(int postNum, int commentNum, String newComment, User user);
    String option13(int postNum);
    String option15(String oldPass, String newPass, User user);
    void option16();
}
