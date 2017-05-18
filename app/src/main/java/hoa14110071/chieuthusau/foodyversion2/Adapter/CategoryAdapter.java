package hoa14110071.chieuthusau.foodyversion2.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hoa14110071.chieuthusau.foodyversion2.Object.Category;
import hoa14110071.chieuthusau.foodyversion2.R;

import static hoa14110071.chieuthusau.foodyversion2.Fragment.fragmentEatWhat.newIndexChangedMoiNhatEatWhat;
import static hoa14110071.chieuthusau.foodyversion2.Fragment.fragmentWhere.newIndexChangedMoiNhat;


/**
 * Created by minhh on 3/28/2017.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {
    Activity context = null;
    int layoutId;
    ArrayList<Category> listViewItemArrayList = null;
    int byWhat; // biến được chọn bởi ai . Nếu bằng 1 thì được chọn bởi fragmentWhere nếu bằng 2 thì được chọn bởi fragmentEatWhat

    public void setContext(Activity context) {
        this.context = context;
    }

    public CategoryAdapter(Activity context, int layoutId, ArrayList<Category> listViewItemArrayList, int byWhat) {
        super(context, layoutId, listViewItemArrayList);
        this.context = context;
        this.layoutId = layoutId;
        this.listViewItemArrayList = listViewItemArrayList;
        this.byWhat = byWhat;
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        //Nếu view null thì khởi tạo các widget
        if (view == null) {
            view = LayoutInflater.from(context).inflate(layoutId, null);
            viewHolder = new ViewHolder();

            viewHolder.imagePic = (ImageView) view.findViewById(R.id.imgPic);
            viewHolder.textView = (TextView) view.findViewById(R.id.txtName);
            viewHolder.imageCheck = (ImageView) view.findViewById(R.id.imgCheck);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //Nếu được chọn bởi fragmentWhere
        if (byWhat == 0) {
            if (newIndexChangedMoiNhat == position) {
                viewHolder.imagePic.setImageBitmap(listViewItemArrayList.get(position).getImagePress());
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.colorRed));
                viewHolder.imageCheck.setVisibility(View.VISIBLE);
            } else {
                viewHolder.imagePic.setImageBitmap(listViewItemArrayList.get(position).getImage());
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.colorStroke));
                viewHolder.imageCheck.setVisibility(View.GONE);
            }
            viewHolder.textView.setText(listViewItemArrayList.get(position).getName());
        } else if (byWhat == 1) { //Nếu được chọn bởi fragmentEatWhat
            if (newIndexChangedMoiNhatEatWhat == position) {
                viewHolder.imagePic.setImageBitmap(listViewItemArrayList.get(position).getImagePress());
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.colorRed));
                viewHolder.imageCheck.setVisibility(View.VISIBLE);
            } else {
                viewHolder.imagePic.setImageBitmap(listViewItemArrayList.get(position).getImage());
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.colorStroke));
                viewHolder.imageCheck.setVisibility(View.GONE);
            }
            viewHolder.textView.setText(listViewItemArrayList.get(position).getName());
        }
        return view;
    }
    //tạo ViewHolder
    static class ViewHolder {
        ImageView imagePic;
        TextView textView;
        ImageView imageCheck;
    }
}
