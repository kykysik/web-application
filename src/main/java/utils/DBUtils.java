package utils;

import DAO.DaoApp;
import beans.Comment;
import beans.Post;
import beans.UserAccount;
import connections.ConUt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils implements DaoApp {
    public  UserAccount findUser(String userName, String password) throws SQLException {
        String sql = "SELECT USER_NAME, GENDER, PASSWORD from USER_ACCOUNT WHERE USER_NAME = ? AND  PASSWORD = ?";
         ConUt conUt = new ConUt();
        try(Connection conn = conUt.getConnection()) {

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String gender = rs.getString("Gender");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }

    public  void registr(UserAccount account) throws SQLException {
        String sql = "INSERT INTO USER_ACCOUNT(USER_NAME,GENDER, PASSWORD) VALUES (?,?,?)";
        ConUt conUt = new ConUt();
        try(Connection conn = conUt.getConnection()) {

         PreparedStatement pstm = conn.prepareStatement(sql);
         pstm.setString(1, account.getUserName());
         pstm.setString(2, account.getGender());
         pstm.setString(3, account.getPassword());

         pstm.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public  UserAccount findUser(String userName) throws SQLException {
        String sql = "SELECT USER_NAME, GENDER, PASSWORD FROM USER_ACCOUNT WHERE USER_NAME = ?";
        ConUt conUt = new ConUt();
        try(Connection conn = conUt.getConnection()) {

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String password = rs.getString("Password");
            String gender = rs.getString("Gender");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  void insertComment(Comment comm) throws SQLException {
        String sql = "INSERT INTO COMMENTS(CONTENT, USER_NAME, POST_ID) VALUES (?, ?, ?)";
        ConUt conUt = new ConUt();
        try(Connection conn = conUt.getConnection()) {

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,comm.getContent());
        pstm.setString(2,comm.getLogin());
        pstm.setInt(3,comm.getPostId());
        pstm.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public  List<Comment> retrieveComment(int postId) throws SQLException {
        String sql= "SELECT * FROM COMMENTS WHERE POST_ID=? ORDER BY COMMENT_ID ASC";
        ConUt conUt = new ConUt();
        List<Comment> comments = new ArrayList<>();

        try(Connection conn = conUt.getConnection()) {

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, postId);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            String content = rs.getString("content");
            String login = rs.getString("user_name");
            int id = rs.getInt("comment_id");
            comments.add(new Comment(id, content, login, postId));
        }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return comments;
    }


    public  void insert(Post post) throws SQLException {
        String sql = "INSERT INTO POSTS(TITLE, CONTENT, USER_NAME) VALUES (?, ?, ?)";
        ConUt conUt = new ConUt();
        try(Connection conn = conUt.getConnection()) {

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, post.getTitle() );
        pstm.setString(2, post.getContent());
        pstm.setString(3, post.getLogin());
        pstm.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  List<Post> listPost() throws SQLException {
        ConUt conUt = new ConUt();
        String sql = "SELECT * FROM POSTS ORDER BY POST_ID DESC";
        List<Post> posts = new ArrayList<>();

        try(Connection conn = conUt.getConnection()) {

        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("post_id");
            String title = rs.getString("title");
            String content = rs.getString("content");
            String user_name = rs.getString("user_name");
            posts.add(new Post(id,title,content,user_name));
        }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public  List<Post> retrieve(int id) throws SQLException {
        ConUt conUt = new ConUt();
        String sql = "SELECT * FROM POSTS WHERE post_id=?";
        List<Post> posts = new ArrayList<>();

        try(Connection conn = conUt.getConnection()) {

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()) {
            String title = rs.getString("title");
            String content = rs.getString("content");
            String user_name = rs.getString("user_name");
             posts.add(new Post(id,title,content,user_name));
        }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public  void updateComments(Comment comment) throws SQLException {
        String sql = "UPDATE COMMENTS SET CONTENT = ? WHERE  COMMENT_ID = ? AND POST_ID = ?";
        ConUt conUt = new ConUt();
        try(Connection conn = conUt.getConnection()) {

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, comment.getContent());
        pstm.setInt(2, comment.getId());
        pstm.setInt(3, comment.getPostId());
        pstm.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  void deleteComments(String id, String postId) throws SQLException {
        String sql = "DELETE FROM COMMENTS WHERE COMMENT_ID = ? AND POST_ID = ?";
        ConUt conUt = new ConUt();

        try(Connection conn = conUt.getConnection()) {

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
        pstm.setString(2, postId);

        pstm.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  Comment findComment(String id, String postId) throws SQLException {
        String sql = "Select COMMENT_ID, CONTENT, USER_NAME, POST_ID FROM COMMENTS WHERE COMMENT_ID = ? AND POST_ID = ?";
        ConUt conUt = new ConUt();
        try(Connection conn = conUt.getConnection()) {

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
        pstm.setString(2, postId);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            int code = rs.getInt("comment_id");
            int postCode = rs.getInt("post_Id");
            String userName = rs.getString("user_name");
            String content = rs.getString("content");
            Comment comment = new Comment(code, content, userName,postCode);
            return comment;
        }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
