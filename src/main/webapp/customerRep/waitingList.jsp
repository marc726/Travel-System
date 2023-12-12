<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String username = (session != null) ? (String) session.getAttribute("username") : null;
%>
<!DOCTYPE html>
<html>
<head>
    <title>View Waiting List for Flight</title>
</head>
<body>
	<a href="/Travel-System/customerRepDash.jsp">Back to Dashboard</a>
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
        <input type="hidden" name="action" value="getWaitingList">
        <h2>View Waiting List for Flight</h2>
        <div>
            <label for="flightId">Flight ID:</label>
            <input type="text" id="flightId" name="flightId">
        </div>
        <input type="submit" value="Search">
    </form>
    <h3>List of users will appear below after search</h3>
    <c:forEach var="item" items="${waitingList}">
	    <p>${item}</p>
	</c:forEach>

</body>
</html>
