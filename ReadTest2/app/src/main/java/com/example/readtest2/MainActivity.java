package com.example.readtest2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_STORAGE =1000;
    private static final int READ_REQUEST_CODE =42;
    Button buttonRead,buttonWrite;
    TextView textView_output, textViewAddress;
EditText editTextfilename, editTextText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //request permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission( Manifest.permission.READ_EXTERNAL_STORAGE )
        != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions( new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_STORAGE );
        }
        textView_output = findViewById(R.id.tv_output);
        editTextfilename     = findViewById(R.id.textview_filename);
        editTextText = findViewById(R.id.textview_text);
        buttonRead  = findViewById(R.id.b_load);
        textViewAddress = findViewById(R.id.Address);
        buttonWrite = findViewById(R.id.b_write);
        buttonWrite.setOnClickListener( new View.OnClickListener() {
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
    //read content
    private  String ReadText(String input)
    {
        File file = new File( Environment.getExternalStorageDirectory() ,input);
        StringBuilder text = new StringBuilder(  );
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line ;
            while ((line = br.readLine())!=null)
            {
                text.append(line);
                text.append("\n");

            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return text.toString();
    }
    //select file from storage
    private  void performFileSearch()
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory( Intent.CATEGORY_OPENABLE );
        intent.setType( "text/*" );
        startActivityForResult( intent,READ_REQUEST_CODE );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                String path = uri.getPath();
                path = path.substring( path.indexOf( ":" ) + 1 );
                Toast.makeText( this, "" + path, Toast.LENGTH_SHORT ).show();
                textView_output.setText( ReadText( path ) );
                String namefile = path;
                textViewAddress.setText( namefile );
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_REQUEST_STORAGE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText( this, "granted", Toast.LENGTH_SHORT ).show();

            }
            else {
                Toast.makeText( this, "not granted", Toast.LENGTH_SHORT ).show();
                finish();
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
        if (editTextfilename.length() <3)
        {
            editTextfilename.setError( "Insert Your File Name" );
        }
        else
        {
            if(isExternalStorageWritable() && checkPermission( Manifest.permission.WRITE_EXTERNAL_STORAGE ))
            {
                String text = editTextfilename.getText().toString();
                File filepath = Environment.getExternalStorageDirectory();
                File file = new File(filepath ,text+".txt");


                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write( editTextText.getText().toString().getBytes() );
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

    }
    public boolean checkPermission(String permission)
    {
        int check = ContextCompat.checkSelfPermission( this,permission );
        return (check== PackageManager.PERMISSION_GRANTED);
    }
}
