package com.example.user.foodtruck;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.user.adapter.LoginPreference;
import com.example.user.networkutil.NetworkAvailable;

public class UserDetailActivity extends AppCompatActivity {

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

//사용자 정보를 꺼내서 보여주고
                //수정을 하면 수정한 값을 서버에 보내준다.


            } else {
                Toast.makeText(this, "network is not available", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "로그인 되어있지 않습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
