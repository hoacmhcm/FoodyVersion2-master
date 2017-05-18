package hoa14110071.chieuthusau.foodyversion2.Database;

/**
 * Created by minhh on 4/4/2017.
 */

public class DatabaseString {
    //table CATEGORY
    public static final String CATEGORY = "CATEGORY";
    public static final String CATEGORY_ID = "ID";
    public static final String CATEGORY_NAME = "NAME";
    public static final String CATEGORY_IMG = "IMG";
    public static final String CATEGORY_IMGPRESS = "IMG_PRESS";

    //table LIST
    public static final String LIST = "LIST";
    public static final String LIST_ID = "ID";
    public static final String LIST_NAME = "NAME";
    public static final String LIST_IMG = "IMG";

    //table CITY
    public static final String CITY = "CITY";
    public static final String CITY_ID = "ID";
    public static final String CITY_NAME = "NAME";

    //table DISTRICT
    public static final String DISTRICT = "DISTRICT";
    public static final String DISTRICT_ID = "ID";
    public static final String DISTRICT_NAME = "NAME";
    public static final String DISTRICT_COUNT_STREET = "COUNT";
    public static final String DISTRICT_CITYID = "CITYID";

    //table STREET
    public static final String STREET = "STREET";
    public static final String STREET_ID = "ID";
    public static final String STREET_NAME = "NAME";
    public static final String STREET_DISTRICTID = "DISTRICTID";


    //table ITEM
    public static final String ITEM = "ITEM";
    public static final String ITEM_ID = "ID";
    public static final String ITEM_ADDRESS = "ADDRESS";
    public static final String ITEM_NAME = "NAME";
    public static final String ITEM_TOTALREVIEWS = "TOTALREVIEWS";
    public static final String ITEM_IMG = "IMG";
    public static final String ITEM_DISTRICTID = "DISTRICTID";
    public static final String ITEM_AVGRATING = "AVGRATING";
    public static final String ITEM_CATEGORYID = "CATEGORYID";
    public static final String ITEM_LISTID = "LISTID";
    public static final String ITEM_TOTALPICTURES = "TOTALPICTURES";
    public static final String ITEM_STREETID = "STREETID";

    //table REVIEW
    public static final String REVIEW = "REVIEW";
    public static final String REVIEW_ID = "ID";
    public static final String REVIEW_NAME = "NAME";
    public static final String REVIEW_RATING = "RATING";
    public static final String REVIEW_COMMENT = "COMMENT";
    public static final String REVIEW_COMMENTTRIM = "COMMENTTRIM";
    public static final String REVIEW_AVATAR = "AVATAR";
    public static final String REVIEW_ITEMID = "ITEMID";
    public static final String REVIEW_REVIEWURL = "REVIEWURL";

}
