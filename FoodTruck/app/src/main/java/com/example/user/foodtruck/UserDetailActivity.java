package com.example.user.foodtruck;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.adapter.LoginPreference;
import com.example.user.networkutil.NetworkAvailable;

public class UserDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        LoginPreference pref = new LoginPreference(this);
        boolean user = pref.getValue(LoginPreference.USER_INFO, false);

        if (user) {
            NetworkAvailable networkAvailable = new NetworkAvailable(this);
            if (networkAvailable.isNetworkAvailable()) {


                Button logoutBtn = findViewById(R.id.logoutBtn);
                logoutBtn.setOnClickListener(this);

            } else {
                Toast.makeText(this, "network is not available", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "로그인 되어있지 않습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logoutBtn:
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("로그아웃 하시겠습니까?").setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoginPreference loginPreference = new LoginPreference(getApplicationContext());
                        loginPreference.deleteAllValue();
                        finish();

                        return;
                    }
                }).setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                builder.create().show();
                break;
            default:
                Toast.makeText(this, "입력된 값이 없습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
