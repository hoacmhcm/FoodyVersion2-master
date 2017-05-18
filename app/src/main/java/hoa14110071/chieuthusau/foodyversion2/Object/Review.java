package hoa14110071.chieuthusau.foodyversion2.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("ID")
    @Expose
    private Integer Id;
    @SerializedName("NAME")
    @Expose
    private String Name;
    @SerializedName("RATING")
    @Expose
    private Double Rating;
    @SerializedName("COMMENT")
    @Expose
    private Object Comment;
    @SerializedName("COMMENTTRIM")
    @Expose
    private String CommentTrim;
    @SerializedName("ITEMID")
    @Expose
    private Integer ItemId;
    @SerializedName("IMAGE")
    @Expose
    private String Image;

    public Review(int id, int itemId, String name, String comment, String commentTrim, String image, double rating) {
        Id = id;
        ItemId = itemId;
        Name = name;
        Comment = comment;
        CommentTrim = commentTrim;
        Image = image;
        Rating = rating;
    }

    public Review() {
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getRating() {
        return Rating;
    }

    public void setRating(Double rating) {
        Rating = rating;
    }

    public Object getComment() {
        return Comment;
    }

    public void setComment(Object comment) {
        Comment = comment;
    }

    public String getCommentTrim() {
        return CommentTrim;
    }

    public void setCommentTrim(String commentTrim) {
        CommentTrim = commentTrim;
    }

    public Integer getItemId() {
        return ItemId;
    }

    public void setItemId(Integer itemId) {
        ItemId = itemId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
