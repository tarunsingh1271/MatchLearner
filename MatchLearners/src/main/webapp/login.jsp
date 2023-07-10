<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  </head>
  <% String errorMessage = (String)request.getAttribute("ERROR"); %>
  <body>
    <div id="login">
      <h3 class="text-center text-white pt-5">Login form</h3>
      <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
          <div id="login-column" class="col-md-6">
            <div id="login-box" class="col-md-12">
              <!-- ${ERROR}-->
              <%if (errorMessage != null){%>
              <p><%=errorMessage%></p>
              <%};%>
              <form id="login-form" class="form" action="login-servlet" method="post">
                <h3 class="text-center text-info">Login</h3>
                <div class="form-group">
                  <label for="username" class="text-info">Username:</label><br>
                  <input type="text" name="j_username" id="username" class="form-control" required>
                </div>
                <div class="form-group">
                  <label for="password" class="text-info">Password:</label><br>
                  <input type="password" name="j_password" id="password" class="form-control" minlength="4" required>
                </div>
                <div class="form-group">
                  <label for="remember-me" class="text-info"><span>Remember me</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br>
                  <center><input type="submit" name="submit" class="btn btn-info btn-md" value="submit"></center>
                </div>
                <center>
                  <div id="register-link" class="text-right">
                    <a href="registration-servlet" class="text-info">Register here</a>
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
