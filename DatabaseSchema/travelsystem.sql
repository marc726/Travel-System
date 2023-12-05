DROP DATABASE IF EXISTS travelsystem;
CREATE DATABASE IF NOT EXISTS travelsystem;
USE travelsystem;

CREATE TABLE Users(
	username VARCHAR(100) UNIQUE,
  	password VARCHAR(100),
  	name VARCHAR(100),
  	role INT,
  	PRIMARY KEY (username)
);
CREATE TABLE Airline(
	alid VARCHAR(2),
	name VARCHAR(100),
	PRIMARY KEY (alid)
);
CREATE TABLE Aircraft(
	alid VARCHAR(2),
	aircraft_number INT,
	operation_days VARCHAR(7),
	num_seats INT,
	PRIMARY KEY (aircraft_number, alid),
	FOREIGN KEY (alid) REFERENCES Airline(alid)
);

CREATE TABLE Airport(
	arid VARCHAR(3),
	name VARCHAR(100),
	location VARCHAR(100),
	PRIMARY KEY(arid)
);

CREATE TABLE Flights(
	flight_number INT,
	alid VARCHAR(2),
	aircraft_number INT,
	price FLOAT,
	is_domestic BOOLEAN,
	roundtrip INT,
	nextflight INT,
	departure_airport VARCHAR(3),
	destination_airport VARCHAR(3),
	departure_time TIME,
	departure_date DATE,
	arrival_time TIME,
	arrival_date DATE,
	PRIMARY KEY (flight_number),
    FOREIGN KEY (departure_airport) REFERENCES Airport(arid),
    FOREIGN KEY (destination_airport) REFERENCES Airport(arid),
    FOREIGN KEY (alid) REFERENCES Airline(alid),
    FOREIGN KEY (roundtrip) REFERENCES Flights(flight_number) ON DELETE CASCADE,
    FOREIGN KEY (nextflight) REFERENCES Flights(flight_number) ON DELETE CASCADE,
    FOREIGN KEY (aircraft_number) REFERENCES Aircraft(aircraft_number)
);

CREATE TABLE Waiting_List(
	flight_number INT,
	username VARCHAR(100),
	PRIMARY KEY (flight_number, username),
	FOREIGN KEY (flight_number) REFERENCES Flights(flight_number),
	FOREIGN KEY (username) REFERENCES Users(username)
);

CREATE TABLE Ticket(
	seat_num INT,
	fare FLOAT,
	class_type VARCHAR(100),
	username VARCHAR(100),
	purchase_date DATE DEFAULT (CURDATE()),
	ticket_num INT,
	booking_fee FLOAT,
	flight_number INT,
	PRIMARY KEY (ticket_num, flight_number, username),
	FOREIGN KEY (username) REFERENCES Users(username),
	FOREIGN KEY (flight_number) REFERENCES Flights(flight_number)
);
CREATE TABLE Customer_Tickets(
	ticket_num INT,
	username VARCHAR(100),
	PRIMARY KEY (ticket_num,username),
	FOREIGN KEY (ticket_num) REFERENCES Ticket(ticket_num),
	FOREIGN KEY (username) REFERENCES Users(username)
);

CREATE TABLE Questions(
	question_id INT,
	question_text VARCHAR(250),
	answer_text VARCHAR(250),
	PRIMARY KEY (question_id)
);