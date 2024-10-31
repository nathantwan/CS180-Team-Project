public class Post{
    private String caption;
    private String image;
    private int upvote;
    private int downvote;
    private User user;
    private ArrayList<Comment> comments = new ArrayList<>();
    public Post(String caption, String image, User user) throws InvalidPostException{
        if(caption == null || caption.isEmpty() || user == null) {
            throw InvalidPostException("Invalid Post");
        }
        this.caption = caption;
        this.image = image;
        this.user = user;
        this.upvote = 0;
        this.downvote = 0;
    }
    public String getCaption() {
        return caption;
    }
    public String getImage() {
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
    public void addComment(Comment comment) {
        try {
            comments.add(comment);
        } catch (InvalidCommentException e) {
            e.getMessage();
        }
    }
    public void deleteComment(Comment comment, User user) throws InvalidCommentException{
        if (!(comment.getPostOwner().equals(user)) && !(comment.getCommenter().equals(user))) {
            throw InvalidCommentException("You do not have permission!");
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
            throw new InvalidCommentException("Comment does not exist")
        }

    public void editComment(String text, Comment comment, User user) throws InvalidCommentException {
        if (!(comment.getCommenter.equals(user))) {
            throw new InvalidCommentException("You do not have permission!")
            return;
        }
        for (int i = 0; i < comments.size(); i++) {
            if (comments.get(i).equals(comment)) {
                index = i;
                break;
            }
        }
        if (!(index == -1)) {
            comments.get(index).editComment(text);
        } else {
            throw new InvalidCommentException("Comment does not exist")
        }



    }
    }
    public boolean equals(Object o) {
        if(!(this == o)) {
            return false;
        }
        Post compare = (Post) o;
        return compare.getCaption().equals(caption) &&
        compare.getImage().equals(image) && compare.getUser().equals(user);
    }

}