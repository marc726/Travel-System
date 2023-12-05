package web;

import java.io.IOException;
import java.sql.Time;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FlightDAO;
import model.Flight;

@WebServlet("/customerRep/FlightManagementServlet")
public class FlightManagementServlet extends HttpServlet {
    private FlightDAO flightDAO;

    public void init() {
        flightDAO = new FlightDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                Time departureTime = parseTime(request.getParameter("departureTime"));
                Time arrivalTime = parseTime(request.getParameter("arrivalTime"));
                Date departureDate = parseDate(request.getParameter("departureDate"));
                Date arrivalDate = parseDate(request.getParameter("arrivalDate"));

                if (departureTime == null || arrivalTime == null || departureDate == null || arrivalDate == null) {
                    request.setAttribute("errorMessage", "Invalid date/time format");
                    request.getRequestDispatcher("/customerRep/manageFlights.jsp").forward(request, response);
                    return; // Early return if any date/time is invalid
                }
    
                Flight flight = new Flight(
                    Integer.parseInt(request.getParameter("flightNumber")),
                    request.getParameter("alid"),
                    Integer.parseInt(request.getParameter("aircraftNumber")),
                    Float.parseFloat(request.getParameter("price")),
                    Boolean.parseBoolean(request.getParameter("isDomestic")),
                    Integer.parseInt(request.getParameter("roundTrip")),
                    Integer.parseInt(request.getParameter("stops")),
                    request.getParameter("departureAirport"),
                    request.getParameter("destinationAirport"),
                    departureTime,
                    arrivalTime,
                    departureDate,
                    arrivalDate
                );
                flightDAO.insertFlight(flight);
        } else if ("delete".equals(action)) {
            int flightNumber = Integer.parseInt(request.getParameter("flightNumber"));
            flightDAO.deleteFlight(flightNumber);
        }
        request.getRequestDispatcher("/customerRep/manageFlights.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error processing the request: " + e.getMessage(), e);
        }
    }

    private Time parseTime(String timeStr) {
        try {
            return Time.valueOf(timeStr + ":00"); // Appends seconds if missing
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    private Date parseDate(String dateStr) {
        try {
            return Date.valueOf(dateStr);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
}
