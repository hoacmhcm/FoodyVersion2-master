package hoa14110071.chieuthusau.foodyversion2.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import hoa14110071.chieuthusau.foodyversion2.Fragment.fragmentEatWhat;
import hoa14110071.chieuthusau.foodyversion2.Fragment.fragmentWhere;
import hoa14110071.chieuthusau.foodyversion2.Object.Review;
import hoa14110071.chieuthusau.foodyversion2.R;
import hoa14110071.chieuthusau.foodyversion2.Services.RetrofitCreate;
import hoa14110071.chieuthusau.foodyversion2.Services.Services;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hoa14110071.chieuthusau.foodyversion2.Fragment.fragmentEatWhat.changeCityEatWhat;
import static hoa14110071.chieuthusau.foodyversion2.Fragment.fragmentWhere.changeCity;

public class HomeActivity extends AppCompatActivity {
    //Tên và đường dẫn database
    public static final String DATABASE_NAME = "Foody.sqlite";
    public static final String DB_PATH_SUFFIX = "/databases/";
    //tạo một database public tĩnh
    public static SQLiteDatabase database = null;

    //khai báo các widget
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    public static Services services;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //khởi tạo widget
        initControls();
        setupViewPager(viewPager);
        setupTablayout(tabLayout);
        //mở database
        openDataBase();
        database = this.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);

        services = RetrofitCreate.getService();
    }


    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    //setup cho ViewPager
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragmentWhere(), "Ở đâu");
        adapter.addFragment(new fragmentEatWhat(), "Ăn gì");
        viewPager.setAdapter(adapter);
    }

    //setup cho Tablayout
    private void setupTablayout(TabLayout tabLayout) {
        tabLayout.setupWithViewPager(viewPager);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
    }


    //Xử lý City được trả về từ CityActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                int CityId = (data.getIntExtra("Id", 1));
                String CityName = (data.getStringExtra("Name"));
                changeCity(this, CityId, CityName);
            }
        }else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                int CityId = (data.getIntExtra("Id", 1));
                String CityName = (data.getStringExtra("Name"));
                changeCityEatWhat(this, CityId, CityName);
            }
        }
    }


    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists()) {
            CopyDataBaseFromAsset();
            System.out.println("Copying sucess from Assets folder");
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    //copy Database từ Assets
    private void CopyDataBaseFromAsset() {
        try {
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFile = getPath();

            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);

            if (!f.exists()) {
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();

        } catch (Exception ex) {
            Log.d("Error", ex.toString());
        }
    }

    //Lấy đường dẫn
    private String getPath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

}
