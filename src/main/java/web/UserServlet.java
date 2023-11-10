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
				case "/signup":
					signup(request, response);
					break;
				case "/login":
					login(request, response);
					break;
				case "/logout":
					logout(request, response); // Add this case for logout
					break;
				default:
					// If no action matches, you can set a default action
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
	private void signup(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
//        Map<String, String> map = parseFormData(request.getReader());//takes the reader of the request and puts it into a map
		String username = request.getParameter("username");                       //so we can use it like map.get("username"), etc.
		String password = request.getParameter("password");
	    if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
	        // Handle the case where either username or password is null or empty
	        System.out.println("username or password is missing");
	        return;
	    }
		User newUser = new User(username, password);
		if (userDAO.insertUser(newUser)) {
			System.out.println("successfully signed up");
			response.sendRedirect("loggedin.jsp");
		} else {
			response.sendRedirect("signup.jsp?error=true");
		}
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	    System.out.println("login called");
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    User user = new User(username, password);

	    if (userDAO.userExists(user)) {
	        // If user exists, set the username attribute in the session
	        request.getSession().setAttribute("username", username);
	        // Redirect to loggedin.jsp
	        response.sendRedirect("loggedin.jsp");
	    } else {
	        // If user does not exist, redirect back to login page with error message
	        response.sendRedirect("login.jsp?error=true");
	    }
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		response.sendRedirect("login.jsp");
	}

}
