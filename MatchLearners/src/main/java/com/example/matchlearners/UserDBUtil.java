package com.example.matchlearners;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class UserDBUtil {
    private DataSource dataSource;

    public UserDBUtil(DataSource theDataSource) {
        this.dataSource = theDataSource;
    }

    public List<Post> getPosts() throws Exception {
        List<Post> posts = new ArrayList();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = this.dataSource.getConnection();
            String sql = "SELECT * FROM post";
            myStmt = myConn.prepareStatement(sql);
            myRs = myStmt.executeQuery(sql);

            while(myRs.next()) {
                int newIdPost = myRs.getInt("id_post");
                String postTitle = myRs.getString("title");
                String postDescription = myRs.getString("description");
                int newIdUser = myRs.getInt("id_user");
                String newUsername = myRs.getString("username");
                int isLiked = myRs.getInt("is_liked");
                Post tempPost = new Post(newIdPost, postTitle, postDescription, newIdUser, newUsername, isLiked);
                posts.add(tempPost);
            }

            return posts;
        } finally {
            this.close(myConn, myStmt, myRs, (PreparedStatement)null);
        }
    }

    public List<User> getUsers() throws Exception {
        List<User> users = new ArrayList();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = this.dataSource.getConnection();
            String sql = "SELECT * FROM users";
            myStmt = myConn.prepareStatement(sql);
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()) {
                int newIdUser = myRs.getInt("id_user");
                String newUsername = myRs.getString("username");
                User tempUser = new User(newIdUser, newUsername, null, null);
                users.add(tempUser);
            }
            return users;
        } finally {
            this.close(myConn, myStmt, myRs, (PreparedStatement)null);
        }
    }


    private void close(Connection myConn, Statement myStmt, ResultSet myRs, PreparedStatement pStmt) {
        try {
            if (myStmt != null) {
                myStmt.close();
            }
            if (myRs != null) {
                myRs.close();
            }
            if (myConn != null) {
                myConn.close();
            }
            if (pStmt != null) {
                pStmt.close();
            }
        } catch (Exception var6) {
            System.out.println(var6.getMessage());
        }
    }
}
