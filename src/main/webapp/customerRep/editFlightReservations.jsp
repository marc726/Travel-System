<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Ticket"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Flights for Customer</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
	<a href="/Travel-System/customerRepDash.jsp">Back to Dashboard</a>
    <h1>Edit Tickets for User</h1>
        <form action="<%=request.getContextPath()%>/customerPages/viewReservedFlights" method="get">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <input type="submit" value="Get Tickets">
        </form>
        <form action="<%=request.getContextPath()%>/customerPages/viewReservedFlights" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <label for="ticketNum">Ticket Number to Delete:</label>
            <input type="number" id="ticketNum" name="ticketNum" required>
            <input type="submit" value="Delete Ticket">
        </form>

    <%
        ArrayList<Ticket> tickets = (ArrayList<Ticket>) session.getAttribute("reservedTickets");
        if (tickets != null && !tickets.isEmpty()) {
    %>
        <table>
            <thead>
                <tr>
                    <th>Ticket Number</th>
                    <th>Flight Number</th>
                    <th>Purchase Date</th>
                    <th>Seat Number</th>
                    <th>Fare</th>
                    <th>Booking Fee</th>
                    <th>Class Type</th>
                </tr>
            </thead>
            <tbody>
                <% for (Ticket ticket : tickets) { %>
                    <tr>
                        <td><%= ticket.getTicket_num() %></td>
                        <td><%= ticket.getFlight_number() %></td>
                        <td><%= ticket.getPurchase_date() %></td>
                        <td><%= ticket.getSeat_num() %></td>
                        <td><%= ticket.getFare() %></td>
                        <td><%= ticket.getBooking_fee() %></td>
                        <td><%= ticket.getClass_type() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>No Tickets reserved.</p>
    <% } %>
</body>
</html>
