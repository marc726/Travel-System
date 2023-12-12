<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Question"%>
<!DOCTYPE html>
<html>
<head>
    <title>Search Questions</title>
</head>
<body>
    <h1>Search Questions</h1>
    <form action="SearchQuestionsServlet" method="post">
        <label for="keyword">Enter keyword:</label>
        <input type="text" id="keyword" name="keyword">
        <input type="submit" value="Search">
    </form>

    <h2>Search Results</h2>
    <%
        List<Question> questions = (List<Question>) request.getAttribute("questions");
        if (questions != null && !questions.isEmpty()) {
            for (Question question : questions) {
    %>
                <div>
                    <p><strong>Question:</strong> <%= question.getQuestionText() %></p>
                    <p><strong>Answer:</strong> <%= question.getAnswerText() != null ? question.getAnswerText() : "No answer yet" %></p>
                </div>
    <%
            }
        } else {
    %>
        <p>No questions found.</p>
    <%
        }
    %>
</body>
</html>
