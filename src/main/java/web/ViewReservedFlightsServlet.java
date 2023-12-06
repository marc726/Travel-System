package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FlightDAO;
import model.Flight;

@WebServlet("/customerPages/viewReservedFlights")
public class ViewReservedFlightsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        FlightDAO flightDAO = new FlightDAO();
        ArrayList<Flight> flights = flightDAO.getReservedFlightsForUser(username);
        System.out.println(username);
        
        request.setAttribute("reservedFlights", flights);
        request.getRequestDispatcher("/customerPages/viewResFlights.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flightNumber = Integer.parseInt(request.getParameter("flightNumber"));
        // Logic to delete flight goes here
        FlightDAO flightDAO = new FlightDAO();

        try {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            flightDAO.deleteReservedFlight(flightNumber, username); // Assuming username is available via getUserPrincipal
            request.getRequestDispatcher("/customerPages/viewResFlights.jsp").forward(request, response);
        } catch (Exception e) {
            if (e instanceof SQLException) {
                throw new ServletException("Database error: " + e.getMessage());
            } else {
                throw e;
            }
        }
    }

}
