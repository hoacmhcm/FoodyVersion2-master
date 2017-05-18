package hoa14110071.chieuthusau.foodyversion2.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hoa14110071.chieuthusau.foodyversion2.Activity.ChooseCityActivity;
import hoa14110071.chieuthusau.foodyversion2.Adapter.CategoryAdapter;
import hoa14110071.chieuthusau.foodyversion2.Adapter.CustomAdapterExpandableListview;
import hoa14110071.chieuthusau.foodyversion2.Adapter.ListByTypeAdapter;
import hoa14110071.chieuthusau.foodyversion2.Adapter.ItemWhereAdapter;
import hoa14110071.chieuthusau.foodyversion2.Controller.CategoryController.CategoryController;
import hoa14110071.chieuthusau.foodyversion2.Controller.DistrictController.DistrictController;
import hoa14110071.chieuthusau.foodyversion2.Controller.ItemController.ItemController;
import hoa14110071.chieuthusau.foodyversion2.Controller.ListByTypeController.ListByTypeController;
import hoa14110071.chieuthusau.foodyversion2.JavaClass.LoadMore;
import hoa14110071.chieuthusau.foodyversion2.JavaClass.iLoadData;
import hoa14110071.chieuthusau.foodyversion2.JavaClass.iLoadMore;
import hoa14110071.chieuthusau.foodyversion2.Model.ModelReview;
import hoa14110071.chieuthusau.foodyversion2.Object.Category;
import hoa14110071.chieuthusau.foodyversion2.Object.District;
import hoa14110071.chieuthusau.foodyversion2.Object.Item;
import hoa14110071.chieuthusau.foodyversion2.Object.ListByType;
import hoa14110071.chieuthusau.foodyversion2.Object.Review;
import hoa14110071.chieuthusau.foodyversion2.Object.Street;
import hoa14110071.chieuthusau.foodyversion2.R;
import hoa14110071.chieuthusau.foodyversion2.Services.RetrofitCreate;
import hoa14110071.chieuthusau.foodyversion2.Services.Services;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hoa14110071.chieuthusau.foodyversion2.Activity.HomeActivity.database;
import static hoa14110071.chieuthusau.foodyversion2.Activity.HomeActivity.services;
import static hoa14110071.chieuthusau.foodyversion2.JavaClass.LoadMore.isLoadding;


public class fragmentWhere extends Fragment implements TabHost.OnTabChangeListener, View.OnClickListener, iLoadData, iLoadMore {
    public static TabHost tabHost;

    //tab Mới nhất
    private ListView lstMoiNhatWhere;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> listCategories;

    //tab Danh mục
    private ListView lstDanhMucWhere;
    private ListByTypeAdapter listByTypeAdapter;
    private ArrayList<ListByType> listByTypesWhere;

    //Tab Thành phố
    private static ExpandableListView exLstWhere;
    private static CustomAdapterExpandableListview customAdapterExpandableListview;

    //Dữ liệu thành phố và quận huyện
    public static ArrayList<District> listDistrictWhere;
    public static HashMap<District, List<Street>> mData;

    //Khai báo các biến liên quan đến item cần load lên
    public static ItemWhereAdapter itemWhereAdapter;
    private List<Item> itemsWhere = new ArrayList<>();
    public static RecyclerView recycler_list_items;
    public static LoadMore loadMore;
    FrameLayout frame_progress;

    //Button chọn thành phố và TextView hiện tên thành phố
    private static TextView tvChooseCity;
    private Button btnChooseCity;

    public static TabWidget tabWidget;

    //biến quản lý việc truy vấn
    private static boolean tabStreetQuery = false;
    private static boolean tabTPHCMQuery = false;

    private Button btnHuyWhereMoiNhat;
    private Button btnHuyWhereDanhMuc;
    private Button btnHuyWhereTPHCM;

    //khai báo các controller
    CategoryController categoryController;
    ListByTypeController listByTypeController;
    static DistrictController districtController;
    static ItemController itemController;


    //biến vị trí được chọn
    public static int newIndexChangedMoiNhat = 0;
    public static int newIndexChangedDanhMuc = 0;

    //các biến ID hiện thời được chọn
    public static int CategoryIdWhere = 1;
    public static int ListIdWhere = 0;
    public static int DistrictId = 1;
    public static int CityId = 1;
    public static int StreetID;

    static boolean isError = false;

    static boolean isLoadMore = false;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //khởi tạo các controller
        categoryController = new CategoryController(this, getContext());
        listByTypeController = new ListByTypeController(this, getContext());
        districtController = new DistrictController(this, getContext());
        itemController = new ItemController(this, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_where, container, false);
        //khởi tạo các widget
        tabHost = (TabHost) view.findViewById(R.id.tabhost);
        lstMoiNhatWhere = (ListView) view.findViewById(R.id.lstMoiNhatWhere);
        lstDanhMucWhere = (ListView) view.findViewById(R.id.lstDanhMucWhere);
        exLstWhere = (ExpandableListView) view.findViewById(R.id.exLstWhere);
        tvChooseCity = (TextView) view.findViewById(R.id.tvChooseCity);
        btnChooseCity = (Button) view.findViewById(R.id.btnChooseCity);
        recycler_list_items = (RecyclerView) view.findViewById(R.id.recycler_list_items);
        frame_progress = (FrameLayout) view.findViewById(R.id.frame_progress);

        btnChooseCity.setOnClickListener(this);
        setupButtonHuy(view);
        //khởi tạo tabhost
        tabHostSetup();

        //Lấy danh sách từ controller
        categoryController.get_Category();
        listByTypeController.get_ListByType();
        districtController.get_District(DistrictId);
        itemController.get_Item_ByCategoryandListAndCity(CategoryIdWhere, ListIdWhere, CityId, 0);


        return view;
    }

    private void setupButtonHuy(View view) {
        btnHuyWhereMoiNhat = (Button) view.findViewById(R.id.btnHuyWhereMoiNhat);
        btnHuyWhereDanhMuc = (Button) view.findViewById(R.id.btnHuyWhereDanhMuc);
        btnHuyWhereTPHCM = (Button) view.findViewById(R.id.btnHuyWhereTPHCM);

        btnHuyWhereMoiNhat.setOnClickListener(this);
        btnHuyWhereDanhMuc.setOnClickListener(this);
        btnHuyWhereTPHCM.setOnClickListener(this);
    }


    private void tabHostSetup() {
        tabHost.setup();

        TabHost.TabSpec tabMoiNhat = tabHost.newTabSpec("Moi nhat");
        tabMoiNhat.setContent(R.id.tabMoiNhatWhere);
        tabMoiNhat.setIndicator("Mới nhất");
        tabHost.addTab(tabMoiNhat);

        TabHost.TabSpec tabDanhMuc = tabHost.newTabSpec("Danh muc");
        tabDanhMuc.setIndicator("Danh mục");
        tabDanhMuc.setContent(R.id.tabDanhMucWhere);
        tabHost.addTab(tabDanhMuc);

        TabHost.TabSpec tabTPHCM = tabHost.newTabSpec("TPHCM");
        tabTPHCM.setIndicator("TPHCM");
        tabTPHCM.setContent(R.id.tabTPHCMWhere);
        tabHost.addTab(tabTPHCM);

        TabHost.TabSpec tabMain = tabHost.newTabSpec("Main");
        tabMain.setIndicator("MAIN");
        tabMain.setContent(R.id.tabMainWhere);
        tabHost.addTab(tabMain);

        TabHost.TabSpec tabNothing = tabHost.newTabSpec("Nothing");
        tabNothing.setIndicator("Nothing");
        tabNothing.setContent(R.id.tabNothing);
        tabHost.addTab(tabNothing);

        tabWidget = tabHost.getTabWidget();

        final TextView tvMoiNhat = (TextView) tabWidget.getChildTabViewAt(0).findViewById(android.R.id.title);
        final TextView tvDanhMuc = (TextView) tabWidget.getChildTabViewAt(1).findViewById(android.R.id.title);
        final TextView tvTPHCM = (TextView) tabWidget.getChildTabViewAt(2).findViewById(android.R.id.title);

        tvMoiNhat.setTextColor(getContext().getResources().getColor(R.color.colorRed));
        tvDanhMuc.setTextColor(getContext().getResources().getColor(R.color.colorPressed));
        tvTPHCM.setTextColor(getContext().getResources().getColor(R.color.colorPressed));

        tvMoiNhat.setAllCaps(false);
        tvDanhMuc.setAllCaps(false);
        tvTPHCM.setAllCaps(false);

        tabWidget.getChildAt(3).setVisibility(View.GONE);
        tabWidget.getChildAt(4).setVisibility(View.GONE);


        tabWidget.getChildAt(0).setBackgroundResource(R.drawable.background_tabhost_unselected);
        tabWidget.getChildAt(1).setBackgroundResource(R.drawable.background_tabhost_unselected);
        tabWidget.getChildAt(2).setBackgroundResource(R.drawable.background_tabhost_unselected);

        tabHost.setCurrentTab(3);

        tabHost.setOnTabChangedListener(this);

    }

    //TabHostlistener
    @Override
    public void onTabChanged(String tabId) {//nếu tab được chọn thì sẽ đổi màu xám ngược lại có màu trắng
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            tabWidget.getChildAt(i).setBackgroundResource(R.drawable.background_tabhost_unselected);
        }
        tabWidget.getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.background_tabhost_selected);
    }

    //Chọn vào tỉnh
    public static void setOnGroup(Context context, int groupPosition) {
        final TextView tvTPHCM = (TextView) tabHost.getTabWidget().getChildTabViewAt(2).findViewById(android.R.id.title);
        tvTPHCM.setText(listDistrictWhere.get(groupPosition).getName());
        tvTPHCM.setTextColor(context.getResources().getColor(R.color.colorRed));
        DistrictId = listDistrictWhere.get(groupPosition).getId();
        tabTPHCMQuery = true;
        tabStreetQuery = false;
        loadNew(context);
    }

    //Chọn vào đường
    public static void setOnChild(Context context, int groupPosition, int childPosition) {
        final TextView tvTPHCM = (TextView) tabWidget.getChildTabViewAt(2).findViewById(android.R.id.title);
        tvTPHCM.setText(String.valueOf(mData.get(listDistrictWhere.get(groupPosition)).get(childPosition).getName()));
        tvTPHCM.setTextColor(context.getResources().getColor(R.color.colorRed));

        StreetID = mData.get(listDistrictWhere.get(groupPosition)).get(childPosition).getId();
        tabTPHCMQuery = false;
        tabStreetQuery = true;
        loadNew(context);
    }

    //Chọn vào thành phố mới sẽ load lại tỉnh
    public static void changeCity(Context context, int CityID, String CityName) {
        districtController.get_District(CityID);

        final TextView tvTPHCM = (TextView) tabWidget.getChildTabViewAt(2).findViewById(android.R.id.title);
        tvTPHCM.setText(CityName);
        tvTPHCM.setTextColor(context.getResources().getColor(R.color.colorPressed));
        tvChooseCity.setText(CityName);

        tabTPHCMQuery = false;
        tabStreetQuery = false;
        loadNew(context);

        tabHost.setCurrentTab(3);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnHuyWhereMoiNhat:
            case R.id.btnHuyWhereDanhMuc:
            case R.id.btnHuyWhereTPHCM:
                tabHost.setCurrentTab(3);
                break;
            case R.id.btnChooseCity:
                Intent intent = new Intent(getContext(), ChooseCityActivity.class);
                getActivity().startActivityForResult(intent, 1);
                break;
        }
    }

    //Load thêm dữ liệu
    @Override
    public void loadMore(int countItem) {
        List<Item> items;
        isLoadMore= true;
        if (tabTPHCMQuery) {
            items = itemController.get_Item_ByCategoryandListAndDistrictLoadMore(CategoryIdWhere, ListIdWhere, DistrictId, countItem, frame_progress);
        } else if (tabStreetQuery) {
            items = itemController.get_Item_ByCategoryandListAndStreetLoadMore(CategoryIdWhere, ListIdWhere, StreetID, countItem, frame_progress);
        } else {
            items = itemController.get_Item_ByCategoryandListAndCityLoadMore(CategoryIdWhere, ListIdWhere, CityId, countItem, frame_progress);
        }
        itemsWhere.addAll(items);
        itemWhereAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreResultItem(List<Item> items) {
//        itemsWhere.addAll(items);
//        Log.e("Log",itemsWhere.get(itemsWhere.size()-1).getName());
//        itemWhereAdapter.notifyDataSetChanged();
    }

    //Load category lên tabMoiNhat
    @Override
    public void loadCategory(ArrayList<Category> categories) {
        this.listCategories = categories;
        categoryAdapter = new CategoryAdapter(getActivity(), R.layout.list_row_item, categories, 0);
        categoryAdapter.notifyDataSetChanged();
        lstMoiNhatWhere.setAdapter(categoryAdapter);
        lstMoiNhatWhere.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newIndexChangedMoiNhat = position;
                categoryAdapter.notifyDataSetChanged();

                final TextView tvMoiNhat = (TextView) tabWidget.getChildTabViewAt(0).findViewById(android.R.id.title);
                tvMoiNhat.setText(listCategories.get(position).getName());
                tvMoiNhat.setTextColor(getContext().getResources().getColor(R.color.colorRed));


                if (CategoryIdWhere != listCategories.get(position).getId()) {
                    CategoryIdWhere = listCategories.get(position).getId();
                    loadNew(getContext());
                }

            }
        });
    }

    //Load List lên tabDanhMuc
    @Override
    public void loadListByType(final ArrayList<ListByType> listByTypes) {
        this.listByTypesWhere = listByTypes;
        listByTypeAdapter = new ListByTypeAdapter(getActivity(), R.layout.list_row_item, listByTypes, 0);
        lstDanhMucWhere.setAdapter(listByTypeAdapter);

        lstDanhMucWhere.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newIndexChangedDanhMuc = position;
                listByTypeAdapter.notifyDataSetChanged();
                final TextView tvDanhMuc = (TextView) tabWidget.getChildTabViewAt(1).findViewById(android.R.id.title);
                tvDanhMuc.setText(listByTypesWhere.get(position).getName());
                tvDanhMuc.setTextColor(getContext().getResources().getColor(R.color.colorRed));

                if (ListIdWhere != listByTypesWhere.get(position).getId()) {
                    ListIdWhere = listByTypesWhere.get(position).getId();
                    loadNew(getContext());
                }
            }
        });
    }

    //LoadQuanHuyen lên tab Thành phố
    @Override
    public void loadDistrict(ArrayList<District> districts) {
        listDistrictWhere = districts;
        mData = new HashMap<>();

        for (int i = 0; i < districts.size(); i++) {
            mData.put(districts.get(i), districts.get(i).getStreets());
        }
        customAdapterExpandableListview = new CustomAdapterExpandableListview(getContext(), districts, mData, 0);
        exLstWhere.setAdapter(customAdapterExpandableListview);
    }

    //hàm show item
    public void showItem(List<Item> items) {
        itemsWhere = items;
        itemWhereAdapter = new ItemWhereAdapter(getContext(), R.layout.custom_one_row_food, itemsWhere);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_list_items.setLayoutManager(layoutManager);
        recycler_list_items.setAdapter(itemWhereAdapter);

        itemWhereAdapter.notifyDataSetChanged();

        loadMore = new LoadMore(layoutManager, this, false);
        recycler_list_items.addOnScrollListener(loadMore);
    }

    @Override
    public void loadItemByCategoryandListAndCity(List<Item> items) {
//        if (isLoadMore) {
//            itemsWhere.addAll(items);
//            itemWhereAdapter.notifyDataSetChanged();
//
//            showItem(itemsWhere);
//        } else {
            showItem(items);
//        }
    }

    @Override
    public void loadItemByCategoryandListAndDistrict(List<Item> items) {
        showItem(items);
    }

    @Override
    public void loadItemByCategoryandListAndStreet(List<Item> items) {
        showItem(items);
    }


    //hàm load dữ liệu mới lên
    private static void loadNew(Context context) {
        isLoadMore=false;
        if (tabTPHCMQuery) {
            if(itemWhereAdapter.getItemCount()!=0)
            {
                itemWhereAdapter.clearData();
                itemWhereAdapter.notifyDataSetChanged();
            }
            recycler_list_items.removeOnScrollListener(loadMore);
            itemController.get_Item_ByCategoryandListAndDistrict(CategoryIdWhere, ListIdWhere, DistrictId, 0);
        } else if (tabStreetQuery) {
            if(itemWhereAdapter.getItemCount()!=0)
            {
                itemWhereAdapter.clearData();
                itemWhereAdapter.notifyDataSetChanged();
            }
            recycler_list_items.removeOnScrollListener(loadMore);
            itemController.get_Item_ByCategoryandListAndStreet(CategoryIdWhere, ListIdWhere, StreetID, 0);
        } else {
            if(itemWhereAdapter.getItemCount()!=0)
            {
                itemWhereAdapter.clearData();
                itemWhereAdapter.notifyDataSetChanged();
            }
            recycler_list_items.removeOnScrollListener(loadMore);
            itemController.get_Item_ByCategoryandListAndCity(CategoryIdWhere, ListIdWhere, CityId, 0);
        }
        if (isError) {
            tabHost.setCurrentTab(4);
            isError = false;
        } else {
            tabHost.setCurrentTab(3);
        }

    }

    @Override
    public void error() {
        isError = true;
    }
}

