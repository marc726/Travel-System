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
import dao.CustomerRepDAO;
import model.User;

@WebServlet("/adminpages/CustomerRepServlet")
public class CustomerRepServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerRepDAO CustomerRepDAO;

    public void init() {
        CustomerRepDAO = new CustomerRepDAO(); // Initialize the DAO
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CustomerRepServlet doPost called");
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                addCustomerRep(request, response);
            } else if ("edit".equals(action)) {
                editCustomerRep(request, response);
            } else if ("delete".equals(action)) {
                deleteCustomerRep(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage());
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("CustomerRepServlet doGet called");
            List<User> customerReps = CustomerRepDAO.getAllCustomerReps();
            request.setAttribute("customerReps", customerReps);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adminpages/customerRepManager.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage());
        }
    }

    private void addCustomerRep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password"); 
        String name = request.getParameter("name");
    
        User newUser = new User(username, password, name, 1); // Role 1 for customer rep
        try {
            boolean isAdded = CustomerRepDAO.insertCustomerRep(newUser);
            if (isAdded) {
                request.setAttribute("message", "User was successfully added.");
            } else {
                request.setAttribute("message", "Error adding user.");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/adminpages/customerRepManager.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("message", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/adminpages/customerRepManager.jsp").forward(request, response);
        }
    }
    
    private void editCustomerRep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");
        String newName = request.getParameter("newName");
    
        User user = new User(username, newPassword, newName, 1); // Role 1 for customer rep
        try {
            boolean isUpdated = CustomerRepDAO.updateCustomerRep(user);
            if (isUpdated) {
                request.setAttribute("message", "User was successfully updated.");
            } else {
                request.setAttribute("message", "Error updating user.");
            }
            response.sendRedirect(request.getContextPath() + "/adminpages/customerRepManager.jsp");
        } catch (SQLException e) {
            request.setAttribute("message", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/adminpages/customerRepManager.jsp").forward(request, response);
        }
    }

    private void deleteCustomerRep(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String username = request.getParameter("delUsername");
        CustomerRepDAO.deleteCustomerRep(username);

        response.sendRedirect("customerRepManager.jsp"); // Redirect back to the management page
    }
}
