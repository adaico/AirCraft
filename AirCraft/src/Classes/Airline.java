package Classes;

/**
 * Created by adaico on 27.10.16.
 */
public class Airline {
    private int airline_id;
    private String name;
    private Date foundDate;

    public Airline() {
        airline_id = 0;
        name = null;
        foundDate = null;
    }

//    Getters and Setters ************************************************************

    public int getAirline_id() {
        return airline_id;
    }

    public void setAirline_id(int airline_id) {
        this.airline_id = airline_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(Date foundDate) {
        this.foundDate = foundDate;
    }

//    Methods *************************************************************************

}
