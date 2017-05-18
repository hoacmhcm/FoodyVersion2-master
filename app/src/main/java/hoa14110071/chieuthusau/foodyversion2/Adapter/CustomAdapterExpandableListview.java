package hoa14110071.chieuthusau.foodyversion2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import hoa14110071.chieuthusau.foodyversion2.Object.District;
import hoa14110071.chieuthusau.foodyversion2.Object.Street;
import hoa14110071.chieuthusau.foodyversion2.R;

import static hoa14110071.chieuthusau.foodyversion2.Fragment.fragmentEatWhat.setOnChildEatWhat;
import static hoa14110071.chieuthusau.foodyversion2.Fragment.fragmentEatWhat.setOnGroupEatWhat;
import static hoa14110071.chieuthusau.foodyversion2.Fragment.fragmentWhere.setOnChild;
import static hoa14110071.chieuthusau.foodyversion2.Fragment.fragmentWhere.setOnGroup;


public class CustomAdapterExpandableListview extends BaseExpandableListAdapter {
    private Context mContext;
    private List<District> mHeaderGroup;
    private HashMap<District, List<Street>> mDataChild;
    int byWhat;

    public CustomAdapterExpandableListview(Context mContext, List<District> mHeaderGroup, HashMap<District, List<Street>> mDataChild, int byWhat) {
        this.mContext = mContext;
        this.mHeaderGroup = mHeaderGroup;
        this.mDataChild = mDataChild;
        this.byWhat = byWhat;
    }

    @Override
    public int getGroupCount() {
        return mHeaderGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mDataChild.get(mHeaderGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mHeaderGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDataChild.get(mHeaderGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.list_row_header_expandable, parent, false);
        }
        //khởi tạo các widget
        TextView tvDistrictName = (TextView) convertView.findViewById(R.id.tvDistrictName);
        Button btnChoosen = (Button) convertView.findViewById(R.id.btnChildCountStreet);
        btnChoosen.setAllCaps(false);
        //Header là tên của các tỉnh
        tvDistrictName.setText(mHeaderGroup.get(groupPosition).getName());
        //Custom lại và nút chọn sẽ có hiện tên số đường
        btnChoosen.setText(String.valueOf(mHeaderGroup.get(groupPosition).getCountStreet() + " đường"));
        btnChoosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //đặt lại sự kiện expand cho nút Choosen
                if (isExpanded)
                    ((ExpandableListView) parent).collapseGroup(groupPosition);
                else
                    ((ExpandableListView) parent).expandGroup(groupPosition, true);

            }
        });
        //Khi chọn header sẽ xử lý sự kiện loadItem mới
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Nếu byWhat = 0 thì được truy vấn bởi fragmentWhere ngược lại nếu byWhat = 1 thì được truy vấn bởi fragmentEatWhat
                if (byWhat == 0) {
                    setOnGroup(mContext, groupPosition);
                } else if (byWhat == 1) {
                    setOnGroupEatWhat(mContext, groupPosition);
                }
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.list_row_child_expandable, parent, false);
        }
        //Lấy tên đường
        TextView tvStreet = (TextView) convertView.findViewById(R.id.tvStreet);
        tvStreet.setText(String.valueOf(((Street) getChild(groupPosition, childPosition)).getName()));
        //Nếu chọn đường thì truy vấn lại
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (byWhat == 0) {
                    setOnChild(mContext, groupPosition, childPosition);
                } else if (byWhat == 1) {
                    setOnChildEatWhat(mContext, groupPosition, childPosition);
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
