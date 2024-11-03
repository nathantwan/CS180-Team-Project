import javax.swing.ImageIcon;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Post {
    private String caption;
    private ImageIcon image;
    private int upvote;
    private int downvote;
    private User user;
    private static int counter = 1;
    private int postNumber;
    private ArrayList<Comment> comments = new ArrayList<>();

    //write a constructor that intializes all fields
    public Post(String caption, ImageIcon image, User user) throws InvalidPostException {
        if (caption == null || caption.isEmpty() || user == null) {
            throw new InvalidPostException("Invalid Post");
        }
        this.caption = caption;
        this.image = image;
        this.user = user;
        this.upvote = 0;
        this.downvote = 0;
        this.postNumber = counter;
        counter++;
    }

    public Post(String caption, ImageIcon image, User user, int upvote, int downvote) throws InvalidPostException {
        if (caption == null || caption.isEmpty() || user == null) {
            throw new InvalidPostException("Invalid Post");
        }
        this.caption = caption;
        this.image = image;
        this.user = user;
        this.upvote = upvote;
        this.downvote = downvote;
        this.postNumber = counter;
        counter++;
    }

    // if only User parameter given, prompt user to onput fields
    public Post(User user) {
        Scanner scan = new Scanner(System.in);
        while (caption == null || caption.length() == 0) {
            System.out.println("Enter your caption:");
            caption = scan.nextLine();
            if (caption == null || caption.length() == 0) {
                System.out.println("Enter a valid caption");
            }
        }
        upvote = 0;
        downvote = 0;
        this.user = user;
        scan.close();

    }

    //getters and setters
    public String getCaption() {
        return caption;
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getUpvote() {
        return upvote;
    }

    public int getDownvote() {
        return downvote;
    }

    public User getUser() {
        return user;
    }

    public void editPost(String newCaption) {
        this.caption = newCaption;
    }

    public void incrementUpvote() {
        this.upvote++;
    }

    public void incrementDownvote() {
        this.downvote++;
    }

    public void setPost(String caption, ImageIcon image) {
        this.caption = caption;
        this.image = image;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
    
    public void addComment(String text, User postOwner, User commenter, Post post) {
        try {
            Comment comment = new Comment(text, postOwner, commenter, post);
            comments.add(comment);
        } catch (InvalidCommentException e) {
            System.out.println("Invalid Comment");
        }
    }

    public void deleteComment(Comment comment, User user) {
        if (!(comment.getPostOwner().equals(user)) && !(comment.getCommenter().equals(user))) {
            System.out.println("You do not have permission");
            return;
        }
        int index = -1;
        for (int i = 0; i < comments.size(); i++) {
            if (comments.get(i).equals(comment)) {
                index = i;
                break;
            }
        }
        if (!(index == -1)) {
            comments.remove(index);
        } else {
            System.out.println("Comment does not exist");
        }
    }

    public void editComment(String text, Comment comment, User user) throws InvalidCommentException {
        int index = -1;
        if (!(comment.getCommenter().equals(user))) {
            System.out.println("You do not have permission!");
            return;
        }
        for (int i = 0; i < comments.size(); i++) {
            if (comments.get(i).equals(comment)) {
                index = i;
                break;
            }
        }
        if (!(index == -1)) {
            comments.get(index).setComment(text);
        } else {
            System.out.println("Comment does not exist");
        }


    }

    public boolean equals(Object o) {
        if (!(this == o)) {
            return false;
        }
        Post compare = (Post) o;
        return compare.getCaption().equals(caption) &&
                compare.getImage().equals(image) && compare.getUser().equals(user);
    }

    public String toString() { 
        String s = "------------\n";
        s += user.toString();
        s += "Caption: " + caption + "\n";
        String im = (image == null) ? "null" : image.getDescription();
        s += "Image: " + im + "\n";
        s += "Comments: ";
        if (comments.size() == 0) {
            s += "None\n";
        }
        for (int i = 0; i < comments.size(); i++) {
            if (i == comments.size() - 1) {
                s += comments.get(i).getText() + "\n";
            } else {
                s += comments.get(i).getText() + ", ";
            }
        }
        s += "------------";

        return s;
    }

    public int getPostNumber() {
        return postNumber;
    }
    public void writePost() {
        String fileName = "Post" + postNumber + ".txt";
        File f = new File(fileName);
        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            String im = (image == null) ? "null" : image.getDescription();
            pw.write(caption + ", " +  im + ", " + upvote + ", " + downvote + ", " + user.getUsername() + ", " + postNumber + "\n");
            if(!(comments == null || comments.size() == 0)) {
                for (Comment comment : comments) {
                    pw.write(comment.getText() + ", " + comment.getPostOwner().getUsername() + ", " + comment.getCommenter().getUsername() + "\n");
                }
            }


        } catch (Exception e) {
            System.out.println("File not created");
        }

    }

}
