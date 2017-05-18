package hoa14110071.chieuthusau.foodyversion2.Object;


public class Street {
    int Id;
    private String Name;
    int DistrictID;

    public Street() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getDistrictID() {
        return DistrictID;
    }

    public void setDistrictID(int districtID) {
        DistrictID = districtID;
    }

    public Street(int id, String name, int districtID) {
        Id = id;
        Name = name;
        DistrictID = districtID;
    }



}
