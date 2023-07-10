<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login / Registration</title>
    </head>
    <body>
        <div id="index">
            <center>
                <h3 class="text-center text-white pt-5 text-info">Welcome, please login or register</h3>
            </center>
            <center>
                <div class="container">
                    <div id="index-row" class="row justify-content-center align-items-center">
                        <div id="index-column" class="col-md-6">
                            <div id="index-box" class="col-md-12">
                                <form id="index-form" class="form" method="get">
                                    <input type="submit" value="Login" formaction="login-servlet" id="login"/>
                                    <input type="submit" value="Register" formaction="registration-servlet"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </center>
        </div>
    </body>
</html>
