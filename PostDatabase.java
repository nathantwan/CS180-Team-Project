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
        int index = -1;
        for (int i = 0; i < feed.size(); i++) {
            if (feed.get(i).equals(post)) {
                index = i;
                break;
            }
        }
        if (!(index == -1)) {
            feed.get(index).setPost(caption, image);
        } else {
            System.out.println("Post does not exist");
        }

        
    }
    public void deletePost(Post post) {
        int index = -1;
        for (int i = 0; i < feed.size(); i++) {
            if (feed.get(i).equals(post)) {
                index = i;
                break;
            }
        }
        if (!(index == -1)) {
            feed.remove(index);
        } else {
            System.out.println("Post does not exist");
        }
        
    }


    

}