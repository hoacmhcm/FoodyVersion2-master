package hoa14110071.chieuthusau.foodyversion2.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString;
import hoa14110071.chieuthusau.foodyversion2.Object.Item;
import hoa14110071.chieuthusau.foodyversion2.Services.RetrofitCreate;
import hoa14110071.chieuthusau.foodyversion2.Services.Services;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hoa14110071.chieuthusau.foodyversion2.Activity.HomeActivity.database;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.DISTRICT;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.DISTRICT_CITYID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.DISTRICT_ID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.ITEM;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.ITEM_CATEGORYID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.ITEM_DISTRICTID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.ITEM_ID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.ITEM_LISTID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.ITEM_STREETID;

/**
 * Created by minhh on 4/11/2017.
 */

public class ModelItem {
    ModelReview modelReview = new ModelReview();


    private ArrayList<Item> get_ListItem(Cursor cursor) {
        ArrayList<Item> items = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Item item = new Item();
            item.setID(cursor.getInt(cursor.getColumnIndex(DatabaseString.ITEM_ID)));
            item.setName(cursor.getString(cursor.getColumnIndex(DatabaseString.ITEM_NAME)));
            item.setAddress(cursor.getString(cursor.getColumnIndex(DatabaseString.ITEM_ADDRESS)));
            item.setImage(cursor.getString(cursor.getColumnIndex(DatabaseString.ITEM_IMG)));
            item.setAVGRating(cursor.getDouble(cursor.getColumnIndex(DatabaseString.ITEM_AVGRATING)));
            item.setCategoryID(cursor.getInt(cursor.getColumnIndex(DatabaseString.ITEM_CATEGORYID)));
            item.setDistrictID(cursor.getInt(cursor.getColumnIndex(DatabaseString.ITEM_DISTRICTID)));
            item.setTotalPictures(cursor.getInt(cursor.getColumnIndex(DatabaseString.ITEM_TOTALPICTURES)));
            item.setTotalReviews(cursor.getInt(cursor.getColumnIndex(DatabaseString.ITEM_TOTALREVIEWS)));
            item.setListID(cursor.getInt(cursor.getColumnIndex(DatabaseString.ITEM_LISTID)));
            item.setStreetID(cursor.getInt(cursor.getColumnIndex(DatabaseString.ITEM_STREETID)));
//            item.setReviews(modelReview.get_ReviewByItemID(database, item.getID()));
            items.add(item);
            cursor.moveToNext();

        }
        return items;
    }


//    public ArrayList<Item> get_Item_ByCategoryandListAndCity(SQLiteDatabase database, int CategoryId, int ListID, int CityId, int limitFromIndex) {
//        Cursor cursor;
//        if (ListID == 0) {
//            cursor = database.rawQuery("select * from "
//                    + "(select ID from district where  " + DISTRICT_CITYID + " = " + CityId + ") as " + DISTRICT +
//                    " inner join " + ITEM + " on " + DISTRICT + "." + DISTRICT_ID + " = " + ITEM + "." + ITEM_DISTRICTID +
//                    " where " + ITEM_CATEGORYID + " = " + CategoryId + " order by " + ITEM_ID + " limit " + limitFromIndex + ",10", null);
//        } else {
//            cursor = database.rawQuery("select * from "
//                    + "(select ID from district where  " + DISTRICT_CITYID + " = " + CityId + ") as " + DISTRICT +
//                    " inner join " + ITEM + " on " + DISTRICT + "." + DISTRICT_ID + " = " + ITEM + "." + ITEM_DISTRICTID +
//                    " where " + ITEM_CATEGORYID + " = " + CategoryId + " and " + ITEM_LISTID + " = " + ListID + " order by " + ITEM_ID + " limit " + limitFromIndex + ",10", null);
//        }
//
//        ArrayList<Item> items = get_ListItem(cursor);
//        cursor.close();
//
//        return items;
//    }

//    public void get_Item_ByCategoryandListAndCity(int CategoryId, int ListID, int CityId, int limitFromIndex) {
//        final ArrayList<Item> items = new ArrayList<>();
//        if (ListID == 0) {
//            Call<List<Item>> call = services.listItemByCategoryandAndCity(CategoryId, CityId, limitFromIndex);
//            Log.e("URL",call.request().url().toString());
//            call.enqueue(new Callback<List<Item>>() {
//                @Override
//                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
//                    if (response.isSuccessful()) {
//                        Log.e("item",response.body().get(0).getName());
//                        List<Item> list = response.body();
//                        for (int i = 0; i < list.size(); i++) {
//                            items.add(list.get(i));
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<Item>> call, Throwable t) {
//                    Log.d("MainActivity", "error loading from API" + t.getMessage());
//                }
//            });
//        } else {
//            Call<List<Item>> call = services.listItemByCategoryandListAndCity(CategoryId, ListID, CityId, limitFromIndex);
//            call.enqueue(new Callback<List<Item>>() {
//                @Override
//                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
//                    if (response.isSuccessful()) {
//                        List<Item> list = response.body();
//                        for (int i = 0; i < list.size(); i++) {
//                            items.add(list.get(i));
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<Item>> call, Throwable t) {
//                    Log.d("MainActivity", "error loading from API" + t.getMessage());
//                }
//            });
//        }
//    }


    public ArrayList<Item> get_Item_ByCategoryandListAndDistrict(SQLiteDatabase database, int CategoryId, int ListID, int DistrictID, int limitFromIndex) {
        Cursor cursor;
        if (ListID == 0) {
            cursor = database.rawQuery("select * from " + ITEM + " where " + ITEM_CATEGORYID + " = "
                    + CategoryId + " and " + ITEM_DISTRICTID + " = " + DistrictID + " order by " + ITEM_ID + " limit " + limitFromIndex + ",10", null);
        } else {
            cursor = database.rawQuery("select * from " + ITEM + " where " + ITEM_CATEGORYID + " = "
                    + CategoryId + " and " + ITEM_LISTID + " = " + ListID + " and " + ITEM_DISTRICTID + " = " + DistrictID + " order by " + ITEM_ID + " limit " + limitFromIndex + ",10", null);
        }
        ArrayList<Item> items = get_ListItem(cursor);
        cursor.close();
        return items;
    }

    public ArrayList<Item> get_Item_ByCategoryandListAndStreet(SQLiteDatabase database, int CategoryId, int ListID, int StreetID, int limitFromIndex) {
        Cursor cursor;
        if (ListID == 0) {
            cursor = database.rawQuery("select * from " + ITEM + " where " + ITEM_CATEGORYID + " = "
                    + CategoryId + " and " + ITEM_STREETID + " = " + StreetID + " order by " + ITEM_ID + " limit " + limitFromIndex + ",10", null);
        } else {
            cursor = database.rawQuery("select * from " + ITEM + " where " + ITEM_CATEGORYID + " = "
                    + CategoryId + " and " + ITEM_LISTID + " = " + ListID + " and " + ITEM_STREETID + " = " + StreetID + " order by " + ITEM_ID + " limit " + limitFromIndex + ",10", null);
        }
        ArrayList<Item> items = get_ListItem(cursor);
        cursor.close();
        return items;
    }
}
