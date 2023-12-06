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
import dao.TicketDAO;
import model.Flight;
import model.User;
import model.Ticket;
import java.util.ArrayList;


@WebServlet("/adminpages/ReservationListServlet")
public class ReservationListServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchType = request.getParameter("searchType");
        TicketDAO ticketDAO = new TicketDAO();
        List<Ticket> tickets = new ArrayList<>();

        if ("flightNumber".equals(searchType)) {
            int flightNumber = Integer.parseInt(request.getParameter("flightNumber"));
            tickets = ticketDAO.getTicketsByFlightNumber(flightNumber);
        } else if ("customerName".equals(searchType)) {
            String customerName = request.getParameter("customerName");
            tickets = ticketDAO.getTicketsByCustomerName(customerName);
        }

        request.setAttribute("tickets", tickets);
        request.getRequestDispatcher("/adminpages/viewReservations.jsp").forward(request, response);
    }
}
