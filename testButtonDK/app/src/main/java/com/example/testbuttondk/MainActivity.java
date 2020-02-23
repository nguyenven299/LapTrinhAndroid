package com.example.testbuttondk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {



   private Button buttonDangKy, buttonDangNhap;
   private EditText editTextNguoiDung, editTextMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        buttonDangKy = (Button) findViewById(R.id.buttonLoginL);
        buttonDangNhap = (Button) findViewById(R.id.buttonSigninL);
        editTextNguoiDung = (EditText) findViewById(R.id.edittextUserNameL);
        editTextMatKhau = (EditText) findViewById(R.id.edittextPassWordL);

        buttonDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignIn.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"Resgister",Toast.LENGTH_LONG).show();
            }
        });
        buttonDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextNguoiDung.getText().toString().isEmpty() && editTextMatKhau.getText().toString().isEmpty())
                {
                    editTextNguoiDung.setError("Please Enter User");
                    editTextMatKhau.setError("Please Enter PassWord");
                }
                 else if (editTextNguoiDung.getText().toString().isEmpty()&&editTextMatKhau.getText().toString().isEmpty())
                {
                    editTextNguoiDung.setError("Please Enter User");
                    editTextMatKhau.setError("Please Enter PassWord");
                }

                else
                {
                    Intent intent = new Intent(MainActivity.this, welcome1.class);
                    startActivity(intent);
                }
            }
        });
    }
}
