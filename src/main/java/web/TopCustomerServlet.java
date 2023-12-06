package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.TicketDAO;


@WebServlet("/adminpages/TopCustomerServlet")
public class TopCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TicketDAO ticketDAO = new TicketDAO();
        String[] topCustomerData = ticketDAO.getTopRevenueCustomer();

        request.setAttribute("username", topCustomerData[0]);
        request.setAttribute("totalRevenue", topCustomerData[1]);
        request.getRequestDispatcher("/adminpages/topCustomer.jsp").forward(request, response);
    }
}
