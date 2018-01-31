package com.example.user.foodtruck;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ServiceCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_center);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        NetworkAvailable networkAvailable = new NetworkAvailable();

        if (networkAvailable.isNetworkAvailable(this)) {

        }
        else {
            Toast.makeText(this, "network is not available", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        finish();
    }


}
