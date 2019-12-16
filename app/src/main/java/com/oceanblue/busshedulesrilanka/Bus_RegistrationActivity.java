package com.oceanblue.busshedulesrilanka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Bus_RegistrationActivity extends AppCompatActivity implements TimePickerFragment.TimePickerListner{


    private Button btnStartTime,btnEndtTime,btnRegistration;
    private TextView txtstarttime,txtendtime;
    private Spinner cmbStartPlaces,cmbEndPlace;
    private EditText txtRootNumber,txtBusNumbe;

    private static int isClick =1;

    private SweetAlertDialog pDialog;
    DatabaseReference databaseBus;

    private String startPlace,endPlace,starttimee,endtimee,rootNumber,busNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus__registration);

        txtstarttime = findViewById(R.id.lblStartTime);
        txtendtime = findViewById(R.id.lblEndTime);
        btnStartTime=findViewById(R.id.btn_strattime);
        btnEndtTime = findViewById(R.id.btn_endtime);
        btnRegistration = findViewById(R.id.btn_BusRegi);
        cmbStartPlaces = findViewById(R.id.cmb_startplace);
        cmbEndPlace = findViewById(R.id.cmb_endtplace);
        txtRootNumber = findViewById(R.id.txtbusnosearch);
        txtBusNumbe = findViewById(R.id.txtBusNumber);




        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isClick = 1;
                DialogFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setCancelable(false);
                timePickerFragment.show(getSupportFragmentManager(),"timePicker");
            }
        });

        btnEndtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick = 2;
                DialogFragment timePickerFragmentend = new TimePickerFragment();
                timePickerFragmentend.setCancelable(false);
                timePickerFragmentend.show(getSupportFragmentManager(),"timePicker");
            }
        });

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               saveBusDetails();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

        if(isClick == 1)
        {
            txtstarttime.setText(hour+"h "+minute+"m");
        }
       else if(isClick == 2)
        {
            txtendtime.setText(hour+"h "+minute+"m");
        }
    }

    private void saveBusDetails() {

        startPlace = cmbStartPlaces.getSelectedItem().toString();
        endPlace = cmbEndPlace.getSelectedItem().toString();
        starttimee = txtstarttime.getText().toString();
        endtimee = txtendtime.getText().toString();
        rootNumber = txtRootNumber.getText().toString();
        busNumber = txtBusNumbe.getText().toString();


        databaseBus = FirebaseDatabase.getInstance().getReference("bus_information");

        if(ValidateFields() != false)
        {
            String Id = databaseBus.push().getKey();
            Bus bus  = new Bus(Id,startPlace,endPlace,starttimee,endtimee,rootNumber,busNumber);
            databaseBus.child(Id).setValue(bus);

            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Registration is Successfully")
                    .show();
            clearAllFields();
            //Toast.makeText(this,"ready to send data",Toast.LENGTH_SHORT).show();
        }

    }
    private void clearAllFields() {
        txtBusNumbe.getText().clear();
        txtRootNumber.getText().clear();
        txtstarttime.setText("Time");
        txtendtime.setText("Time");
        txtRootNumber.setFocusable(true);

    }

    private boolean ValidateFields()
    {
        boolean isValidate = true;

        if(startPlace.equals(""))
        {
            isValidate = false;
            Toast.makeText(this,"Required Bus Start Place.",Toast.LENGTH_SHORT).show();
        }
        else if(endPlace.equals(""))
        {
            isValidate = false;
            Toast.makeText(this,"Required Bus End Place.",Toast.LENGTH_SHORT).show();
        }
        else if(startPlace.equals(endPlace))
        {
            isValidate = false;
            Toast.makeText(this,"Bus Start and Destination are the Same",Toast.LENGTH_SHORT).show();
        }
        else if(starttimee.equals("Time"))
        {
            isValidate = false;
            Toast.makeText(this,"Required Start Time.",Toast.LENGTH_SHORT).show();
        }
        else if(endtimee.equals("Time"))
        {
            isValidate = false;
            Toast.makeText(this,"Required End Time.",Toast.LENGTH_SHORT).show();
        }
        else if(rootNumber.equals(""))
        {
            isValidate = false;
            Toast.makeText(this,"Required Root Number.",Toast.LENGTH_SHORT).show();
        }
        else if(busNumber.equals(""))
        {
            isValidate = false;
            Toast.makeText(this,"Required Bus Number.",Toast.LENGTH_SHORT).show();
        }
        return isValidate;
    }

}
