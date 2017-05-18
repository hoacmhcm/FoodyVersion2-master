package hoa14110071.chieuthusau.foodyversion2.Controller.ListByTypeController;

import android.content.Context;

import java.util.ArrayList;

import hoa14110071.chieuthusau.foodyversion2.JavaClass.iLoadData;
import hoa14110071.chieuthusau.foodyversion2.Model.ModelListByType;
import hoa14110071.chieuthusau.foodyversion2.Object.ListByType;

import static hoa14110071.chieuthusau.foodyversion2.Activity.HomeActivity.database;

/**
 * Created by minhh on 4/12/2017.
 */

public class ListByTypeController implements iListByTypeController {
    iLoadData iLoadData;
    ModelListByType modelListByType;
    Context context;

    public ListByTypeController(iLoadData iLoadData, Context context) {
        this.iLoadData = iLoadData;
        modelListByType = new ModelListByType();
        this.context = context;
    }

    //Lấy danh sách danh mục
    @Override
    public void get_ListByType() {
        ArrayList<ListByType> listByTypes = modelListByType.get_ListByType(database);
        if (listByTypes.size() > 0) {
            iLoadData.loadListByType(listByTypes);
        } else {
            iLoadData.error();
        }
    }
}
