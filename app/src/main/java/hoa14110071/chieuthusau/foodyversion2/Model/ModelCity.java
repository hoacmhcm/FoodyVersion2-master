package hoa14110071.chieuthusau.foodyversion2.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hoa14110071.chieuthusau.foodyversion2.Object.City;

import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.CITY;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.CITY_ID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.CITY_NAME;

/**
 * Created by minhh on 4/11/2017.
 */

public class ModelCity {
    public ArrayList<City> get_City(SQLiteDatabase database) {
        ArrayList<City> cities = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from " + CITY, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            City city = new City();
            city.setId(cursor.getInt(cursor.getColumnIndex(CITY_ID)));
            city.setName(cursor.getString(cursor.getColumnIndex(CITY_NAME)));
            cities.add(city);
            cursor.moveToNext();
        }
        cursor.close();
        return cities;
    }

    public String get_CityName(SQLiteDatabase database, int CityId) {
        ArrayList<City> cities = new ArrayList<>();
        Cursor cursor = database.rawQuery("select " + CITY_NAME + " from " + CITY + " where " + CITY_ID + " = " + CityId, null);
        cursor.moveToFirst();
        if (cursor != null && cursor.moveToFirst()) {
            String cityName = cursor.getString(0);
            cursor.close();
            return cityName;
        }
        return null;
    }
}
