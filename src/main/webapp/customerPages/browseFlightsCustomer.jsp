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
    <h1>Welcome, <%= username %></h1>
    <script>
        function validateForm() {
            var selectedDate = document.getElementById('datepicker').value;
            var lowerPrice = document.getElementById('lowerprice').value;
            var higherPrice = document.getElementById('higherprice').value;
            var lowerStops = document.getElementById('lowerstops').value;
            var higherStops = document.getElementById('higherstops').value;
            var filterByAirline = document.getElementById('checkboxairline').checked;
            var airline = document.getElementById('airline').value;
            var selectReturnDateEnabled = document.getElementById('checkbox').checked;
            var selectReturnDate = document.getElementById('datepickerreturn').value;
            
            // Validate selectedDate is not empty
            if (selectedDate.trim() === '') {
                alert('Please select a date.');
                return false; // Block form submission
            }

            // Validate lowerStops and higherStops are numeric (excluding "any")
            if (isNaN(lowerStops) || (higherStops !== 'any' && isNaN(higherStops))) {
                alert('Number of stops must be numeric. Upper stop limit may be "any"');
                return false; // Block form submission
            }

            // Validate lowerPrice and higherPrice are numeric (excluding "any")
            if (lowerPrice !== 'any' && (isNaN(lowerPrice) || (higherPrice !== 'any' && isNaN(higherPrice)))) {
                alert('Price values must be numeric. Upper price limit may be "any"');
                return false; // Block form submission
            }

         // Validate lowerStops is smaller than or equal to higherStops (excluding "any")
            if (parseInt(lowerStops) > parseInt(higherStops) && higherStops !== 'any') {
                alert('Lower number of stops must be smaller than or equal to the higher number of stops.');
                return false; // Block form submission
            }

            // Validate lowerPrice is smaller than or equal to higherPrice (excluding "any")
            if (lowerPrice !== 'any' && (parseInt(lowerPrice) > parseInt(higherPrice) && higherPrice !== 'any')) {
                alert('Lower price must be smaller than or equal to the higher price.');
                return false; // Block form submission
            }
            
            // If filterByAirline is enabled, validate airline is exactly 2 characters
            if (filterByAirline && airline.length !== 2) {
                alert('Airline code must be exactly 2 characters.');
                return false; // Block form submission
            }

            // If selectReturnDateEnabled is enabled, validate selectReturnDate is not empty
            if (selectReturnDateEnabled && selectReturnDate.trim() === '') {
                alert('Please select a return date.');
                return false; // Block form submission
            }
            // If all validations pass, allow form submission
            return true;
        }
    </script>
    <form action="<%=request.getContextPath()%>/FlightServlet" method="get" onsubmit="return validateForm();">
        <input type="hidden" name="action" value="getFlights">
        <div>
            <label for="sortby">Sort By:</label>
             <select id="sortby" name="sortby">
                <option value="price">Price</option>
                <option value="takeoff">Take-off Time</option>
                <option value="landing">Landing Time</option>
                <option value="duration">Flight Duration</option>
            </select>
            
            <label for="datepicker">Select Date:</label>
            <input type="date" id="datepicker" name="selectedDate">
            <label for="checkbox">Search For Round Trips?</label>
            <input type="checkbox" id="checkbox" onchange="toggleInput()">
            <label for="datepickerreturn">Select Return Date:</label>
            <input type="date" id="datepickerreturn" name="selectedReturnDate" disabled>
            <script>
                function toggleInput() {
                    // Get the checkbox and input elements
                    var checkbox = document.getElementById('checkbox');
                    var inputToDisable = document.getElementById('datepickerreturn');
    
                    // Set the 'disabled' property based on the checkbox state
                    inputToDisable.disabled = !checkbox.checked;
                }
            </script>
            <label for="sortby">Flexibility:</label>
             <select id="flexibility" name="flexibility">
                <option value="0flex">None</option>
                <option value="1flex">+/- 1 Day</option>
                <option value="2flex">+/- 2 Day</option>
                <option value="3flex">+/- 3 Day</option>
            </select>
        </div>
        <label for="checkboxairline">Filter by Airline?</label>
        <input type="checkbox" id="checkboxairline" onchange="toggleInputAirline()">
        <label for="airline">Airline:</label>
        <input type="text" id="airline" name="airline" disabled>
        <script>
            function toggleInputAirline() {
                // Get the checkbox and input elements
                var checkbox1 = document.getElementById('checkboxairline');
                var inputToDisable1 = document.getElementById('airline');
    
                // Set the 'disabled' property based on the checkbox state
                inputToDisable1.disabled = !checkbox1.checked;
            }
        </script>
        <div>
            <div>
                <label for="lowerprice">Price Range:</label>
                <input type="text" id="lowerprice" name="lowerprice" value="0">
                <label for="higherprice">to</label>
                <input type="text" id="higherprice" name="higherprice" value="any">
            </div>   
            <div>
                <label for="lowerstops">Number of Stops:</label>
                <input type="text" id="lowerstops" name="lowerstops" value="0">
                <label for="higherstops">to</label>
                <input type="text" id="higherstops" name="higherstops" value="any">
            </div> 
            <div>
                <label for="earlytakeoff">Take-off Time:</label>
                <input type="time" id="earlytakeoff" name="earlytakeoff" value="00:00">
                <label for="latetakeoff">to</label>
                <input type="time" id="latetakeoff" name="latetakeoff" value="23:59">
            </div>
            <div>
                <label for="earlylanding">Landing Time:</label>
                <input type="time" id="earlylanding" name="earlylanding" value="00:00">
                <label for="latelanding">to</label>
                <input type="time" id="latelanding" name="latelanding" value="23:59">
            </div>
        </div>
        <input type="submit" value="Search Flights">
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
    <% } else { %>
    <div>Search for Flights Above</div>
    <% } %>

    <!-- Flight Reservation Form -->
    <form action="<%=request.getContextPath()%>/ReservationServlet" method="post">
        <input type="hidden" name="action" value="reserveFlight">
        <div>
            <label for="flightId">Enter Flight ID to Reserve:</label>
            <input type="text" id="flightId" name="flightId">
        </div>
        <input type="submit" value="Reserve">
    </form>

</body>
</html>
