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

public class Client {
    private static final String[] MENU = new String[]{"1 - Add a friend", "2 - Remove a friend", "3 - Block a user" + "4 - Unblock a user"
            ,"5 - View a user profile", "6 - View feed",
            "7 - Create a post", "8 - Delete a post",
            "9 - Edit a post", "10 - Create a comment",
            "11 - Delete a comment", "12 - Edit a comment", "13 - Upvote a post",
            "14 - Downvote a post", "15 - Change password", "16 - Exit"};

    public static void main(String[] args) {
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
            } else if (option.equals("Sign Up") {
                while (true) {
                    System.out.println("Enter a username");
                    username = s.nextLine();
                    //username = showUsernameTextInputDialog();
                    writer.write(username);
                    writer.println();
                    writer.flush();
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
                }

            }
            boolean runLoop = true;
            while (runLoop) {
                int action = displayOptions();
                switch(action) {
                    case 1:
                        writer.write("Option 1");
                        writer.println();
                        writer.flush();
                        String friendUsername = addOrRemoveFriend(0);
                        writer.write(friendUsername);
                        writer.println();
                        writer.flush();

                        break;
                    case 2:
                        writer.write("Option 2");
                        writer.println();
                        writer.flush();
                        String removeUsername = addOrRemoveFriend(1);
                        writer.write(removeUsername);
                        writer.println();
                        writer.flush();
                        break;
                    case 3:
                        writer.write("Option 3");
                        writer.println();
                        writer.flush();
                        String blockUsername = blockOrRemoveUser(0);
                        writer.write(blockUsername);
                        writer.println();
                        writer.flush();
                        break;
                    case 4:
                        writer.write("Option 4");
                        writer.println();
                        writer.flush();
                        String unblockUsername = blockOrRemoveUser(1);
                        writer.write(unblockUsername);
                        writer.println();
                        writer.flush();
                    case 5:
                        writer.write("Option 5");
                        writer.println();
                        writer.flush();
                        String viewUser = viewProfile();
                        writer.write(viewUser);
                        writer.println();
                        writer.flush();
                        break;
                    case 6:
                        writer.write("Option 6");
                        writer.println();
                        writer.flush();
                        break;
                    case 7:





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

    public static void showWelcomeMessageDialog() {
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
    }



}
