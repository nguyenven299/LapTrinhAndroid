package com.example.firestoredatabase;

import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddDelActivity extends AppCompatActivity {
    private EditText editTextFullName, editTextAge, editTextPhone, editTextAddress, editTextId;
    private Spinner spinnerClass;
    Button buttonSave, buttonDel;
    private TextView textViewFullName, textViewAge, textViewPhone, textViewAddress;

    //Khai báo tên cho các cột con của document

    final String FullName ="Full Name";
    final  String Class ="Class";
    final  String Age = "Age";
    final  String PhoneNumber ="Phone Number";
    final  String Address = "Address";

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    protected void onCreate(Bundle savedInstantcesState) {

        super.onCreate( savedInstantcesState );
        setContentView( R.layout.add_del_activity );

        editTextId = findViewById(R.id.EditTextId);
        editTextFullName = findViewById(R.id.EditTextFullName);
        editTextAge = findViewById(R.id.EditTextAge);
        editTextPhone =findViewById( R.id.EditTextPhone);
        editTextAddress = findViewById(R.id.EditTextAddress);
        spinnerClass = findViewById( R.id.SpinnerClass );
        buttonSave = findViewById(R.id.ButtonSave);
        buttonDel = findViewById(R.id.ButtonDel);

        buttonSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             InsertDatabase();
            }
        } );

        buttonDel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DelDatabase();
            }
        } );


        final ArrayList<String > arrayClass = new ArrayList<String >();
        arrayClass.add("10");
        arrayClass.add( "11" );
        arrayClass.add("12");

        ArrayAdapter arrayAdapter = new ArrayAdapter( AddDelActivity.this, android.R.layout.simple_spinner_item,arrayClass );
        spinnerClass.setAdapter(arrayAdapter);

        spinnerClass.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText( AddDelActivity.this, arrayClass.get(position), Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ((TextView)spinnerClass.getSelectedView()).setError( "Please Select Class" );
            }
        } );

        //lấy dữ liệu từ người dùng nhap




    }
    public void InsertDatabase()
    {
        Map<String, Object> data = new HashMap<>();

        data.put( FullName,editTextFullName.getText().toString()  );
        data.put( Age, editTextAge.getText().toString() );
        data.put(Class,spinnerClass.getSelectedItem().toString());
        data.put( PhoneNumber,editTextPhone.getText().toString() );
        data.put( Address,editTextAddress.getText().toString() );
        // kết nối tới Firebase để add Database

        firebaseFirestore.collection( "THPT Ngô Quyền" ).document(editTextId.getText().toString()).set( data )
                .addOnSuccessListener( new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText( AddDelActivity.this, "Your Data Insert Successfuly!", Toast.LENGTH_SHORT ).show();
                    }
                } )
                .addOnFailureListener( new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText( AddDelActivity.this, "Your Data Cannot Insert", Toast.LENGTH_SHORT ).show();
                    }
                } );
    }
    public void DelDatabase()
    {
        // kết nối tới Firebase để add Database

        firebaseFirestore.collection( "THPT Ngô Quyền" ).document(editTextId.getText().toString() ).delete()
                .addOnSuccessListener( new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText( AddDelActivity.this, "Your Data Insert Successfuly!", Toast.LENGTH_SHORT ).show();
                    }
                } )
                .addOnFailureListener( new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText( AddDelActivity.this, "Your Data Cannot Insert", Toast.LENGTH_SHORT ).show();
                    }
                } );
    }
}
