package model;
import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Time;
public class Flight {
	public int flightNumber;
	public String ALID;
	public int aircraftNumber;
	public float price;
	public boolean isDomestic;
	public int roundTrip;
	public int stops;
	public String departureAirport;
	public String destinationAirport;
	public Time departureTime;
	public Time arrivalTime;
	public Date departureDate;
	public Date arrivalDate;

	public Flight(int flightNumber, String ALID, int aircraftNumber, float price, boolean isDomestic, int roundTrip, int stops, String departureAirport, String destinationAirport, Time departureTime, Time arrivalTime, Date departureDate, Date arrivalDate) {
		this.flightNumber = flightNumber;
		this.ALID = ALID;
		this.aircraftNumber = aircraftNumber;
		this.price = price;
		this.isDomestic = isDomestic;
		this.roundTrip = roundTrip;
		this.stops = stops;
		this.departureAirport = departureAirport;
		this.destinationAirport = destinationAirport;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		result.append(this.getClass().getName());
		result.append(" Object {");
		result.append(newLine);

		// determine fields declared in this class only (no fields of superclass)
		Field[] fields = this.getClass().getDeclaredFields();

		// print field names paired with their values
		for (Field field : fields) {
			result.append("  ");
			try {
				result.append(field.getName());
				result.append(": ");
				// requires access to private field:
				result.append(field.get(this));
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
			result.append(newLine);
		}
		result.append("}");

		return result.toString();
	}

	//getters
	public int getFlightNumber() {
		return flightNumber;
	}

	public String getALID() {
		return ALID;
	}

	public int getAircraftNumber() {
		return aircraftNumber;
	}

	public float getPrice() {
		return price;
	}

	public boolean getIsDomestic() {
		return isDomestic;
	}

	public int getRoundTrip() {
		return roundTrip;
	}

	public int getStops() {
		return stops;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

}