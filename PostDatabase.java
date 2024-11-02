public class PostDatabase {
    private ArrayList<Post> feed;
    public PostDatabase () {
        feed = new ArrayList(Post)<>;
    }
    public void addPost(String caption, ImageIcon image, User user) {
        
        try {
            Post post = new Post(caption, iamge, user);
            feed.add(post);
        } catch {
            System.out.println("Invalid Post");
        }
    }
    public void editPost(Post post, String caption, ImageIcon image) {

        
    }
    public void deletePost(Post post) {
        
    }


    

}