<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Ticket"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reservation List</title>
</head>
<body>
    <h1>Search Reservations</h1>
    <form action="ReservationListServlet" method="post">
        <input type="radio" id="flightNumber" name="searchType" value="flightNumber">
        <label for="flightNumber">Flight Number:</label>
        <input type="number" name="flightNumber"><br><br>

        <input type="radio" id="customerName" name="searchType" value="customerName">
        <label for="customerName">Customer Name:</label>
        <input type="text" name="customerName"><br><br>

        <input type="submit" value="Search">
    </form>

    <h2>Reservation Results</h2>
    <%
        List<Ticket> tickets = (List<Ticket>) request.getAttribute("tickets");
        if (tickets != null && !tickets.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>Ticket Number</th>
                <th>Flight Number</th>
                <th>Username</th>
                <th>Class Type</th>
                <th>Fare</th>
                <th>Purchase Date</th>
                <!-- Add more columns as needed -->
            </tr>
            <% for (Ticket ticket : tickets) { %>
                <tr>
                    <td><%= ticket.getTicket_num() %></td>
                    <td><%= ticket.getFlight_number() %></td>
                    <td><%= ticket.getUsername() %></td>
                    <td><%= ticket.getClass_type() %></td>
                    <td><%= ticket.getFare() %></td>
                    <td><%= ticket.getPurchase_date() %></td>
                    <!-- Add more data as needed -->
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No reservations found.</p>
    <% } %>
</body>
</html>