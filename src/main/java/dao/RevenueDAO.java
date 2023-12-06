package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RevenueDAO {
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

    public float getRevenueByFlight(int flightNumber) {
        String sql = "SELECT SUM(fare) AS total_revenue FROM Ticket WHERE flight_number = ?";
        return executeRevenueQuery(sql, Integer.toString(flightNumber));
    }

    public float getRevenueByAirline(String airlineId) {
        String sql = "SELECT SUM(fare) AS total_revenue FROM Ticket INNER JOIN Flights ON Ticket.flight_number = Flights.flight_number WHERE alid = ?";
        return executeRevenueQuery(sql, airlineId);
    }

    public float getRevenueByCustomer(String username) {
        String sql = "SELECT SUM(fare) AS total_revenue FROM Ticket WHERE username = ?";
        return executeRevenueQuery(sql, username);
    }

    private float executeRevenueQuery(String sql, String parameter) {
        float revenue = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, parameter);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                revenue = resultSet.getFloat("total_revenue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenue;
    }

    public float getSalesReportForMonth(int year, int month) {
        String sql = "SELECT SUM(fare) AS total_sales FROM Ticket WHERE YEAR(purchase_date) = ? AND MONTH(purchase_date) = ?";
        float totalSales = 0;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, year);
            preparedStatement.setInt(2, month);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalSales = resultSet.getFloat("total_sales");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalSales;
    }
}

