<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Airports</title>
</head>
<body>
    <h1>Manage Airports</h1>

    <h2>Add Airport</h2>
    <form action="AirportManagementServlet" method="post">
        <input type="hidden" name="action" value="add">
        
        <label for="arid">Airport ID:</label>
        <input type="text" id="arid" name="arid" required><br><br>
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        
        <label for="location">Location:</label>
        <input type="text" id="location" name="location" required><br><br>
        
        <input type="submit" value="Add Airport">
    </form>

    <h2>Delete Airport</h2>
    <form action="AirportManagementServlet" method="post">
        <input type="hidden" name="action" value="delete">
        
        <label for="aridDel">Airport ID:</label>
        <input type="text" id="aridDel" name="arid" required><br><br>
        
        <input type="submit" value="Delete Airport">
    </form>
</body>
</html>
