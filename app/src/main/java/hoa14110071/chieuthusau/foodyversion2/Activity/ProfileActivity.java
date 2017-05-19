package hoa14110071.chieuthusau.foodyversion2.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import hoa14110071.chieuthusau.foodyversion2.Fragment.fragmentEatWhat;
import hoa14110071.chieuthusau.foodyversion2.R;

public class ProfileActivity extends AppCompatActivity {
    LinearLayout btnDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnDangNhap = (LinearLayout) findViewById(R.id.buttonDangNhap);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),LoginActivity.class);
                startActivityForResult(intent,1);
            }
        });

    }
}
