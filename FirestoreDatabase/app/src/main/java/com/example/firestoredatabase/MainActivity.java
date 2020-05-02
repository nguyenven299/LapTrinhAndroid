package com.example.firestoredatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Test";
    private ListView listViewData;
    private ArrayAdapter<String> arrayAdapter;
    private  FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private  ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        arrayAdapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1 );
//        arrayList = new ArrayList<>(this, android.R.layout.simple_list_item_1);
        listViewData = findViewById( R.id.ListViewData );
//        firebaseFirestore.collection( "THPT Ngô Quyền" ).addSnapshotListener( new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//
//            }
//        } );

        firebaseFirestore.collection( "THPT Ngô Quyền" ).get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            for ( QueryDocumentSnapshot documentSnapshot : task.getResult())
//                            Log.d(TAG,documentSnapshot.getId()+ "=>" + documentSnapshot.getData());
                            arrayAdapter.add( documentSnapshot.getId()+ documentSnapshot.getData() );

                            listViewData.setAdapter(arrayAdapter );
                        }
                        else
                        {
                            Log.d(TAG, "Error",task.getException());
                        }
                    }
                } );
        listViewData.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = arrayAdapter.getItem( position );
                String key = data.split( "\n")[0];
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra( "KEY", key );
                startActivity( intent );
            }
        } );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionMode mode;
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate( R.menu.menu_activity,menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuManager)
        {
            Intent intent = new Intent(MainActivity.this, AddDelActivity.class);
            startActivity( intent );
        }
        return super.onOptionsItemSelected( item );
    }
}
