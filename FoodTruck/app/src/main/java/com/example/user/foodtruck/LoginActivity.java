package com.example.user.foodtruck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.networkutil.RestTempleatAsyncTask;
import com.example.user.vo.MemberVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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


        /*put*/
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("key", "value");
            editor.putString("user", jsonObject.toString());
            editor.putBoolean("remember", true);
            editor.commit();

            String user = sharedPreferences.getString("user", "0");
            System.out.println("user : " + user);
            boolean remember = sharedPreferences.getBoolean("remember", false);
            System.out.println("remember : " + remember);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        /*get*/
        String result = sharedPreferences.getString("id", "0");

        /*delete*/
        editor.remove("id");

        /*all delete*/
        editor.clear();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.loginBtn:
                /*로그인 할때 id와 패스워드를 유효성 확인 후*/
                EditText idText = findViewById(R.id.idInput);
                EditText pwText = findViewById(R.id.pwInput);
                CheckBox rememberChk = findViewById(R.id.rememberChk);

                if (idText.getText().toString().length() <= 3) {
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


                    switch (result) {
                        case "id failed":
                            Toast.makeText(getApplicationContext(), "없는 사용자입니다.", Toast.LENGTH_SHORT).show();
                            idText.setText("");
                            pwText.setText("");
                            idText.requestFocus();
                            break;
                        case "pw failed":
                            Toast.makeText(getApplicationContext(), "비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                            pwText.setText("");
                            pwText.requestFocus();
                            break;
                        default:
                            String user;

                                /*저장소에 사용자 정보를 저장*/
                            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                /*json 정보를 저장*/
                            editor.putString("user", result);
                            editor.commit();
                            if (rememberChk.isChecked()) {
                                editor.putBoolean("autoLogin", true);

                                user = sharedPreferences.getString("user", getResources().toString());
                                try {
                                    MemberVO mvo = new ObjectMapper().readValue(user, MemberVO.class);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                editor.putBoolean("remember", false);
                                /*저장소의 정보를 모두 삭제*/
                            }
                            // Toast.makeText(getApplicationContext(), id + "님 로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();


                            finish();
                            break;
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
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
