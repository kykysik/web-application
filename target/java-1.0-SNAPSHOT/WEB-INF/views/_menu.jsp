<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 07.04.18
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<div style="padding: 5px;">
    <p>
    <a style="color: #000000;font-weight: bold;" href="${pageContext.request.contextPath}/">Home</a>
    |
    <a style="color: #000000;font-weight: bold;" href="${pageContext.request.contextPath}/userInfo">My Account Info</a>
    |
    <a style="color: #000000;font-weight: bold;" href="${pageContext.request.contextPath}/registration">Registration</a>
    |
    <a style="color: #000000;font-weight: bold;" href="${pageContext.request.contextPath}/posts">Forum</a>
    |
    <c:if test="${user != null}">
        <a style="color: #000000;font-weight: bold;" href="${pageContext.request.contextPath}/logOut">LogOut</a>
    </c:if>
    <c:if test="${user == null}">
        <a style="color: #000000;font-weight: bold;" href="${pageContext.request.contextPath}/login">LogIn</a>
    </c:if>
    </p>

</div>
