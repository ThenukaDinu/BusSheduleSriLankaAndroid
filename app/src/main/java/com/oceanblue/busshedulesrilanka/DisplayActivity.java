package com.oceanblue.busshedulesrilanka;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listView;
    String txtstartPlace;
    Button btnback;
    TextView txtstart2,txtend,txtstart23;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        listView=(ListView)findViewById(R.id.list_view);
        txtstart2=(TextView) findViewById(R.id.txt_sp);
        txtstart23=(TextView) findViewById(R.id.txt_sp2);
        txtend=(TextView) findViewById(R.id.txt_sp3);
        btnback=(Button) findViewById(R.id.btn_back);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DisplayActivity.this, BusSearchForStartEndPlaceActivity.class);
                startActivity(intent2);
            }
        });

        Intent intent = getIntent();
        String startlocation = intent.getStringExtra("startlocation");
        txtstart2.setText("Your Search: " + startlocation);
        String endlocation = intent.getStringExtra("endlocation");
        txtend.setText(endlocation);

        Query get = FirebaseDatabase.getInstance().getReference("bus_information").orderByChild("startPlace").equalTo(startlocation);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);


        get.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(getdata.class).toString();

                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
