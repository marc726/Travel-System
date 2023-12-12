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
    <title>Search Flights</title>
</head>
<body>
    <h1>Create Flight Reservations for Users</h1>
    <script>
    	function validateBookTicket() {
            var classType = document.getElementById('classType').value;
            var flightId = document.getElementById('flightId').value;
            var username = document.getElementById('username').value;
            
            if (username.trim() === '') {
                alert('Username is missing.');
                return false; // Block form submission
            }
            if (flightId.trim() === '' || isNaN(flightId)) {
                alert('Flight ID is either missing or not a number.');
                return false; // Block form submission
            }

            if (classType.trim() === '') {
                alert('Class type is missing.');
                return false; // Block form submission
            }
    	}
    </script>
    <!-- Flight Reservation Form -->
    <form action="<%=request.getContextPath()%>/ReservationServlet" method="post" onsubmit="return validateBookTicket();">
        <input type="hidden" name="action" value="reserveFlight">
        <h2>Reserve Flight Tickets</h2>
        <div>
           	<label for="username">Username:</label>
            <input type="text" id="username" name="username">
            <label for="flightId">Flight ID:</label>
            <input type="text" id="flightId" name="flightId">
            <label for="classType">Class Type:</label>
            <select id="classType" name="classType">
                <option value="economy">Economy</option>
                <option value="firstclass">First-Class</option>
                <option value="business">Business</option>
            </select>
        </div>
        <input type="submit" value="Reserve">
    </form>

</body>
</html>
