package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
        System.out.println("LoginServlet initialized");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginServlet doPost called");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // check for admin login
        if (username.equals("admin") && password.equals("admin")) {
            HttpSession session = request.getSession();
            session.setAttribute("username", "admin");
            response.sendRedirect("adminDash.jsp");
            return;
        }

        try {
            User user = userDAO.getUser(username);

            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("role", user.getRole());

                // Redirect based on role
                if (user.getRole() == 1) {
                    response.sendRedirect("customerRepDash.jsp");
                } else {
                    response.sendRedirect("customerDash.jsp");
                }
            } else {
                request.setAttribute("loginError", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.err.println("Error in LoginServlet: " + e.getMessage());
            throw new ServletException(e);
        }
    }
}
