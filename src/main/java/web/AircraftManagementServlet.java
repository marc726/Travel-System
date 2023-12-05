package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AircraftDAO;
import model.Aircraft;

@WebServlet("/customerRep/AircraftManagementServlet")
public class AircraftManagementServlet extends HttpServlet {
    private AircraftDAO aircraftDAO;

    public void init() {
        aircraftDAO = new AircraftDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                // Logic to add aircraft
                Aircraft aircraft = new Aircraft(
                    request.getParameter("alid"),
                    Integer.parseInt(request.getParameter("aircraftNumber")),
                    Integer.parseInt(request.getParameter("operationDays")),
                    Integer.parseInt(request.getParameter("numberOfSeats")));
                aircraftDAO.insertAircraft(aircraft);
            } else if ("delete".equals(action)) {
                // Logic to delete aircraft
                int aircraftNumber = Integer.parseInt(request.getParameter("aircraftNumber"));
                aircraftDAO.deleteAircraft(aircraftNumber);
                
            }
            request.getRequestDispatcher("/customerRep/manageAircrafts.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
