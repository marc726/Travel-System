<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Aircraft</title>
</head>
<body>
    <h1>Manage Aircraft</h1>

    <h2>Add Aircraft</h2>
    <form action="AircraftManagementServlet" method="post">
        <input type="hidden" name="action" value="add">
        
        <label for="alid">Airline ID:</label>
        <input type="text" id="alid" name="alid" required><br><br>
        
        <label for="aircraftNumber">Aircraft Number:</label>
        <input type="number" id="aircraftNumber" name="aircraftNumber" required><br><br>
        
        <label for="operationDays">Operation Days:</label>
        <input type="text" id="operationDays" name="operationDays" required><br><br>
        
        <label for="numberOfSeats">Number of Seats:</label>
        <input type="number" id="numberOfSeats" name="numberOfSeats" required><br><br>
        
        <input type="submit" value="Add Aircraft">
    </form>
    

    <h2>Delete Aircraft</h2>
    <form action="AircraftManagementServlet" method="post">
        <input type="hidden" name="action" value="delete">
        <label for="aircraftNumber">Aircraft Number:</label>
        <input type="number" id="aircraftNumber" name="aircraftNumber" required>
        <input type="submit" value="Delete Aircraft">
    </form>
</body>
</html>
