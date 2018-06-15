<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 07.04.18
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="all" href="style/forum.css">

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body style="background: aliceblue">

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Home Page</h3>

This is demo Simple web application using JSP/JSTL, Servlets &amp; JDBC.<br><br>
<b>It includes the following functions:</b>
<ul>
    <li>Posts</li>
    <li>Comments</li>
    <li>Delete/Edit/Add comments</li>
    <li>LogIn/LogOut</li>
    <li>Registration</li>
    <li>Storing user information in cookies</li>
    <li>Storing user information in sessions</li>
</ul>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>