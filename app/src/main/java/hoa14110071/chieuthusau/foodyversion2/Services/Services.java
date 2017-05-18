package hoa14110071.chieuthusau.foodyversion2.Services;

import java.util.List;

import hoa14110071.chieuthusau.foodyversion2.Object.Item;
import hoa14110071.chieuthusau.foodyversion2.Object.Review;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by minhh on 12-May-17.
 */

public interface Services {
    @GET("items")
    Call<List<Item>> listItemByCategoryandAndCity(@Query("CategoryId") int CategoryId,@Query("CityId") int CityId, @Query("limitFromIndex")int limitFromIndex);

    @GET("items")
    Call<List<Item>> listItemByCategoryandListAndCity(@Query("CategoryId") int CategoryId, @Query("ListId") int ListId, @Query("CityId") int CityId, @Query("limitFromIndex")int limitFromIndex);

    @GET("items")
    Call<List<Item>> listItemByCategoryandAndDistrict(@Query("CategoryId") int CategoryId,@Query("DistrictId") int DistrictId, @Query("limitFromIndex")int limitFromIndex);

    @GET("items")
    Call<List<Item>> listItemByCategoryandListAndDistrict(@Query("CategoryId") int CategoryId, @Query("ListId") int ListId, @Query("DistrictId") int DistrictId, @Query("limitFromIndex")int limitFromIndex);

    @GET("items")
    Call<List<Item>> listItemByCategoryandAndStreet(@Query("CategoryId") int CategoryId,@Query("StreetId") int StreetId, @Query("limitFromIndex")int limitFromIndex);

    @GET("items")
    Call<List<Item>> listItemByCategoryandListAndStreet(@Query("CategoryId") int CategoryId, @Query("ListId") int ListId, @Query("StreetId") int StreetId, @Query("limitFromIndex")int limitFromIndex);

    @GET("reviews")
    Call<List<Review>> getReviewByItemId(@Query("ItemId")int ItemId);
}