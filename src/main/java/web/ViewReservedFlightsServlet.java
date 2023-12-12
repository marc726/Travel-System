package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FlightDAO;
import dao.TicketDAO;
import model.Flight;
import model.Ticket;

@WebServlet("/customerPages/viewReservedFlights")
public class ViewReservedFlightsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        System.out.println("username is " + username);
        if (username == null) {
        	username = (String) session.getAttribute("username");
            FlightDAO flightDAO = new FlightDAO();
            ArrayList<Flight> flights = flightDAO.getReservedFlightsForUser(username);
            request.setAttribute("reservedFlights", flights);
            request.getRequestDispatcher("/customerPages/viewResFlights.jsp").forward(request, response);
            return;
        }
        TicketDAO ticketDAO = new TicketDAO();
        ArrayList<Ticket> tickets = new ArrayList<Ticket>(ticketDAO.getTicketsByUsername(username));
        tickets.forEach(ticket -> System.out.println(ticket.ticket_num));
        System.out.println(tickets.size());
        request.getSession().setAttribute("reservedTickets", tickets);
        response.sendRedirect("/Travel-System/customerRep/editFlightReservations.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flight_num = request.getParameter("flightNumber");
		String ticket_num = request.getParameter("ticketNum");
		int ticketNumber = 0;
		int flightNumber = 0;
		if (flight_num != null) {
			flightNumber = Integer.parseInt(flight_num);
		} else if (ticket_num != null) {
			ticketNumber = Integer.parseInt(ticket_num);
		}
    	
        
        
        // Logic to delete flight goes here
        FlightDAO flightDAO = new FlightDAO();
        String username = request.getParameter("username");
        try {
        	if (username == null) {
	            HttpSession session = request.getSession();
	            username = (String) session.getAttribute("username");
	            flightDAO.deleteReservedFlight(flightNumber, username); // Assuming username is available via getUserPrincipal
	            request.getRequestDispatcher("/customerPages/viewResFlights.jsp").forward(request, response);
	            return;
        	} 
        	TicketDAO ticketDAO = new TicketDAO();
        	ticketDAO.deleteTicket(ticketNumber);
        	System.out.println("deleted ticket");
            response.sendRedirect("/Travel-System/customerPages/viewReservedFlights?username=" + username);
        } catch (Exception e) {
            if (e instanceof SQLException) {
                throw new ServletException("Database error: " + e.getMessage());
            } else {
                throw e;
            }
        }
    }

}
