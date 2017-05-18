package hoa14110071.chieuthusau.foodyversion2.Controller.ItemController;

import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import hoa14110071.chieuthusau.foodyversion2.Object.Item;

/**
 * Created by minhh on 4/12/2017.
 */

public interface iItemController {
    void get_Item_ByCategoryandListAndCity(int CategoryId, int ListID, int CityId, int limitFromIndex);
    void get_Item_ByCategoryandListAndDistrict(int CategoryId, int ListID, int DistrictID, int limitFromIndex);
    void get_Item_ByCategoryandListAndStreet(int CategoryId, int ListID, int StreetID, int limitFromIndex);

    void get_Item_ByCategoryandListAndCityLoadMore(int CategoryId, int ListID, int CityId, int limitFromIndex, FrameLayout frameProgress);
    void get_Item_ByCategoryandListAndDistrictLoadMore(int CategoryId, int ListID, int DistrictID, int limitFromIndex, FrameLayout frameProgress);
    void get_Item_ByCategoryandListAndStreetLoadMore(int CategoryId, int ListID, int StreetID, int limitFromIndex, FrameLayout frameProgress);

}
