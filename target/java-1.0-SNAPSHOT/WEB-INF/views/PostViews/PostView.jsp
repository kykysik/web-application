<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 26.04.18
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>${postq.get(0).id} - Web Forum</title>
            <link rel="stylesheet" type="text/css" media="all" href="style/forum.css"/>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/post">
    <div class="topic-content">
        <p class="topic-link"><a class="topic-link" href="posts">Return</a></p>
        <c:forEach var="item" items="${postq}">
            <p class="topic-title">${item.getTitle()}</p>
            <p class="topic-user">by: <span class="topic-user">${item.getLogin()}</span></p>
            <p class="topic-text">${item.getContent()}</p>
        </c:forEach>

    </div>

</form>
        <div class="topic-comments">
            <c:forEach var="cmnt" items="${comments}">

                <div class="topic-cmt">
                    <p class="topic-cmt-text">${cmnt.getContent()}</p>
                    <p class="topic-cmt-user">${cmnt.getLogin()}
                        <a href="editComments?id=${cmnt.getId()}&postId=${cmnt.getPostId()}">Edit</a>
                        <a href="deleteComments?id=${cmnt.getId()}&postId=${cmnt.getPostId()}">Del</a>
                    </p>
                </div>

            </c:forEach>
            <c:if test="${editComments == null}">


                <form class="cmt-form" action="${pageContext.request.contextPath}/post" method="post">
                        <input type="hidden" name="postId" value="${postq.get(0).getId()}">
                    <textarea placeholder="write your comment..." name="comment" required></textarea>
                    <button>submit</button>
                </form>
            </c:if>

            <c:if test="${editComments != null}">
                <form class="cmt-form" action="${pageContext.request.contextPath}/editComments" method="post">
                    <input type="hidden" name="postId" value="${editComments.getPostId()}">
                    <input type="hidden" name="id" value="${editComments.getId()}">
                    <input type="hidden" name="userName" value="${editComments.getLogin()}">
                    <textarea placeholder="${editComments.getContent()}" name="content" required></textarea>
                    <button>submit</button>
                </form>
            </c:if>
        </div>

</body>
</html>
