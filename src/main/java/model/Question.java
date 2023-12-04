package model;

public class Question {
    public int questionId;
    public String questionText;
    public String answerText;

    public Question(int questionId, String questionText, String answerText) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.answerText = answerText;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
