package hoa14110071.chieuthusau.foodyversion2.JavaClass;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by minhh on 4/7/2017.
 */

public class LoadMore extends RecyclerView.OnScrollListener {

    private RecyclerView.LayoutManager layoutManager;
    private iLoadMore iLoadMore;
    private int firstVisibleItem;
    private int countItem;
    private int visibleThreshold;
    private int preCountItem=0;

    public static boolean isLoadding = true;

    public LoadMore(RecyclerView.LayoutManager layoutManager, iLoadMore iLoadMore, boolean byWhat) {
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
        firstVisibleItem = 0;
        countItem = 0;
        if (!byWhat) {
            visibleThreshold = 5;
        } else {
            visibleThreshold = 8;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //đếm số item hiện có
        countItem = layoutManager.getItemCount();

//        Log.e("COUNTITEM111111111111", String.valueOf(countItem));
        if (layoutManager instanceof LinearLayoutManager) {
            firstVisibleItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof GridLayoutManager) {
            firstVisibleItem = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        }
        // nếu số item hiện có vẫn còn bé hơn số item load lên thì chưa load thêm
        if (preCountItem != countItem && countItem <= (firstVisibleItem+ visibleThreshold)) {
            preCountItem = countItem;
            Log.e("COUNTITEM", String.valueOf(countItem));
            Log.e("firstVisibleItem", String.valueOf(firstVisibleItem));
            Log.e("visibleThreshold", String.valueOf(visibleThreshold));
            iLoadMore.loadMore(countItem+1);
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

}
