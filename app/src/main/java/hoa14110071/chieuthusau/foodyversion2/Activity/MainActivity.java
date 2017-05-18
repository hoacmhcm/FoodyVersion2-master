package hoa14110071.chieuthusau.foodyversion2.Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;

import hoa14110071.chieuthusau.foodyversion2.R;

public class MainActivity extends TabActivity implements TabHost.OnTabChangeListener {
    TabHost tabHostBottom;
    TabWidget tabWidget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHostBottom = getTabHost();
//        tabHostBottom.getTabWidget().setStripEnabled(false);
        TabHost.TabSpec tabHome = tabHostBottom.newTabSpec("Home");
        tabHome.setIndicator("",getResources().getDrawable(R.drawable.tab_home_icon));
        tabHome.setContent(new Intent(this, HomeActivity.class));
        tabHostBottom.addTab(tabHome);


        TabHost.TabSpec tabAlbum = tabHostBottom.newTabSpec("Album");
        tabAlbum.setIndicator("",getResources().getDrawable(R.drawable.tab_album_icon));
        tabAlbum.setContent(new Intent(this, AlbumActivity.class));
        tabHostBottom.addTab(tabAlbum);


        TabHost.TabSpec tabSearch = tabHostBottom.newTabSpec("Search");
        tabSearch.setIndicator("",getResources().getDrawable(R.drawable.tab_search_icon));
        tabSearch.setContent(new Intent(this, SearchActivity.class));
        tabHostBottom.addTab(tabSearch);


        TabHost.TabSpec tabNotification = tabHostBottom.newTabSpec("Notification");
        tabNotification.setIndicator("",getResources().getDrawable(R.drawable.tab_notification_icon));
        tabNotification.setContent(new Intent(this, NotificationActivity.class));
        tabHostBottom.addTab(tabNotification);


        TabHost.TabSpec tabProfile = tabHostBottom.newTabSpec("Notification");
        tabProfile.setIndicator("",getResources().getDrawable(R.drawable.tab_profile_icon));
        tabProfile.setContent(new Intent(this, ProfileActivity.class));
        tabHostBottom.addTab(tabProfile);

        tabHostBottom.setCurrentTab(0);
        tabHostBottom.setOnTabChangedListener(this);
        tabWidget = tabHostBottom.getTabWidget();
        tabWidget.setBackgroundResource(R.color.colorWhite);
        tabWidget.setDividerDrawable(null);

    }

    @Override
    public void onTabChanged(String tabId) {

    }
}
