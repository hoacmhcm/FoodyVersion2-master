package hoa14110071.chieuthusau.foodyversion2.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hoa14110071.chieuthusau.foodyversion2.Object.Category;

import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.CATEGORY;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.CATEGORY_ID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.CATEGORY_IMG;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.CATEGORY_IMGPRESS;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.CATEGORY_NAME;

/**
 * Created by minhh on 4/11/2017.
 */

public class ModelCategory {
    public ArrayList<Category> get_Category(SQLiteDatabase database) {
        ArrayList<Category> categories = new ArrayList<>();
        Cursor cursor = database.query(CATEGORY, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = new Category();
            category.setId(cursor.getInt(cursor.getColumnIndex(CATEGORY_ID)));
            category.setName(cursor.getString(cursor.getColumnIndex(CATEGORY_NAME)));
            category.setImage(cursor.getBlob(cursor.getColumnIndex(CATEGORY_IMG)));
            category.setImagePress(cursor.getBlob(cursor.getColumnIndex(CATEGORY_IMGPRESS)));
            categories.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        return categories;
    }
}
