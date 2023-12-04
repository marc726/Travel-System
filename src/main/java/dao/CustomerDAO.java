package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/travelsystem";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    public CustomerDAO() {
    }

    protected Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            System.out.println("Database driver not found");
            e.printStackTrace();
        }
        return connection;
    }

    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, name, role) VALUES (?, ?, ?, 0);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }
    }

    public boolean updateUser(User user) throws SQLException {
        System.out.println("Updating user: " + user.username);
        // Correct SQL update statement
        String sql = "UPDATE users SET password = ?, name = ? WHERE username = ? AND role = 0;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.password);
            preparedStatement.setString(2, user.name);
            preparedStatement.setString(3, user.username);
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQL Exception in updateUser: " + e.getMessage());
            throw e;
        }
    }
    

    public boolean deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE username = ? AND role = 0;"; // role 0 for customers
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }
    }

    //get all customers
    public List<User> getAllCustomers() throws SQLException {
        List<User> customers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 0;"; // role 0 for customers
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                int role = resultSet.getInt("role");
                User user = new User(username, password, name, role);
                customers.add(user);
            }
        }
        return customers;
    }
}
