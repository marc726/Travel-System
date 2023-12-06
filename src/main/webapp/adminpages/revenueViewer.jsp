<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Revenue Summary</title>
</head>
<body>
    <h1>Revenue Summary</h1>
    <form action="RevenueSummaryServlet" method="post">
        <select name="type">
            <option value="flight">Flight</option>
            <option value="airline">Airline</option>
            <option value="customer">Customer</option>
        </select>
        <input type="text" name="identifier" placeholder="Enter Flight Number/Airline ID/Username">
        <input type="submit" value="Get Revenue">
    </form>
    
    <h2>Revenue: $<%= request.getAttribute("revenue") %></h2>
</body>
</html>
