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

<!DOCTYPE html>
<html>
    <head>
        <title>${name}</title>
        <link href="css/user.css" type="text/css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <center>
            <div class="titre">
                <span class="titre1">Bonjour</span>
            </div>

            <div class="logout">
                <form action="logout-servlet" class="mb-2" method="get">
                    <input type="submit" value="Log out"/>
                </form>
            </div>

            <div id="container">
                <div id="content">
                    <table class="table table-sm">
                        <%int i=0;%>
                        <c:forEach var="tempPost" items="${POST_LIST}" >
                            <tr>
                                <form>
                                    <input type="hidden" name="name" value="${name}">
                                    <td><input type="hidden" class="input-user" name="id" value="${tempPost.idPost}"/></td>

                                    <td><input type="hidden" class="input-user" name="title" value="${tempPost.title}"/><center>${tempPost.title}</center></td>

                                    <td><input type="hidden" class="description1" name="description" value="${tempPost.description}"/><center>${tempPost.description}</center></td>

                                    <td><input type="hidden" class="username" name="username" value="${tempPost.username}"/><center>${tempPost.username}</center></td>

                                    <input type="hidden" name="idConnectedUser" value="${idConnectedUser}"/>

                                    <input type="hidden" class="input-user" name="isLiked" value="${tempPost.isLiked}"/>

                                    <c:choose>
                                        <c:when test="${tempPost.idUser == idConnectedUser}">
                                            <td colspan="2"><input type="submit" class="edit" value="Edit" formmethod="get" formaction="edit-post-servlet"></td>
                                            <td><input type="submit" class="delete" value="Delete" formmethod="post" formaction="delete-post-servlet"></td>
                                        </c:when>
                                        <c:when test="${tempPost.idUser != idConnectedUser}">
                                            <c:choose>
                                                <c:when test="${tempPost.isLiked==0}">
                                                    <td><input type="submit" class="delete" value="Like" formmethod="post" formaction="like-post-servlet"></td>
                                                </c:when>
                                                <c:when test="${tempPost.isLiked==1}">
                                                    <td><input type="submit" class="delete bg-success" value="Liked" formmethod="post" formaction="like-post-servlet"></td>
                                                </c:when>
                                            </c:choose>
                                        </c:when>
                                    </c:choose>
                                </form>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <form action="create-post-servlet">
                <input type="hidden" name="name" value="${name}">
                <input type="hidden" name="idConnectedUser" value="${idConnectedUser}">
                <input class="newPost" type="submit" value="create new post" formmethod="get"/>
            </form>
        </center>
    </body>
</html>
