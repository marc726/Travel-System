package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	public User getUser(String username) {
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                try (ResultSet results = statement.executeQuery()) {
                    if (results.next()) {
                        String password = results.getString("password");
						String name = results.getString("name");
                        int role = results.getInt("role");
                        return new User(username, password, name, role);
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

	public boolean userExists(User user) {
		try {
			Connection connection = getConnection();
		    PreparedStatement checkForExistence = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
			checkForExistence.setString(1, user.username);
			ResultSet results = checkForExistence.executeQuery();
			while (results.next()) {
				if (results.getRow() == 0) {
					return false;
				} else if (results.getString("password").equals(user.password)) {
					return true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}