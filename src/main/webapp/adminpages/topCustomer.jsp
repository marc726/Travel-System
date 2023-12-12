<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Top Revenue Generating Customer</title>
</head>
<body>
	<a href="/Travel-System/AdminDash.jsp">Back to Dashboard</a>
    <h1>Top Revenue Generating Customer</h1>

        <!-- Refresh Button -->
        <form action="TopCustomerServlet" method="get">
            <input type="submit" value="Get Top Customer">
        </form>
    <%
        String username = (String) request.getAttribute("username");
        String totalRevenue = (String) request.getAttribute("totalRevenue");
        if (username != null && totalRevenue != null) {
    %>
        <p><strong>Username:</strong> <%= username %></p>
        <p><strong>Total Revenue:</strong> $<%= totalRevenue %></p>
    <% } else { %>
        <p>No data available.</p>
    <% } %>
</body>
</html>
