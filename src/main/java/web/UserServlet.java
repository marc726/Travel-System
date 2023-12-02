package web;

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

import dao.UserDAO;
import model.User;


@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	
	public void init() {
		userDAO = new UserDAO();
		System.out.println("UserServlet initialized");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost called");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet called");
		String action = request.getServletPath();
		try {
			switch (action) {
				case "/login":
					login(request, response);
					break;
				case "/logout":
					logout(request, response); // Add this case for logout
					break;
				default:
					// If no action matches, we can set a default action
					response.sendRedirect("login.jsp"); // or some default page
					break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
    public static Map<String, String> parseFormData(BufferedReader reader) {// not sure if this works with every request body
		System.out.println("parseFormData called");
        String requestBody = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(requestBody);
    	Map<String, String> formDataMap = new HashMap<>();
        String[] lines = requestBody.split("\n");
        String name = null;
        String value = null;
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("Content-Disposition:")) {
                name = line.substring(line.indexOf("name=\"") + 6, line.lastIndexOf("\""));
            } else if (line.startsWith("\"")) {
                value = line.replace("\"", "");
                formDataMap.put(name, value);
            }
        }
        return formDataMap;
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        System.out.println("login called");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

		// Check for admin credentials
		if ("admin".equals(username) && "admin".equals(password)) {
			// Set admin session attributes
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("role", -1); // Assuming -1 as a special indicator for admin role

			response.sendRedirect("adminDash.jsp"); // Redirect to admin dashboard
			return; // Important to prevent further processing
		}

        User user = userDAO.getUser(username);

        if (user != null && user.password.equals(password)) {
            // User exists and password matches
            HttpSession session = request.getSession();
            session.setAttribute("username", user.username);
            session.setAttribute("role", user.role);

            // Redirect based on role
            if (user.role == 1) {
                // Role 1 indicates a customer representative
                response.sendRedirect("customerRepDashboard.jsp"); // Redirect to customer representative dashboard
            } else {
                // regular users (role 0)
                response.sendRedirect("customerDash.jsp"); // Redirect to the logged in customer page
            }
        } else {
            // User does not exist or password does not match
            response.sendRedirect("login.jsp?error=true"); // Redirect back to login page with an error message
        }
    }

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("logout called");
        HttpSession session = request.getSession(false); // false means don't create a new session if one doesn't exist
        if (session != null) {
            session.invalidate(); // Invalidate the session, removing any session data
        }
        response.sendRedirect("login.jsp"); // Redirect to the login page
    }

}
