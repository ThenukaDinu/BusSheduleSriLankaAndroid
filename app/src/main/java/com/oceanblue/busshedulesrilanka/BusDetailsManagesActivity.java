package com.oceanblue.busshedulesrilanka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BusDetailsManagesActivity extends AppCompatActivity implements TimePickerFragment.TimePickerListner {

    private EditText txtSearch,lblstartplace,lblendplace,lblrootnumber,lblstarttime,lalendtime;
    private Button btnClear,btnUpdate,btnSearch,btnstarttime,btnendtime;

    private static int isClick =1;

    private String txtBusNumber,splace,eplace,stime,etime,rno,bsno,id;
    DatabaseReference databasebusesview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details_manages);

        txtSearch       = findViewById(R.id.txtbusnosearch);
        lblstartplace   = findViewById(R.id.txtStartsPlace);
        lblendplace     = findViewById(R.id.txtEndPlace);
        lblrootnumber   = findViewById(R.id.txtRootsNumber);
        lblstarttime    = findViewById(R.id.txtStartsTime);
        lalendtime      = findViewById(R.id.txtEndsTime);
        btnClear        = findViewById(R.id.btn_clear);
        btnUpdate       = findViewById(R.id.btn_update);
        btnSearch       = findViewById(R.id.btn_search);
        btnstarttime   = findViewById(R.id.btn_modiStarttime);
        btnendtime = findViewById(R.id.btn_modiEndtime);

        lblstartplace.setEnabled(false);
        lblendplace.setEnabled(false);
        lblrootnumber.setEnabled(false);

        databasebusesview = FirebaseDatabase.getInstance().getReference("bus_information");

        btnstarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick = 1;
                DialogFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setCancelable(false);
                timePickerFragment.show(getSupportFragmentManager(),"timePicker");
            }
        });

        btnendtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick = 2;
                DialogFragment timePickerFragmentend = new TimePickerFragment();
                timePickerFragmentend.setCancelable(false);
                timePickerFragmentend.show(getSupportFragmentManager(),"timePicker");
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBusNumber =  txtSearch.getText().toString();
                if(txtBusNumber.equals(""))
                {
                    Toast.makeText(BusDetailsManagesActivity.this,"Requird Bus Number",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    retrieveBusData(txtBusNumber);
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clsFields();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBusNumber =  txtSearch.getText().toString();
                if(txtBusNumber.equals(""))
                {
                    Toast.makeText(BusDetailsManagesActivity.this, "Required Bus Number", Toast.LENGTH_SHORT).show();
                }
                else if(lblstarttime.getText().toString().equals(""))
                {
                    Toast.makeText(BusDetailsManagesActivity.this, "Required Start Time Number", Toast.LENGTH_SHORT).show();
                }
                else if(lalendtime.getText().toString().equals(""))
                {
                    Toast.makeText(BusDetailsManagesActivity.this, "Required End Time Number", Toast.LENGTH_SHORT).show();
                }
                else if(lblendplace.getText().toString().equals(""))
                {
                    Toast.makeText(BusDetailsManagesActivity.this, "Bus number Should be Searched before Update", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    updateBusInfo(id,lblstartplace.getText().toString(),lblendplace.getText().toString(),lblstarttime.getText().toString(),lalendtime.getText().toString(),lblrootnumber.getText().toString(),txtSearch.getText().toString());
                    clsFields();
                }
            }
        });
    }

    private void updateBusInfo(String ID, String splace, String eplace, String stime, String etime, String rno, String bsno) {

        DatabaseReference databaseBusModi = FirebaseDatabase.getInstance().getReference("bus_information").child(ID);
        Bus bus = new Bus(ID,splace,eplace,stime,etime,rno,bsno);
        databaseBusModi.setValue(bus);
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this file!")
                .setConfirmText("Yes,Update it!")
                .show();
    }

    private void retrieveBusData(final String txtBusNumber) {

        databasebusesview.orderByChild("busNumber").equalTo(txtBusNumber).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getChildrenCount() == 0)
                {
                    new SweetAlertDialog(BusDetailsManagesActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Wrong Bus No, Please try using different Bus Number...!")
                            .show();
                   // Toast.makeText(BusDetailsManagesActivity.this, "Wrong Bus No, Please try using different Bus Number...", Toast.LENGTH_SHORT).show();
                }
                else
                {

                  //  Toast.makeText(BusDetailsManagesActivity.this, "have data", Toast.LENGTH_SHORT).show();

                    for (DataSnapshot busSnapshot : dataSnapshot.getChildren()){

                        Bus bus =  busSnapshot.getValue(Bus.class);

                        splace = bus.getStartPlace();
                        eplace = bus.getEndPlace();
                        rno = bus.getRootNumber();
                        stime = bus.getStartTime();
                        etime = bus.getEndTimee();
                        bsno = bus.getBusNumber();

                        lblstartplace.setText(splace);
                        lblendplace.setText(eplace);
                        lblrootnumber.setText(rno);
                        lblstarttime.setText(stime);
                        lalendtime.setText(etime);
                        id = bus.getBusId();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void clsFields()
    {

        txtSearch.getText().clear();
        lblstartplace.getText().clear();
        lblendplace.getText().clear();
        lblrootnumber.getText().clear();
        lblstarttime.getText().clear();
        lalendtime.getText().clear();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        if(isClick == 1)
        {
            lblstarttime.setText(hour+"h "+minute+"m");
        }
        else if(isClick == 2)
        {
            lalendtime.setText(hour+"h "+minute+"m");
        }
    }
}
