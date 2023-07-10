package com.example.matchlearners;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(
        name = "createPostServlet",
        value = {"/create-post-servlet"}
)
public class CreatePostServlet extends HttpServlet {
    private DataSource dataSource;
    private UserDBUtil userDBUtil;


    public void init() throws ServletException {
        super.init();

        try {
            this.dataSource = this.getDataSource();
            this.userDBUtil = new UserDBUtil(this.dataSource);
        } catch (NamingException var2) {
            var2.printStackTrace();
        }

    }

    private DataSource getDataSource() throws NamingException {
        String jndi = "java:comp/env/jdbc/matchLearners";
        Context context = new InitialContext();
        DataSource dataSource = (DataSource)context.lookup(jndi);
        return dataSource;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tempId = req.getParameter("id");
        String tempDesc = req.getParameter("description");
        String name_account = req.getParameter("name");
        req.setAttribute("id", tempId);
        req.setAttribute("description", tempDesc);
        req.setAttribute("name", name_account);
        int idConnectedUser = Integer.parseInt(req.getParameter("idConnectedUser"));
        req.setAttribute("idConnectedUser", idConnectedUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/create-post.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newDescription = req.getParameter("description");
        String newTitle = req.getParameter("title");
        int idConnectedUser = Integer.parseInt(req.getParameter("idConnectedUser"));
        List<User> users = null;
        String username = "";
        try {
            users = userDBUtil.getUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (User temp: users) {
            if (temp.getIdUser() == idConnectedUser){
                username = temp.getUsername();
            }
        }
        if (!newDescription.equals("")) {
            Connection myConn = null;
            PreparedStatement preparedStmt = null;
            try {
                this.dataSource = this.getDataSource();
                myConn = this.dataSource.getConnection();
                String query = "INSERT INTO post(title,description,id_user,username,is_liked) VALUES (?,?,?,?,0)";
                preparedStmt = myConn.prepareStatement(query);
                preparedStmt.setString(1, newTitle);
                preparedStmt.setString(2, newDescription);
                preparedStmt.setInt(3, idConnectedUser);
                preparedStmt.setString(4, username);
                preparedStmt.executeUpdate();
                String name_account = req.getParameter("name");
                req.setAttribute("name", name_account);
                req.setAttribute("idConnectedUser", idConnectedUser);
                req.getRequestDispatcher("user-controller-servlet").forward(req, resp);
            } catch (Exception var12) {
                System.out.println(var12.getMessage());
            } finally {
                this.close(myConn, (Statement)null, preparedStmt, (ResultSet)null);
            }
        } else {
            String errorMessage = "The description cannot be empty";
            req.setAttribute("ERROR", errorMessage);
            this.doGet(req, resp);
        }

    }

    private void close(Connection myConn, Statement myStmt, PreparedStatement preparedStmt, ResultSet myRs) {
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

            if (preparedStmt != null) {
                preparedStmt.close();
            }
        } catch (Exception var6) {
            System.out.println(var6.getMessage());
        }

    }
}
