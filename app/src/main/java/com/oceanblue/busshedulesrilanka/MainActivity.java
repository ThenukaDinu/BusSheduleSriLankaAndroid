package com.oceanblue.busshedulesrilanka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnbusregii,btnbusmodii;
     private Button btnbusregi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnbusregii = findViewById(R.id.btnBusRegi);
        btnbusmodii = findViewById(R.id.btn_ManageBusShedule);



        btnbusregii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Bus_RegistrationActivity.class);
                startActivity(intent);
            }
        });

        btnbusmodii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,BusDetailsManagesActivity.class);
                startActivity(intent1);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exapel_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId())
        {
            case R.id.logout:
                Intent intent3 = new Intent(MainActivity.this,MainLoginActivity.class);//log out
                startActivity(intent3);
                return true;
            case R.id.User_Home:
                Intent intent2 = new Intent(MainActivity.this,UserHomeActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
