package hoa14110071.chieuthusau.foodyversion2.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("ID")
    @Expose
    private Integer ID;
    @SerializedName("ADDRESS")
    @Expose
    private String Address;
    @SerializedName("NAME")
    @Expose
    private String Name;
    @SerializedName("TOTALREVIEWS")
    @Expose
    private Integer TotalReviews;
    @SerializedName("DISTRICTID")
    @Expose
    private Integer DistrictID;
    @SerializedName("AVGRATING")
    @Expose
    private Double AVGRating;
    @SerializedName("CATEGORYID")
    @Expose
    private Integer CategoryID;
    @SerializedName("LISTID")
    @Expose
    private Integer ListID;
    @SerializedName("TOTALPICTURES")
    @Expose
    private Integer TotalPictures;
    @SerializedName("IMAGE")
    @Expose
    private String Image;
    @SerializedName("STREETID")
    @Expose
    private Integer StreetID;
    private List<Review> reviews;


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getTotalReviews() {
        return TotalReviews;
    }

    public void setTotalReviews(Integer totalReview) {
        TotalReviews = totalReview;
    }

    public Integer getDistrictID() {
        return DistrictID;
    }

    public void setDistrictID(Integer districtID) {
        DistrictID = districtID;
    }

    public Double getAVGRating() {
        return AVGRating;
    }

    public void setAVGRating(Double AVGRating) {
        this.AVGRating = AVGRating;
    }

    public Integer getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(Integer categoryID) {
        CategoryID = categoryID;
    }

    public Integer getListID() {
        return ListID;
    }

    public void setListID(Integer listID) {
        ListID = listID;
    }

    public Integer getTotalPictures() {
        return TotalPictures;
    }

    public void setTotalPictures(Integer totalPictures) {
        TotalPictures = totalPictures;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Integer getStreetID() {
        return StreetID;
    }

    public void setStreetID(Integer streetID) {
        StreetID = streetID;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews)
    {
        this.reviews = reviews;
    }
}