package com.example.appchatv4.Main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appchatv4.Module.Verify;
import com.example.appchatv4.R;
import com.hbb20.CountryCodePicker;

public class InserInfoActivity extends AppCompatActivity {
    Button buttonHuy, buttonDongY;
    EditText editTextSDT, editTextMSSV, editTextHoTen,editTextMatKhau, editTextMatKhau2;
    Spinner spinnerNganhHoc, spinnerLopHoc;
    CountryCodePicker countryCodePicker;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_insert_info );
        editTextSDT = findViewById(R.id.edtiTextSDT);
        countryCodePicker = findViewById( R.id.ccp );
buttonHuy = findViewById(R.id.buttonHuy);
buttonDongY = findViewById(R.id.buttonDongY);
buttonDongY.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (editTextSDT.length()==9)
        {
            Verify verify = new Verify( );

            countryCodePicker.registerCarrierNumberEditText( editTextSDT );

            verify.setSDT( countryCodePicker.getFullNumberWithPlus() );
            Toast.makeText( InserInfoActivity.this, verify.getSDT(), Toast.LENGTH_SHORT ).show();
        }
       else
        {
            editTextSDT.setError( "Vui Long Nhap SDT" );
        }

    }
} );
buttonHuy.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(InserInfoActivity.this, SignInActivity.class);
        startActivity( intent );
        finish();
    }
} );
    }
}
