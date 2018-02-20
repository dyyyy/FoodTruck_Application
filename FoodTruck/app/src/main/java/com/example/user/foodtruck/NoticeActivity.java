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

import com.example.user.adapter.NoticeAdapter;
import com.example.user.networkutil.HttpAsyncTask;
import com.example.user.networkutil.NetworkAvailable;
import com.example.user.vo.NoticeVO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NoticeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        NetworkAvailable networkAvailable = new NetworkAvailable();
        List<NoticeVO> list;
        if (networkAvailable.isNetworkAvailable(this)) {
            String addr = "notice";
            try {

                HttpAsyncTask httpAsyncTask = new HttpAsyncTask(addr);
                String result = httpAsyncTask.execute().get();

                list = new ObjectMapper().readValue(result, new TypeReference<List<NoticeVO>>() {});

                ListAdapter listAdapter = new NoticeAdapter(this, list);
                ListView listView = findViewById(R.id.noticeListview);
                listView.setAdapter(listAdapter);
                /*공지사항 리스너*/
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        NoticeVO item = (NoticeVO) adapterView.getItemAtPosition(i);
                        Intent intent;
                        if (item != null) {
                            intent = new Intent(NoticeActivity.this, NoticeDetailActivity.class);
                            intent.putExtra("object", item);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), "게시물이 없습니다.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
