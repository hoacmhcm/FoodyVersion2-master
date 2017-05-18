package hoa14110071.chieuthusau.foodyversion2.Controller.CategoryController;

import android.content.Context;

import java.util.ArrayList;

import hoa14110071.chieuthusau.foodyversion2.JavaClass.iLoadData;
import hoa14110071.chieuthusau.foodyversion2.Model.ModelCategory;
import hoa14110071.chieuthusau.foodyversion2.Object.Category;

import static hoa14110071.chieuthusau.foodyversion2.Activity.HomeActivity.database;

/**
 * Created by minhh on 4/12/2017.
 */

public class CategoryController implements iCategoryController {
    iLoadData iLoadData;
    ModelCategory modelCategory;
    Context context;

    public CategoryController(iLoadData iLoadData, Context context) {
        this.iLoadData = iLoadData;
        modelCategory = new ModelCategory();
        this.context = context;
    }
    //Lấy Category
    @Override
    public void get_Category() {
        ArrayList<Category> categories = modelCategory.get_Category(database);
        if (categories.size() > 0) {
            iLoadData.loadCategory(categories);
        } else {
            iLoadData.error();
        }
    }
}
