package com.example.appchatv4.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appchatv4.R;
public class SignInActivity extends AppCompatActivity {
    Button buttonHuyDangKy, buttonDangKy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sigup);
        buttonHuyDangKy = findViewById(R.id.buttonHuyDangKy);
        buttonDangKy = findViewById(R.id.buttonDangKy);
        buttonDangKy.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, InserInfoActivity.class);
                startActivity( intent );

            }
        } );
        buttonHuyDangKy.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( SignInActivity.this, MainActivity.class);
                startActivity( intent );
                finish();
            }
        } );
    }
}
