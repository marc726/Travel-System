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
                break;
        }
    }

    private void reserveFlight(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int flight_number = Integer.parseInt(request.getParameter("flightId"));
        String classType = request.getParameter("classType");
        String username = request.getParameter("username");
        System.out.println("username param" + username);
        if (username != null) {   
            //Check if flight has available tickets
            System.out.println("book ticket status: " + flightDAO.bookTicket(flight_number, username, classType));
            //
            response.sendRedirect("customerRep/makeFlightReservations.jsp");
            return;
        	
        }    
        username = request.getSession().getAttribute("username").toString();   
        //Check if flight has available tickets
        System.out.println("book ticket status: " + flightDAO.bookTicket(flight_number, username, classType));
        //
        response.sendRedirect("customerPages/browseFlightsCustomer.jsp");
    }
}
