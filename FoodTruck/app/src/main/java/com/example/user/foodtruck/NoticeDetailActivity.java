package com.example.user.foodtruck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class NoticeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);

        TextView titleView = findViewById(R.id.titleView);
        TextView regView = findViewById(R.id.regView);
        TextView contentView = findViewById(R.id.contentView);

        String title = getIntent().getStringExtra("title");
        String reg = getIntent().getStringExtra("reg");
        String content = getIntent().getStringExtra("content");

        titleView.setText(title);
        regView.setText(reg);
        contentView.setText(content);
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
