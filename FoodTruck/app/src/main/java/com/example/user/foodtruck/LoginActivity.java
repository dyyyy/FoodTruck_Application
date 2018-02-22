package com.example.user.foodtruck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.adapter.LoginPreference;
import com.example.user.networkutil.RestTempleatAsyncTask;
import com.example.user.vo.MemberVO;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);



        /*로그인*/
        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);

        /*회원가입*/
        Button registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;


        switch (v.getId()) {
            case R.id.loginBtn:
                /*로그인 할때 id와 패스워드를 유효성 확인 후*/
                EditText idText = findViewById(R.id.idInput);
                EditText pwText = findViewById(R.id.pwInput);

                if (idText.getText().toString().length() <= 8) {
                    Toast.makeText(LoginActivity.this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
                    idText.setText("");
                    idText.requestFocus();
                    return;
                } else if (pwText.getText().toString().length() <= 3) {
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    pwText.setText("");
                    pwText.requestFocus();
                    return;
                }

                JSONObject jsonObject = new JSONObject();

                try {
                    String uri = "/login";

                    jsonObject.put("id", idText.getText().toString());
                    jsonObject.put("pw", pwText.getText().toString());

                    RestTempleatAsyncTask restTempleatAsyncTask = new RestTempleatAsyncTask(uri, jsonObject);
                    String result = restTempleatAsyncTask.execute().get();

                    Log.d("result",""+result);


                    Map<String, Object> map = new ObjectMapper().readValue(result, Map.class);
                    Log.d("userinfo",""+map.get("userinfo"));


                    switch (map.get("userinfo").toString()) {
                        case "0":
                            Toast.makeText(getApplicationContext(), "로그인 실패했습니다. 아이디나 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                            idText.setText("");
                            pwText.setText("");
                            idText.requestFocus();
                            break;

                        case "1":
                                /*저장소에 사용자 정보를 저장*/
                            LoginPreference pref = new LoginPreference(this);

                            Log.d("data",""+map.get("data"));
                            pref.putUser(LoginPreference.USER_INFO, map);
                            Toast.makeText(getApplicationContext(), pref.getValue(LoginPreference.MEMBER_NAME, "?") + "님 로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "잘못된 요청입니다.", Toast.LENGTH_SHORT).show();
                            finish();
                            break;
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.registerBtn:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(getApplicationContext(), "?", Toast.LENGTH_SHORT).show();
                break;
        }

    }


}
