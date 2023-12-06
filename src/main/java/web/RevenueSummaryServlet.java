package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RevenueDAO;
import model.Question;


@WebServlet("/adminpages/RevenueSummaryServlet")
public class RevenueSummaryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        RevenueDAO revenueDAO = new RevenueDAO();
        float revenue = 0;

        switch (type) {
            case "flight":
                int flightNumber = Integer.parseInt(request.getParameter("identifier"));
                revenue = revenueDAO.getRevenueByFlight(flightNumber);
                break;
            case "airline":
                String airlineId = request.getParameter("identifier");
                revenue = revenueDAO.getRevenueByAirline(airlineId);
                break;
            case "customer":
                String username = request.getParameter("identifier");
                System.out.println(username);
                revenue = revenueDAO.getRevenueByCustomer(username);
                break;
        }

        request.setAttribute("revenue", revenue);
        request.getRequestDispatcher("/adminpages/revenueViewer.jsp").forward(request, response);
    }
}
