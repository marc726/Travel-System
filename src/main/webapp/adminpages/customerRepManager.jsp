<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Representative Manager</title>
</head>
<body>
    <h1>Customer Representative Manager</h1>

    <!-- Display message from servlet -->
    <% if (request.getAttribute("message") != null) { %>
        <p><%= request.getAttribute("message") %></p>
    <% } %>

    <!-- Form for Adding a New Customer Representative -->
    <h2>Add New Representative</h2>
    <form action="CustomerRepServlet" method="POST">
        <input type="hidden" name="action" value="add">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <label for="Name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        <input type="submit" value="Add Representative">
    </form>


    
    <!-- Form for Editing an Existing Customer Representative -->
    <h2>Edit Representative</h2>
    <form action="CustomerRepServlet" method="GET">
        <input type="submit" value="Load Representatives"/>
    </form>
    <form action="CustomerRepServlet" method="POST">
        <input type="hidden" name="action" value="edit">
        <label for="username">Select Representative:</label>
        <select name="username" id="username">
            <c:forEach items="${customerReps}" var="rep">
                <option value="${rep.username}">${rep.name}</option>
            </c:forEach>
        </select><br><br>
        <label for="newName">New Name:</label>
        <input type="text" id="newName" name="newName" required><br><br>
        <label for="newPassword">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" required><br><br>
        <input type="submit" value="Edit Representative">
    </form>

    <!-- Form for Deleting an Existing Customer Representative -->
    <h2>Delete Representative</h2>
    <form action="CustomerRepServlet" method="POST">
        <input type="hidden" name="action" value="delete">
        <label for="delUsername">Select Representative:</label>
        <select name="delUsername" id="delUsername">
            <c:forEach items="${customerReps}" var="rep">
                <option value="${rep.username}">${rep.name}</option>
            </c:forEach>        </select><br><br>
        <input type="submit" value="Delete Representative">
    </form>
</body>
</html>
