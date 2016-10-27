package Classes;

/**
 * Created by adaico on 27.10.16.
 */
public class Airport {
    private int airport_id;
    private int location_id;
    private String name;
    private int capacity;

    public Airport() {
        airport_id = 0;
        location_id = 0;
        name = null;
        capacity = 0;
    }

//    Getters and Setters ************************************************************

    public int getAirport_id() {
        return airport_id;
    }

    public void setAirport_id(int airport_id) {
        this.airport_id = airport_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

//    Methods *************************************************************************


}
