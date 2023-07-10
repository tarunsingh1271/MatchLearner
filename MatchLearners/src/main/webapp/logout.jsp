<%--
  Created by IntelliJ IDEA.
  User: PierreB
  Date: 02/12/2022
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML PUBliC "-//W3C/DTD HTML 4.0 Transitional//EN">
<html>
    <head>
        <META HTTP-EQUIV = "Pragma" CONTENT="no-cache">
        <title>Logout Page</title>
        <link href="css/index.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <center><h2>Are you sure to logout ?</h2></center>
        <center>
            <form METHOD=POST ACTION="login.jsp" NAME="logout">
                <input type="submit" name="logout" value="Logout">
            </form>
        </center>
    </body>
</html>
