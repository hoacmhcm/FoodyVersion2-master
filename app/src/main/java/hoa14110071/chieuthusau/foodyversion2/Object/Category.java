package hoa14110071.chieuthusau.foodyversion2.Object;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by minhh on 4/2/2017.
 */

public class Category {
    int Id;
    String Name;
    byte[] Image;
    byte[] ImagePress;


    public Category(int id, String name, byte[] image, byte[] imagePress) {
        Id = id;
        Name = name;
        Image = image;
        ImagePress = imagePress;
    }

    public Category() {
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }



    public Bitmap getImage() {
        Bitmap bitmap= BitmapFactory.decodeByteArray(Image,0,Image.length);
        return bitmap;
    }

    public Bitmap getImagePress() {
        Bitmap bitmap= BitmapFactory.decodeByteArray(ImagePress,0,ImagePress.length);
        return bitmap;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public void setImagePress(byte[] imagePress) {
        ImagePress = imagePress;
    }
}
