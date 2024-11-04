# CS180-Team-Project


1. Instructions on how to compile and run your project.
   In order to run the project, the Twitter class must be run. When trying to recreate the database from hardware, the
   public Twitter(String usernameFile, ArrayList<String> userFiles, ArrayList<String> postFiles) method.

   usernameFile contains information about each user in the following format:

       fullName, username, password, profilePicturePath
   
   ArrayList<String> userFiles contains the name of all the files for the specific user info. The format is detailed below:

        username
        FRIENDS
        username1
        username2
        ...
        usernameN
        BLOCKED
        username1
        username2
        ...
        usernameN

   ArrayList<String> postFiles contains the name of all the files for each post. The format is detailed below:

        caption, image, upvote, downvote, username, postnumber
        text, postowner, commenter
        text, postowner, commenter

   The first line contains information about the post while the following lines contain information about the comments.

   
Phase 1 submitted to Vocareum Workspace by Nathan Wan.

InvalidPostException class: throw a new exception if a user tries to create a post that doesn't meet the criteria
to create a post (having a caption and user).
Utilized in Post and PostDatabase classes to verify the user is creating a valid post, and in PostTest and PostTest1
 classes to verify that the exception is being thrown in those circumstances.

InvalidCommentException class:  throws an exception when a user tries to create a comment that is null or empty.
Utilized in Comment and Post classes to throw an exception when a user tries to create a comment that is null or empty.
 It is also used in our CommentTest, CommentTest1, PostTest, and PostTest1 to throw an exception if a comment is being
 added, deleted, or edited without the correct prerequisites.

User class: Creates a user based on input from the user, which is then added to an arraylist of Users to allow users to
add friends or block other users by retrieving data from that array and creating personal arraylists for friends and 
blocked users. Each user has a unique feed based on posts from their friends, which are added to an arraylist. The
data created in User class is stored in UserFile.txt and implements UserInterface. UserTest verifies that the different
getter and setter methods are retrieving the correct information, throwing exception errors when necessary, and that
the various features (such as adding friends) are adding or removing the correct information.

UserTest class: Uses various tests to check if the User class is creating, storing, and manipulating data correctly.
Specifically, if the getters and setters are getting the correct information, methods such as addFriend are able to 
search through stored information and retrieve the wanted data, and to print out information correctly.

UserInterface: Interface implemented by User class to structure the methods used to create and manage users.

Post class: Creates a post based on the input given, and prompts the user to enter missing information for the post. 
There are getters and setters to obtain information from posts, such as upvotes/downvotes, and to edit a post. Posts
have an arraylist to store, edit, and delete comments made. The information from this class is stored in the Post file
and PostDatabase, implements PostInterface, and has its methods and exceptions checked by PostTest and PostTest1.

PostDatabase: Contains an arraylist of all posts from the Post class, and allows users to add, edit, or delete existing 
posts in the arraylist. The InvalidPostException is utilized if there is an invalid or nonexistent post that a user is
trying to be accessed.

PostTest class: Uses InvalidPostException and InvalidCommentException to validate the methods in Post class to create
and delete posts and comments, along with incrementing/decrementing upvotes and downvotes.

PostTest1 class: Verifies Post class methods to create posts, retrieve information with getters and set new data,
edit/create/delete comments, increment/decrement votes, see if two comments are equal, and to print out a string with
user and post information.

PostInterface: Interface implemented by Post class to structure methods to create and edit Posts, along with other
features from that class (such as editing comments).

Comment class: Creates a comment based on whether there is text, an original post, and a valid user for the original
post and the user commenting. This class relies on the Post and User classes to have valid information in order to
create a comment from a specific post and user and implements CommentInterface. InvalidCommentException is used to throw
 a new exception if the comment isn't a valid input. CommentTest and CommentTest1 test the validity of creating a
 comment, along with the different methods used to get specific information from the Comment class and comparing if a 
comment is equal or not.

CommentTest class: Tests the exceptions with empty and null strings, editing a comment, and if a comment is
equal or not in the Comment class.

CommentTest1 class: Tests the thrown exceptions, whether the getter and setter methods are retrieving the correct data,
and whether two comments are equal in the Comment class.

CommentInterface: Interface implemented by the Comment class to structure the methods used to create new comments.

Twitter class: Allows the user to create a user and to interact with the different features of the app, such as adding
friends, commenting on a post, and so on. The class implements TwitterInterface.

TwitterInterface: Interface implemented by the Twitter class to structure the methods used to set up new users and 
manage how users can interact with the app. 
