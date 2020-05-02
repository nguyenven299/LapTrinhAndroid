package com.example.loginfbfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.security.Permission;
import java.util.Arrays;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class MainActivity extends AppCompatActivity {

    private CallbackManager mCallbaclManager;

    private String TAG = "Test";
    private FirebaseAuth mAuth;
    private Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        mAuth = FirebaseAuth.getInstance();
        mCallbaclManager = CallbackManager.Factory.create();
        LoginButton loginButton= findViewById( R.id.login_button );

        loginButton.setReadPermissions( Arrays.asList("email", "public_profile"));
        loginButton.registerCallback( mCallbaclManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG,"fb: onSuccess" +loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG,"fb: onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG,"fb:onError", error);
            }
        } );

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult( requestCode,resultCode,data );
        mCallbaclManager.onActivityResult( requestCode,resultCode,data );
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {
        Log.d(TAG,"handleFBAccessToken"+accessToken);
        AuthCredential credential = FacebookAuthProvider.getCredential( accessToken.getToken() );
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Log.d(TAG,"Success");
                        Toast.makeText( MainActivity.this, "Success", Toast.LENGTH_SHORT ).show();
                        FirebaseUser user  = requireNonNull( mAuth ).getCurrentUser();
                        updateUI( user );
                    }
                    else
                    {
                        Log.w(TAG,"fail", task.getException());
                        Toast.makeText( MainActivity.this, "Fail", Toast.LENGTH_SHORT ).show();
                        updateUI( null );
                    }
                    }
                } );
    }
    public void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {


    }
    public void  Log_Out(View v)
    {
        btnLogOut.setVisibility( View.VISIBLE );
        FirebaseAuth.getInstance().signOut();
        deleteUser();
    }
    public void deleteUser ()
    {
        final FirebaseUser user  = requireNonNull( mAuth ).getCurrentUser();
        user.delete()
        .addOnCompleteListener( new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful())
        {
            Toast.makeText( MainActivity.this, "deleted success", Toast.LENGTH_SHORT ).show();
        }
            else
        {
            Toast.makeText( MainActivity.this, "delete fail", Toast.LENGTH_SHORT ).show();
        }
            }
        } );
    }
}
