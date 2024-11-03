import java.io.*;
import java.util.*;

import javax.swing.ImageIcon;


public class Twitter {
   private ArrayList<User> users = new ArrayList<User>();
   private ArrayList<Post> posts = new ArrayList<Post>();
   private final String MENU = "1 - add a friend\n" + "2 - remove a friend\n"
                            + "3 - block a user\n" + "4 - unblock a user\n" + 
                            "5 - view a user profile\n" + "6 - view feed\n" +
                            "7 - create a post\n" + "8 - delete a post\n" + 
                            "9 - edit a post\n" + "10 - edit a post\n" + "11 - create a comment\n" + 
                            "12 - delete a comment\n" + "13 - edit a comment\n" + "14 - upvote a post\n" + 
                            "15 - downvote a post\n" + "16 - exit";

    public Twitter(String usernameFile, ArrayList<String> userFiles, ArrayList<String> postFiles) {
        readFile(usernameFile, userFiles, postFiles);
    }

    public Twitter() {

    }

   //User us = new User("yajushi", "gokhale", "ygokhale", "1234567", null);
   //TESTING REMOVE

    public User createNewUser() {
        User u = new User();
        users.add(u);
        return u;
    }

    public User getUser(String username) {
        User u = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                u = user;
                break;
            }
        }
        return u;
    }

    public User login() {
        System.out.println("Please enter your username: ");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        String username = "";
        User u = null;  
        while (true) {
            for (User user : users) {
                if (user.getUsername().equals(input)) {
                    username = input;
                    u = user;
                }
            }
            if (username.length() > 0) {
                break;
            } else {
                System.out.println("The username you entered has not been created.");
                System.out.println("Would you like to create a new user? (Y/N): ");

                if (s.nextLine().toLowerCase().charAt(0) == 'y') {
                    return createNewUser();
                } else {
                    System.out.println("Please enter your username: ");
                    input = s.nextLine();
                }
            }
        }


        System.out.println("Please enter your password");
        input = s.nextLine();
        while (true) {
            if (input.equals(u.getPassword())) {
                System.out.println("You have logged in!");
                break;
            } else {
                System.out.println("Incorrect Password. Try again: ");
            }
        }

        
        s.close();

        return u;

    }

    public void writeFile() {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File("users.txt"), false));
            for (User u : users) {
                u.writeFile();
                String picture = (u.getProfilePicture() == null) ? "null" : u.getProfilePicture().getDescription();
                String userInfo = u.getName() + ", " + u.getUsername() + ", " + u.getPassword() + ", " + picture;
                pw.println(userInfo);
            }
            pw.close();;
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not created");
        }
        for (Post p : posts) {
            p.writePost();
        }
    }
    public void readFile(String usernameFile, ArrayList<String> userFiles, ArrayList<String> postFiles) {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(new File(usernameFile)));
            while (true) {
                String userInfo = bfr.readLine();
                if (userInfo == null) {
                    break;
                }
                String[] uArray = userInfo.split(", ");
                String firstname = uArray[0].substring(0, uArray[0].indexOf(" "));
                String lastname = uArray[0].substring(uArray[0].indexOf(" ") + 1);
                ImageIcon image = (uArray[3].equals("null")) ? null : new ImageIcon(uArray[3]);
                users.add(new User(firstname, lastname, uArray[1], uArray[2], image));
            }

            for (String file : userFiles) {
                bfr = new BufferedReader(new FileReader(new File(file)));
                String username = bfr.readLine();
                User user = getUser(username);

                ArrayList<User> friends = new ArrayList<User>();
                bfr.readLine();
                username = bfr.readLine();
                while (username.equals("BLOCKED") == false) {
                    friends.add(getUser(username));
                    username = bfr.readLine();
                }

                ArrayList<User> blocked = new ArrayList<User>();
                username = bfr.readLine();
                while (username != null){
                    blocked.add(getUser(username));
                    username = bfr.readLine();
                }

                user.setFriends(friends);
                user.setBlocked(blocked);
            }

            for (String file : postFiles) {
                bfr = new BufferedReader(new FileReader(new File(file)));
                String postInfo = bfr.readLine();
                String[] postArray = postInfo.split(", ");
                ImageIcon im = (postArray[1].equals("null")) ? null : new ImageIcon(postArray[1]);
                Post p = new Post(postArray[0], im, getUser(postArray[4]), Integer.parseInt(postArray[2]), Integer.parseInt(postArray[3]));
                while (true) {
                    String commentInfo = bfr.readLine();
                    if (commentInfo == null) {
                        break;
                    }
                    String[] commentArray = commentInfo.split(", ");
                    p.addComment(commentArray[0], p.getUser(), getUser(commentArray[2]), p);

                }
            }

            bfr.close();
        } catch (Exception e) {
            System.out.println("Error: Files could not be read");
        }
    }
   
    public void run() {
        System.out.println("Hello! Welcome to Twitter. Please login.");
        System.out.println("Would you like to create a new user accout? (Y/N)");
        Scanner s = new Scanner(System.in);

        //login
        // User user = null;
        // if (s.nextLine().equals("Y")) {
        //     user = createNewUser();
        // } else {
        //     user = login();
        // }
        User user = new User("yajushi", "gokhale", "ygokhale", "1234567", null);

        while (true) {
            System.out.println(MENU);
            int option = s.nextInt(); s.nextLine();
            if (option == 1) {
                System.out.println("Please enter the username of the friend you would like to add: ");
                String username = s.nextLine();
                User friend = getUser(username);
                if (friend == null) {
                    System.out.println("Error: Invalid username");
                } else {
                    user.addFriend(friend);
                    System.out.println("Friend added!");
                }
            }
            if (option == 2) {
                System.out.println("Please enter the username of the friend you would like to remove: ");
                String username = s.nextLine();
                User friend = getUser(username);
                if (friend == null) {
                    System.out.println("Error: Invalid username");
                } else {
                    user.removeFriend(friend);
                    System.out.println("Friend removed.");
                }
            }
            if(option == 16) {
                writeFile();
                return;
            }
        }
    }
    public static void main(String[] args) {
        Twitter t = new Twitter();
        t.run();
    }
}
