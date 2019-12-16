package com.oceanblue.busshedulesrilanka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserHomeActivity extends AppCompatActivity {

    private Button btnSRoot,btnsDistination,btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        btnSRoot = findViewById(R.id.btnsearchRoot);
        btnsDistination = findViewById(R.id.btnsearchDes);
        btnlogout = findViewById(R.id.btn_logout);


        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeActivity.this,MainLoginActivity.class);
                startActivity(intent);
            }
        });

        btnSRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeActivity.this,BusSearchForRootNumberActivity.class);
                startActivity(intent);
            }
        });
        btnsDistination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeActivity.this,BusSearchForStartEndPlaceActivity.class);
                startActivity(intent);
            }
        });

    }
}
