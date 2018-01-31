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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
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

        if (networkAvailable.isNetworkAvailable(this)) {
            String addr = "notice";
            try {

                HttpReceiveAsyncTask httpReceiveAsyncTask = new HttpReceiveAsyncTask();
                String result = httpReceiveAsyncTask.execute("" + addr + "").get();
                System.out.println("result: " + result);
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = new JsonObject();
                jsonObject = (JsonObject) parser.parse(result);
                JsonArray jsonArray = new JsonArray();
                Gson gson = new Gson();


                jsonArray = jsonObject.get("data").getAsJsonArray();
                ArrayList<BoardVO> bvolist = gson.fromJson(jsonArray.toString(), new TypeToken<ArrayList<BoardVO>>() {
                }.getType());

                ListAdapter listAdapter = new BoardAdapter(this, bvolist);
                ListView listView = findViewById(R.id.noticeListview);
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /*공지사항을 볼수있는 페이지로 넘겨주기*/
                        BoardVO item = (BoardVO) adapterView.getItemAtPosition(i);
                        Intent intent;
                            if (item != null) {
                                intent = new Intent(NoticeActivity.this, BoardDetailActivity.class);
                                intent.putExtra("title", item.getBoardTitle());
                                intent.putExtra("reg", item.getBoardReg());
                                intent.putExtra("content", item.getBoardContent());

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
