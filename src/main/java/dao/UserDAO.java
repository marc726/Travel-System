package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.User;


public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/travelsystem";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	public UserDAO() {
	}

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

	public void insertUser(User user) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?);")) {
			preparedStatement.setString(1, user.username);
			preparedStatement.setString(2, user.password);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

		}
	}
}