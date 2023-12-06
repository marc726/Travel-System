package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import model.Ticket;

public class TicketDAO {
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

    private Ticket extractTicketFromResultSet(ResultSet resultSet) throws SQLException {
        int seatNum = resultSet.getInt("seat_num");
        float fare = resultSet.getFloat("fare");
        String classType = resultSet.getString("class_type");
        String username = resultSet.getString("username");
        Date purchaseDate = new Date(resultSet.getDate("purchase_date").getTime());
        int ticketNum = resultSet.getInt("ticket_num");
        float bookingFee = resultSet.getFloat("booking_fee");
        int flightNumber = resultSet.getInt("flight_number");

        return new Ticket(seatNum, fare, classType, username, purchaseDate, ticketNum, bookingFee, flightNumber);
    }

    public List<Ticket> getTicketsByFlightNumber(int flightNumber) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM Ticket WHERE flight_number = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, flightNumber);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tickets.add(extractTicketFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public List<Ticket> getTicketsByCustomerName(String customerName) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT Ticket.* FROM Ticket JOIN Users ON Ticket.username = Users.username WHERE Users.name = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customerName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tickets.add(extractTicketFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public String[] getTopRevenueCustomer() {
        String[] topCustomerData = new String[2]; // Array to hold username and total revenue
        String sql = "SELECT username, SUM(fare + booking_fee) AS totalRevenue FROM Ticket GROUP BY username ORDER BY totalRevenue DESC LIMIT 1";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                topCustomerData[0] = resultSet.getString("username"); // Username
                topCustomerData[1] = String.valueOf(resultSet.getDouble("totalRevenue")); // Total Revenue
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topCustomerData;
    }
}
