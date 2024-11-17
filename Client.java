import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * SearchClient class
 * <p>
 * Purdue University -- CS18000 -- homework 11
 *
 * @author Nathan Wan
 * @version Nov 3, 2024
 */

public class Client implements Runnable{
    private static final String[] MENU = new String[]{"1 - Add a friend", "2 - Remove a friend", "3 - Block a user", "4 - Unblock a user",
            "5 - View a user profile", "6 - View feed",
            "7 - Create a post", "8 - Delete a post",
            "9 - Edit a post", "10 - Create a comment",
            "11 - Delete a comment", "12 - Edit a comment", "13 - Upvote a post",
            "14 - Downvote a post", "15 - Change password", "16 - Exit"};

    public void run() {
        Scanner s = new Scanner(System.in);
        String hostName = "localhost";
        int portNumber = 4242;
        String option;
        String username;
        String password;
        //showWelcomeMessageDialog();
        // Port is 4242
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            socket = new Socket(hostName, portNumber);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("Connection Established");
            //showConnectionEstablishedDialog();
            System.out.println("Would you like to 1: Login or 2: Sign Up?");
            //option = loginOrSignUp();
            option = s.nextLine();
            boolean loop = true;
            if (option.equals("Login")) {
                while (loop) {
                    writer.write("Login");
                    writer.println();
                    writer.flush();
                    System.out.println("Enter your username");
                    username = s.nextLine();
                    //username = showUsernameTextInputDialog();
                    //send username to server to check if valid
                    writer.write(username);
                    writer.println();
                    writer.flush();
                    // server sends "VALID" if username is found in database
                    boolean response = Boolean.parseBoolean(reader.readLine());
                    if (response) {
                        System.out.println("Enter your password");
                        password = s.nextLine();
                        //password = showPasswordTextInputDialog();
                        // sends password
                        writer.write(password);
                        writer.println();
                        writer.flush();
                        boolean passwordResponse = Boolean.parseBoolean(reader.readLine());
                        if (passwordResponse) {
                            System.out.println("Login Success");
                            //JOptionPane.showMessageDialog(null, "Login Success", "Twitter", JOptionPane.INFORMATION_MESSAGE);
                            loop = false;
                        } else {
                            //JOptionPane.showMessageDialog(null, "Password is incorrect", "Twitter", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println("Password is incorrect");
                        }
                    } else {
                        //JOptionPane.showMessageDialog(null, "Username doesn't exist", "Twitter", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Username does not exist");

                    }
                }
            } else if (option.equals("Sign Up")) {
                while (true) {
                    writer.write("Sign Up");
                    writer.println();
                    writer.flush();
                    System.out.println("Enter your first name");
                    writer.write(s.nextLine());
                    writer.println();
                    System.out.println("Enter your last name");
                    writer.write(s.nextLine());
                    writer.println();
                    System.out.println("Enter the path for your profile picture. If no profile picture, enter null");
                    writer.write(s.nextLine());
                    writer.println();
                    System.out.println("Enter a username");
                    username = s.nextLine();
                    //username = showUsernameTextInputDialog();
                    writer.write(username);
                    writer.println();
                    writer.flush();

                    boolean usernameValid = Boolean.parseBoolean(reader.readLine());
                    if (usernameValid) {
                        System.out.println("Enter a password");
                        password = s.nextLine();
                        //password = showPasswordTextInputDialog();
                        writer.write(password);
                        writer.println();
                        writer.flush();
                        boolean passwordValid = Boolean.parseBoolean(reader.readLine());
                        if (passwordValid) {
                            System.out.println("New Account Created");
                            break;
                        } else {
                            System.out.println("Invalid password. Try Again!");
                        }
                        //JOptionPane.showMessageDialog(null, "New Account Created", "Twitter", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        System.out.println("Username taken. Try Again!");
                    }

                    
                }

            }
            boolean runLoop = true;

            while (runLoop) {
                //int action = displayOptions();
                System.out.println("Please select an option");
                for (String i: MENU) {
                    System.out.println(i);
                }
                int action = s.nextInt();
                s.nextLine();

                switch(action) {
                    case 1:
                        writer.write("Option 1");
                        writer.println();
                        writer.flush();
                        //String friendUsername = addOrRemoveFriend(0);
                        System.out.println("Enter the username of the user you would like to add");
                        String friendUsername = s.nextLine();
                        writer.write(friendUsername);
                        writer.println();
                        writer.flush();
                        String valid = reader.readLine();
                        System.out.println(valid);

                        break;
                    case 2:
                        writer.write("Option 2");
                        writer.println();
                        writer.flush();
                        //String removeUsername = addOrRemoveFriend(1);
                        System.out.println("Enter the username of the user you would like to remove");
                        String removeUsername = s.nextLine();
                        writer.write(removeUsername);
                        writer.println();
                        writer.flush();
                        String validRemoval = reader.readLine();
                        System.out.println(validRemoval);
                        break;
                    case 3:
                        writer.write("Option 3");
                        writer.println();
                        writer.flush();
                        //String blockUsername = blockOrRemoveUser(0);
                        System.out.println("Enter the username of the user you would like to block");
                        String blockUsername = s.nextLine();
                        writer.write(blockUsername);
                        writer.println();
                        writer.flush();
                        String validBlock = reader.readLine();
                        System.out.println(validBlock);
                        break;
                    case 4:
                        writer.write("Option 4");
                        writer.println();
                        writer.flush();
                        //String unblockUsername = blockOrRemoveUser(1);
                        System.out.println("Enter the username of the user you would like to unblock");
                        String unblockUsername = s.nextLine();
                        writer.write(unblockUsername);
                        writer.println();
                        writer.flush();
                        String validunBlock = reader.readLine();
                        System.out.println(validunBlock);
                        break;
                    case 5:
                        writer.write("Option 5");
                        writer.println();
                        writer.flush();
                        //String viewUser = viewProfile();
                        System.out.println("Enter the username of the profile you would like to view");
                        String viewUser = s.nextLine();
                        writer.write(viewUser);
                        writer.println();
                        writer.flush();
                        String line = reader.readLine();
                        while (line.equals("stop") == false) {
                            System.out.println(line);
                            line = reader.readLine();

                        }
                        break;
                    case 6:
                        writer.write("Option 6");
                        writer.println();
                        writer.flush();
                        String line2 = reader.readLine();
                        while (line2.equals("stop") == false) {
                            System.out.println(line2);
                            line2 = reader.readLine();

                        }
                        break;
                    case 7:
                        writer.write("Option 7");
                        writer.println();
                        writer.flush();
                        System.out.println("What is the caption for your post?");
                        String caption = s.nextLine();
                        writer.write(caption);
                        writer.println();
                        writer.flush();
                        System.out.println("What is the path for the image for your post?");
                        String path = s.nextLine();
                        writer.write(path);
                        writer.println();
                        writer.flush();
                        break;
                    case 8:
                        writer.write("Option 8");
                        writer.println();
                        writer.flush();
                        System.out.println("Enter the post number");
                        int postNumber = s.nextInt();
                        s.nextLine();
                        writer.write(postNumber);
                        writer.println();
                        writer.flush();
                        String postDeletionResult = reader.readLine();
                        System.out.println(postDeletionResult);
                        break;
                    case 9:
                        writer.write("Option 9");
                        writer.println();
                        writer.flush();
                        System.out.println("Enter the post number");
                        int postNumberEdit = s.nextInt();
                        s.nextLine();
                        writer.write(postNumberEdit);
                        writer.println();
                        writer.flush();
                        System.out.println("Enter a new caption");
                        String newCaption = s.nextLine();
                        writer.write(newCaption);
                        writer.println();
                        writer.flush();
                        String editPostResposne = reader.readLine();
                        System.out.println(editPostResposne);
                        break;
                    case 10:
                        writer.write("Option 10");
                        writer.println();
                        writer.flush();
                        System.out.println("Enter the post number");
                        int commentPostNumber = s.nextInt();
                        s.nextLine();
                        writer.write(commentPostNumber);
                        writer.println();
                        writer.flush();
                        System.out.println("Enter your comment");
                        String comment = s.nextLine();
                        writer.write(comment);
                        writer.println();
                        writer.flush();
                        String commentResult = reader.readLine();
                        System.out.println(commentResult);
                        break;
                    case 11:
                        writer.write("Option 11");
                        writer.println();
                        writer.flush();
                        System.out.println("Enter the post number");
                        int deleteCommentPost = s.nextInt();
                        s.nextLine();
                        writer.write(deleteCommentPost);
                        writer.println();
                        writer.flush();
                        System.out.println("Enter your comment number");
                        int commentNum = s.nextInt();
                        s.nextLine();
                        writer.write(commentNum);
                        writer.println();
                        writer.flush();
                        String deleteCommentResponse = reader.readLine();
                        System.out.println(deleteCommentResponse);
                        break;
                    case 12:
                        writer.write("Option 12");
                        writer.println();
                        writer.flush();
                        System.out.println("Enter the post number");
                        int editCommentPost = s.nextInt();
                        s.nextLine();
                        writer.write(editCommentPost);
                        writer.println();
                        writer.flush();
                        System.out.println("Enter your comment number");
                        int editCommentNum = s.nextInt();
                        s.nextLine();
                        writer.write(editCommentNum);
                        writer.println();
                        writer.flush();
                        System.out.println("Enter your new comment");
                        String newComment = s.nextLine();
                        writer.write(newComment);
                        writer.println();
                        writer.flush();
                        String editCommentResponse = reader.readLine();
                        System.out.println(editCommentResponse);
                        break;
                    case 13:
                        writer.write("Option 13");
                        writer.println();
                        writer.flush();
                        System.out.println("Enter the post number");
                        int upvotePost = s.nextInt();
                        s.nextLine();
                        writer.write(upvotePost);
                        writer.println();
                        writer.flush();
                        String upvoteResponse = reader.readLine();
                        System.out.println(upvoteResponse);
                        break;
                    case 14:
                        writer.write("Option 14");
                        writer.println();
                        writer.flush();
                        System.out.println("Enter the post number");
                        int downvotePost = s.nextInt();
                        s.nextLine();
                        writer.write(downvotePost);
                        writer.println();
                        writer.flush();
                        String downvoteResponse = reader.readLine();
                        System.out.println(downvoteResponse);
                        break;
                    case 15:
                        writer.write("Option 15");
                        writer.println();
                        writer.flush();
                        System.out.println("Enter your old password");
                        String oldPass = s.nextLine();
                        writer.write(oldPass);
                        writer.println();
                        writer.flush();
                        System.out.println("Enter your new password");
                        String newPass = s.nextLine();
                        writer.write(newPass);
                        writer.println();
                        writer.flush();
                        String changePassResult = reader.readLine();
                        System.out.println(changePassResult);
                        break;
                    case 16:
                        writer.write("Option 16");
                        writer.println();
                        writer.flush();
                        runLoop = false;
                        socket.close();
                        break;
                    default:
                        System.out.println("Please select a valid option");

                }
            }



        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Connection Failed", "Search Engine",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error closing resources", "Search Engine",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void main(String[] args) {
        Thread thread = new Thread(new Client());
        thread.start();
    }

    /*public static void showWelcomeMessageDialog() {
        JOptionPane.showMessageDialog(null, "Welcome to Twitter",
                "Twitter", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showConnectionEstablishedDialog() {
        JOptionPane.showMessageDialog(null, "Connection Established",
                "Twitter", JOptionPane.INFORMATION_MESSAGE);
    }
    public static String loginOrSignUp() {
        String option;
        do {
            option = (String) JOptionPane.showInputDialog(null, "Would you like to login or signup?",
                    "Twitter", JOptionPane.QUESTION_MESSAGE, null, new String[]{"Login", "Sign Up"}, new String[0] );
            if (option.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You must pick an option", "Search Engine",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (option.isEmpty() || option == null);

        return option;
    }


    public static String showUsernameTextInputDialog() {
        String text;
        do {
            text = JOptionPane.showInputDialog(null, "Enter your username",
                    "Twitter", JOptionPane.QUESTION_MESSAGE);
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username cannot be empty!", "Twitter",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (text.isEmpty() || text == null);

        return text;
    }
    public static String showPasswordTextInputDialog() {
        String text;
        do {
            text = JOptionPane.showInputDialog(null, "Enter your password",
                    "Twitter", JOptionPane.QUESTION_MESSAGE);
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Twitter",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (text.isEmpty() || text == null);

        return text;
    }
    public static int displayOptions() {
        String searchOption;
        do {
            searchOption = (String) JOptionPane.showInputDialog(null, "Select an action",
                    "Twitter", JOptionPane.QUESTION_MESSAGE, null, MENU,
                    MENU[0]);
            if (searchOption == null) {
                return -1;
            }
        } while (searchOption.isEmpty());
        return Integer.parseInt(searchOption.substring(0,1));
    }
    public static String addOrRemoveFriend(int choice) {
        String text = "";
        do {
            if (choice == 0) {
                text = JOptionPane.showInputDialog(null, "Enter the username of the friend you would like to add",
                        "Twitter", JOptionPane.QUESTION_MESSAGE);
            } else if (choice == 1) {
                text = JOptionPane.showInputDialog(null, "Enter the username of the friend you would like to remove",
                        "Twitter", JOptionPane.QUESTION_MESSAGE);
            }
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Friend Username cannot be empty!", "Twitter",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (text.isEmpty());
        return text;
    }
    public static String blockOrRemoveUser(int choice) {
        String text = "";
        do {
            if (choice == 0) {
                text = JOptionPane.showInputDialog(null, "Enter the username of the user you would like to block",
                        "Twitter", JOptionPane.QUESTION_MESSAGE);
            } else if (choice == 1) {
                text = JOptionPane.showInputDialog(null, "Enter the username of the user you would like to unblock",
                        "Twitter", JOptionPane.QUESTION_MESSAGE);
            }
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(null, "User cannot be empty!", "Twitter",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (text.isEmpty());
        return text;
    }
    public static String viewProfile() {
        String text = "";
        do {
            text = JOptionPane.showInputDialog(null, "Enter the username of the profile you would like to view",
                        "Twitter", JOptionPane.QUESTION_MESSAGE);
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(null, "User cannot be empty!", "Twitter",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (text.isEmpty());
        return text;
    } */



}
