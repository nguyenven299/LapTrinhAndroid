package com.nguyenven.randomdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.view.Window;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtNumber;
    Button btnRandom;
    EditText edtBatDau;
    EditText edtKetThuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        btnRandom.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View view) {
                // tao so ngau nhien
                String batDau = edtBatDau.getText().toString().trim();
                String ketThuc = edtKetThuc.getText().toString().trim(); // tránh bị tính khoảng trắng làm ký tự

                //cach 1: .length() cach 2: .isEmpty()
                if (batDau.length() == 0 || ketThuc.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui Lòng Nhập Đủ Số",Toast.LENGTH_LONG).show();
                } else {
                    // ep kieu
                    int so1 = Integer.parseInt(batDau); // "12" -> 12
                    int so2 = Integer.parseInt(ketThuc);
                    Random random = new Random();
                    if (so1 < so2) {
                        int number = random.nextInt(so2 - so1 + 1) + so1;
                        // so -> chuoi
                        txtNumber.setText(String.valueOf(number)); // number + "" ;
                    } else if (so2 < so1) {
                        int number = random.nextInt(so1 - so2 + 1) + so2;
                        txtNumber.setText(String.valueOf(number));
                    }
                }
            }
        });
    }
    private void AnhXa()
    {
        edtBatDau = (EditText) findViewById(R.id.editTextBatDau);
        edtKetThuc = (EditText) findViewById(R.id.editTextKetThuc);
        txtNumber = (TextView) findViewById(R.id.textViewNumber);
        btnRandom   = (Button) findViewById(R.id.buttonRandom);
    }
}
