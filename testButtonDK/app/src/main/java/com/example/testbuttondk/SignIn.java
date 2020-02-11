package com.example.testbuttondk;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {
   private TextView textViewHello;
    private EditText editTextUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
    textViewHello = (TextView) findViewById(R.id.textviewHelloS);
    editTextUser = (EditText) findViewById(R.id.editextUsernameS);
//    if(editTextUser.getText().toString().trim().equalsIgnoreCase(""))
//    {
//        editTextUser.setError("This field cannot be bank");
//    }
        if (editTextUser.getText().toString().length() <=0)
        {
            editTextUser.setError("Enter User Name!");
        }
        else
        {
            editTextUser.setError(null);
        }
    }

}
