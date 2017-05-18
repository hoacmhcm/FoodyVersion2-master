package hoa14110071.chieuthusau.foodyversion2.Object;

/**
 * Created by minhh on 4/3/2017.
 */

public class City {
    int Id;
    String Name;

    public City(int id, String name) {
        Id = id;
        Name = name;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public City() {

    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

}
