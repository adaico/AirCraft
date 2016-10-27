package Classes;

/**
 * Created by adaico on 27.10.16.
 */
public class Plane {
    private int plain_id;
    private int airline_id;
    private String model;
    private String yearOfIssue;

    public Plain() {
        plain_id = 0;
        airline_id = 0;
        model = null;
        yearOfIssue = null;
    }

//    Getters and Setters ************************************************************

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(String yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

//    Methods *************************************************************************

}
