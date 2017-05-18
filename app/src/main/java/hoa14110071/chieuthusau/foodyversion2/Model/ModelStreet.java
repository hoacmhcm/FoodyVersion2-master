package hoa14110071.chieuthusau.foodyversion2.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hoa14110071.chieuthusau.foodyversion2.Object.Street;

import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.STREET;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.STREET_DISTRICTID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.STREET_ID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.STREET_NAME;

/**
 * Created by minhh on 4/11/2017.
 */

public class ModelStreet {
    public ArrayList<Street> get_StreetByDistrictID(SQLiteDatabase database, int DistrictID) {
        ArrayList<Street> streets = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from " + STREET + " where " + STREET_DISTRICTID
                + " = " +DistrictID, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Street street = new Street();
            street.setId(cursor.getInt(cursor.getColumnIndex(STREET_ID)));
            street.setName(cursor.getString(cursor.getColumnIndex(STREET_NAME)));
            street.setDistrictID(cursor.getInt(cursor.getColumnIndex(STREET_DISTRICTID)));
            streets.add(street);
            cursor.moveToNext();
        }
        cursor.close();
        return streets;
    }
}
