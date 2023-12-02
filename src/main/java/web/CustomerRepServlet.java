package web;

import dao.CustomerRepDAO;
import model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CustomerRepServlet")
public class CustomerRepServlet extends HttpServlet {

    private CustomerRepDAO CustomerRepDAO;

    public void init() {
        CustomerRepDAO = new CustomerRepDAO();
        // Other initialization code
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                // Code to handle adding a new customer representative
                break;
            case "edit":
                // Code to handle editing an existing customer representative
                break;
            case "delete":
                // Code to handle deleting a customer representative
                break;
            default:
                // Handle unknown action
                break;
        }
        // Redirect or forward to an appropriate page
    }

    // Additional methods if needed
}
