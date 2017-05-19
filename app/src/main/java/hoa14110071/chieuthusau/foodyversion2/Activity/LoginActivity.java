package hoa14110071.chieuthusau.foodyversion2.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hoa14110071.chieuthusau.foodyversion2.R;

public class LoginActivity extends AppCompatActivity {
    private EditText txt_email;
    private EditText txt_pass;
    private Button  btn_dangnhap;
    private Button  btn_dangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_dangky = (Button) findViewById(R.id.btn_dangky);
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
