package com.nguyenven.imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ImageView imgHinh1;
    RelativeLayout manHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgHinh1 = (ImageView) findViewById(R.id.ImageViewHinh1);
        imgHinh1.setImageResource(R.drawable.android_logo2);
        manHinh = (RelativeLayout) findViewById(R.id.namHinh);
        manHinh.setBackgroundResource(R.drawable.background2);
    }
}
