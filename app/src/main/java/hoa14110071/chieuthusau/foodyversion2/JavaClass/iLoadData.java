package hoa14110071.chieuthusau.foodyversion2.JavaClass;

import java.util.ArrayList;
import java.util.List;

import hoa14110071.chieuthusau.foodyversion2.Object.Category;
import hoa14110071.chieuthusau.foodyversion2.Object.District;
import hoa14110071.chieuthusau.foodyversion2.Object.Item;
import hoa14110071.chieuthusau.foodyversion2.Object.ListByType;
import hoa14110071.chieuthusau.foodyversion2.Object.Review;

/**
 * Created by minhh on 4/12/2017.
 */

public interface iLoadData {
    //interface chịu trách nhiệm loadData lên từ controller lên view
    void loadCategory(ArrayList<Category> categories);
    void loadListByType(ArrayList<ListByType> listByTypes);
    void loadDistrict(ArrayList<District> districts);

    void loadItemByCategoryandListAndCity(List<Item> items);
    void loadItemByCategoryandListAndDistrict(List<Item> items);
    void loadItemByCategoryandListAndStreet(List<Item> items);

    void loadMoreResultItem(List<Item> items);


    void error();
}
