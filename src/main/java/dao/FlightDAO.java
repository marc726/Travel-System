package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Flight;
import model.User;


public class FlightDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/travelsystem";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";

	public FlightDAO() {
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

	public ArrayList<Flight> getFlights(String selectedDate, String roundTripDate, int flexibility){//, Date date, boolean isDirect, boolean isRoundTrip, boolean isFlexible) {
		try {
			Connection connection = getConnection();
			PreparedStatement getFlights;
			if (roundTripDate == null) {
				getFlights = connection.prepareStatement("SELECT * FROM flights WHERE departure_date < ? AND departure_date > ?;");
				getFlights.setDate(1, Date.valueOf(LocalDate.parse(selectedDate).plusDays(flexibility)));
				getFlights.setDate(2, Date.valueOf(LocalDate.parse(selectedDate).minusDays(flexibility)));
			} else {
				getFlights = connection.prepareStatement("SELECT * FROM flights AS first_flight, flights AS second_flight WHERE first_flight.departure_date < ? AND first_flight.departure_date > ? AND first_flight.roundtrip = second_flight.flight_number AND second_flight.departure_date < ? AND second_flight.departure_date > ?;");
				getFlights.setDate(1, Date.valueOf(LocalDate.parse(selectedDate).plusDays(flexibility)));
				getFlights.setDate(2, Date.valueOf(LocalDate.parse(selectedDate).minusDays(flexibility)));
				getFlights.setDate(3, Date.valueOf(LocalDate.parse(roundTripDate).plusDays(flexibility)));
				getFlights.setDate(4, Date.valueOf(LocalDate.parse(roundTripDate).minusDays(flexibility)));
			}
			ResultSet results = getFlights.executeQuery();
			ArrayList<Flight> flights = new ArrayList<Flight>();
			PreparedStatement getStopCount = connection.prepareStatement("WITH RECURSIVE FlightCTE AS ("
					+ "  SELECT flight_number, nextflight, 1 AS execution_count"
					+ "  FROM flights"
					+ "  WHERE flight_number = ?"
					+ "  UNION ALL"
					+ "  SELECT f.flight_number, f.nextflight, fc.execution_count + 1"
					+ "  FROM flights f"
					+ "  INNER JOIN FlightCTE fc ON f.flight_number = fc.nextflight)"
					+ "  SELECT COUNT(*) AS total_executions"
					+ "  FROM FlightCTE"
					+ "  WHERE nextflight IS NOT NULL;");
            while (results.next()) {
                int flightNumber = results.getInt("flight_number");
                String alid = results.getString("alid");
                int aircraftNumber = results.getInt("aircraft_number");
                float price = results.getFloat("price");
                boolean isDomestic = results.getBoolean("is_domestic");
                int roundTrip = results.getInt("roundtrip");
                if (roundTripDate == null && roundTrip != 0) {
                	continue;
                }
                getStopCount.setInt(1, flightNumber);
                ResultSet resultSet = getStopCount.executeQuery();
                resultSet.next();
                int stops = resultSet.getInt(1);
                String departureAirport = results.getString("departure_airport");
                String destinationAirport = results.getString("destination_airport");
                Time departureTime = results.getTime("departure_time");
                Date departureDate = results.getDate("departure_date");
                Time arrivalTime = results.getTime("arrival_time");
                Date arrivalDate = results.getDate("arrival_date");
                flights.add(new Flight(flightNumber, alid, aircraftNumber,price,isDomestic, roundTrip, stops, departureAirport, destinationAirport, departureTime, arrivalTime, departureDate, arrivalDate));
            }
			
//			flights.add(new Flight())
			return flights;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean insertFlight(Flight flight) throws SQLException {
		String sql = "INSERT INTO Flights (flight_number, alid, aircraft_number, price, is_domestic, roundtrip, nextflight, departure_airport, destination_airport, departure_time, departure_date, arrival_time, arrival_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, flight.getFlightNumber());
			preparedStatement.setString(2, flight.getALID());
			preparedStatement.setInt(3, flight.getAircraftNumber());
			preparedStatement.setFloat(4, flight.getPrice());
			preparedStatement.setBoolean(5, flight.getIsDomestic());
			preparedStatement.setInt(6, flight.getRoundTrip());
			preparedStatement.setInt(7, flight.getStops());
			preparedStatement.setString(8, flight.getDepartureAirport());
			preparedStatement.setString(9, flight.getDestinationAirport());
			preparedStatement.setTime(10, flight.getDepartureTime());
			preparedStatement.setDate(11, flight.getDepartureDate());
			preparedStatement.setTime(12, flight.getArrivalTime());
			preparedStatement.setDate(13, flight.getArrivalDate());
			return preparedStatement.executeUpdate() > 0;
		}
	}

	public boolean deleteFlight(int flightNumber) throws SQLException {
		String sql = "DELETE FROM flights WHERE flight_number = ?";
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, flightNumber);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		}
	}
	
	public boolean bookTicket(int flight_number, String username, String classType) {
		try {
		Connection connection = getConnection();
		PreparedStatement flight_exists = connection.prepareStatement("SELECT COUNT(*) FROM flights WHERE flight_number = ?");
		flight_exists.setInt(1, flight_number);
		ResultSet resultSet = flight_exists.executeQuery();
		resultSet.next();
		if (resultSet.getInt(1) == 0) {//if flight does not exist
			System.out.println("Flight does not exist");
			return false;
		}
		PreparedStatement current_seats_query = connection.prepareStatement("SELECT COUNT(*) FROM ticket WHERE ticket.flight_number = ?");
		PreparedStatement max_seats_query = connection.prepareStatement("SELECT num_seats FROM flights, aircraft WHERE flights.aircraft_number = aircraft.aircraft_number AND flights.flight_number = ?;");
		PreparedStatement fare_query = connection.prepareStatement("SELECT price FROM flights WHERE flights.flight_number = ?;");

		current_seats_query.setInt(1, flight_number);
		max_seats_query.setInt(1, flight_number);
		fare_query.setInt(1, flight_number);
		resultSet = current_seats_query.executeQuery();
		resultSet.next();
		int current_seats = resultSet.getInt(1);
		resultSet = max_seats_query.executeQuery();
		resultSet.next();
		int max_seats = resultSet.getInt(1);
		resultSet = fare_query.executeQuery();
		resultSet.next();
		float fare = resultSet.getFloat(1);
		System.out.println(current_seats + " " + max_seats + " " + fare);
		if (current_seats < max_seats) { //there are seats available
			PreparedStatement insertTicket = connection.prepareStatement("INSERT INTO `ticket` (`seat_num`, `fare`, `class_type`, `username`, `booking_fee`, `flight_number`) VALUES (?, ?, ?, ?, 10, ?);");
			insertTicket.setFloat(1,current_seats + 1);
			insertTicket.setFloat(2,fare);
			insertTicket.setString(3,classType);
			insertTicket.setString(4,username);
			insertTicket.setInt(5,flight_number);
			insertTicket.executeUpdate();
			return true;
		} else { //there are no seats available
			return false;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
	}
}