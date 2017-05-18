package hoa14110071.chieuthusau.foodyversion2.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import hoa14110071.chieuthusau.foodyversion2.Object.Review;
import hoa14110071.chieuthusau.foodyversion2.R;
import hoa14110071.chieuthusau.foodyversion2.Services.RetrofitCreate;

/**
 * Created by minhh on 4/4/2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolderShowListReview>{

    private Context context;
    private int layout;
    private List<Review> reviews;

    public ReviewAdapter(Context context, int layout, List<Review> reviews){
        this.context = context;
        this.layout = layout;
        this.reviews = reviews;
    }

    public class ViewHolderShowListReview extends RecyclerView.ViewHolder {
        CircleImageView imgAvatar;
        TextView txtName, txtComment, txtRating;

        public ViewHolderShowListReview(View itemView) {
            super(itemView);

            imgAvatar = (CircleImageView) itemView.findViewById(R.id.img_avatar_one_review);
            txtName = (TextView) itemView.findViewById(R.id.txt_name_one_review);
            txtRating = (TextView) itemView.findViewById(R.id.txt_rating_one_review);
            txtComment = (TextView) itemView.findViewById(R.id.txt_comment_one_review);

        }
    }

    @Override
    public ReviewAdapter.ViewHolderShowListReview onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout,parent,false);
        ViewHolderShowListReview viewHolderShowListReview = new ViewHolderShowListReview(view);
        return viewHolderShowListReview;
    }
    @Override
    public void onBindViewHolder(ReviewAdapter.ViewHolderShowListReview holder, int position) {
        Review review = reviews.get(position);

//        int imageResource = context.getResources().getIdentifier("ava"+review.getImage(), "drawable", context.getPackageName());
        if(!review.getImage().equals("")){
//            Log.e("URLIMGREVIEW", RetrofitCreate.IMGE_URL + "ava" + review.getName() + ".png");
            Glide.with(context).load(RetrofitCreate.IMGE_URL  + review.getImage()).into(holder.imgAvatar);
        } else {
            holder.imgAvatar.setImageDrawable(null);
        }
        holder.txtName.setText(review.getName());
        holder.txtRating.setText(String.format("%.1f",review.getRating())+"");
        holder.txtComment.setText(review.getCommentTrim());
    }

    @Override
    public int getItemCount() {
        if(reviews != null){
            return reviews.size();
        }
        return 0;
    }


}
