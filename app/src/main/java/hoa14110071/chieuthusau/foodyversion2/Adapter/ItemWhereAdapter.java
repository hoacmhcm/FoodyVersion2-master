package hoa14110071.chieuthusau.foodyversion2.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.util.List;

import hoa14110071.chieuthusau.foodyversion2.Object.Item;
import hoa14110071.chieuthusau.foodyversion2.R;
import hoa14110071.chieuthusau.foodyversion2.Services.RetrofitCreate;

/**
 * Created by minhh on 4/4/2017.
 */

public class ItemWhereAdapter extends RecyclerView.Adapter<ItemWhereAdapter.ViewHolderShowListItem> {

    Context context;
    int layout;
    List<Item> items;


    public ItemWhereAdapter(Context context, int layout, List<Item> items) {
        this.context = context;
        this.layout = layout;
        this.items = items;

    }

    public class ViewHolderShowListItem extends RecyclerView.ViewHolder {
        TextView txtAvgRating, txtName, txtAddress, txtState;
        ImageView imgItem;
        VideoView videoItem;
        RecyclerView recyclerImg, recyclerReview;
        Button btnComment, btnPicture, btnOderNow, btnCountComment, btnCountPicture;

        public ViewHolderShowListItem(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_name_one_item);
            txtAvgRating = (TextView) itemView.findViewById(R.id.txt_avg_rating);
            txtAddress = (TextView) itemView.findViewById(R.id.txt_address_one_item);
            txtState = (TextView) itemView.findViewById(R.id.txt_state_one_item);
//            btnComment = (Button) itemView.findViewById(R.id.btn_count_comment);
//            btnPicture = (Button) itemView.findViewById(R.id.btn_count_picture);
            btnOderNow = (Button) itemView.findViewById(R.id.btn_oder_now);
            btnCountComment = (Button) itemView.findViewById(R.id.btn_count_comment);
            btnCountPicture = (Button) itemView.findViewById(R.id.btn_count_picture);
            imgItem = (ImageView) itemView.findViewById(R.id.img_one_item);
            videoItem = (VideoView) itemView.findViewById(R.id.video_one_item);
            recyclerReview = (RecyclerView) itemView.findViewById(R.id.recycler_list_reviews);
            recyclerImg = (RecyclerView) itemView.findViewById(R.id.recycler_img_item);
        }
    }

    @Override
    public ViewHolderShowListItem onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, parent, false);
        ViewHolderShowListItem viewHolderShowListItem = new ViewHolderShowListItem(view);
        return viewHolderShowListItem;
    }

    @Override
    public void onBindViewHolder(ViewHolderShowListItem holder, int position) {
        Item item = items.get(position);
        //load file ảnh từ Drawable với Id được lấy từ database
        if (!item.getImage().equals("")) {
//            Log.e("URLIMG",RetrofitCreate.IMGE_URL + "fdi" + item.getImage() + ".png");
            Glide.with(context).load(RetrofitCreate.IMGE_URL + "fdi" + item.getImage() + ".png").into(holder.imgItem);
        } else {
            holder.imgItem.setImageDrawable(null);
        }
        holder.txtName.setText(item.getName());
        holder.txtAvgRating.setText(String.format("%.1f", item.getAVGRating()) + "");
        holder.txtAddress.setText(item.getAddress());
        holder.btnCountComment.setText(String.valueOf(item.getTotalReviews()));
        holder.btnCountPicture.setText(String.valueOf(item.getTotalPictures()));
        //Load bình luận
        ReviewAdapter reviewAdapter = new ReviewAdapter(context, R.layout.custom_one_row_review, item.getReviews());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.recyclerReview.setLayoutManager(layoutManager);
        holder.recyclerReview.setAdapter(reviewAdapter);
        reviewAdapter.notifyDataSetChanged();
    }

    public void clearData() {
        items.clear();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}

