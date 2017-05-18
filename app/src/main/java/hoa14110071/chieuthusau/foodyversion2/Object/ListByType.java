package hoa14110071.chieuthusau.foodyversion2.Object;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by minhh on 3/21/2017.
 */

public class ListByType {
    int Id;
    String Name;
    byte[] Image;

    public ListByType(int id, String name, byte[] image) {
        Id = id;
        Name = name;
        Image = image;
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

    public ListByType() {

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


}
