# CS180-Team-Project


1. Instructions on how to compile and run your project.

Phase 1 submitted to Vocareum Workspace by ___.

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