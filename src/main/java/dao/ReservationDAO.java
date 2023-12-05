package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import model.Flight;
import model.User;

public class ReservationDAO {
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

    public boolean reserveFlight(User user, Flight flight) {
        try {
            Connection connection = getConnection();
            PreparedStatement reserveFlight = connection
                    .prepareStatement("INSERT INTO Customer_Tickets (ticket_num, username) VALUES (?, ?);");
            
            // Assuming you have a method to generate unique ticket numbers
            int ticketNumber = generateUniqueTicketNumber();
            
            reserveFlight.setInt(1, ticketNumber);
            reserveFlight.setString(2, user.getUsername());

            int rowsAffected = reserveFlight.executeUpdate();
            connection.close();
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //generate unique ticket numbers here
    private int generateUniqueTicketNumber() throws SQLException {
        Random random = new Random();
        int ticketNumber;
        do {
            ticketNumber = random.nextInt(Integer.MAX_VALUE);
        } while (isTicketNumberExists(ticketNumber));
        return ticketNumber;
    }

    private boolean isTicketNumberExists(int ticketNumber) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM customer_tickets WHERE ticket_num = ?;");
        preparedStatement.setInt(1, ticketNumber);
        boolean isExists = preparedStatement.executeQuery().next();
        connection.close();
        return isExists;
    }
}
