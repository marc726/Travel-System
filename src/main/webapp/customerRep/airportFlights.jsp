<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="dao.FlightDAO"%>
<%@ page import="model.Flight"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.util.ArrayList"%>
<%
    String username = (session != null) ? (String) session.getAttribute("username") : null;
%>
<!DOCTYPE html>
<html>
<head>
    <title>View Waiting List for Flight</title>
</head>
<body>
    <script>
    	function validateFunction() {
            var flightId = document.getElementById('flightId').value;

            if (flightId.trim() === '' || isNaN(flightId)) {
                alert('Flight ID is either missing or not a number.');
                return false; // Block form submission
            }
    	}
    </script>
    <!-- Flight Reservation Form -->
    <form action="<%=request.getContextPath()%>/FlightServlet" method="post" onsubmit="return validateFunction();">
        <input type="hidden" name="action" value="getFlightsByAirline">
        <h2>View Waiting List for Flight</h2>
        <div>
            <label for="airportId">Airport ID:</label>
            <input type="text" id="airportId" name="airportId">
        </div>
        <input type="submit" value="Search">
    </form>
    <% List<Flight> flights = (List<Flight>) session.getAttribute("flights");
    if (flights != null) { %>
    <table border="1">
        <thead>
            <tr>
                <th>Flight#</th>
                <th>Airline ID</th>
                <th>Departure Date</th>
                <th>Departure Time</th>
                <th>Arrival Date</th>
                <th>Arrival Time</th>
                <th>Departing From</th>
                <th>Arriving At</th>
                <th>Aircraft#</th>
                <th>Round-Trip</th>
                <th># of Stops</th>
                <th>Domestic</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <% for (Flight flight : flights) { 
                String roundTrip;
                if (flight.roundTrip == 0) {
                    roundTrip = "no";
                } else {
                    roundTrip = String.valueOf(flight.roundTrip);
                } %>
                <tr>
                    <td><%= flight.flightNumber %></td>
                    <td><%= flight.ALID %></td>
                    <td><%= flight.departureDate %></td>
                    <td><%= flight.departureTime %></td>
                    <td><%= flight.arrivalDate %></td>
                    <td><%= flight.arrivalTime %></td>
                    <td><%= flight.departureAirport %></td>
                    <td><%= flight.destinationAirport %></td>
                    <td><%= flight.aircraftNumber %></td>
                    <td><%= roundTrip %></td>
                    <td><%= flight.stops %></td>
                    <td><%= flight.isDomestic %></td>
                    <td><%= flight.price %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
<% } %>
</body>
</html>
