package com.example.appchatv4.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appchatv4.R;

public class MainActivity extends AppCompatActivity {
Button buttonDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        buttonDangKy= findViewById(R.id.buttonDangKy);
        buttonDangKy.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity( intent );
            }
        } );
    }
}
