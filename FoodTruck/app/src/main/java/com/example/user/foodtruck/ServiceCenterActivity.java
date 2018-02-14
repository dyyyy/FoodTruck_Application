package com.example.user.foodtruck;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.networkutil.NetworkAvailable;

import java.util.ArrayList;

public class ServiceCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_center);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        NetworkAvailable networkAvailable = new NetworkAvailable(this);
        ArrayList<String> arraylist = new ArrayList<String>();
        arraylist.add("문의하기");
        arraylist.add("문의내역");
        arraylist.add("콜센터");
        ArrayAdapter<String> scAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arraylist);

        ListView listView = findViewById(R.id.sc_listview);
        listView.setAdapter(scAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(ServiceCenterActivity.this, ServiceQaActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(ServiceCenterActivity.this, ServiceQaInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+82)15770000"));
                        startActivity(intent);
                        break;
                    default:
                        Toast.makeText(ServiceCenterActivity.this, "fail", Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        });


        if (networkAvailable.isNetworkAvailable()) {

        } else {
            Toast.makeText(this, "network is not available", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
