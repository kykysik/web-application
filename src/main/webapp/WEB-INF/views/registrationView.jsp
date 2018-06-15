<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 12.04.18
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="style/forum.css">

</head>
<body>

<p style="color: red;">${errorString}</p>
<form class="form" method="POST" action="${pageContext.request.contextPath}/registration">
    <table border="0">
        <tr>
            <td>User Name</td>
            <td><input type="text" name="userName" value= "${account.userName}" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value= "${account.password}" /> </td>
        </tr>
        <tr>
            <td>Man</td>
            <td><input type="radio" name="gender" value="M"/></td>
        </tr>
        <tr>
            <td>Woman</td>
            <td><input type="radio" name="gender" value="F"/></td>
        </tr>

        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="/">Cancel</a>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
