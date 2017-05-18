package hoa14110071.chieuthusau.foodyversion2.JavaClass;

import java.util.ArrayList;

import hoa14110071.chieuthusau.foodyversion2.Object.City;

/**
 * Created by minhh on 4/12/2017.
 */

public interface iLoadDataCity {
    void loadCity(ArrayList<City> cities);

    void loadCityName(String CityName);

    void error();
}
