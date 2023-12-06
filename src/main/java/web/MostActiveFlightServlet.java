package web;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;
import dao.FlightDAO;
import model.Flight;
import model.User;

@WebServlet("/adminpages/MostActiveFlightsServlet")
public class MostActiveFlightServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FlightDAO flightDAO = new FlightDAO();
        Map<Flight, Integer> mostActiveFlights = flightDAO.getMostActiveFlights();

        request.setAttribute("mostActiveFlights", mostActiveFlights);
        request.getRequestDispatcher("/adminpages/mostActiveFlights.jsp").forward(request, response);
    }
}

