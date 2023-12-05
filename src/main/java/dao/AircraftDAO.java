package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Aircraft;

public class AircraftDAO {
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

    public boolean insertAircraft(Aircraft aircraft) throws SQLException {
        String sql = "INSERT INTO Aircraft (aircraft_number, alid, operation_days, num_seats) VALUES (?, ?, ?, ?);";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, aircraft.getAircraftNumber());
            preparedStatement.setString(2, aircraft.getAlid());
            preparedStatement.setInt(3, aircraft.getOperationDays());
            preparedStatement.setInt(4, aircraft.getNumberOfSeats());
            
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public boolean deleteAircraft(int aircraftNumber) throws SQLException {
        String sql = "DELETE FROM Aircraft WHERE aircraft_number = ?;";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, aircraftNumber);
            
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
