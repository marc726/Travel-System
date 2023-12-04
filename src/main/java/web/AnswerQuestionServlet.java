package web;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionsDAO;

@WebServlet("/customerRep/AnswerQuestionServlet")
public class AnswerQuestionServlet extends HttpServlet {
    private QuestionsDAO questionsDAO;

    public void init() {
        questionsDAO = new QuestionsDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String answerText = request.getParameter("answerText");

        try {
        boolean success = questionsDAO.answerQuestion(questionId, answerText);
        response.sendRedirect("repViewQuestions.jsp");
        // Handle the response based on 'success'
        } catch (SQLException e) {
        throw new ServletException("Database error: " + e.getMessage());
        }
    }
}
