package hoa14110071.chieuthusau.foodyversion2.Object;

import java.util.List;

/**
 * Created by minhh on 4/3/2017.
 */

public class District {
    int Id;
    String Name;
    int CountStreet;
    int CityId;
    private List<Street> streets;


    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCountStreet(int countStreet) {
        CountStreet = countStreet;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }

    public District() {

    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public int getCountStreet() {
        return CountStreet;
    }

    public int getCityId() {
        return CityId;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {

        this.streets = streets;
    }
}
