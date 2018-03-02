package com.example.user.foodtruck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.adapter.LoginPreference;
import com.example.user.adapter.ServiceQaInfoAdapter;
import com.example.user.networkutil.NetworkAvailable;
import com.example.user.networkutil.RestTempleatAsyncTask;
import com.example.user.vo.MInquiryVO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ServiceQaInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_qa_info);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        LoginPreference pref = new LoginPreference(this);
        /*로그인정보가 있는지 없는지 확인 후 로그인버튼을 보여줄지 사용자 정보를 보여줄지 결정*/
        boolean user = pref.getValue(LoginPreference.USER_INFO, false);

        //저장된 사용자 정보가 있을때
        if (user) {
            NetworkAvailable networkAvailable = new NetworkAvailable(this);
            if (networkAvailable.isNetworkAvailable()) {
                String uri = "/user/inqueryinfo/{id:.+}/{auth}";

                try {
                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("id", pref.getValue(LoginPreference.MEMBER_ID, null));
                    jsonObject.put("auth", pref.getValue(LoginPreference.MEMBER_AUTH));

                    RestTempleatAsyncTask restTempleatAsyncTask = new RestTempleatAsyncTask(uri, jsonObject.toString());

                    String result = restTempleatAsyncTask.execute().get();

                    if (result != null) {
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
                    }


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
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else {
                Toast.makeText(this, "network is not available", Toast.LENGTH_SHORT).show();
            }
        } else { //저장된 사용자정보가 없을때
            Toast.makeText(this, "로그인 되어있지 않습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
