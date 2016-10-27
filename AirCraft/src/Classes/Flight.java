package Classes;

/**
 * Created by adaico on 27.10.16.
 */
public class Flight {
    private int flight_id;
    private int depAirport_id;
    private int arrAirport_id;
    private int pilot_id;
    private int plain_id;
    private int airline_id;
    private int durationMin;
    private Date depTime;

    public Flight() {
        flight_id = 0;
        depAirport_id = 0;
        arrAirport_id = 0;
        pilot_id = 0;
        plain_id = 0;
        airline_id = 0;
        durationMin = 0;
        depTime = null;
    }

//    Getters and Setters ************************************************************

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public int getDepAirport_id() {
        return depAirport_id;
    }

    public void setDepAirport_id(int depAirport_id) {
        this.depAirport_id = depAirport_id;
    }

    public int getArrAirport_id() {
        return arrAirport_id;
    }

    public void setArrAirport_id(int arrAirport_id) {
        this.arrAirport_id = arrAirport_id;
    }

    public int getPilot_id() {
        return pilot_id;
    }

    public void setPilot_id(int pilot_id) {
        this.pilot_id = pilot_id;
    }

    public int getPlain_id() {
        return plain_id;
    }

    public void setPlain_id(int plain_id) {
        this.plain_id = plain_id;
    }

    public int getAirline_id() {
        return airline_id;
    }

    public void setAirline_id(int airline_id) {
        this.airline_id = airline_id;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public Date getDepTime() {
        return depTime;
    }

    public void setDepTime(Date depTime) {
        this.depTime = depTime;
    }

//    Methods *************************************************************************


}
