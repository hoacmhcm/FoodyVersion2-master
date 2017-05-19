package hoa14110071.chieuthusau.foodyversion2.JavaClass;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hoa14110071.chieuthusau.foodyversion2.Activity.AddItemActivity;
import hoa14110071.chieuthusau.foodyversion2.R;

/**
 * Created by minhh on 19-May-17.
 */

public class FragmentFooterMenu extends BottomSheetDialogFragment {

    private Button btn_themdiadiem;

    public FragmentFooterMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.custom_dialog, container, false);
        btn_themdiadiem = (Button) v.findViewById(R.id.itAddAddress);
        btn_themdiadiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AddItemActivity.class);
                startActivity(intent);
            }
        });
        return v;

    }

}
