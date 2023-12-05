package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Airport;

public class AirportDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/travelsystem";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

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

    public boolean insertAirport(Airport airport) throws SQLException {
        String sql = "INSERT INTO Airport (arid, name, location) VALUES (?, ?, ?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, airport.getArid());
            preparedStatement.setString(2, airport.getName());
            preparedStatement.setString(3, airport.getLocation());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean deleteAirport(String arid) throws SQLException {
        String sql = "DELETE FROM Airport WHERE arid = ?;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, arid);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
