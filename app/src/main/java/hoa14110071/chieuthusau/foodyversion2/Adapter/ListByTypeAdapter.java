package hoa14110071.chieuthusau.foodyversion2.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hoa14110071.chieuthusau.foodyversion2.Object.ListByType;
import hoa14110071.chieuthusau.foodyversion2.R;

import static hoa14110071.chieuthusau.foodyversion2.Fragment.fragmentEatWhat.newIndexChangedDanhMucEatWhat;
import static hoa14110071.chieuthusau.foodyversion2.Fragment.fragmentWhere.newIndexChangedDanhMuc;

/**
 * Created by minhh on 3/21/2017.
 */

public class ListByTypeAdapter extends ArrayAdapter<ListByType> {
    Activity context;
    int layoutId;
    ArrayList<ListByType> listViewListArrayListByType;
    int byWhat;


    public ListByTypeAdapter(Activity context, int layoutId, ArrayList<ListByType> listViewListArrayListByType, int byWhat) {
        super(context, layoutId, listViewListArrayListByType);
        this.context = context;
        this.layoutId = layoutId;
        this.listViewListArrayListByType = listViewListArrayListByType;
        this.byWhat = byWhat;
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(layoutId, null);
            viewHolder = new ViewHolder();

            viewHolder.imagePic = (ImageView) view.findViewById(R.id.imgPic);
            viewHolder.textView = (TextView) view.findViewById(R.id.txtName);
            viewHolder.imageCheck = (ImageView) view.findViewById(R.id.imgCheck);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ListByTypeAdapter.ViewHolder) view.getTag();
        }
        //Nếu được chọn bởi fragmentWhere thì byWhat = 0 ngược lại là fragmentEatWhat
        if (byWhat == 0) {
            if (newIndexChangedDanhMuc == position) {
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.colorRed));
                viewHolder.imageCheck.setVisibility(View.VISIBLE);
            } else {
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.colorStroke));
                viewHolder.imageCheck.setVisibility(View.GONE);
            }
            if (listViewListArrayListByType.get(position).getImage() != null) {
                viewHolder.imagePic.setImageBitmap(listViewListArrayListByType.get(position).getImage());
            } else {
                viewHolder.imagePic.setVisibility(View.GONE);
            }
            viewHolder.textView.setText(listViewListArrayListByType.get(position).getName());

        } else if (byWhat == 1) {
            if (newIndexChangedDanhMucEatWhat == position) {
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.colorRed));
                viewHolder.imageCheck.setVisibility(View.VISIBLE);
            } else {
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.colorStroke));
                viewHolder.imageCheck.setVisibility(View.GONE);
            }
            if (listViewListArrayListByType.get(position).getImage() != null) {
                viewHolder.imagePic.setImageBitmap(listViewListArrayListByType.get(position).getImage());
            } else {
                viewHolder.imagePic.setVisibility(View.GONE);
            }
            viewHolder.textView.setText(listViewListArrayListByType.get(position).getName());
        }
        return view;
    }

    static class ViewHolder {
        ImageView imagePic;
        TextView textView;
        ImageView imageCheck;
    }
}


