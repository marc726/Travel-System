<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%
    String username = (session != null) ? (String) session.getAttribute("username") : null;
%>
<!DOCTYPE html>
<html>
<head>
    <title>Logged In</title>
</head>
<body>
    <h1>Welcome, <%= username %></h1>
    <!-- The link for logging out should point to the logout action in your servlet -->
    <a href="<%=request.getContextPath()%>/logout">Logout</a>
</body>
</html>
