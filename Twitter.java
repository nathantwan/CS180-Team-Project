import java.io.*;
import java.util.*;

import javax.swing.ImageIcon;


public class Twitter {
   private ArrayList<User> users = new ArrayList<User>();
   private ArrayList<Post> posts = new ArrayList<Post>();
   private final String MENU = "1 - Add a friend\n" + "2 - Remove a friend\n"
                            + "3 - Block a user\n" + "4 - Unblock a user\n" + 
                            "5 - View a user profile\n" + "6 - View feed\n" +
                            "7 - Create a post\n" + "8 - Delete a post\n" + 
                            "9 - Edit a post\n" + "10 - Create a comment\n" + 
                            "11 - Delete a comment\n" + "12 - Edit a comment\n" + "13 - Upvote a post\n" + 
                            "14 - Downvote a post\n" + "15 - Exit";

    public Twitter(String usernameFile, ArrayList<String> userFiles, ArrayList<String> postFiles) {
        readFile(usernameFile, userFiles, postFiles);
    }

    public Twitter() {

    }

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
        User user = null;
        if (s.nextLine().equals("Y")) {
            user = createNewUser();
        } else {
            user = login();
        }

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
            if (option == 3) {
                System.out.println("Please enter the username of the user you would like to block: ");
                String username = s.nextLine();
                User blocked = getUser(username);
                if (blocked == null) {
                    System.out.println("Error: Invalid username");
                } else {
                    user.blockUser(blocked);
                    System.out.println("User blocked.");
                }
            }
            if (option == 4) {
                System.out.println("Please enter the username of the user you would like to unblock: ");
                String username = s.nextLine();
                User blocked = getUser(username);
                if (blocked == null) {
                    System.out.println("Error: Invalid username");
                } else {
                    user.unblock(blocked);
                    System.out.println("User unblocked.");
                }
            }
            if (option == 5) {
                System.out.println("Please enter the username of the user you would like to view: ");
                String username = s.nextLine();
                User other = getUser(username);
                if (other == null) {
                    System.out.println("Error: Invalid username");
                } else {
                    System.out.println(other);
                }
            }
            if (option == 6) {
                ArrayList<Post> feed = user.displayFeed(posts);
                if (feed.size() == 0) {
                    System.out.println("There are no posts in your feed.");
                }
                for (Post p : feed) {
                    System.out.println(p);
                }
            }
            if (option == 7) {
                Post p = new Post(user);
                posts.add(p);
            }
            if (option == 8) {
                System.out.println("Please enter the number of the post you would like to delete (Note the first post is number 0):");
                int postNum = s.nextInt(); s.nextLine();
                if (postNum < 0 || postNum >= posts.size()) {
                    System.out.println("Error: Post could not be found");
                } else {
                    Post p = posts.get(postNum);
                    if (p.getUser().equals(user) == false) {
                        System.out.println("Error: You do not have the permissions to delete this post");
                    } else {
                        posts.remove(p);
                        System.out.println("Post deleted");
                    }
                }
            }
            if (option == 9) {
                System.out.println("Please enter the number of the post you would like to edit (Note the first post is number 0):");
                int postNum = s.nextInt(); s.nextLine();
                if (postNum < 0 || postNum >= posts.size()) {
                    System.out.println("Error: Post could not be found");
                } else {
                    Post p = posts.get(postNum);
                    if (p.getUser().equals(user) == false) {
                        System.out.println("Error: You do not have the permissions to edit this post");
                    } else {
                        System.out.println("Enter the new caption");
                        String caption = s.nextLine();
                        if (caption == null || caption.length() == 0) {
                            System.out.println("Error: Invalid caption");
                        } else {
                            p.editPost(caption);
                            System.out.println("Post edited");
                        }
                        
                    }
                }
            }
            if (option == 10) {
                System.out.println("Please enter the number of the post you would like to comment on (Note the first post is number 0):");
                int postNum = s.nextInt(); s.nextLine();
                if (postNum < 0 || postNum >= posts.size()) {
                    System.out.println("Error: Post could not be found");
                } else {
                    Post p = posts.get(postNum);
                    System.out.println("Enter the comment");
                        String caption = s.nextLine();
                        if (caption == null || caption.length() == 0) {
                            System.out.println("Error: Invalid comment");
                        } else {
                            p.addComment(caption, p.getUser(), user, p);
                            System.out.println("Comment created");
                        }
                }
            }
            if (option == 11) {
                System.out.println("Please enter the number of the post you would like to delete the comment on (Note the first post is number 0):");
                int postNum = s.nextInt(); s.nextLine();
                if (postNum < 0 || postNum >= posts.size()) {
                    System.out.println("Error: Post could not be found");
                } else {
                    Post p = posts.get(postNum);
                    System.out.println("Please enter the number of the comment you would like to delete (Note the first comment in number 0):");
                    int commentNum = s.nextInt(); s.nextLine();
                    if (commentNum < 0 || commentNum >= p.getComments().size()) {
                        System.out.println("Error: Comment could not be found");
                    } else {
                        Comment comment = p.getComments().get(commentNum);
                        if (comment.getCommenter().equals(user) == false && comment.getPostOwner().equals(user) == false) {
                            System.out.println("Error: You do not have the permissions to delete this comment");
                        } else {
                            p.getComments().remove(comment);
                            System.out.println("Comment deleted");
                        }
                    }
                }
            }
            if (option == 12) {
                System.out.println("Please enter the number of the post you would like to edit the comment on (Note the first post is number 0):");
                int postNum = s.nextInt(); s.nextLine();
                if (postNum < 0 || postNum >= posts.size()) {
                    System.out.println("Error: Post could not be found");
                } else {
                    Post p = posts.get(postNum);
                    System.out.println("Please enter the number of the comment you would like to edit (Note the first comment in number 0):");
                    int commentNum = s.nextInt(); s.nextLine();
                    if (commentNum < 0 || commentNum >= p.getComments().size()) {
                        System.out.println("Error: Comment could not be found");
                    } else {
                        Comment comment = p.getComments().get(commentNum);
                        if (comment.getCommenter().equals(user) == false) {
                            System.out.println("Error: You do not have the permissions to edit this comment");
                        } else {
                            System.out.println("Enter the new comment");
                            String newComment = s.nextLine();
                            if (newComment == null || newComment.length() == 0) {
                                System.out.println("Error: Invalid comment");
                            } else {
                                comment.setComment(newComment);
                                System.out.println("Comment edited");
                            }
                        }
                    }
                }
            }
            if (option == 13) {
                System.out.println("Please enter the number of the post you would like to upvote (Note the first post is number 0):");
                int postNum = s.nextInt(); s.nextLine();
                if (postNum < 0 || postNum >= posts.size()) {
                    System.out.println("Error: Post could not be found");
                } else {
                    Post p = posts.get(postNum);
                    p.incrementUpvote();
                    System.out.println("Post upvoted");
                }
            }
            if (option == 14) {
                System.out.println("Please enter the number of the post you would like to downvote (Note the first post is number 0):");
                int postNum = s.nextInt(); s.nextLine();
                if (postNum < 0 || postNum >= posts.size()) {
                    System.out.println("Error: Post could not be found");
                } else {
                    Post p = posts.get(postNum);
                    p.incrementDownvote();
                    System.out.println("Post downvoted");
                }
            }
            if(option == 15) {
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
