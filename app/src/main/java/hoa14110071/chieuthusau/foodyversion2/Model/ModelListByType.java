package hoa14110071.chieuthusau.foodyversion2.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hoa14110071.chieuthusau.foodyversion2.Object.ListByType;

import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.LIST;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.LIST_ID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.LIST_IMG;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.LIST_NAME;

/**
 * Created by minhh on 4/11/2017.
 */

public class ModelListByType {
    public ArrayList<ListByType> get_ListByType(SQLiteDatabase database) {
        ArrayList<ListByType> listByTypes = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from " + LIST, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            ListByType listByType = new ListByType();
            listByType.setId(cursor.getInt(cursor.getColumnIndex(LIST_ID)));
            listByType.setName(cursor.getString(cursor.getColumnIndex(LIST_NAME)));
            listByType.setImage(cursor.getBlob(cursor.getColumnIndex(LIST_IMG)));
            listByTypes.add(listByType);
            cursor.moveToNext();
        }
        cursor.close();
        return listByTypes;
    }
}
