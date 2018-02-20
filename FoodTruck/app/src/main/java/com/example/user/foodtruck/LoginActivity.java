package com.example.user.foodtruck;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.networkutil.HttpAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
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
        TextView regTextView = findViewById(R.id.registerBtn);
        regTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.loginBtn:
                /*로그인 할때 id와 패스워드를 유효성 확인 후*/
                EditText idText = findViewById(R.id.loginidText);
                EditText pwText = findViewById(R.id.loginpwText);

                if (idText.getText().toString().length() <= 3) {
                    Toast.makeText(LoginActivity.this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
                    idText.setText("");
                    idText.requestFocus();
                    return;
                }
                if (pwText.getText().toString().length() <= 3) {
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    pwText.setText("");
                    pwText.requestFocus();
                    return;
                }
                JSONObject jsonObject = new JSONObject();

                try {
                    String addr = "androidlogin";

                    jsonObject.put("id", idText.getText().toString());
                    jsonObject.put("pw", pwText.getText().toString());
                    HttpAsyncTask httpAsyncTask = new HttpAsyncTask(addr, jsonObject.toString());
                    String result = httpAsyncTask.execute().get();

                    switch (result){
                        case "not id":
                            Toast.makeText(getApplicationContext(), "아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                            idText.setText("");
                            pwText.setText("");
                            idText.requestFocus();
                            break;
                        case "not pw":
                            Toast.makeText(getApplicationContext(), "비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                            pwText.setText("");
                            pwText.requestFocus();
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
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
