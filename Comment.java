public class Comment implements CommentInterface {
    private String text;
    private User postOwner;
    private User commenter;
    private Post post;
    public Comment(String text, User postOwner, User commenter, Post post) throws InvalidCommentException {
        if (text == null || text.isEmpty()) {
            throw new InvalidCommentException("Invalid Comment");
        }
        this.text = text;
        this.postOwner = postOwner;
        this.commenter = commenter;
        this.post = post;
    }
    public void setComment(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public User getPostOwner() {
        return postOwner;
    }
    public User getCommenter() {
        return commenter;
    }
    public Post getPost() {
        return post;
    }
    public boolean equals(Object o) {
        if (!(this == o)) {
            return false;
        }
        Comment compare = (Comment) o;
        return compare.getText().equals(text) && 
        compare.getPostOwner().equals(postOwner) && 
        compare.getCommenter().equals(commenter) && 
        compare.getPost().equals(post);
    }


}
