import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
/**
 * Client class
 * <p>
 * Purdue University -- CS18000 
 *
 * @author Nathan Wan
 * @version Nov 3, 2024
 */

public class Client {
    private static String[] searchOptions;

    public static void main(String[] args) {
        String hostName = "localhost";
        int portNumber = 4242;
        String option;
        String username;
        String password;
        showWelcomeMessageDialog();
        // Port is 4242
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            socket = new Socket(hostName, portNumber);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            showConnectionEstablishedDialog();
            option = loginOrSignUp();
            boolean loop = true;
            if (option.equals("Login")) {
                while (loop) {
                    username = showUsernameTextInputDialog();
                    //send username to server to check if valid
                    writer.write(username);
                    // server sends "VALID" if username is found in database
                    String response = reader.readLine();
                    if (response.equals("VALID")) {
                        password = showPasswordTextInputDialog();
                        // sends password
                        writer.write(password);
                        String passwordResponse = reader.readLine();
                        if (passwordResponse.equals("VALID")) {
                            JOptionPane.showMessageDialog(null, "Login Success",
                                    "Twitter", JOptionPane.INFORMATION_MESSAGE);
                            loop = false;
                        } else {
                            JOptionPane.showMessageDialog(null, "Password is incorrect",
                                    "Twitter", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Username doesn't exist",
                                "Twitter", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            } else if (option.equals("Sign Up")) {
                username = showUsernameTextInputDialog();
                writer.write(username);
                password = showPasswordTextInputDialog();
                writer.write(password);
                JOptionPane.showMessageDialog(null, "New Account Created",
                        "Twitter", JOptionPane.INFORMATION_MESSAGE);
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

}
