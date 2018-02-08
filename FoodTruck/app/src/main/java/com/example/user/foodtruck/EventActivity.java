package com.example.user.foodtruck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.adapter.EventAdapter;
import com.example.user.networkutil.HttpAsyncTask;
import com.example.user.networkutil.NetworkAvailable;
import com.example.user.vo.EventVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        NetworkAvailable networkAvailable = new NetworkAvailable();

        if (networkAvailable.isNetworkAvailable(this)) {
            String addr = "event";
            try {

                HttpAsyncTask httpAsyncTask = new HttpAsyncTask(addr);
                String result = httpAsyncTask.execute().get();
                System.out.println("result: " + result);

                ObjectMapper objectMapper = new ObjectMapper();

                try {
                    List<EventVO> list = objectMapper.readValue(result, new TypeReference<List<EventVO>>() {
                    });
                    ListAdapter listAdapter = new EventAdapter(this, list);
                    ListView listView = findViewById(R.id.eventListview);
                    listView.setAdapter(listAdapter);
                    /*이벤트 리스너*/
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            EventVO item = (EventVO) adapterView.getItemAtPosition(i);
                            Intent intent;
                            if (item != null) {


                                intent = new Intent(EventActivity.this, EventDetailActivity.class);
                                intent.putExtra("object", item);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getApplicationContext(), "게시물이 없습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(this, "network is not available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
