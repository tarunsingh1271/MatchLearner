package com.example.matchlearners;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
        name = "editPostServlet",
        value = {"/edit-post-servlet"}
)
public class EditPostServlet extends HttpServlet {
    private DataSource dataSource;
    private UserDBUtil userDBUtil;

    public EditPostServlet() {
    }

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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/edit-post.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idPost = req.getParameter("id");
        String newDescription = req.getParameter("description");
        System.out.println(newDescription);
        if (!newDescription.equals("")) {
            Connection myConn = null;
            PreparedStatement preparedStmt = null;
            try {
                this.dataSource = this.getDataSource();
                myConn = this.dataSource.getConnection();
                String query = "UPDATE post SET description=? WHERE id_post=?";
                preparedStmt = myConn.prepareStatement(query);
                preparedStmt.setString(1, newDescription);
                preparedStmt.setString(2, idPost);
                preparedStmt.executeUpdate();
                String name_account = req.getParameter("name");
                req.setAttribute("name", name_account);
                int idConnectedUser = Integer.parseInt(req.getParameter("idConnectedUser"));
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
