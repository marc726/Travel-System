<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Representative Manager</title>
</head>
<body>
    <h1>Customer Representative Manager</h1>

    <!-- Form for Adding a New Customer Representative -->
    <h2>Add New Representative</h2>
    <form action="CustomerRepServlet" method="POST">
        <input type="hidden" name="action" value="add">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <input type="submit" value="Add Representative">
    </form>

    <!-- Form for Editing an Existing Customer Representative -->
    <h2>Edit Representative</h2>
    <form action="CustomerRepServlet" method="POST">
        <input type="hidden" name="action" value="edit">
        <label for="username">Select Representative:</label>
        <select name="username" id="username">
            <%-- Populate this dropdown with customer representative usernames from your database --%>
        </select><br><br>
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
            <%-- Populate this dropdown with customer representative usernames from your database --%>
        </select><br><br>
        <input type="submit" value="Delete Representative">
    </form>
</body>
</html>
