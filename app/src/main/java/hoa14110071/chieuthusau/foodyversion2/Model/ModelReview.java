package hoa14110071.chieuthusau.foodyversion2.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.RecognizerIntent;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hoa14110071.chieuthusau.foodyversion2.Object.Review;
import hoa14110071.chieuthusau.foodyversion2.Services.RetrofitCreate;
import hoa14110071.chieuthusau.foodyversion2.Services.Services;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hoa14110071.chieuthusau.foodyversion2.Activity.HomeActivity.services;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.REVIEW;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.REVIEW_AVATAR;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.REVIEW_COMMENT;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.REVIEW_COMMENTTRIM;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.REVIEW_ID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.REVIEW_ITEMID;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.REVIEW_NAME;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.REVIEW_RATING;
import static hoa14110071.chieuthusau.foodyversion2.Database.DatabaseString.REVIEW_REVIEWURL;

/**
 * Created by minhh on 4/11/2017.
 */

public class ModelReview{

    public List<Review> get_ReviewByItemID(SQLiteDatabase database, int ItemID) {
        ArrayList<Review> reviews = new ArrayList<>();
        String query = "select * from " + REVIEW + " where " + REVIEW_ITEMID + " =" + ItemID + "";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Review review = new Review();
            review.setId(cursor.getInt(cursor.getColumnIndex(REVIEW_ID)));
            review.setName(cursor.getString(cursor.getColumnIndex(REVIEW_NAME)));
            review.setImage(cursor.getString(cursor.getColumnIndex(REVIEW_AVATAR)));
            review.setComment(cursor.getString(cursor.getColumnIndex(REVIEW_COMMENT)));
            review.setCommentTrim(cursor.getString(cursor.getColumnIndex(REVIEW_COMMENTTRIM)));
            review.setItemId(cursor.getInt(cursor.getColumnIndex(REVIEW_ITEMID)));
            review.setRating(cursor.getDouble(cursor.getColumnIndex(REVIEW_RATING)));
            review.setItemId(cursor.getInt(cursor.getColumnIndex(REVIEW_ITEMID)));
            reviews.add(review);
            cursor.moveToNext();
        }
        cursor.close();
        return reviews;
    }

//    public void get_ReviewByItemID(int ItemId)
//    {
//
//
//        return
//    }

//    public void get_ReviewByItemID(int ItemID) {
//        final List<Review> reviews = new ArrayList<>();
//        Call<List<Review>> call2 = services.getReviewByItemId(ItemID);
//        Log.e("ReviewURL",call2.request().url().toString());
//        call2.enqueue(new Callback<List<Review>>() {
//            @Override
//            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Review>> call, Throwable t) {
//
//            }
//        });
//        return reviews;
//    }
//
//
//    @Override
//    public void onResponse(Call<Review> call, Response<Review> response) {
//        if(response.isSuccessful())
//        {
//            List<Review> list = response.body();
//            for(int i=0;i<list.size();i++)
//            {
//                reviews.add(list.get(i));
//            }
//        }
//    }
//
//    @Override
//    public void onFailure(Call<Review> call, Throwable t) {
//
//    }
}
