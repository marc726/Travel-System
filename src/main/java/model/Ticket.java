package model;

import java.util.Date;


public class Ticket {

    public int seat_num;
    public float fare;
    public String class_type;
    public String username;
    public Date purchase_date;
    public int ticket_num;
    public float booking_fee;
    public int flight_number;

    public Ticket(int seat_num, float fare, String class_type, String username, Date purchase_date, int ticket_num, float booking_fee, int flight_number) {
        this.seat_num = seat_num;
        this.fare = fare;
        this.class_type = class_type;
        this.username = username;
        this.purchase_date = purchase_date;
        this.ticket_num = ticket_num;
        this.booking_fee = booking_fee;
        this.flight_number = flight_number;
    }

    public int getSeat_num() {
        return seat_num;
    }

    public float getFare() {
        return fare;
    }

    public String getClass_type() {
        return class_type;
    }

    public String getUsername() {
        return username;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public int getTicket_num() {
        return ticket_num;
    }

    public float getBooking_fee() {
        return booking_fee;
    }

    public int getFlight_number() {
        return flight_number;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }
    
}
