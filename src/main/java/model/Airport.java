package model;

public class Airport {

    public String arid;
    public String name;
    public String location;

    public Airport(String arid, String name, String location) {
        this.arid = arid;
        this.name = name;
        this.location = location;
    }

    public String getArid() {
        return arid;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setArid(String arid) {
        this.arid = arid;
    }

    public void setName(String name) {
        this.name = name;
    } 

    public void setLocation(String location) {
        this.location = location;
    }
    
}
