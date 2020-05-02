package com.example.firestoredatabase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MenuActivity  extends AppCompatActivity {

    private EditText editTextFullName, editTextAge, editTextPhone, editTextAddress, editTextId;
    private Spinner spinnerClass;
    Button buttonSave, buttonDel;
    private TextView textViewFullName, textViewAge, textViewPhone, textViewAddress, textViewId;
    final String FullName ="Full Name";
    final  String Class ="Class";
    final  String Age = "Age";
    final  String PhoneNumber ="Phone Number";
    final  String Address = "Address";
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private  ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView( R.layout.add_del_activity );
        super.onCreate( savedInstanceState );
        editTextFullName = findViewById(R.id.EditTextFullName);
        editTextAge = findViewById(R.id.EditTextAge);
        editTextPhone =findViewById( R.id.EditTextPhone);
        editTextAddress = findViewById(R.id.EditTextAddress);
        spinnerClass = findViewById( R.id.SpinnerClass );
        editTextId = findViewById(R.id.EditTextId);
        buttonSave = findViewById(R.id.ButtonSave);

        buttonDel = findViewById(R.id.ButtonDel);




        firebaseFirestore.collection( "THPT Ngô Quyền" ).get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            for ( QueryDocumentSnapshot documentSnapshot : task.getResult())
//                            Log.d(TAG,documentSnapshot.getId()+ "=>" + documentSnapshot.getData());
                               // arrayAdapter.add( documentSnapshot.getId()+ documentSnapshot.getData() );
                            //editTextId.setText( documentSnapshot.getId() );
                            {
                                editTextId.setText( documentSnapshot.getId() );
                                editTextFullName.setText( documentSnapshot.getData().toString() );
                            }
                        }
                        else
                        {
                            Log.d("Test", "Error",task.getException());
                        }
                    }
                } );

    }
}
