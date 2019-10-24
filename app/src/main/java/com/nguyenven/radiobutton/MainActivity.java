package com.nguyenven.radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroupTime;
    RadioButton rdSang, rdTrua,rdChieu;
    Button btnXacNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroupTime = (RadioGroup) findViewById(R.id.RadioGroup);

        btnXacNhan = (Button) findViewById(R.id.buttonXacNhan);
        rdSang = (RadioButton) findViewById(R.id.radioButtonSang);
        rdTrua = (RadioButton) findViewById(R.id.radioButtonTrua);
        rdChieu = (RadioButton) findViewById(R.id.radioButtonChieu);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rdSang.isChecked())
                {
                    Toast.makeText(MainActivity.this,rdSang.getText(),Toast.LENGTH_SHORT).show();
                }
                if(rdTrua.isChecked())
                {
                    Toast.makeText(MainActivity.this,rdTrua.getText(),Toast.LENGTH_SHORT).show();
                }
                if (rdChieu.isChecked())
                {
                    Toast.makeText(MainActivity.this, rdChieu.getText(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        radioGroupTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                // i trả về id radiobutton chọn
                switch (i)
                {
                    case R.id.radioButtonSang:
                        Toast.makeText(MainActivity.this, "Bạn Chọn Sáng", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButtonTrua:
                        Toast.makeText(MainActivity.this,"Bạn Chọn trưa",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButtonChieu:
                        Toast.makeText(MainActivity.this,"Bạn Chọn Chiều",Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
    }
}
