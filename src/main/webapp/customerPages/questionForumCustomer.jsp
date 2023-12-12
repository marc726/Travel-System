<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Ask and View Questions</title>
    <style>
        .question-block, .answer-block {
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ddd;
        }
    </style>
</head>
<body>
	<a href="/Travel-System/customerDash.jsp">Back to Dashboard</a>
    <h1>Ask a Question</h1>
    <form action="SubmitQuestionServlet" method="POST">
        <input type="hidden" name="userId" value="${sessionScope.username}">
        <label for="questionText">Your Question:</label><br>
        <textarea id="questionText" name="questionText" rows="4" cols="50" required></textarea><br>
        <input type="submit" value="Submit Question">
    </form>
    

    <h2>Browse All Questions</h2>
    <form action="FetchQuestionsServlet" method="GET">
        <input type="submit" value="Load All Questions"/>
    </form>
    
    <c:forEach items="${allQuestions}" var="question">
        <div class="question-block">
            <p><strong>Question:</strong> ${question.questionText}</p>
            <c:if test="${question.answerText != null}">
                <div class="answer-block">
                    <p><strong>Answer:</strong> ${question.answerText}</p>
                </div>
            </c:if>
        </div>
    </c:forEach>
    
</body>
</html>
