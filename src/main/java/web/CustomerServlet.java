package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;
import model.User;

@WebServlet("/adminpages/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO CustomerDAO;

    public void init() {
        CustomerDAO = new CustomerDAO(); // Initialize the CustomerDAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            System.out.println("CustomerServlet doGet called");
            List<User> customers = CustomerDAO.getAllCustomers(); // Method to fetch all customers
            request.setAttribute("customers", customers);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adminpages/customerManager.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    addCustomer(request, response);
                    break;
                case "edit":
                    editCustomer(request, response);
                    break;
                case "delete":
                    deleteCustomer(request, response);
                    break;
                default:
                    response.sendRedirect("customerManager.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage());
        }
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        int role = 0; // Assuming role 0 for regular customers

        User newUser = new User(username, password, name, role);
        boolean isAdded = CustomerDAO.insertUser(newUser);

        if (isAdded) {
            request.setAttribute("message", "Customer added successfully.");
        } else {
            request.setAttribute("message", "Error adding customer.");
        }

        request.getRequestDispatcher("customerManager.jsp").forward(request, response);
    }

    private void editCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("newPassword"); // Assuming password can be changed
        String name = request.getParameter("newName"); // Assuming name can be changed
        int role = 0; // Assuming role 0 for regular customers

        User user = new User(username, password, name, role);
        boolean isUpdated = CustomerDAO.updateUser(user);

        if (isUpdated) {
            request.setAttribute("message", "Customer updated successfully.");
        } else {
            request.setAttribute("message", "Error updating customer.");
        }

        request.getRequestDispatcher("customerManager.jsp").forward(request, response);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String username = request.getParameter("delUsername");

        boolean isDeleted = CustomerDAO.deleteUser(username);

        if (isDeleted) {
            request.setAttribute("message", "Customer deleted successfully.");
        } else {
            request.setAttribute("message", "Error deleting customer.");
        }

        request.getRequestDispatcher("customerManager.jsp").forward(request, response);
    }
}
