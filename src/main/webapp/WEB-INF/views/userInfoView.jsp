<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 07.04.18
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="style/forum.css">

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body style="background: aliceblue">

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Hello: ${user.userName}</h3>

User Name: <b>${user.userName}</b>
<br />
Gender: ${user.gender } <br />

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>