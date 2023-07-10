<%--
  Created by IntelliJ IDEA.
  User: PierreB
  Date: 26/11/2022
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registration</title>
        <link href="css/forms.css" rel="stylesheet" type="text/css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>

    <% String errorMessage = (String)request.getAttribute("ERROR"); %>

    <body>
        <div id="Registration">
            <h3 class="text-center text-white pt-5">Registration form</h3>
            <div class="container">
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" class="col-md-6">
                        <div id="login-box" class="col-md-12">
                            <!-- ${ERROR}-->
                            <%if (errorMessage != null){%>
                            <p><%=errorMessage%></p>
                            <%};%>
                            <form id="login-form" class="form" action="registration-servlet" method="post">
                                <h3 class="text-center text-info">Register</h3>
                                <div class="form-group">
                                    <label for="username" class="text-info">Username:</label><br>
                                    <input type="text" name="name" id="username" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="username" class="text-info">Email:</label><br>
                                    <input type="email" name="email" id="email" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="text-info">Password:</label><br>
                                    <input type="password" name="password" id="password" class="form-control" minlength="4" required>
                                </div>
                                <div class="form-group">
                                    <center><input type="submit" name="submit" class="btn btn-info btn-md mt-2" value="submit"></center>
                                </div>
                                <center>
                                    <div id="register-link" class="text-right">
                                        <a href="login-servlet" class="text-info">Login here</a>
                                    </div>
                                </center>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
