package hoa14110071.chieuthusau.foodyversion2.Controller.CityController;

import android.content.Context;

import java.util.ArrayList;

import hoa14110071.chieuthusau.foodyversion2.JavaClass.iLoadDataCity;
import hoa14110071.chieuthusau.foodyversion2.Model.ModelCity;
import hoa14110071.chieuthusau.foodyversion2.Object.City;

import static hoa14110071.chieuthusau.foodyversion2.Activity.HomeActivity.database;

/**
 * Created by minhh on 4/12/2017.
 */

public class CityController implements iCityController {
    iLoadDataCity iLoadDataCity;
    ModelCity modelCity;
    Context context;

    public CityController(iLoadDataCity iLoadDataCity, Context context) {
        this.iLoadDataCity = iLoadDataCity;
        modelCity = new ModelCity();
        this.context = context;
    }

    //lấy danh sách thành phố
    @Override
    public void get_City() {
        ArrayList<City> cities = modelCity.get_City(database);
        if (cities.size() > 0) {
            iLoadDataCity.loadCity(cities);
        } else {
            iLoadDataCity.error();
        }
    }

    //lấy tên thành phố
    @Override
    public void get_CityName(int CityId) {
        String cityName = modelCity.get_CityName(database, CityId);
        if (!cityName.isEmpty()) {
            iLoadDataCity.loadCityName(cityName);
        } else {
            iLoadDataCity.error();
        }
    }
}
