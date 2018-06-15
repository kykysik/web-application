<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 26.04.18
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New Post - Web Forum</title>
    <link rel="stylesheet" type="text/css" media="all   " href="style/forum.css">
</head>
<body >
            <div class="insert-form">
                <form action="insert" method="post">
                    <p class="form-title">Write the content of the new post:</p>
                    <input type="text" placeholder="title" name="title" required/>
                    <textarea placeholder="post content" name="content" required></textarea>

                    <button>submit</button>
                    <%--<c:if test="${request.getAttribute(msgError) != null}">
                        <p class="error">${msgError}</p>
                    </c:if>--%>
                </form>
                <p class="insert-link"><a class="insert-link" href="posts">« Return</a></p>
            </div>
</body>
</html>
