public interface CommentInterface {
    void setComment(String text);
    String getText();
    User getPostOwner();
    User getCommenter();
    Post getPost();
    boolean equals(Object o);
}
