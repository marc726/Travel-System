<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Flights</title>
</head>
<body>
    <h1>Manage Flights</h1>

    <!-- Form for Adding New Flight -->
    <h2>Add New Flight</h2>
    <form action="FlightManagementServlet" method="post">
        <input type="hidden" name="action" value="add">
        
        <label for="flightNumber">Flight Number: (Required)</label>
        <input type="number" id="flightNumber" name="flightNumber" required><br><br>
        
        <label for="alid">Airline ID: (Required)</label>
        <input type="text" id="alid" name="alid" required><br><br>
        
        <label for="aircraftNumber">Aircraft Number: (Required)</label>
        <input type="number" id="aircraftNumber" name="aircraftNumber" required><br><br>
        
        <label for="price">Price: (Required)</label>
        <input type="text" id="price" name="price" required><br><br>
        
        <label for="isDomestic">Is Domestic: (Required)</label>
        <select id="isDomestic" name="isDomestic">
            <option value="true">Yes</option>
            <option value="false">No</option>
        </select><br><br>

        <label for="roundTrip">Round Trip (Flight Number): (Required)</label>
        <input type="number" id="roundTrip" name="roundTrip"><br><br>

        <label for="stops">Number of Stops (Flight Number): (Required)</label>
        <input type="number" id="stops" name="stops"><br><br>

        <label for="departureAirport">Departure Airport: (Required)</label>
        <input type="text" id="departureAirport" name="departureAirport" required><br><br>

        <label for="destinationAirport">Destination Airport: (Required)</label>
        <input type="text" id="destinationAirport" name="destinationAirport" required><br><br>

        <label for="departureTime">Departure Time: (Required)</label>
        <input type="time" id="departureTime" name="departureTime" required><br><br>

        <label for="departureDate">Departure Date: (Required)</label>
        <input type="date" id="departureDate" name="departureDate" required><br><br>

        <label for="arrivalTime">Arrival Time: (Required)</label>
        <input type="time" id="arrivalTime" name="arrivalTime" required><br><br>

        <label for="arrivalDate">Arrival Date: (Required)</label>
        <input type="date" id="arrivalDate" name="arrivalDate" required><br><br>

        <input type="submit" value="Add Flight">
    </form>

    <!-- Form for Deleting Existing Flight -->
    <h2>Delete Flight</h2>
    <form action="FlightManagementServlet" method="post">
        <input type="hidden" name="action" value="delete">
        
        <label for="flightNumberDel">Flight Number:</label>
        <input type="number" id="flightNumberDel" name="flightNumber" required><br><br>
        
        <input type="submit" value="Delete Flight">
    </form>
</body>
</html>
