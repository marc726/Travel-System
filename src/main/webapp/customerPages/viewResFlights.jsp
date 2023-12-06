<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Flight"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Reserved Flights</title>
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
    <h1>Reserved Flights</h1>

        <!-- Refresh Button -->
        <form action="viewReservedFlights" method="get">
            <input type="submit" value="Refresh Flights">
        </form>

        <!-- Delete Flight Form -->
        <form action="viewReservedFlights" method="post">
            <label for="flightNumberToDelete">Flight Number to Delete:</label>
            <input type="number" id="flightNumberToDelete" name="flightNumber" required>
            <input type="submit" value="Delete Flight">
        </form>


    <%
        ArrayList<Flight> flights = (ArrayList<Flight>) request.getAttribute("reservedFlights");
        if (flights != null && !flights.isEmpty()) {
    %>
        <table>
            <thead>
                <tr>
                    <th>Flight Number</th>
                    <th>Airline ID</th>
                    <th>Aircraft Number</th>
                    <th>Price</th>
                    <th>Is Domestic</th>
                    <th>Round Trip</th>
                    <th>Stops</th>
                    <th>Departure Airport</th>
                    <th>Destination Airport</th>
                    <th>Departure Time</th>
                    <th>Departure Date</th>
                    <th>Arrival Time</th>
                    <th>Arrival Date</th>
                </tr>
            </thead>
            <tbody>
                <% for (Flight flight : flights) { %>
                    <tr>
                        <td><%= flight.getFlightNumber() %></td>
                        <td><%= flight.getALID() %></td>
                        <td><%= flight.getAircraftNumber() %></td>
                        <td><%= flight.getPrice() %></td>
                        <td><%= flight.getIsDomestic() ? "Yes" : "No" %></td>
                        <td><%= flight.getRoundTrip() %></td>
                        <td><%= flight.getStops() %></td>
                        <td><%= flight.getDepartureAirport() %></td>
                        <td><%= flight.getDestinationAirport() %></td>
                        <td><%= flight.getDepartureTime() %></td>
                        <td><%= flight.getDepartureDate() %></td>
                        <td><%= flight.getArrivalTime() %></td>
                        <td><%= flight.getArrivalDate() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>No flights reserved.</p>
    <% } %>
</body>
</html>
