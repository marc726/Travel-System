<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Questions List</title>
    <style>
        .question-item {
            border: 1px solid #ddd;
            padding: 10px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <h1>Questions List</h1>
    <form action="FetchRepQuestionsServlet" method="GET">
        <input type="submit" value="Load All Questions"/>
    </form>
    
    <c:forEach items="${allQuestions}" var="question">
        <div class="question-item">
            <p><strong>Question:</strong> ${question.questionText}</p>
            <c:choose>
                <c:when test="${not empty question.answerText}">
                    <p><strong>Answer:</strong> ${question.answerText}</p>
                </c:when>
                <c:otherwise>
                    <form action="AnswerQuestionServlet" method="POST">
                        <input type="hidden" name="questionId" value="${question.questionId}" />
                        <label for="answerText-${question.questionId}">Your Answer:</label><br>
                        <textarea name="answerText" id="answerText-${question.questionId}" rows="3" cols="30" required></textarea><br>
                        <input type="submit" value="Submit Answer" />
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </c:forEach>
</body>
</html>
