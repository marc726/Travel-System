package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/travelsystem";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    public CustomerRepDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean insertCustomerRep(User user) throws SQLException {
        if (user.role != 1) {
            throw new IllegalArgumentException("User is not a customer representative");
        }
        String sql = "INSERT INTO users (username, password, name, role) VALUES (?, ?, ?, 1);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.username);
            preparedStatement.setString(2, user.password);
            preparedStatement.setString(3, user.name);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean updateCustomerRep(User user) throws SQLException {
        if (user.role != 1) {
            throw new IllegalArgumentException("User is not a customer representative");
        }
        String sql = "UPDATE users SET password = ?, name = ? WHERE username = ? AND role = 1;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.password);
            preparedStatement.setString(2, user.name);
            preparedStatement.setString(3, user.username);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean deleteCustomerRep(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE username = ? AND role = 1;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }
    }


    public List<User> getAllCustomerReps() throws SQLException {
        System.out.println("Getting all customer reps");
        List<User> customerReps = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 1;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    String username = results.getString("username");
                    String password = results.getString("password");
                    String name = results.getString("name");
                    int role = results.getInt("role");
                    customerReps.add(new User(username, password, name, role));
                }
            }
        }
        return customerReps;
    }
}
