package hoa14110071.chieuthusau.foodyversion2.Controller.DistrictController;

import android.content.Context;

import java.util.ArrayList;

import hoa14110071.chieuthusau.foodyversion2.JavaClass.iLoadData;
import hoa14110071.chieuthusau.foodyversion2.Model.ModelDistrict;
import hoa14110071.chieuthusau.foodyversion2.Object.District;

import static hoa14110071.chieuthusau.foodyversion2.Activity.HomeActivity.database;

/**
 * Created by minhh on 4/12/2017.
 */

public class DistrictController implements iDistrictController {
    iLoadData iLoadData;
    ModelDistrict modelDistrict;
    Context context;


    public DistrictController(iLoadData iLoadData, Context context) {
        this.iLoadData = iLoadData;
        modelDistrict = new ModelDistrict();
        this.context = context;
    }

    //Lấy danh sách quận huyện
    @Override
    public void get_District(int CityId) {
        ArrayList<District> districts = modelDistrict.get_District(database, CityId);
        if (districts.size() > 0) {
            iLoadData.loadDistrict(districts);
        } else {
            iLoadData.error();
        }
    }
}
