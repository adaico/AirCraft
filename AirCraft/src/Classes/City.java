package Classes;

/**
 * Created by adaico on 27.10.16.
 */
public class City {
    private int city_id;
    private String name;
    private String countryName;

    public City() {
        city_id = 0;
        name = null;
        countryName = null;
    }

//    Getters and Setters ************************************************************

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

//    Methods *************************************************************************


}
