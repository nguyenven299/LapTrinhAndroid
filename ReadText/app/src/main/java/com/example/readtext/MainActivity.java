package com.example.readtext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private  static final  int PERMISION_REQUEST_STORAGE =1000;
    private  static final  int   READ_REQUEST_CODE =42;
Button buttonSave, buttonRead;
EditText editText1, editText2;
TextView textView;
    File myExternalFile;
    String myData = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        buttonSave = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        editText1 = findViewById(R.id.editText);
        editText2 =findViewById(R.id.editText2);
        buttonRead = findViewById(R.id.button2);
        requestPermission();
        buttonSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile();
            }
        } );
        buttonRead.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFileSearch();
            }
        } );
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission( Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions( new String [] {Manifest.permission.READ_EXTERNAL_STORAGE},PERMISION_REQUEST_STORAGE );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        if(requestCode == PERMISION_REQUEST_STORAGE)
        {
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText( this, "Permission granted", Toast.LENGTH_SHORT ).show();
                Log.d( "Test1", "onRequestPermissionsResult: success" );
            }
            else {
                Toast.makeText( this, "not granted", Toast.LENGTH_SHORT ).show();
                Log.d( "Test2", "onRequestPermissionsResult: failed" );
                finish();
            }
        }
    }

    private String readText(String input) {
        File file = new File( Environment.getExternalStorageDirectory(),input );
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line=bufferedReader.readLine()) != null)
            {
text.append(line);
text.append("\n");

            }
            bufferedReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return text.toString();
    }
//chon file
    private void performFileSearch()
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory( Intent.CATEGORY_OPENABLE );
        intent.setType( "text/*" );
        startActivityForResult( intent,READ_REQUEST_CODE );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if(requestCode == READ_REQUEST_CODE && requestCode == Activity.RESULT_OK)
        {
            if(data !=null)
            {
                Uri uri = data.getData();
                String path = uri.getPath();
                path = path.substring( path.indexOf( ":" ) +1 );
                Toast.makeText( this, ""+path, Toast.LENGTH_SHORT ).show();


                textView.setText( readText(path) );

            }
        }
    }

    private  boolean isExternalStorageWritable()
{
    if(Environment.MEDIA_MOUNTED.equals( Environment.getExternalStorageState() ))
    {
        Log.i("State", "Yes");
        return true;
    }
    else
    {
        return false;
    }
}
    private void writeFile() {
        if(isExternalStorageWritable() && checkPermission( Manifest.permission.WRITE_EXTERNAL_STORAGE ))
        {
            String text = editText1.getText().toString();
            File file = new File(Environment.getExternalStorageDirectory(),text+".txt");

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write( editText2.getText().toString().getBytes() );
                fileOutputStream.close();
                Toast.makeText( this, "File Saved", Toast.LENGTH_SHORT ).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        else {
            Toast.makeText( this, "Cannot save", Toast.LENGTH_SHORT ).show();
        }
    }
    public boolean checkPermission(String permission)
    {
        int check = ContextCompat.checkSelfPermission( this,permission );
        return (check== PackageManager.PERMISSION_GRANTED);
    }
}
