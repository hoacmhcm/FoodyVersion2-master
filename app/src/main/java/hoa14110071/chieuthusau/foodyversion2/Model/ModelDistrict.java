package hoa14110071.chieuthusau.foodyversion2.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hoa14110071.chieuthusau.foodyversion2.Object.District;

import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.DISTRICT;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.DISTRICT_CITYID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.DISTRICT_COUNT_STREET;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.DISTRICT_ID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.DISTRICT_NAME;

/**
 * Created by minhh on 4/11/2017.
 */

public class ModelDistrict {

    ModelStreet modelStreet = new ModelStreet();

    public ArrayList<District> get_District(SQLiteDatabase database, int CityId) {
        ArrayList<District> districts = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from " + DISTRICT + " where " + DISTRICT_CITYID + " =" + CityId, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            District district = new District();
            district.setId(cursor.getInt(cursor.getColumnIndex(DISTRICT_ID)));
            district.setName(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME)));
            district.setCityId(cursor.getInt(cursor.getColumnIndex(DISTRICT_CITYID)));
            district.setCountStreet(cursor.getInt(cursor.getColumnIndex(DISTRICT_COUNT_STREET)));
            district.setStreets(modelStreet.get_StreetByDistrictID(database,district.getId()));
            districts.add(district);
            cursor.moveToNext();
        }
        cursor.close();
        return districts;
    }
}
