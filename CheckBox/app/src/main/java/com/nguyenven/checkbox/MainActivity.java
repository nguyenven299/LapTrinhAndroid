package com.nguyenven.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox cbAndroid, cbIos, cbWdP;
    Button btnXacNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MonHoc= "Ban Da Chon Mon Hoc\n";
                if(cbAndroid.isChecked())
                {
                    MonHoc += cbAndroid.getText() + "\n";
                }
                if (cbIos.isChecked())
                {
                    MonHoc += cbIos.getText()+"\n";
                }
                if (cbWdP.isChecked())
                {
                    MonHoc += cbWdP.getText()+"\n";
                }
                Toast.makeText(MainActivity.this, MonHoc, Toast.LENGTH_SHORT).show();
            }
        });

//        cbAndroid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean a) {
//                if (a)
//                {
//                    Toast.makeText(MainActivity.this, "Ban Da Chon Andorid", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(MainActivity.this, "Ban Da Bo Chon Android", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
     private void AnhXa()
     {
         cbAndroid      = (CheckBox) findViewById(R.id.checkBoxAndroid);
         cbIos           = (CheckBox) findViewById(R.id.checkBoxIos);
         cbWdP          = (CheckBox) findViewById(R.id.checkBoxWdP);
         btnXacNhan = (Button) findViewById(R.id.buttonXacNhan);
     }
}
