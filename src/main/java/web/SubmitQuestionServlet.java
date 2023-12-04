package web;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionsDAO;

@WebServlet("/customerPages/SubmitQuestionServlet")
public class SubmitQuestionServlet extends HttpServlet {
    private QuestionsDAO QuestionsDAO;

    public void init() {
        QuestionsDAO = new QuestionsDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String questionText = request.getParameter("questionText");

        try {
            boolean success = QuestionsDAO.submitQuestion(userId, questionText);
            if (success) {
                request.setAttribute("message", "Question submitted successfully.");
            } else {
                request.setAttribute("message", "Error submitting question.");
            }
            request.getRequestDispatcher("/customerPages/questionForumCustomer.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage());
        }
    }
}
