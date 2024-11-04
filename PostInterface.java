import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Post Interface
 * <p>
 * Purdue University -- CS18000 -- Team Project
 *
 * @author Yajushi
 * @version Nov 3, 2024
 */

public interface PostInterface {
    String getCaption();
    ImageIcon getImage();
    int getUpvote();
    int getDownvote();
    User getUser();
    void editPost(String newCaption);
    void incrementUpvote();
    void incrementDownvote();
    void setPost(String caption, ImageIcon image);
    ArrayList<Comment> getComments();
    void addComment(String text, User postOwner, User commenter, Post post);
    void deleteComment(Comment comment, User user);
    void editComment(String text, Comment comment, User user) throws InvalidCommentException;
    boolean equals(Object o);
    String toString();
    int getPostNumber();
    void writePost();
}
