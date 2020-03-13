package com.example.cloudfirestorev2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.core.View;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private  static  final  String TAG = "MainActivity";
    private  static  final  String TITLE = "title";
    private  static  final  String DES = "description";

    private  EditText editTextTitle, editTextDes;
    private  Button buttonSave;
    private  FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTitle = findViewById(R.id.EditTextTitle);
        editTextDes = findViewById(R.id.EditTextDes );
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String title = editTextTitle.getText().toString();
                String descrption = editTextDes.getText().toString();

                Map<String, Object> note = new HashMap<>();
                note.put(TITLE,title);
                note.put(DES , descrption);


                db.collection("Notebook").document("My First Note").set(note)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(MainActivity.this, "Note saved  " , Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Note cannot saved  " , Toast.LENGTH_SHORT).show();
                                Log.d(TAG,e .toString());
                            }
                        });

            }
        });
    }




}
