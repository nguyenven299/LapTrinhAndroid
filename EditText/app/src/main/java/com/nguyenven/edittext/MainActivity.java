package com.nguyenven.edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNoiDung;
    Button btnClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNoiDung = (EditText) findViewById(R.id.editTextHoTen);
        btnClick = (Button) findViewById(R.id.btnClick);

        edtNoiDung.setText("Android");
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noiDung = edtNoiDung.getText().toString();
                Toast.makeText(MainActivity.this, noiDung,Toast.LENGTH_LONG).show(); // hien thi ham Toast
            }
        });
    }
}
