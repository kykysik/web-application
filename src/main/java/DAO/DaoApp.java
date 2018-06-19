package DAO;

import beans.Comment;
import beans.Post;
import beans.UserAccount;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DaoApp {

    Connection c = null;
    UserAccount findUser( String userName, String password) throws SQLException ;

    void registr(UserAccount account) throws SQLException;

    UserAccount findUser(String userName) throws SQLException;

    void insertComment(Comment comm) throws SQLException ;

    List<Comment> retrieveComment(int postId) throws SQLException;

    void insert(Post post) throws SQLException;

    List<Post> listPost() throws SQLException;

    List<Post> retrieve(int id) throws SQLException;

    void updateComments(Comment comment) throws SQLException;

    void deleteComments(String id, String postId) throws SQLException;

    Comment findComment(String id, String postId) throws SQLException;

}
