package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Question;
import java.util.Random;

public class QuestionsDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/travelsystem";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public boolean submitQuestion(String userId, String questionText) throws SQLException {
        String sql = "INSERT INTO questions (question_id, question_text) VALUES (?, ?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int questionId = generateUniqueQuestionId();
            preparedStatement.setInt(1, questionId);
            preparedStatement.setString(2, questionText);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean answerQuestion(int questionId, String answerText) throws SQLException {
        String sql = "UPDATE questions SET answer_text = ? WHERE question_id = ?;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, answerText);
            preparedStatement.setInt(2, questionId);
            return preparedStatement.executeUpdate() > 0;
        }
    }

        public List<Question> getUnansweredQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE answer_text IS NULL;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("question_id");
                String questionText = resultSet.getString("question_text");
                Question question = new Question(id, questionText, null);
                questions.add(question);
            }
        }
        return questions;
    }

    public List<Question> getAllQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questions;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("question_id");
                String questionText = resultSet.getString("question_text");
                String answerText = resultSet.getString("answer_text");
                questions.add(new Question(id, questionText, answerText));
            }
        }
        return questions;
    }
    
    private int generateUniqueQuestionId() throws SQLException {
        Random random = new Random();
        int questionId;
        do {
            questionId = random.nextInt(Integer.MAX_VALUE);
        } while (isQuestionIdExists(questionId));
        return questionId;
    }

    private boolean isQuestionIdExists(int questionId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM questions WHERE question_id = ?;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, questionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        }
    }
}
