import javax.swing.ImageIcon;
import java.util.scanner;

public class Post {
    private String caption;
    private ImageIcon image;
    private int upvote;
    private int downvote;
    private User user;
    private ArrayList<Comment> comments = new ArrayList<>();

    //write a constructor that intializes all fields
    public Post(String caption, ImageIcon image, User user) throws InvalidPostException{
        if(caption == null || caption.isEmpty() || user == null) {
            throw InvalidPostException("Invalid Post");
        }
        this.caption = caption;
        this.image = image;
        this.user = user;
        this.upvote = 0;
        this.downvote = 0;
    }
    // if only User parameter given, prompt user to onput fields
    public Post(User user) {
        Scanner scan = new Scanner(System.in);
        while (caption == null) {
            System.out.println("Enter your caption");
            caption = scan.nextLine();
            if (caption == null) {
                System.out.println("Enter a valid caption")
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
    public void addComment(String text, User postOwner, User commenter, Post post) {
        try {
            Comment comment = new Comment(text, postOwner, commenter, post);
            comments.add(comment);
        } catch (InvalidCommentException e) {
            System.out.println("Invalid Comment")
        }
    }
    public void deleteComment(Comment comment, User user){
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

    public void editComment(String text, Comment comment, User user) throws InvalidCommentException {
        if (!(comment.getCommenter.equals(user))) {
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
            comments.get(index).editComment(text);
        } else {
            System.out.println("Comment does not exist");
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