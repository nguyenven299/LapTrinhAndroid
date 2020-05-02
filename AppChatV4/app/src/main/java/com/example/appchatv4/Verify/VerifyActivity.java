package com.example.appchatv4.Verify;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appchatv4.Module.Verify;

public class VerifyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        String SDT, MaXacThuc;
        Verify verify = new Verify();
        SDT= verify.getSDT();
        sendVerification();
        verifySignIncode();
        Log.d( "Test1", "onCreate: "+SDT );
    }


    private static void sendVerification() {

    }

    private static void verifySignIncode() {
    }
}
