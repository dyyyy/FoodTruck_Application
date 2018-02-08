package com.example.user.foodtruck;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.vo.MInquiryVO;

public class ServiceQaInfoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        TextView titleView = findViewById(R.id.titleView);
        TextView regView = findViewById(R.id.regView);
        TextView contentView = findViewById(R.id.contentView);

        MInquiryVO vo = (MInquiryVO) getIntent().getSerializableExtra("object");

        titleView.setText(vo.getQaScTitle());
        regView.setText(vo.getQaScReg());
        contentView.setText(vo.getQaScContent());
    }

    @Override
public void onBackPressed() {
    finish();
}


}
