/**
 * Comment Interface
 * <p>
 * Purdue University -- CS18000 -- Team Project
 *
 * @author Yajushi
 * @version Nov 3, 2024
 */
public interface CommentInterface {
    void setComment(String text);
    String getText();
    User getPostOwner();
    User getCommenter();
    Post getPost();
    boolean equals(Object o);
}
