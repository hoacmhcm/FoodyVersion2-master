package hoa14110071.chieuthusau.foodyversion2.Controller.ItemController;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hoa14110071.chieuthusau.foodyversion2.JavaClass.iLoadData;
import hoa14110071.chieuthusau.foodyversion2.Model.ModelItem;
import hoa14110071.chieuthusau.foodyversion2.Model.ModelReview;
import hoa14110071.chieuthusau.foodyversion2.Object.Item;

import hoa14110071.chieuthusau.foodyversion2.Object.Review;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hoa14110071.chieuthusau.foodyversion2.Activity.HomeActivity.database;
import static hoa14110071.chieuthusau.foodyversion2.Activity.HomeActivity.services;
import static hoa14110071.chieuthusau.foodyversion2.JavaClass.LoadMore.isLoadding;

/**
 * Created by minhh on 4/12/2017.
 */

public class ItemController implements iItemController {
    private static final int LOAD_MORE_IN_CITY = 1;
    private static final int LOAD_MORE_IN_DISTRICT = 2;
    private static final int LOAD_MORE_IN_STREET = 3;
    //    Services services = RetrofitCreate.getService();
    List<Item> itemsRl = new ArrayList<>();
    List<Item> itemsRs = new ArrayList<>();


    iLoadData iLoadData;
    ModelItem modelItem;
    Context context;

    ModelReview modelReview;


    public ItemController(iLoadData iLoadData, Context context) {
        this.iLoadData = iLoadData;
        modelItem = new ModelItem();
        modelReview = new ModelReview();
        this.context = context;
    }

    //Lấy danh sách item theo CityId
    @Override
    public void get_Item_ByCategoryandListAndCity(int CategoryId, final int ListID, int CityId, int limitFromIndex) {
//        ArrayList<Item> items = modelItem.get_Item_ByCategoryandListAndCity(CategoryId, ListID, CityId, limitFromIndex);
//        if (items.size() > 0) {
//            iLoadData.loadItemByCategoryandListAndCity(items);
//        } else {
//            iLoadData.error();
//        }
        if (ListID == 0) {
            Call<List<Item>> call = services.listItemByCategoryandAndCity(CategoryId, CityId, limitFromIndex);
            Log.e("URLCityList0", call.request().url().toString());
            call.enqueue(new Callback<List<Item>>() {
                @Override
                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                    if (response.isSuccessful()) {
                        final List<Item> list = response.body();
                        if (list == null) {
                            return;
                        }
                        for (int i = 0; i < list.size(); i++) {
                            int itemID = list.get(i).getID();
                            Call<List<Review>> call2 = services.getReviewByItemId(itemID);
                            final Item item = list.get(i);
                            call2.enqueue(new Callback<List<Review>>() {
                                @Override
                                public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                                    item.setReviews(response.body());
                                    itemsRl.add(item);
                                    if (itemsRl.size() == list.size()) {
                                        iLoadData.loadItemByCategoryandListAndCity(itemsRl);
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Review>> call, Throwable t) {

                                }
                            });

                        }
//                        Log.e("item",response.body().get(0).getName());
                    } else {
                        Log.e("item", response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Item>> call, Throwable t) {
                    Log.d("MainActivity", "error loading from API" + t.getMessage());
                    iLoadData.error();
                }
            });


        } else {
            Call<List<Item>> call = services.listItemByCategoryandListAndCity(CategoryId, ListID, CityId, limitFromIndex);
            Log.e("URLCityList", call.request().url().toString());
            call.enqueue(new Callback<List<Item>>() {
                @Override
                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                    if (response.isSuccessful()) {
                        final List<Item> list = response.body();
                        if (list == null) {
                            return;
                        }
                        for (int i = 0; i < list.size(); i++) {
                            int itemID = list.get(i).getID();
                            Call<List<Review>> call2 = services.getReviewByItemId(itemID);
                            final Item item = list.get(i);
                            call2.enqueue(new Callback<List<Review>>() {
                                @Override
                                public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                                    item.setReviews(response.body());
                                    itemsRl.add(item);
                                    if (itemsRl.size() == list.size()) {
                                        iLoadData.loadItemByCategoryandListAndCity(itemsRl);
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Review>> call, Throwable t) {

                                }
                            });

                        }
//                        Log.e("item",response.body().get(0).getName());
                    } else {
                        Log.e("item", response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Item>> call, Throwable t) {
                    Log.d("MainActivity", "error loading from API" + t.getMessage());
                    iLoadData.error();
                }
            });
        }

    }

    //Lấy danh sách item theo DistrictId
    @Override
    public void get_Item_ByCategoryandListAndDistrict(int CategoryId, int ListID, int DistrictID, int limitFromIndex) {
//        ArrayList<Item> items = modelItem.get_Item_ByCategoryandListAndDistrict(database, CategoryId, ListID, DistrictID, limitFromIndex);
//        if (items.size() > 0) {
//            iLoadData.loadItemByCategoryandListAndDistrict(items);
//        } else {
//            iLoadData.error();
//        }

        if (ListID == 0) {
            Call<List<Item>> call = services.listItemByCategoryandAndDistrict(CategoryId, DistrictID, limitFromIndex);
            Log.e("URLDistrictList0", call.request().url().toString());
            call.enqueue(new Callback<List<Item>>() {
                @Override
                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                    if (response.isSuccessful()) {
                        final List<Item> list = response.body();
                        if (list == null) {
                            return;
                        }
                        for (int i = 0; i < list.size(); i++) {
                            int itemID = list.get(i).getID();
                            Call<List<Review>> call2 = services.getReviewByItemId(itemID);
                            final Item item = list.get(i);
                            call2.enqueue(new Callback<List<Review>>() {
                                @Override
                                public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                                    item.setReviews(response.body());
                                    itemsRl.add(item);
                                    if (itemsRl.size() == list.size()) {
                                        iLoadData.loadItemByCategoryandListAndDistrict(itemsRl);
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Review>> call, Throwable t) {

                                }
                            });

                        }
//                        Log.e("item",response.body().get(0).getName());
                    } else {
                        Log.e("item", response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Item>> call, Throwable t) {

                }
            });
        } else {
            Call<List<Item>> call = services.listItemByCategoryandListAndDistrict(CategoryId, ListID, DistrictID, limitFromIndex);
            Log.e("URLDistrictList", call.request().url().toString());
            call.enqueue(new Callback<List<Item>>() {
                @Override
                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                    if (response.isSuccessful()) {
                        final List<Item> list = response.body();
                        if (list == null) {
                            return;
                        }
                        for (int i = 0; i < list.size(); i++) {
                            int itemID = list.get(i).getID();
                            Call<List<Review>> call2 = services.getReviewByItemId(itemID);
                            final Item item = list.get(i);
                            call2.enqueue(new Callback<List<Review>>() {
                                @Override
                                public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                                    item.setReviews(response.body());
                                    itemsRl.add(item);
                                    if (itemsRl.size() == list.size()) {
                                        iLoadData.loadItemByCategoryandListAndDistrict(itemsRl);
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Review>> call, Throwable t) {

                                }
                            });

                        }
//                        Log.e("item",response.body().get(0).getName());
                    } else {
                        Log.e("item", response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Item>> call, Throwable t) {

                }
            });
        }


    }

    //Lấy danh sách item theo StreetId
    @Override
    public void get_Item_ByCategoryandListAndStreet(int CategoryId, int ListID, int StreetID,
                                                    int limitFromIndex) {
//        ArrayList<Item> items = modelItem.get_Item_ByCategoryandListAndStreet(database, CategoryId, ListID, StreetID, limitFromIndex);
//        if (items.size() > 0) {
//            iLoadData.loadItemByCategoryandListAndStreet(items);
//        } else {
//            iLoadData.error();
//        }
        if (ListID == 0) {
            Call<List<Item>> call = services.listItemByCategoryandAndStreet(CategoryId, StreetID, limitFromIndex);
            Log.e("URLStreetList0", call.request().url().toString());
            call.enqueue(new Callback<List<Item>>() {
                @Override
                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                    if (response.isSuccessful()) {
                        final List<Item> list = response.body();
                        if (list == null) {
                            return;
                        }
                        for (int i = 0; i < list.size(); i++) {
                            int itemID = list.get(i).getID();
                            Call<List<Review>> call2 = services.getReviewByItemId(itemID);
                            final Item item = list.get(i);
                            call2.enqueue(new Callback<List<Review>>() {
                                @Override
                                public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                                    item.setReviews(response.body());
                                    itemsRl.add(item);
                                    if (itemsRl.size() == list.size()) {
                                        iLoadData.loadItemByCategoryandListAndStreet(itemsRl);
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Review>> call, Throwable t) {

                                }
                            });

                        }
//                        Log.e("item",response.body().get(0).getName());
                    } else {
                        Log.e("item", response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Item>> call, Throwable t) {

                }
            });
        } else {
            Call<List<Item>> call = services.listItemByCategoryandListAndStreet(CategoryId, ListID, StreetID, limitFromIndex);
            Log.e("URLStreetList", call.request().url().toString());
            call.enqueue(new Callback<List<Item>>() {
                @Override
                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                    if (response.isSuccessful()) {
                        final List<Item> list = response.body();
                        if (list == null) {
                            return;
                        }
                        for (int i = 0; i < list.size(); i++) {
                            int itemID = list.get(i).getID();
                            Call<List<Review>> call2 = services.getReviewByItemId(itemID);
                            final Item item = list.get(i);
                            call2.enqueue(new Callback<List<Review>>() {
                                @Override
                                public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                                    item.setReviews(response.body());
                                    itemsRl.add(item);
                                    if (itemsRl.size() == list.size()) {
                                        iLoadData.loadItemByCategoryandListAndStreet(itemsRl);
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Review>> call, Throwable t) {

                                }
                            });

                        }
//                        Log.e("item",response.body().get(0).getName());
                    } else {
                        Log.e("item", response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Item>> call, Throwable t) {

                }
            });
        }
    }

    //Lấy thêm danh sách item theo CityId
    @Override
    public List<Item> get_Item_ByCategoryandListAndCityLoadMore(int CategoryId,
                                                                int ListID, int CityId, int limitFromIndex, FrameLayout frameProgress) {
        return getListLoadMore(CategoryId, ListID, CityId, 0, 0, limitFromIndex, frameProgress, LOAD_MORE_IN_CITY);
    }

    //Lấy thêm danh sách item theo DistrictId
    @Override
    public List<Item> get_Item_ByCategoryandListAndDistrictLoadMore(int CategoryId,
                                                                    int ListID, int DistrictID, int limitFromIndex, FrameLayout frameProgress) {
        return getListLoadMore(CategoryId, ListID, 0, DistrictID, 0, limitFromIndex, frameProgress, LOAD_MORE_IN_DISTRICT);
    }

    //Lấy thêm danh sách item theo StreetId
    @Override
    public List<Item> get_Item_ByCategoryandListAndStreetLoadMore(int CategoryId,
                                                                  int ListID, int StreetID, int limitFromIndex, FrameLayout frameProgress) {
        return getListLoadMore(CategoryId, ListID, 0, 0, StreetID, limitFromIndex, frameProgress, LOAD_MORE_IN_STREET);
    }

    //Hiện progressbar và load thêm dữ liệu
    private List<Item> getListLoadMore(int CategoryId, int ListID, int CityId,
                                       int DistrictId, int StreetId, int limitFromIndex, final FrameLayout frameProgress,
                                       int loadBy) {
        if (loadBy == LOAD_MORE_IN_CITY) {
//            items = modelItem.get_Item_ByCategoryandListAndCity(CategoryId, ListID, CityId, limitFromIndex);
            if (ListID == 0) {
                Call<List<Item>> call = services.listItemByCategoryandAndCity(CategoryId, CityId, limitFromIndex);
                Log.e("URLLoadMoreCityList0", call.request().url().toString());
                call.enqueue(new Callback<List<Item>>() {
                    @Override
                    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                        if (response.isSuccessful()) {
                            final List<Item> list = response.body();
                            if (list == null) {
                                return;
                            }
                            for (int i = 0; i < list.size(); i++) {
                                int itemID = list.get(i).getID();
                                Call<List<Review>> call2 = services.getReviewByItemId(itemID);
                                final Item item = list.get(i);
                                call2.enqueue(new Callback<List<Review>>() {
                                    @Override
                                    public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                                        item.setReviews(response.body());
                                        itemsRs.add(item);
                                        if (itemsRs.size() == list.size())
                                            iLoadData.loadMoreResultItem(itemsRs);
                                    }

                                    @Override
                                    public void onFailure(Call<List<Review>> call, Throwable t) {

                                    }
                                });

                            }
//                        Log.e("item",response.body().get(0).getName());
                        } else {
                            Log.e("item", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Item>> call, Throwable t) {
                        Log.d("MainActivity", "error loading from API" + t.getMessage());
                        iLoadData.error();
                    }
                });
            } else {
                Call<List<Item>> call = services.listItemByCategoryandListAndCity(CategoryId, ListID, CityId, limitFromIndex);
                Log.e("URLLoadMoreCityList", call.request().url().toString());
                call.enqueue(new Callback<List<Item>>() {
                    @Override
                    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                        if (response.isSuccessful()) {
                            final List<Item> list = response.body();
                            if (list == null) {
                                return;
                            }
                            for (int i = 0; i < list.size(); i++) {
                                int itemID = list.get(i).getID();
                                Call<List<Review>> call2 = services.getReviewByItemId(itemID);
                                final Item item = list.get(i);
                                call2.enqueue(new Callback<List<Review>>() {
                                    @Override
                                    public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                                        item.setReviews(response.body());
                                        itemsRs.add(item);
                                    }

                                    @Override
                                    public void onFailure(Call<List<Review>> call, Throwable t) {

                                    }
                                });

                            }
                        } else {
                            Log.e("item", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Item>> call, Throwable t) {
                        Log.d("MainActivity", "error loading from API" + t.getMessage());
                        iLoadData.error();
                    }
                });
            }
        } else if (loadBy == LOAD_MORE_IN_DISTRICT) {
//            items = modelItem.get_Item_ByCategoryandListAndDistrict(database, CategoryId, ListID, DistrictId, limitFromIndex);
        } else if (loadBy == LOAD_MORE_IN_STREET) {
//            items = modelItem.get_Item_ByCategoryandListAndStreet(database, CategoryId, ListID, StreetId, limitFromIndex);
        }
        if (!itemsRs.isEmpty()) {
            frameProgress.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    frameProgress.setVisibility(View.GONE);
                }
            }, 1000);
        }
        return itemsRs;
    }
}
