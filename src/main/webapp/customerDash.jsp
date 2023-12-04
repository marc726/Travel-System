<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
</head>
<body>
    <h1>Welcome to the Customer Dashboard</h1>
    
    <h2>Flight Search</h2>
    <ul>
        <li><a href="/search/one-way">One-Way on a specific date</a></li>
        <li><a href="/search/round-trip">Round-Trip on specific dates</a></li>
        <li><a href="/search/flexible-dates">One-Way/ Round-Trip on flexible dates (+/- 3 days)</a></li>
    </ul>
    
    <h2>Browse Flights</h2>
    <ul>
        <li><a href="/flights">Browse all flights</a></li>
        <li><a href="/flights/sort/price">Sort flights by price</a></li>
        <li><a href="/flights/sort/take-off-time">Sort flights by take-off time</a></li>
        <li><a href="/flights/sort/landing-time">Sort flights by landing time</a></li>
        <li><a href="/flights/sort/duration">Sort flights by duration</a></li>
        <li><a href="/flights/filter/price">Filter flights by price</a></li>
        <li><a href="/flights/filter/stops">Filter flights by number of stops</a></li>
        <li><a href="/flights/filter/airline">Filter flights by airline</a></li>
        <li><a href="/flights/filter/take-off-time">Filter flights by take-off time</a></li>
        <li><a href="/flights/filter/landing-time">Filter flights by landing time</a></li>
    </ul>
    
    <h2>Flight Reservations</h2>
    <ul>
        <li><a href="/reservations">View all reservations</a></li>
        <li><a href="/reservations/cancel">Cancel a reservation</a></li>
        <li><a href="/reservations/waiting-list">Enter the waiting list</a></li>
        <li><a href="/reservations/upcoming">View upcoming reservations</a></li>
    </ul>
    
    <h2>Customer Support</h2>
    <ul>
        <li><a href="customerPages/questionForumCustomer.jsp">Browse and send questions to customer representatives</a></li>
    </ul>
</body>
</html>
