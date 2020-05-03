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
    private static final int READ_REQUEST_CODE_1 =200;
    Button buttonRead_1,buttonWrite, buttonRead_2;
    TextView textView_output, textViewAddress,textViewMaHoa,textViewGiaiMa, textViewND1, textViewND2;
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
//        editTextfilename     = findViewById(R.id.textview_filename);
//        editTextText = findViewById(R.id.textview_text);
        buttonRead_2 = findViewById(R.id.b_load_2);
        buttonRead_1  = findViewById(R.id.b_load);
        textViewND1 =findViewById(R.id.noidung1);
        textViewND2 = findViewById(R.id.noidung2);
        textViewAddress = findViewById(R.id.Address);
        textViewGiaiMa=findViewById(R.id.giama_output);
        textViewMaHoa= findViewById(R.id.mahoa_output);
        buttonWrite = findViewById(R.id.b_write);
        buttonWrite.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile();
            }
        } );
        buttonRead_1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFileSearch();

            }
        } );
        buttonRead_2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFileSearch1();

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
    private  void performFileSearch1()
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory( Intent.CATEGORY_OPENABLE );
        intent.setType( "text/*" );
        startActivityForResult( intent,READ_REQUEST_CODE_1 );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (requestCode == 42 && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                String path = uri.getPath();
                path = path.substring( path.indexOf( ":" ) + 1 );
                Toast.makeText( this, "" + path, Toast.LENGTH_SHORT ).show();
               textView_output.setText( ReadText( path ) );
                String namefile = path;
                textViewAddress .setText( namefile );
//                //chuyển đổi từ chuỗi sang hệ nhị phân
                StringtoBinary();
                textViewGiaiMa.setVisibility( View.GONE );
                textViewND2.setVisibility( View.GONE );
                textViewMaHoa.setVisibility( View.VISIBLE );
                textViewND1.setVisibility( View.VISIBLE );

            }
        }

       else if (requestCode == 200 && resultCode == RESULT_OK)
        {
            if (data != null) {
                Uri uri = data.getData();
                String path = uri.getPath();
                path = path.substring( path.indexOf( ":" ) + 1 );
                Toast.makeText( this, "" + path, Toast.LENGTH_SHORT ).show();
                textView_output.setText( ReadText( path ) );
                String namefile = path;
                textViewAddress .setText( namefile );
//                //chuyển đổi từ nhij phaan sang chuoi
                BinarytoString();
                textViewMaHoa.setVisibility( View.GONE );
                textViewND1.setVisibility( View.GONE );
                textViewGiaiMa.setVisibility( View.VISIBLE );
                textViewND2.setVisibility( View.VISIBLE );
            }
        }
    }
//
//
    private void StringtoBinary() {
        byte[] bytes = textView_output.getText().toString().getBytes();
                StringBuilder binary = new StringBuilder();
                for (byte b : bytes)
                {
                    int val = b;
                    for (int i = 0; i < 8; i++)
                    {
                        binary.append((val & 128) == 0 ? 0 : 1);
                        val <<= 1;
                    }
                    binary.append(' ');
                }
                textViewMaHoa.setText( binary );
    }

    public  void BinarytoString ()
        {
            //java solution
            String letters = textView_output.getText().toString();
            String s = " ";
            for(int index = 0; index < letters.length(); index+=9) {
                String temp = letters.substring(index, index+8);
                int num = Integer.parseInt(temp,2);
                char letter = (char) num;
                s = s+letter;
            }
           textViewGiaiMa.setText( s );
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

            if(isExternalStorageWritable() && checkPermission( Manifest.permission.WRITE_EXTERNAL_STORAGE ))
            {
                String text = textViewAddress.getText().toString().trim();
                File filepath = Environment.getExternalStorageDirectory();
                String nameFile = text;
                nameFile = nameFile.replace( ".txt","" );
                File file = new File(filepath ,nameFile+"_2.txt");


                try {
                    if(textViewGiaiMa.length()!=0)
                    {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write( textViewGiaiMa.getText().toString().trim().getBytes() );
                        fileOutputStream.close();
                        Toast.makeText( this, "File Saved", Toast.LENGTH_SHORT ).show();
                    }
                    else  if(textViewMaHoa.length()!=0)
                    {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write( textViewMaHoa.getText().toString().trim().getBytes() );
                        fileOutputStream.close();
                        Toast.makeText( this, "File Saved", Toast.LENGTH_SHORT ).show();
                    }

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
