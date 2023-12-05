package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.FlightDAO;
import dao.ReservationDAO;
import model.Flight;
import model.User;

@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FlightDAO flightDAO;
    private ReservationDAO reservationDAO;

    public void init() {
        flightDAO = new FlightDAO();
        reservationDAO = new ReservationDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "reserveFlight":
                reserveFlight(request, response);
                break;
            default:
                response.sendRedirect("customerPages/browseFlightsCustomer.jsp");
                break;
        }
    }

    private void reserveFlight(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String flightIdStr = request.getParameter("flightId");
        int flightId = Integer.parseInt(flightIdStr);

        // Assuming you have a way to retrieve the currently logged in user
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        // Check if the flight is available and can be reserved
        Flight flight = flightDAO.getFlightById(flightId);
        if (flight != null) {
            int ticketAvailable = flightDAO.getTicketAvailable(flightId);
            if (ticketAvailable > 0) {
                // Reserve the flight for the logged-in user
                boolean success = reservationDAO.reserveFlight(loggedInUser, flight);
                if (success) {
                    // Update the available tickets count
                    flightDAO.updateTicketAvailable(flightId, ticketAvailable - 1);
                    response.sendRedirect("customerPages/successPage.jsp");
                    return;
                }
            }
        }
    }
}
