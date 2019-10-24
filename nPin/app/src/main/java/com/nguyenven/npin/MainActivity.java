package com.nguyenven.npin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private boolean Check = true;
    private boolean Flash = true;
    private Camera camera;
    private Camera.Parameters parameters;

    @Override
    protected void onStart() {
        super.onStart();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int hasCameraPermission = checkSelfPermission(Manifest.permission.CAMERA);

            List<String> permissions = new ArrayList<String>();

            if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.CAMERA);

            }
            if (!permissions.isEmpty()) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), 111);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageButton = (ImageButton) findViewById(R.id.imageButton);
        Check = getApplicationContext()
                .getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH); //kiểm tra thiết bị có hỗ trợ đèn flash hay ko

        if (Check == false) {
            Context context;
            AlertDialog.Builder alerDialog = new AlertDialog.Builder(MainActivity.this);
            alerDialog.create();
            alerDialog.setTitle("Loi");
            alerDialog.setMessage("Thiết bị của bạn không hổ trợ đèn Flash");
            alerDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alerDialog.show();

        }

        getCamera();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Flash == true) {
                    MoFlash();
                } else {
                    TatFlash();
                }
            }
        });


    }

    private void getCamera() {
        if (camera == null && parameters == null) {
            camera = Camera.open();
            parameters = camera.getParameters();
        }
    }

    private void MoFlash() {
        if (Flash == true) {
            parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH); //kiểu hiển thị Fash (More_Torch = luôn hiển thị đèn đến khi mình tắt)
            camera.getParameters();
            camera.startPreview();
            Flash = false;
            TrangThai();
        }
    }

    private void TatFlash() {
        if (Flash == false) {
            parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.stopPreview();
            Flash = true;
            TrangThai();
        }
    }

    private void TrangThai() {
        if (Flash == true) {
            imageButton.setImageResource(R.drawable.off);
        } else {
            imageButton.setImageResource(R.drawable.on);
        }
    }
}
