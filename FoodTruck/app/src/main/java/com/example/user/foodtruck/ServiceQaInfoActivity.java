package com.example.user.foodtruck;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.adapter.ServiceQaInfoAdapter;
import com.example.user.networkutil.HttpAsyncTask;
import com.example.user.networkutil.NetworkAvailable;
import com.example.user.vo.MInquiryVO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ServiceQaInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_qa_info);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        NetworkAvailable networkAvailable = new NetworkAvailable();
        if (networkAvailable.isNetworkAvailable(this)) {
            String addr = "androidinqueryinfo";
            /*로그인 기능 구현되면 삭제*/
            String jsonString = "{\"id\":\"user@naver.com\"}";
            HttpAsyncTask httpAsyncTask = new HttpAsyncTask(addr, jsonString);
            try {
                String result = httpAsyncTask.execute().get();

                List<MInquiryVO> list = new ObjectMapper().readValue(result, new TypeReference<List<MInquiryVO>>() {
                });

                ListAdapter listAdapter = new ServiceQaInfoAdapter(this, list);
                ListView listView = findViewById(R.id.qalistView);
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Toast.makeText(getApplicationContext(), "" + i, Toast.LENGTH_SHORT);
                        MInquiryVO item = (MInquiryVO) adapterView.getItemAtPosition(i);
                        Intent intent;
                        if (item != null) {
                            intent = new Intent(ServiceQaInfoActivity.this, ServiceQaInfoDetailActivity.class);
                            intent.putExtra("object", item);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), "게시물이 없습니다.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                Log.d("result:", "" + result);

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
}
