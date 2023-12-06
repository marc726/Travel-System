<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Sales Report</title>
</head>
<body>
    <h1>Sales Report for a Month</h1>
    <form action="SalesReportServlet" method="post">
        <label for="year">Year:</label>
        <input type="number" id="year" name="year" required>
        <label for="month">Month:</label>
        <input type="number" id="month" name="month" required min="1" max="12">
        <input type="submit" value="Get Sales Report">
    </form>

    <%
        if (request.getAttribute("totalSales") != null) {
            float totalSales = (float) request.getAttribute("totalSales");
            int year = (int) request.getAttribute("year");
            int month = (int) request.getAttribute("month");
    %>
        <h2>Sales Report for <%= month %>/<%= year %>:</h2>
        <p>Total Sales: $<%= totalSales %></p>
    <%
        }
    %>
</body>
</html>
