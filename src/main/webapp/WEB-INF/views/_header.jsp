<%--
  Created by IntelliJ IDEA.
  User: kykysik
  Date: 07.04.18
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%--
<link rel="stylesheet" type="text/css" href="style/forum.css">
--%>

<style>
    header {
        text-align: center;
        background: #2196f3;
        height: 55px;
        padding: 5px;
    }

    header img {
        display: inline-block;
        height: 55px;
        padding: inherit;
    }
</style>

<header>
    <img src="flower.png"/>
    <img src="flower.png"/>
    <img src="flower.png"/>
    <img src="flower.png"/>
    <img src="flower.png"/>
    <div style="float: left">
        <h2>Forum</h2>
    </div>
<%--style="float: right; padding: 10px; text-align: right;"--%>
    <div style="float: right; padding: 10px;text-align: right;">

        <!-- User store in session with attribute: loginedUser -->
         <b>Hello </b><b>${loginedUser.userName}</b><br>
         <b>^_^</b>
    </div>

</header>
