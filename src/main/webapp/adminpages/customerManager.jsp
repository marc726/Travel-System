<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
</head>
<body>
	<a href="/Travel-System/AdminDash.jsp">Back to Dashboard</a>
    <h1>Customer Dashboard</h1>

    <!-- Form for Adding a New Customer -->
    <h2>Add New Customer</h2>
    <form action="CustomerServlet" method="POST">
        <input type="hidden" name="action" value="add">
        <label for="addUsername">Username:</label>
        <input type="text" id="addUsername" name="username" required><br><br>
        <label for="addPassword">Password:</label>
        <input type="password" id="addPassword" name="password" required><br><br>
        <label for="addName">Name:</label>
        <input type="text" id="addName" name="name" required><br><br>
        <input type="submit" value="Add Customer">
    </form>

    <!-- Form for Editing an Existing Customer -->
    <h2>Edit Customer</h2>
    <form action="CustomerServlet" method="GET">
        <input type="submit" value="Load Customers"/>
    </form>
    <form action="CustomerServlet" method="POST">
        <input type="hidden" name="action" value="edit">
        <label for="username">Select Customer:</label>
        <select name="username" id="username">
            <c:forEach items="${customers}" var="customer">
                <option value="${customer.username}">${customer.name}</option>
            </c:forEach>
        </select><br><br>
        <label for="editPassword">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" required><br><br>
        <label for="editName">New Name:</label>
        <input type="text" id="newName" name="newName" required><br><br>
        <input type="submit" value="Edit Customer">
    </form>

    <!-- Form for Deleting an Existing Customer -->
    <h2>Delete Customer</h2>
    <form action="CustomerServlet" method="GET">
        <input type="submit" value="Load Customers"/>
    </form>
    <form action="CustomerServlet" method="POST">
        <input type="hidden" name="action" value="delete">
        <label for="delUsername">Select Customer:</label>
        <select name="delUsername" id="delUsername">
            <c:forEach items="${customers}" var="customer">
                <option value="${customer.username}">${customer.name}</option>
            </c:forEach>
        </select><br><br>
        <input type="submit" value="Delete Customer">
    </form>

</body>
</html>
