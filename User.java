import java.util.*;
import javax.swing.ImageIcon;

public class User {
    private ArrayList<User> friends;
    private ArrayList<User> blocked;

    private static ArrayList<String> usernameArray = new ArrayList<String>();

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    
    private ImageIcon imageIcon;

    public User() {
        Scanner s = new Scanner(System.in);
        String input;
        System.out.println("Create a new user profile!");

        //Get first and last name
        System.out.println("Enter your first and last name: ");
        while (true) {
            input = s.nextLine();
            if (input == null || input.indexOf(" ") == -1) {
                System.out.println("Error: Invalid Input! Please enter your first and last name.");
            } else {
                firstName = input.substring(0, input.indexOf(" "));
                lastName = input.substring(input.indexOf(" ") + 1);
                break;
            }
        }
        System.out.println("Welcome " + firstName + " " + lastName);

        //Get username
        System.out.println("Enter your desired username: ");
        while (true) {
            input = s.nextLine();
            if (input == null || input.indexOf(" ") != -1) {
                System.out.println("Error: Invalid Input! Please enter a valid username.");
            } else if (usernameArray.contains(input)) { //ensure username not taken
                System.out.println("This username is taken. Please enter another username.");
            } else {
                username = input;
                usernameArray.add(username);
                break;
            }
        }
        System.out.println("Your username has been set to " + username);

        //Get password
        System.out.println("Enter your password: ");
        while (true) {
            input = s.nextLine();
            if (input == null || input.indexOf(" ") != -1) {
                System.out.println("Error: Invalid Input! Please enter a valid password.");
            } else if (input.length() < 7 || input.length() > 12) { //ensure username not taken
                System.out.println("Please enter a password 7 - 12 characters long");
            } else {
                password = input;
                break;
            }
        }
        System.out.println("Your password has been saved");

        //upload picture
        System.out.println("Enter the path for your profile picture");
        input = s.nextLine();
        imageIcon = new ImageIcon(input);
    }

    public String getPassword() { //can be used for password protection login
        return password;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public static void main(String[] args) {
        User u = new User();
    }
    
}

/*
User class (Yajushi)
Arraylist of friends
Arraylist of blocked people

profile picture
user profile


User profiles.
Password protected login.
User search.
User viewer.
Add, block, and remove friend features.
Extra credit opportunity – Add support to upload and display profile pictures.

 */