package hoa14110071.chieuthusau.foodyversion2.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;

import hoa14110071.chieuthusau.foodyversion2.Adapter.CityAdapter;
import hoa14110071.chieuthusau.foodyversion2.Controller.CityController.CityController;
import hoa14110071.chieuthusau.foodyversion2.JavaClass.iLoadDataCity;
import hoa14110071.chieuthusau.foodyversion2.Object.City;
import hoa14110071.chieuthusau.foodyversion2.R;

import static hoa14110071.chieuthusau.foodyversion2.Adapter.CityAdapter.nextIndexChangedCity;

public class ChooseCityActivity extends AppCompatActivity implements iLoadDataCity {

    //Khởi tạo các widget
    private Toolbar toolbarCity;
    private ListView lstCity;
    private CityAdapter cityAdapter;
    private Button btnXongCity;

    //Khai báo CityController
    CityController cityController;

    //tạo một List City
    ArrayList<City> listCity;

    //Tên City được chọn
    String CityNameChoose;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        //Khởi tạo toolbox
        toolbarCity = (Toolbar) findViewById(R.id.toolbarCity);
        lstCity = (ListView) findViewById(R.id.lstCity);
        btnXongCity = (Button) findViewById(R.id.btnXongCity);


        if (toolbarCity != null) {
            setSupportActionBar(toolbarCity);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        //Lấy danh sách City
        cityController= new CityController(this,getApplication());
        cityController.get_City();


        //sự kiện onClickItem trên listCity
        lstCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //lấy vị trí được chọn đưa vào Adapter để xử lí
                nextIndexChangedCity = position;
                //lấy tên City được chọn
                cityController.get_CityName(listCity.get(nextIndexChangedCity).getId());
                cityAdapter.notifyDataSetChanged();
            }
        });
        //Button hoàn tất việc chọn thành phố
        btnXongCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tạo một intent để truyền dễ liệu sang Activity khác
                Intent returnIntent = new Intent();
                returnIntent.putExtra("Id",listCity.get(nextIndexChangedCity).getId());
                returnIntent.putExtra("Name", CityNameChoose);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });


    }


    //load City lên listview
    @Override
    public void loadCity(ArrayList<City> cities) {
        listCity = cities;
        cityAdapter = new CityAdapter(this,R.layout.list_row_city,cities);
        lstCity.setAdapter(cityAdapter);
    }
    //lấy tên City từ Controller
    @Override
    public void loadCityName(String CityName) {
        CityNameChoose = CityName;
    }

    @Override
    public void error() {

    }
}
