package com.oceanblue.busshedulesrilanka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BusSearchForStartEndPlaceActivity extends AppCompatActivity {

    private Button btnsearch,btnmore,btnrev,btnbackhome;
    private EditText txtstart,txtend;
    private String esource,esource2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_search_for_start_end_place);


        setTitle("Search From To Where");

        btnsearch = (Button) findViewById(R.id.btn_search);
        btnmore = (Button) findViewById(R.id.btn_more);
        btnrev = (Button) findViewById(R.id.btn_rev);
        btnbackhome = findViewById(R.id.btn_back_home);
        txtstart=(EditText) findViewById(R.id.txt_start2);
        txtend=(EditText) findViewById(R.id.txt_end);



        btnbackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusSearchForStartEndPlaceActivity.this, UserHomeActivity.class);
                startActivity(intent);
            }
        });

        btnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add thenuka root intend
            }
        });

        btnrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esource= txtstart.getText().toString();
                esource2= txtend.getText().toString();
                String re1=esource;
                String re2=esource2;

                txtstart.setText(re2);
                txtend.setText(re1);

            }
        });


        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Name = txtstart.getText().toString();
                final String word = txtend.getText().toString();
                if (Name.length() == 0) {
                    txtstart.requestFocus();
                    txtstart.setError("FIELD CANNOT BE EMPTY");
                } else if (!Name.matches("[a-zA-Z ]+")) {
                    txtstart.requestFocus();
                    txtstart.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                } else if (word.length() == 0) {
                    txtend.requestFocus();
                    txtend.setError("FIELD CANNOT BE EMPTY");
                } else if (!Name.matches("[a-zA-Z ]+")) {
                    txtend.requestFocus();
                    txtend.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                } else {
                    esource= txtstart.getText().toString();
                    esource2= txtend.getText().toString();
                    Query get = FirebaseDatabase.getInstance().getReference("bus_information").orderByChild("startPlace").equalTo(esource);


                    get.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for(DataSnapshot ds: dataSnapshot.getChildren()){

                                Query get2 = FirebaseDatabase.getInstance().getReference("bus_information").orderByChild("endPlace").equalTo(esource2);
                                get2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                                            Intent intent = new Intent(BusSearchForStartEndPlaceActivity.this, DisplayActivity.class);
                                            intent.putExtra("startlocation", txtstart.getText().toString());
                                            intent.putExtra("endlocation", txtend.getText().toString());
                                            startActivity(intent);
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }

                                });
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });

                }
            }
        });

    }
}
