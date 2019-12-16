package com.oceanblue.busshedulesrilanka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BusSearchForRootNumberActivity extends AppCompatActivity {

    Button buttonSearchByRoot;
    EditText inputRootNo;

    ListView listViewBus;

    DatabaseReference databaseBus;

    List<Bus> busList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_search_for_root_number);

        buttonSearchByRoot = findViewById(R.id.btn_search_by_root);
        inputRootNo = findViewById(R.id.txtRootNumber_search);

        listViewBus = findViewById(R.id.listViewBus);

        databaseBus = FirebaseDatabase.getInstance().getReference("bus_information");

        busList = new ArrayList<>();

        buttonSearchByRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchByRoot();
            }
        });
    }

    private void searchByRoot() {
        String rootNumber = inputRootNo.getText().toString();

        if(TextUtils.isEmpty(rootNumber)){
            Toast.makeText(this, "Please enter Root No", Toast.LENGTH_SHORT).show();
        }
        else
        {
            searchFormDB(rootNumber);
        }
    }

    private void searchFormDB(final String rootNumber) {

        databaseBus.orderByChild("rootNumber").equalTo(rootNumber).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.getChildrenCount() == 0)
                {
                    new SweetAlertDialog(BusSearchForRootNumberActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Wrong Root No, Please try using different root Number...")
                            .show();
                    Toast.makeText(BusSearchForRootNumberActivity.this, "Wrong Root No, Please try using different root Number...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    busList.clear();

                    for (DataSnapshot busSnapshot : dataSnapshot.getChildren()){

                        Bus bus = busSnapshot.getValue(Bus.class);

                        busList.add(bus);

                    }

                    BusList adapter = new BusList(BusSearchForRootNumberActivity.this, busList);
                    listViewBus.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
