package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AirportDAO;
import model.Airport;

@WebServlet("/customerRep/AirportManagementServlet")
public class AirportManagementServlet extends HttpServlet {
    private AirportDAO airportDAO;

    public void init() {
        airportDAO = new AirportDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                Airport airport = new Airport(
                    request.getParameter("arid"),
                    request.getParameter("name"),
                    request.getParameter("location")
                );
                airportDAO.insertAirport(airport);
                // Redirect or forward to a success page
            } else if ("delete".equals(action)) {
                String arid = request.getParameter("arid");
                airportDAO.deleteAirport(arid);
                // Redirect or forward to a success page
            }

            request.getRequestDispatcher("/customerRep/manageAirports.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
