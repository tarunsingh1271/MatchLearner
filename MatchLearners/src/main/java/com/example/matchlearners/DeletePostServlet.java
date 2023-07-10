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
        name = "deletePostServlet",
        value = {"/delete-post-servlet"}
)
public class DeletePostServlet extends HttpServlet {
    private DataSource dataSource;
    private UserDBUtil userDBUtil;

    public DeletePostServlet() {
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
        try {
            String name_account = req.getParameter("name");
            req.setAttribute("name", name_account);
            int idConnectedUser = (int) req.getAttribute("idConnectedUser");
            req.setAttribute("idConnectedUser", idConnectedUser);
            this.listPosts(req, resp);
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    private void listPosts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Post> posts = this.userDBUtil.getPosts();
        request.setAttribute("POST_LIST", posts);
        List<User> users = this.userDBUtil.getUsers();
        request.setAttribute("USER_LIST", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-page.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idPost = req.getParameter("id");
        Connection myConn = null;
        PreparedStatement preparedStmt = null;
        try {
            this.dataSource = this.getDataSource();
            myConn = this.dataSource.getConnection();
            String query = "DELETE FROM post WHERE id_post=?";
            preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setString(1, idPost);
            preparedStmt.execute();
            this.close(myConn, (Statement)null, preparedStmt, (ResultSet)null);
            String name_account = req.getParameter("name");
            req.setAttribute("name", name_account);
            int idConnectedUser = Integer.parseInt(req.getParameter("idConnectedUser"));
            req.setAttribute("idConnectedUser", idConnectedUser);
            req.getRequestDispatcher("user-controller-servlet").forward(req, resp);
        } catch (Exception var8) {
            System.out.println("Erreur delete");
            System.out.println(var8.getMessage());
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
