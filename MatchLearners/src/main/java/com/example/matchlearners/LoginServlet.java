package com.example.matchlearners;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet(
        name = "loginServlet",
        value = {"/login-servlet"}
)
public class LoginServlet extends HttpServlet {
    private DataSource dataSource;

    public LoginServlet() {
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
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                Cookie[] var4 = cookies;
                int var5 = cookies.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    Cookie cookie = var4[var6];
                    if (cookie.getName().equals("username")) {
                        req.setAttribute("username", cookie.getValue());
                    }
                }
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameToCheck = req.getParameter("j_username");
        String passwordToCheck = req.getParameter("j_password");
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        boolean isGood = false;

        try {
            this.dataSource = this.getDataSource();
            myConn = this.dataSource.getConnection();
            myStmt = myConn.createStatement();
            String sql = "select * from users";
            myRs = myStmt.executeQuery(sql);

            while(myRs.next()) {
                if (nameToCheck.equals(myRs.getString("username")) && passwordToCheck.equals(myRs.getString("password"))) {
                    Cookie cookie = new Cookie("username", nameToCheck);
                    cookie.setMaxAge(86400);
                    resp.addCookie(cookie);
                    HttpSession session = req.getSession();
                    isGood = true;
                    req.setAttribute("idConnectedUser", myRs.getInt("id_user"));
                    req.setAttribute("username", nameToCheck);
                    req.getRequestDispatcher("user-controller-servlet").forward(req, resp);
                }
            }

            if (!isGood) {
                String errorMessage = "wrong username or password";
                req.setAttribute("ERROR", errorMessage);
                this.doGet(req, resp);
            }
        } catch (SQLException var16) {
            throw new RuntimeException(var16);
        } catch (NamingException var17) {
            throw new RuntimeException(var17);
        } finally {
            this.close(myConn, myStmt, (PreparedStatement)null, myRs);
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
