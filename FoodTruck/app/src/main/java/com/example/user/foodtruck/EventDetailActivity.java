package com.example.user.foodtruck;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.networkutil.HttpAsyncTask;
import com.example.user.networkutil.RestTempleatAsyncTask;
import com.example.user.vo.EventVO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class EventDetailActivity extends AppCompatActivity {

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

        EventVO vo = (EventVO) getIntent().getSerializableExtra("object");

        titleView.setText(vo.getEventTitle());
        regView.setText(vo.getEventReg());
        contentView.setText(vo.getEventContent());

        String uri = "/eventcount";

        RestTempleatAsyncTask restTempleatAsyncTask = new RestTempleatAsyncTask(uri, vo);
        restTempleatAsyncTask.execute();

    }

    @Override
public void onBackPressed() {
    finish();
}


}
