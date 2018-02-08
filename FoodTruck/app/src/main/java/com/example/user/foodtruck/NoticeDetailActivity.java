package com.example.user.foodtruck;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.networkutil.HttpAsyncTask;
import com.example.user.vo.NoticeVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class NoticeDetailActivity extends AppCompatActivity {

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

        NoticeVO vo = (NoticeVO) getIntent().getSerializableExtra("object");

        titleView.setText(vo.getNoticeTitle());
        regView.setText(vo.getNoticeReg());
        contentView.setText(vo.getNoticeContent());

        String addr = "noticecount";
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("no", vo.getNoticeNo());
            HttpAsyncTask httpAsyncTask = new HttpAsyncTask(addr, jsonObject.toString());
            String result = httpAsyncTask.execute().get();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
