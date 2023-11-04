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

import dao.UserDAO;
import model.User;


@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	
	public void init() {
		userDAO = new UserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/insert":
				insertUser(request, response);
				break;
			default:
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
    public static Map<String, String> parseFormData(BufferedReader reader) {// not sure if this works with every request body
        String requestBody = reader.lines().collect(Collectors.joining(System.lineSeparator()));
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
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Map<String, String> map = parseFormData(request.getReader());//takes the reader of the request and puts it into a map
		String username = map.get("username");                       //so we can use it like map.get("username"), etc.
		String password = map.get("password");
		User newUser = new User(username, password);
		userDAO.insertUser(newUser);
		response.sendRedirect("list");//not sure what this is for yet
	}
}