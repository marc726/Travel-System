INSERT INTO `airport` (`arid`, `name`, `location`) VALUES
('asa', 'asfeas', 'efasefas');
INSERT INTO `airline` (`alid`, `name`) VALUES
('as', 'fefsefaf');
INSERT INTO `airline` (`alid`, `name`) VALUES
('al', 'fesfesf');
INSERT INTO `aircraft` (`alid`, `aircraft_number`, `operation_days`, `num_seats`) VALUES
('as', 1, '0111110', 32);
INSERT INTO `flights` (`flight_number`, `alid`, `aircraft_number`, `price`, `is_domestic`, `departure_airport`, `destination_airport`, `departure_time`, `departure_date`, `arrival_time`, `arrival_date`) VALUES
(2, 'as', 1, 23, true, 'asa', 'asa', '14:23', '2023-12-12', '14:23', '2023-12-12');
INSERT INTO `flights` (`flight_number`, `alid`, `aircraft_number`, `price`, `is_domestic`, `departure_airport`, `destination_airport`, `departure_time`, `departure_date`, `arrival_time`, `arrival_date`) VALUES
(4, 'as', 1, 23, true, 'asa', 'asa', '14:23', '2023-12-12', '14:23', '2023-12-12');
INSERT INTO `flights` (`flight_number`, `alid`, `aircraft_number`, `price`, `is_domestic`, `nextflight`, `departure_airport`, `destination_airport`, `departure_time`, `departure_date`, `arrival_time`, `arrival_date`) VALUES
(3, 'as', 1, 23, true, 4, 'asa', 'asa', '14:23', '2023-12-12', '14:23', '2023-12-12');
INSERT INTO `flights` (`flight_number`, `alid`, `aircraft_number`, `price`, `is_domestic`, `roundtrip`, `nextflight`, `departure_airport`, `destination_airport`, `departure_time`, `departure_date`, `arrival_time`, `arrival_date`) VALUES
(1, 'as', 1, 23, true, 3, 2, 'asa', 'asa', '12:23', '2023-12-12', '14:23', '2023-12-12');
INSERT INTO `users` (`username`, `password`, `name`, `role`) VALUES
('test', 'test', 'afsefas', 0);
INSERT INTO `users` (`username`, `password`, `name`, `role`) VALUES
('test1', 'test1', 'feafsf', 0);
INSERT INTO `users` (`username`, `password`, `name`, `role`) VALUES
('test2', 'test2', 'asefasf', 0);
INSERT INTO `users` (`username`, `password`, `name`, `role`) VALUES
('test3', 'test3', 'fsefasfs', 0);
INSERT INTO `ticket` (`seat_num`, `fare`, `class_type`, `username`, `purchase_date`, `ticket_num`, `booking_fee`, `flight_number`) VALUES
(1, '50', 'first', 'test', '2023-12-12', 1, 5, 1);
INSERT INTO `ticket` (`seat_num`, `fare`, `class_type`, `username`, `purchase_date`, `ticket_num`, `booking_fee`, `flight_number`) VALUES
(2, '45', 'economy', 'test1', '2023-12-12', 2, 5, 1);
INSERT INTO `ticket` (`seat_num`, `fare`, `class_type`, `username`, `purchase_date`, `ticket_num`, `booking_fee`, `flight_number`) VALUES
(3, '40', 'business', 'test2', '2023-12-12', 3, 5, 1);