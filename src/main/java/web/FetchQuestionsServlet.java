package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionsDAO;
import model.Question;

@WebServlet("/customerPages/FetchQuestionsServlet")
public class FetchQuestionsServlet extends HttpServlet {
    private QuestionsDAO questionsDAO;

    public void init() {
        questionsDAO = new QuestionsDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<Question> allQuestions = questionsDAO.getAllQuestions();
            request.setAttribute("allQuestions", allQuestions);
            request.getRequestDispatcher("/customerPages/questionForumCustomer.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage());
        }
    }
}
