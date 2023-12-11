<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
</head>
<body>
	<% String alertFreeSpots = (String) session.getAttribute("alertFreeSpots"); session.setAttribute("alertFreeSpots", null);%>
	<script>
	
    <% if (alertFreeSpots != "There are free spots on flights that you have waitlisted: " && alertFreeSpots != null) { %>
        // JavaScript code to display an alert
        alert("<%= alertFreeSpots %>");
    <% } %>
	</script>
    <h1>Welcome to the Customer Dashboard</h1>
    
    <h2>Browse Flights</h2>
    <ul>
        <li><a href="customerPages/browseFlightsCustomer.jsp">Browse all flights</a></li>
    </ul>
    
    <h2>Flight Reservations</h2>
    <ul>
        <li><a href="customerPages/viewResFlights.jsp">Manage reservations</a></li>
    </ul>
    
    <h2>Customer Support</h2>
    <ul>
        <li><a href="customerPages/questionForumCustomer.jsp">Browse and send questions to customer representatives</a></li>
    </ul>