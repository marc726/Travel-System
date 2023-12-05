package model;

public class Aircraft {

    public String alid;
    public int aircraftNumber;
    public int operationDays;
    public int numberOfSeats;

    public Aircraft(String alid, int aircraftNumber, int operationDays, int numberOfSeats) {
        this.alid = alid;
        this.aircraftNumber = aircraftNumber;
        this.operationDays = operationDays;
        this.numberOfSeats = numberOfSeats;
    }

    public String getAlid() {
        return alid;
    }

    public int getAircraftNumber() {
        return aircraftNumber;
    }

    public int getOperationDays() {
        return operationDays;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setAlid(String alid) {
        this.alid = alid;
    }

    public void setAircraftNumber(int aircraftNumber) {
        this.aircraftNumber = aircraftNumber;
    }

    public void setOperationDays(int operationDays) {
        this.operationDays = operationDays;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
    
}
