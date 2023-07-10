<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.matchlearners.Post" %>
<%--
  Created by IntelliJ IDEA.
  User: PierreB
  Date: 27/11/2022
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>

<html>
    <head>
        <title>${name}/Edit</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>

    <% String errorMessage = (String)request.getAttribute("ERROR"); %>

    <body>
        <div id="edit">
            <h3 class="text-center text-white pt-5">Edit</h3>
            <div class="container">
                <div id="editPost-row" class="row justify-content-center align-items-center">
                    <div id="editPost-column" class="col-md-6">
                        <div id="editPost-box" class="col-md-12">
                            <!-- ${ERROR}-->
                            <%if (errorMessage != null){%>
                            <p><%=errorMessage%></p>
                            <%};%>
                            <form id="editPost-form" class="form" action="edit-post-servlet" method="post">
                                <input type="hidden" name="name" value="${name}">
                                <input type="hidden" name="idConnectedUser" value="${idConnectedUser}"/>
                                <td><input type="hidden" class="input-user" name="id" value="${id}"/></td>
                                <h3 class="text-center text-info">Edit</h3>
                                <div class="form-group">
                                    <label for="description" class="text-info">Description :</label><br>
                                    <input type="text" name="description" id="description" class="form-control" value="${description}" required>
                                </div>
                                <div class="form-group">
                                    <center><input type="submit" name="submit" class="btn btn-info btn-md mt-2" value="submit"></center>
                                </div>
                            </form>
                            <form action="user-controller-servlet" method="get">
                                <input type="hidden" name="name" value="${name}">
                                <input type="hidden" name="idConnectedUser" value="${idConnectedUser}"/>
                                <input type="submit" value="Back">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
