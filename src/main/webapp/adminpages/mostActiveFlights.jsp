<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="model.Flight" %>
<!DOCTYPE html>
<html>
<head>
    <title>Most Active Flights</title>
</head>
<body>
    <h1>Most Active Flights</h1>

        <!-- Refresh Button -->
        <form action="MostActiveFlightsServlet" method="get">
            <input type="submit" value="Get Flights">
        </form>

    <%
        Map<Flight, Integer> mostActiveFlights = (Map<Flight, Integer>) request.getAttribute("mostActiveFlights");
        if (mostActiveFlights != null && !mostActiveFlights.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>Flight Number</th>
                <th>Airline ID</th>
                <th>Departure Airport</th>
                <th>Destination Airport</th>
                <th>Departure Date</th>
                <th>Departure Time</th>
                <th>Tickets Sold</th>
            </tr>
            <% for (Entry<Flight, Integer> entry : mostActiveFlights.entrySet()) {
                   Flight flight = entry.getKey();
                   Integer ticketsSold = entry.getValue();
            %>
                <tr>
                    <td><%= flight.getFlightNumber() %></td>
                    <td><%= flight.getALID() %></td>
                    <td><%= flight.getDepartureAirport() %></td>
                    <td><%= flight.getDestinationAirport() %></td>
                    <td><%= flight.getDepartureDate() %></td>
                    <td><%= flight.getDepartureTime() %></td>
                    <td><%= ticketsSold %></td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No active flights found.</p>
    <% } %>
</body>
</html>
