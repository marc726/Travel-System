package web;

import dao.QuestionsDAO;
import model.Question;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/customerPages/SearchQuestionsServlet")
public class SearchQuestionsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        QuestionsDAO dao = new QuestionsDAO();
        List<Question> questions = dao.searchQuestions(keyword);

        request.setAttribute("questions", questions);
        request.getRequestDispatcher("/customerPages/searchQuestions.jsp").forward(request, response);
    }
}