package web;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;
import dao.FlightDAO;
import model.Flight;
import model.User;

@WebServlet("/FlightServlet")
public class FlightServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FlightDAO flightDAO;

    public void init() {
        flightDAO = new FlightDAO(); // Initialize the CustomerDAO
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
       
        System.out.println(action);
        switch (action) {
		    case "getFlights":
		        getFlights(request, response);
		        break;
		    case "getWaitingList":
		        getWaitingList(request, response);
		        break;
		    default:
		        break;
		}
    }

    private void getFlights(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sortby = request.getParameter("sortby");
		String selectedDate = request.getParameter("selectedDate");
		String roundTripDate = request.getParameter("selectedReturnDate");
		int flexibility = Integer.valueOf(request.getParameter("flexibility").charAt(0));
		//filters
		String airline = request.getParameter("airline");
		int lowerPrice = Integer.valueOf(request.getParameter("lowerprice"));
		int higherPrice = "any".equals(request.getParameter("higherprice")) ? Integer.MAX_VALUE : Integer.valueOf(request.getParameter("higherprice"));
		int lowerStops = Integer.valueOf(request.getParameter("lowerstops"));
		int higherStops = "any".equals(request.getParameter("higherstops")) ? Integer.MAX_VALUE : Integer.valueOf(request.getParameter("higherstops"));
		Time earlyTakeOff = Time.valueOf(request.getParameter("earlytakeoff") + ":00");
		Time lateTakeOff = Time.valueOf(request.getParameter("latetakeoff") + ":00");
		Time earlyLanding = Time.valueOf(request.getParameter("earlylanding") + ":00");
		Time lateLanding = Time.valueOf(request.getParameter("latelanding") + ":00");
	
		System.out.println(airline);

		ArrayList<Flight> flights = flightDAO.getFlights(selectedDate, roundTripDate, flexibility);
		List<Flight> filteredFlights = flights.stream().filter(flight -> flight.price >= lowerPrice && flight.price <= higherPrice && 
				flight.stops >= lowerStops && flight.stops <= higherStops && flight.departureTime.after(earlyTakeOff) 
				&& flight.departureTime.before(lateTakeOff) && flight.arrivalTime.after(earlyLanding) &&
				flight.arrivalTime.before(lateLanding) && airline == null ? true : flight.ALID.equals(airline)).collect(Collectors.toList());
		filteredFlights.forEach(flight -> System.out.println(flight.departureDate.getTime() + flight.departureTime.getTime()));
		if (sortby.equals("price")) {
			filteredFlights.sort((a, b) -> Float.compare(a.price, b.price));
		} else if (sortby.equals("takeoff")) {
			filteredFlights.sort((a, b) -> Long.compare(a.departureTime.getTime() + a.departureDate.getTime(), b.departureTime.getTime() + b.departureDate.getTime()));
		} else if (sortby.equals("landing")) {
			filteredFlights.sort((a, b) -> Long.compare(a.arrivalTime.getTime() + a.arrivalDate.getTime(), b.arrivalTime.getTime() + b.arrivalDate.getTime()));
		} else if (sortby.equals("duration")) {
			filteredFlights.sort((a, b) -> Long.compare((a.arrivalTime.getTime() + a.arrivalDate.getTime())-(a.departureTime.getTime() + a.departureDate.getTime()), (b.arrivalTime.getTime() + b.arrivalDate.getTime())-(b.departureTime.getTime() + b.departureDate.getTime())));
		}
 		
		request.getSession().setAttribute("flights", filteredFlights);
		response.sendRedirect("customerPages/browseFlightsCustomer.jsp");
	}
    
    private void getWaitingList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int flightId = Integer.valueOf(request.getParameter("flightId"));
		
		
		ArrayList<String> waitingList = flightDAO.getWaitingList(flightId);
		waitingList.forEach(username -> System.out.println(username));
		request.getSession().setAttribute("waitingList", waitingList);
		response.sendRedirect("customerRep/waitingList.jsp");
	}
}
