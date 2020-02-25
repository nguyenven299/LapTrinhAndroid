 package com.example.connectfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


 public class MainActivity extends AppCompatActivity {
    EditText editTextName, editTextAge, editTextPhone, editTextHieght;
    Button buttonSave;
     FirebaseDatabase database = FirebaseDatabase.getInstance();
     DatabaseReference myRef = database.getReference("message");
     Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = (EditText) findViewById(R.id.EditTextName);
        editTextAge = (EditText) findViewById(R.id.EditTextAge);
        editTextPhone = (EditText) findViewById(R.id.EditTextPhoneNum);
        editTextHieght = (EditText) findViewById(R.id.EditTextHeight);
        buttonSave = (Button) findViewById(R.id.ButtonSave);
        member = new Member();
        myRef = FirebaseDatabase.getInstance().getReference().child("Member");
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                    int Age = Integer.parseInt(editTextAge.getText().toString().trim());
                    Long Phone = Long.parseLong(editTextPhone.getText().toString().trim());
                    Float Height = Float.parseFloat(editTextHieght.getText().toString().trim()); /*ben class mainactivity chuyen qua ben member bang parseFloat */
                    member.setName(editTextName.getText().toString().trim());
                    member.setAge(Age);
                    member.setPhone(Phone);
                    member.setHeight(Height);


                        myRef.child("member").setValue(member, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                if(databaseError == null)
                                {
                                    Toast.makeText(MainActivity.this, "Data insert successfull", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Data insert fail", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });



//                myRef.setValue("Hello, World!");
            }
        });
    }
}
