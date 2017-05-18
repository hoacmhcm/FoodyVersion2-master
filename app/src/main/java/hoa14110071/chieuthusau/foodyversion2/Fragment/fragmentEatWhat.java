package hoa14110071.chieuthusau.foodyversion2.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import hoa14110071.chieuthusau.foodyversion2.Adapter.ItemEatWhatAdapter;
import hoa14110071.chieuthusau.foodyversion2.Adapter.ListByTypeAdapter;
import hoa14110071.chieuthusau.foodyversion2.Controller.CategoryController.CategoryController;
import hoa14110071.chieuthusau.foodyversion2.Controller.DistrictController.DistrictController;
import hoa14110071.chieuthusau.foodyversion2.Controller.ItemController.ItemController;
import hoa14110071.chieuthusau.foodyversion2.Controller.ListByTypeController.ListByTypeController;
import hoa14110071.chieuthusau.foodyversion2.JavaClass.LoadMore;
import hoa14110071.chieuthusau.foodyversion2.JavaClass.iLoadData;
import hoa14110071.chieuthusau.foodyversion2.JavaClass.iLoadMore;
import hoa14110071.chieuthusau.foodyversion2.Object.Category;
import hoa14110071.chieuthusau.foodyversion2.Object.District;
import hoa14110071.chieuthusau.foodyversion2.Object.Item;
import hoa14110071.chieuthusau.foodyversion2.Object.ListByType;
import hoa14110071.chieuthusau.foodyversion2.Object.Street;
import hoa14110071.chieuthusau.foodyversion2.R;


public class fragmentEatWhat extends Fragment implements TabHost.OnTabChangeListener, View.OnClickListener, iLoadData, iLoadMore {
    public static TabHost tabHost;
    //tab Mới nhất
    private ListView lstMoiNhatEatWhat;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> listCategories;
    //tab Danh mục
    private ListView lstDanhMucEatWhat;
    private ListByTypeAdapter listByTypeAdapter;
    private ArrayList<ListByType> listByTypesEatWhat;
    //Tab Thành phố
    private static ExpandableListView exLstEatWhat;
    private static CustomAdapterExpandableListview customAdapterExpandableListview;
    //Dữ liệu thành phố và quận huyện
    public static ArrayList<District> listDistrictEatWhat;
    public static HashMap<District, List<Street>> mData;

    //Khai báo các biến liên quan đến item cần load lên
    public static ItemEatWhatAdapter itemEatWhatAdapter;
    private List<Item> itemsEatWhat = new ArrayList<>();
    public static RecyclerView recycler_list_items_EatWhat;
    public static LoadMore loadMore;
    FrameLayout frame_progress;

    //Button chọn thành phố và TextView hiện tên thành phố
    private static TextView tvChooseCityEatWhat;
    private Button btnChooseCityEatWhat;

    public static TabWidget tabWidgetEatWhat;
    //biến quản lý việc truy vấn
    private static boolean tabStreetQuery = false;
    private static boolean tabTPHCMQuery = false;

    private Button btnHuyEatWhatMoiNhat;
    private Button btnHuyEatWhatDanhMuc;
    private Button btnHuyEatWhatTPHCM;
    //khai báo các controller
    CategoryController categoryController;
    ListByTypeController listByTypeController;
    static DistrictController districtController;
    static ItemController itemController;

    //biến vị trí được chọn
    public static int newIndexChangedMoiNhatEatWhat = 0;
    public static int newIndexChangedDanhMucEatWhat = 0;
    //các biến ID hiện thời được chọn
    public static int CategoryIdEatWhat = 1;
    public static int ListIdEatWhat = 0;
    public static int DistrictIdEatWhat = 1;
    public static int CityIdEatWhat = 1;
    public static int StreetIdEatWhat;


    static boolean isError = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        View view = inflater.inflate(R.layout.fragment_eat_what, container, false);
        //khởi tạo các widget
        tabHost = (TabHost) view.findViewById(R.id.tabhostEatWhat);
        lstMoiNhatEatWhat = (ListView) view.findViewById(R.id.lstMoiNhatEatWhat);
        lstDanhMucEatWhat = (ListView) view.findViewById(R.id.lstDanhMucEatWhat);
        exLstEatWhat = (ExpandableListView) view.findViewById(R.id.exLstEatWhat);
        tvChooseCityEatWhat = (TextView) view.findViewById(R.id.tvChooseCityEatWhat);
        btnChooseCityEatWhat = (Button) view.findViewById(R.id.btnChooseCityEatWhat);
        recycler_list_items_EatWhat = (RecyclerView) view.findViewById(R.id.recycler_list_items_EatWhat);
        frame_progress = (FrameLayout) view.findViewById(R.id.frame_progress_EatWhat);


        btnChooseCityEatWhat.setOnClickListener(this);

        //Lấy danh sách từ controller
        categoryController.get_Category();
        listByTypeController.get_ListByType();
        districtController.get_District(DistrictIdEatWhat);
        itemController.get_Item_ByCategoryandListAndCity(CategoryIdEatWhat, ListIdEatWhat, CityIdEatWhat, 0);
        //khởi tạo tabhost
        tabHostSetup();

        setupButtonHuy(view);

        return view;

    }

    private void tabHostSetup() {
        tabHost.setup();

        TabHost.TabSpec tabMoiNhat = tabHost.newTabSpec("Moi nhat");
        tabMoiNhat.setContent(R.id.tabMoiNhatEatWhat);
        tabMoiNhat.setIndicator("Mới nhất");
        tabHost.addTab(tabMoiNhat);

        TabHost.TabSpec tabDanhMuc = tabHost.newTabSpec("Danh muc");
        tabDanhMuc.setIndicator("Danh mục");
        tabDanhMuc.setContent(R.id.tabDanhMucEatWhat);
        tabHost.addTab(tabDanhMuc);

        TabHost.TabSpec tabTPHCM = tabHost.newTabSpec("TPHCM");
        tabTPHCM.setIndicator("TPHCM");
        tabTPHCM.setContent(R.id.tabTPHCMEatWhat);
        tabHost.addTab(tabTPHCM);

        TabHost.TabSpec tabMain = tabHost.newTabSpec("Main");
        tabMain.setIndicator("MAIN");
        tabMain.setContent(R.id.tabMainEatWhat);
        tabHost.addTab(tabMain);

        TabHost.TabSpec tabNothing = tabHost.newTabSpec("Nothing");
        tabNothing.setIndicator("Nothing");
        tabNothing.setContent(R.id.tabNothingEatWhat);
        tabHost.addTab(tabNothing);

        tabWidgetEatWhat = tabHost.getTabWidget();

        final TextView tvMoiNhat = (TextView) tabWidgetEatWhat.getChildTabViewAt(0).findViewById(android.R.id.title);
        final TextView tvDanhMuc = (TextView) tabWidgetEatWhat.getChildTabViewAt(1).findViewById(android.R.id.title);
        final TextView tvTPHCM = (TextView) tabWidgetEatWhat.getChildTabViewAt(2).findViewById(android.R.id.title);

        tvMoiNhat.setTextColor(getContext().getResources().getColor(R.color.colorRed));
        tvDanhMuc.setTextColor(getContext().getResources().getColor(R.color.colorPressed));
        tvTPHCM.setTextColor(getContext().getResources().getColor(R.color.colorPressed));

        tvMoiNhat.setAllCaps(false);
        tvDanhMuc.setAllCaps(false);
        tvTPHCM.setAllCaps(false);

        tabWidgetEatWhat.getChildAt(3).setVisibility(View.GONE);
        tabWidgetEatWhat.getChildAt(4).setVisibility(View.GONE);

        tabWidgetEatWhat.getChildAt(0).setBackgroundResource(R.drawable.background_tabhost_unselected);
        tabWidgetEatWhat.getChildAt(1).setBackgroundResource(R.drawable.background_tabhost_unselected);
        tabWidgetEatWhat.getChildAt(2).setBackgroundResource(R.drawable.background_tabhost_unselected);

        tabHost.setCurrentTab(3);

        tabHost.setOnTabChangedListener(this);
    }

    private void setupButtonHuy(View view) {
        btnHuyEatWhatMoiNhat = (Button) view.findViewById(R.id.btnHuyEatWhatMoiNhat);
        btnHuyEatWhatDanhMuc = (Button) view.findViewById(R.id.btnHuyEatWhatDanhMuc);
        btnHuyEatWhatTPHCM = (Button) view.findViewById(R.id.btnHuyEatWhatTPHCM);

        btnHuyEatWhatMoiNhat.setOnClickListener(this);
        btnHuyEatWhatDanhMuc.setOnClickListener(this);
        btnHuyEatWhatTPHCM.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnHuyEatWhatMoiNhat:
            case R.id.btnHuyEatWhatDanhMuc:
            case R.id.btnHuyEatWhatTPHCM:
                tabHost.setCurrentTab(3);
                break;
            case R.id.btnChooseCityEatWhat:
                Intent intent = new Intent(getContext(), ChooseCityActivity.class);
                getActivity().startActivityForResult(intent, 2);
                break;
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        for (int i = 0; i < tabWidgetEatWhat.getChildCount(); i++) {//nếu tab được chọn thì sẽ đổi màu xám ngược lại có màu trắng
            tabWidgetEatWhat.getChildAt(i).setBackgroundResource(R.drawable.background_tabhost_unselected);
        }
        tabWidgetEatWhat.getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.background_tabhost_selected);
    }

    //Load thêm dữ liệu
    @Override
    public void loadMore(int countItem) {
        List<Item> items;
        if (tabTPHCMQuery) {
            itemController.get_Item_ByCategoryandListAndDistrictLoadMore(CategoryIdEatWhat, ListIdEatWhat, DistrictIdEatWhat, countItem, frame_progress);
        } else if (tabStreetQuery) {
            itemController.get_Item_ByCategoryandListAndStreetLoadMore(CategoryIdEatWhat, ListIdEatWhat, StreetIdEatWhat, countItem, frame_progress);
        } else {
            itemController.get_Item_ByCategoryandListAndCityLoadMore(CategoryIdEatWhat, ListIdEatWhat, CityIdEatWhat, countItem, frame_progress);
        }

    }

    @Override
    public void loadMoreResultItem(List<Item> items) {
        itemsEatWhat.addAll(items);
        itemEatWhatAdapter.notifyDataSetChanged();
    }

    //Load category lên tabMoiNhat
    @Override
    public void loadCategory(ArrayList<Category> categories) {
        this.listCategories = categories;
        categoryAdapter = new CategoryAdapter(getActivity(), R.layout.list_row_item, categories, 1);
        categoryAdapter.notifyDataSetChanged();
        lstMoiNhatEatWhat.setAdapter(categoryAdapter);
        lstMoiNhatEatWhat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newIndexChangedMoiNhatEatWhat = position;
                categoryAdapter.notifyDataSetChanged();

                final TextView tvMoiNhat = (TextView) tabWidgetEatWhat.getChildTabViewAt(0).findViewById(android.R.id.title);
                tvMoiNhat.setText(listCategories.get(position).getName());
                tvMoiNhat.setTextColor(getContext().getResources().getColor(R.color.colorRed));


                if (CategoryIdEatWhat != listCategories.get(position).getId()) {
                    CategoryIdEatWhat = listCategories.get(position).getId();
                    loadNew(getContext());
                }

            }
        });
    }

    //Chọn vào tỉnh
    public static void setOnGroupEatWhat(Context context, int groupPosition) {
        final TextView tvTPHCM = (TextView) tabHost.getTabWidget().getChildTabViewAt(2).findViewById(android.R.id.title);
        tvTPHCM.setText(listDistrictEatWhat.get(groupPosition).getName());
        tvTPHCM.setTextColor(context.getResources().getColor(R.color.colorRed));
        DistrictIdEatWhat = listDistrictEatWhat.get(groupPosition).getId();
        tabTPHCMQuery = true;
        tabStreetQuery = false;
        loadNew(context);
    }

    //Chọn vào đường
    public static void setOnChildEatWhat(Context context, int groupPosition, int childPosition) {
        final TextView tvTPHCM = (TextView) tabWidgetEatWhat.getChildTabViewAt(2).findViewById(android.R.id.title);
        tvTPHCM.setText(String.valueOf(mData.get(listDistrictEatWhat.get(groupPosition)).get(childPosition).getName()));
        tvTPHCM.setTextColor(context.getResources().getColor(R.color.colorRed));
        tabStreetQuery = true;
        tabTPHCMQuery = false;
        StreetIdEatWhat = mData.get(listDistrictEatWhat.get(groupPosition)).get(childPosition).getId();
        loadNew(context);
        tabHost.setCurrentTab(3);
    }

    //Load List lên tabDanhMuc
    @Override
    public void loadListByType(ArrayList<ListByType> listByTypes) {
        this.listByTypesEatWhat = listByTypes;
        listByTypeAdapter = new ListByTypeAdapter(getActivity(), R.layout.list_row_item, listByTypes, 1);
        lstDanhMucEatWhat.setAdapter(listByTypeAdapter);

        lstDanhMucEatWhat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newIndexChangedDanhMucEatWhat = position;
                listByTypeAdapter.notifyDataSetChanged();
                final TextView tvDanhMuc = (TextView) tabWidgetEatWhat.getChildTabViewAt(1).findViewById(android.R.id.title);
                tvDanhMuc.setText(listByTypesEatWhat.get(position).getName());
                tvDanhMuc.setTextColor(getContext().getResources().getColor(R.color.colorRed));

                if (ListIdEatWhat != listByTypesEatWhat.get(position).getId()) {
                    ListIdEatWhat = listByTypesEatWhat.get(position).getId();
                    loadNew(getContext());
                }
            }
        });
    }

    //LoadQuanHuyen lên tab Thành phố
    @Override
    public void loadDistrict(ArrayList<District> districts) {
        listDistrictEatWhat = districts;
        mData = new HashMap<>();

        for (int i = 0; i < districts.size(); i++) {
            mData.put(districts.get(i), districts.get(i).getStreets());
        }
        customAdapterExpandableListview = new CustomAdapterExpandableListview(getContext(), districts, mData, 1);
        exLstEatWhat.setAdapter(customAdapterExpandableListview);
    }

    //hàm show item
    public void showItem(List<Item> items) {
        itemsEatWhat = items;
        itemEatWhatAdapter = new ItemEatWhatAdapter(getContext(), R.layout.custom_one_row_eatwhat, itemsEatWhat);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recycler_list_items_EatWhat.setLayoutManager(layoutManager);
        recycler_list_items_EatWhat.setAdapter(itemEatWhatAdapter);

//        itemEatWhatAdapter.notifyDataSetChanged();

        loadMore = new LoadMore(layoutManager, this, true);
        recycler_list_items_EatWhat.addOnScrollListener(loadMore);
    }

    @Override
    public void loadItemByCategoryandListAndCity(List<Item> items) {
        showItem(items);
    }

    @Override
    public void loadItemByCategoryandListAndDistrict(List<Item> items) {
        showItem(items);
    }

    @Override
    public void loadItemByCategoryandListAndStreet(List<Item> items) {
        showItem(items);
    }


    @Override
    public void error() {
        if(itemsEatWhat.isEmpty())
        {
            tabHost.setCurrentTab(4);
        }
    }

    //hàm load dữ liệu mới lêns
    private static void loadNew(Context context) {
        if (tabTPHCMQuery) {
            itemEatWhatAdapter.clearData();
            itemEatWhatAdapter.notifyDataSetChanged();
            recycler_list_items_EatWhat.removeOnScrollListener(loadMore);
            itemController.get_Item_ByCategoryandListAndDistrict(CategoryIdEatWhat, ListIdEatWhat, DistrictIdEatWhat, 0);
        } else if (tabStreetQuery) {
            itemEatWhatAdapter.clearData();
            itemEatWhatAdapter.notifyDataSetChanged();
            recycler_list_items_EatWhat.removeOnScrollListener(loadMore);
            itemController.get_Item_ByCategoryandListAndStreet(CategoryIdEatWhat, ListIdEatWhat, StreetIdEatWhat, 0);
        } else {
            itemEatWhatAdapter.clearData();
            itemEatWhatAdapter.notifyDataSetChanged();
            recycler_list_items_EatWhat.removeOnScrollListener(loadMore);
            itemController.get_Item_ByCategoryandListAndCity(CategoryIdEatWhat, ListIdEatWhat, CityIdEatWhat, 0);
        }
        tabHost.setCurrentTab(3);

    }

    //Chọn vào thành phố mới sẽ load lại tỉnh
    public static void changeCityEatWhat(Context context, int CityID, String CityName) {
        districtController.get_District(CityID);

        CityIdEatWhat = CityID;

        final TextView tvTPHCM = (TextView) tabWidgetEatWhat.getChildTabViewAt(2).findViewById(android.R.id.title);
        tvTPHCM.setText(CityName);
        tvTPHCM.setTextColor(context.getResources().getColor(R.color.colorPressed));
        tvChooseCityEatWhat.setText(CityName);

        tabTPHCMQuery = false;
        tabStreetQuery = false;
        loadNew(context);

        tabHost.setCurrentTab(3);
    }
}
