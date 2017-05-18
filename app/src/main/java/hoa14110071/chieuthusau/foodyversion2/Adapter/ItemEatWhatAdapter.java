package hoa14110071.chieuthusau.foodyversion2.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import hoa14110071.chieuthusau.foodyversion2.Object.Item;
import hoa14110071.chieuthusau.foodyversion2.Object.Review;
import hoa14110071.chieuthusau.foodyversion2.R;
import hoa14110071.chieuthusau.foodyversion2.Services.RetrofitCreate;


/**
 * Created by minhh on 4/14/2017.
 */

public class ItemEatWhatAdapter extends RecyclerView.Adapter<ItemEatWhatAdapter.ViewHolderShowListItem> {
    Context context;
    int layout, width;
    List<Item> items;

    private String[] type =
            new String[]{"Sang trọng", "Buffer",
                    "Nhà hàng", "Ăn vặt/vỉ hè",
                    "Ăn chay", "Cafe/Dessert",
                    "Quán ăn", "Bar/Pub",
                    "Quán nhậu", "Beer club",
                    "Tiệm bánh", "Tiệc tận nơi",
                    "Shop Online", "Giao cơm văn phòng",
                    "Khu ẩm thực"};


    public ItemEatWhatAdapter(Context context, int layout, List<Item> items) {
        this.context = context;
        this.layout = layout;
        this.items = items;
    }

    @Override
    public ViewHolderShowListItem onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, parent, false);
//        width = parent.getMeasuredWidth();
        ViewHolderShowListItem viewHolderShowListItem = new ViewHolderShowListItem(view);
        return viewHolderShowListItem;
    }

    @Override
    public void onBindViewHolder(ViewHolderShowListItem holder, int position) {
        Item item = items.get(position);
        //load file ảnh từ Drawable với Id được lấy từ database
        if (!item.getImage().equals("")) {
//            Log.e("URLIMGEat", RetrofitCreate.IMGE_URL + "fdi" + item.getImage() + ".png");
            Glide.with(context).load(RetrofitCreate.IMGE_URL + "fdi" + item.getImage() + ".png").into(holder.imgItem);
        } else {
            holder.imgItem.setImageDrawable(null);
        }

        holder.txtType.setText(type[item.getListID() - 1]);

        holder.txtName.setText(item.getName());

        holder.txtAddress.setText(item.getAddress());

        if (item.getReviews().size()>0) {
            Review review = item.getReviews().get(0);
            holder.txtNameReview.setText(review.getName());
//            holder.txtDate.setText(review.get);
//            int imageResource = context.getResources().getIdentifier("ava" + review.getImage(), "drawable", context.getPackageName());
            if (!review.getImage().equals("")) {
//                Log.e("URLIMGREVIEW", RetrofitCreate.IMGE_URL + "ava" + review.getName() + ".png");
                Glide.with(context).load(RetrofitCreate.IMGE_URL + review.getImage()).into(holder.imgAvatarReview);
            } else {
                holder.imgAvatarReview.setImageDrawable(null);
            }
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolderShowListItem extends RecyclerView.ViewHolder {
        ImageView imgItem;
        TextView txtName, txtType, txtAddress, txtNameReview, txtDate;
        CircleImageView imgAvatarReview;

        public ViewHolderShowListItem(View itemView) {
            super(itemView);
            itemView.setMinimumHeight(width / 2);
            imgItem = (ImageView) itemView.findViewById(R.id.img_what_one_item);
            imgAvatarReview = (CircleImageView) itemView.findViewById(R.id.img_avatar_what_one_item);
            txtName = (TextView) itemView.findViewById(R.id.txt_name_what_one_item);
            txtType = (TextView) itemView.findViewById(R.id.txt_type_what_one_item);
            txtAddress = (TextView) itemView.findViewById(R.id.txt_address_what_one_item);
            txtNameReview = (TextView) itemView.findViewById(R.id.txt_name_what_one_review);
            txtDate = (TextView) itemView.findViewById(R.id.txt_date_what_one_review);
        }
    }


    public void clearData() {
        items.clear();
    }
}
