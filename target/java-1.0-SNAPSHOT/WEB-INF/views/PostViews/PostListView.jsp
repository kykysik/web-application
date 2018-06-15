<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 26.04.18
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Posts - Web Forum</title>
    <link rel="stylesheet" type="text/css" media="all" href="style/forum.css">
</head>
<body>
            <div class="header">
                <p class="header-link">
                    <span class="header-link-l"><a class="header-link" href="insert">+ New Post</a></span>
                    <span class="header-link-r"><a class="header-link" href="home">✗ Home</a></span>
                    <br>
                </p>
            </div>

            <c:forEach  items="${postList}" var="post">
                <a href="post?id=${post.getId()}" class="list-topic-item">
                    <p class="list-item-title">${post.getTitle()}</p>
                    <p class="list-item-user">by: <span class="list-item-user">${post.getLogin()}</span></p>
                </a>

            </c:forEach>
</body>
</html>
