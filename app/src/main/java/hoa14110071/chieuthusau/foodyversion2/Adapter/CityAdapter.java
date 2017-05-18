package hoa14110071.chieuthusau.foodyversion2.Adapter;

import android.app.Activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hoa14110071.chieuthusau.foodyversion2.Object.City;
import hoa14110071.chieuthusau.foodyversion2.R;

/**
 * Created by minhh on 4/3/2017.
 */

public class CityAdapter extends ArrayAdapter<City> {
    Activity context;
    int layoutId;
    ArrayList<City> arrayCities;
    //Biến đánh dấu vị trí thành phố được chọn
    public static int nextIndexChangedCity = 0;

    public CityAdapter(Activity context, int layoutId, ArrayList<City> arrayCities) {
        super(context, layoutId, arrayCities);
        this.context = context;
        this.layoutId = layoutId;
        this.arrayCities = arrayCities;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        if (view == null) {//nếu view null thì khởi tạo các widget cần thiết
            view = LayoutInflater.from(context).inflate(layoutId, null);
            viewHolder = new ViewHolder();
            viewHolder.imgCheckCity = (ImageView) view.findViewById(R.id.imgCheckCity);
            viewHolder.tvNameCity = (TextView) view.findViewById(R.id.tvNameCity);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //vị trí được chọn là vị trí position thì đưa dấu check hiện lên vị trí được chọn.Ngược lại ẩn nó đi.Và Vị trí chữ được chọn sẽ có màu đỏ
        if(nextIndexChangedCity == position)
        {
            viewHolder.tvNameCity.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            viewHolder.imgCheckCity.setVisibility(View.VISIBLE);
        }else {
            viewHolder.tvNameCity.setTextColor(context.getResources().getColor(R.color.colorBlack));
            viewHolder.imgCheckCity.setVisibility(View.INVISIBLE);
        }
        viewHolder.tvNameCity.setText(arrayCities.get(position).getName());
        return view;
    }

    static class ViewHolder {
        ImageView imgCheckCity;
        TextView tvNameCity;
    }
}
