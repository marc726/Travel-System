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


@WebServlet("/adminpages/SalesReportServlet")
public class SalesReportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));
        RevenueDAO revenueDAO = new RevenueDAO();

        float totalSales = revenueDAO.getSalesReportForMonth(year, month);
        request.setAttribute("totalSales", totalSales);
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.getRequestDispatcher("/adminpages/salesReports.jsp").forward(request, response);
    }
}
