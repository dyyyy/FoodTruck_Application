package com.example.user.foodtruck;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.adapter.LoginPreference;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        LoginPreference pref = new LoginPreference(this);
        /*로그인정보가 있는지 없는지 확인 후 로그인버튼을 보여줄지 사용자 정보를 보여줄지 결정*/
        boolean user = pref.getValue(LoginPreference.USER_INFO, false);

    }
}
