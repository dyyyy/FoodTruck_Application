package com.example.user.foodtruck;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.adapter.NoticeAdapter;
import com.example.user.networkutil.HttpAsyncTask;
import com.example.user.networkutil.NetworkAvailable;
import com.example.user.networkutil.RestTempleatAsyncTask;
import com.example.user.vo.NoticeVO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class NoticeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    ListView listView;
    private int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        NetworkAvailable networkAvailable = new NetworkAvailable(this);
        List<NoticeVO> list;
        if (networkAvailable.isNetworkAvailable()) {
            String uri = "/notice/{pageNo}";
            try {

                Map<String, Integer> params = new HashMap<String, Integer>();
                params.put("pageNo",page);
                RestTempleatAsyncTask restTempleatAsyncTask = new RestTempleatAsyncTask(uri, params);
                String result = restTempleatAsyncTask.execute().get();

                list = new ObjectMapper().readValue(result, new TypeReference<List<NoticeVO>>() {
                });

                ListAdapter listAdapter = new NoticeAdapter(this, list);
                listView = findViewById(R.id.noticeListview);
                listView.setAdapter(listAdapter);
                /*공지사항 리스너*/
                listView.setOnItemClickListener(this);
                listView.setOnScrollListener(this);
                ProgressBar progressBar = findViewById(R.id.progressBar);
                progressBar.setVisibility(View.GONE);


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
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        NoticeVO vo = (NoticeVO) parent.getItemAtPosition(position);

        Intent intent;
        if (vo != null) {
            intent = new Intent(NoticeActivity.this, NoticeDetailActivity.class);
            intent.putExtra("object", vo);
            startActivity(intent);

        } else {
            Toast.makeText(getApplicationContext(), "게시물이 없습니다.", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if( listView.canScrollVertically(-1)){
            Toast.makeText(getApplicationContext(), "scroll!! end", Toast.LENGTH_SHORT).show();
        }
        if(listView.canScrollVertically(1)){
            Log.d("a", "scroll!! first");

        }

        Log.d("a", "onScrollStateChanged: "+scrollState+"virtically-1"+listView.canScrollVertically(-1)+"virtically1"+listView.canScrollVertically(1));
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        page++;
        Log.d("a","onScroll"+page+" firstvisibleitem:"+ firstVisibleItem+" visibleitemcount:"+visibleItemCount+" totalitemcount:"+totalItemCount);
    }
}
