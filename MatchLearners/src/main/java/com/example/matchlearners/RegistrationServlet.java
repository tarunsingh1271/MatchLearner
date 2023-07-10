//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet(
        name = "registrationUpServlet",
        value = {"/registration-servlet"}
)
public class RegistrationServlet extends HttpServlet {
    private DataSource dataSource;

    public RegistrationServlet() {
    }

    private DataSource getDataSource() throws NamingException {
        String jndi = "java:comp/env/jdbc/matchLearners";
        Context context = new InitialContext();
        DataSource dataSource = (DataSource)context.lookup(jndi);
        return dataSource;
    }

    public void init() throws ServletException {
        super.init();

        try {
            this.dataSource = this.getDataSource();
        } catch (NamingException var2) {
            var2.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/registration.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("name");
        String newPassword = req.getParameter("password");
        String newMail = req.getParameter("email");

        try {
            String errorMessage;
            if (this.isNameAvailable(newName)) {
                if (!newPassword.isEmpty()) {
                    Connection myConn = null;
                    PreparedStatement preparedStmt = null;

                    try {
                        this.dataSource = this.getDataSource();
                        myConn = this.dataSource.getConnection();
                        String query = "INSERT INTO users(username,email,password) VALUES (?,?,?)";
                        preparedStmt = myConn.prepareStatement(query);
                        preparedStmt.setString(1, newName);
                        preparedStmt.setString(2, newMail);
                        preparedStmt.setString(3, newPassword);
                        preparedStmt.execute();
                        this.close(myConn, (Statement)null, preparedStmt, (ResultSet)null);
                        HttpSession session = req.getSession();
                        req.setAttribute("name", newName);
                        UserDBUtil userDBUtil = new UserDBUtil(this.dataSource);
                        List<User> users = userDBUtil.getUsers();
                        int idNewUser = 0;
                        int i = 0;
                        while (i<users.size()){
                            if (newName == users.get(i).getUsername()){
                                idNewUser = users.get(i).getIdUser();
                            }
                            i++;
                        }
                        req.setAttribute("idConnectedUser", idNewUser);
                        req.getRequestDispatcher("/user-controller-servlet").forward(req, resp);
                    } catch (Exception var10) {
                        System.out.println(var10.getMessage());
                    }
                } else {
                    errorMessage = "wrong username or password";
                    req.setAttribute("ERROR", errorMessage);
                    this.doGet(req, resp);
                }
            } else {
                errorMessage = "The name is not available";
                req.setAttribute("ERROR", errorMessage);
                this.doGet(req, resp);
            }

        } catch (Exception var11) {
            throw new RuntimeException(var11);
        }
    }

    public boolean isNameAvailable(String newName) throws Exception {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        boolean var11;
        try {
            this.dataSource = this.getDataSource();
            myConn = this.dataSource.getConnection();
            myStmt = myConn.createStatement();
            String sql = "select * from users";
            myRs = myStmt.executeQuery(sql);

            while(myRs.next()) {
                String username = myRs.getString("username");
                if (username.equals(newName)) {
                    boolean var7 = false;
                    return var7;
                }
            }

            var11 = true;
        } finally {
            this.close(myConn, myStmt, (PreparedStatement)null, myRs);
        }

        return var11;
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
