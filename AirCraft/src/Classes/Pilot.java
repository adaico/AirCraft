package Classes;

/**
 * Created by adaico on 27.10.16.
 */
public class Pilot {
    private int pilot_id;
    private int airline_id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Pilot() {
        pilot_id = 0;
        airline_id = 0;
        firstName = null;
        lastName = null;
        birthDate = null;
    }
//    Getters and Setters ************************************************************

    public int getPilot_id() {
        return pilot_id;
    }

    public void setPilot_id(int pilot_id) {
        this.pilot_id = pilot_id;
    }

    public int getAirline_id() {
        return airline_id;
    }

    public void setAirline_id(int airline_id) {
        this.airline_id = airline_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

//    Methods *************************************************************************


}
