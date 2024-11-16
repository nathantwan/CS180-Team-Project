import java.io.*;
import java.util.*;
import javax.swing.ImageIcon;
import java.net.*;

public class TwitterServer implements Runnable {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Post> posts = new ArrayList<Post>();
    private final Object obj = new Object();

    public User getUser(String username) {
        User u = null;
        synchronized (obj) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    u = user;
                    break;
                }
            }
        }
        return u;
    }

    public String option1(String friendUsername, User user) { //add friend
        User friend = getUser(friendUsername);
        if (friend == null) {
            return "Error: Invalid username";
        } else {
            user.addFriend(friend);
            return "Friend added!";
        }
    }
    public String option2(String friendUsername, User user) { //remove friend
        User friend = getUser(friendUsername);
        if (friend == null) {
            return "Error: Invalid username";
        } else {
            user.removeFriend(friend);
            return "Friend removed.";
        }
    }
    public String option3(String blockedUsername, User user) { //block user
        User blocked = getUser(blockedUsername);
        if (blocked == null) {
            return "Error: Invalid username";
        } else {
            user.blockUser(blocked);
            return "User blocked.";
        }
    }
    public String option4(String blockedUsername, User user) { //unblock user
        User blocked = getUser(blockedUsername);
        if (blocked == null) {
            return "Error: Invalid username";
        } else {
            user.unblock(blocked);
            return "User unblocked.";
        }
    }
    public String option5(String userprofile) { //user profile
        User other = getUser(userprofile);
        if (other == null) {
            return "Error: Invalid username";
        } else {
            return other.toString();
        }
    }
    public String option6(User user) { //display feed
        ArrayList<Post> feed;
        synchronized (obj) {
            feed = user.displayFeed(posts);
        }   
        if (feed.size() == 0) {
            return "There are no posts in your feed.";
        }
        String toReturn = "";
        for (Post p : feed) {
            toReturn += p.toString() + "\n";
        }
        toReturn = toReturn.substring(0, toReturn.length() - 1);
        return toReturn;
    }
    public void option7(User user, String caption, String path) { //create post
        ImageIcon image;
        if (path == null) {
            image = null;
        } else {
            image = new ImageIcon(path);
        }
        Post p = new Post(caption, image, user);
        synchronized (obj) {
            posts.add(p);
        }
    }
    public String option8(int postNum, User user) { //delete post
        if (postNum < 0 || postNum >= posts.size()) {
            return "Error: Post could not be found";
        } else {
            Post p = posts.get(postNum);
            if (p.getUser().equals(user) == false) {
                return "Error: You do not have the permissions to delete this post";
            } else {
                posts.remove(p);
                return "Post deleted";
            }
        }
    }
    public String option9(int postNum, String caption, User user) { //edit post
        if (postNum < 0 || postNum >= posts.size()) {
            return "Error: Post could not be found";
        } else {
            Post p = posts.get(postNum);
            if (p.getUser().equals(user) == false) {
                return "Error: You do not have the permissions to edit this post";
            } else {
                if (caption == null || caption.length() == 0) {
                    return "Error: Invalid caption";
                } else {
                    p.editPost(caption);
                    return "Post edited";
                }
                
            }
        }
    }
    public String option10(int postNum, String comment, User user) { //create comment
        if (postNum < 0 || postNum >= posts.size()) {
            return "Error: Post could not be found";
        } else {
            Post p = posts.get(postNum);
            if (comment == null || comment.length() == 0) {
                return "Error: Invalid comment";
            } else {
                p.addComment(caption, p.getUser(), user, p);
                return "Comment created";
            }
        }
    }
    public String option11(int postNum, int commentNum, User user) { //delete comment
        if (postNum < 0 || postNum >= posts.size()) {
            return "Error: Post could not be found";
        } else {
            Post p = posts.get(postNum);
            if (commentNum < 0 || commentNum >= p.getComments().size()) {
                return "Error: Comment could not be found";
            } else {
                Comment comment = p.getComments().get(commentNum);
                if (comment.getCommenter().equals(user) == false && comment.getPostOwner().equals(user) == false) {
                    return "Error: You do not have the permissions to delete this comment";
                } else {
                    p.getComments().remove(comment);
                    return "Comment deleted";
                }
            }
        }
    }
    public String option12(int postNum, int commentNum, String newComment, User user) { //edit comment
        if (postNum < 0 || postNum >= posts.size()) {
            return "Error: Post could not be found";
        } else {
            Post p = posts.get(postNum);
            if (commentNum < 0 || commentNum >= p.getComments().size()) {
                return "Error: Comment could not be found";
            } else {
                Comment comment = p.getComments().get(commentNum);
                if (comment.getCommenter().equals(user) == false) {
                    return "Error: You do not have the permissions to edit this comment";
                } else {
                    if (newComment == null || newComment.length() == 0) {
                        return "Error: Invalid comment";
                    } else {
                        comment.setComment(newComment);
                        return "Comment edited";
                    }
                }
            }
        }
    }
    public String option13(int postNum) { //upvote post
        if (postNum < 0 || postNum >= posts.size()) {
            return "Error: Post could not be found";
        } else {
            Post p = posts.get(postNum);
            p.incrementUpvote();
            return "Post upvoted";
        }
    }
    public String option14(int postNum) { //downvote post
        if (postNum < 0 || postNum >= posts.size()) {
            return "Error: Post could not be found";
        } else {
            Post p = posts.get(postNum);
            p.incrementDownvote();
            return "Post downvoted";
        }
    }
    public String option15(String oldPass, String newPass) { //change password
        return (user.setPassword(oldPass, newPass)) ? "Password changed" : "Could not change password";
    }
    public void option16() { //end run
        writeFile();
    }



}
