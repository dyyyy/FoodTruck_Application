package com.example.user.foodtruck;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.networkutil.HttpAsyncTask;
import com.example.user.networkutil.RestTempleatAsyncTask;
import com.example.user.vo.MemberVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        Button registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText idText = findViewById(R.id.idText);
        EditText pwText = findViewById(R.id.pwText);
        EditText nameText = findViewById(R.id.nameText);
        EditText telText = findViewById(R.id.telText);


        if (idText.getText().toString().length() <= 3) {
            Toast.makeText(RegisterActivity.this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
            idText.setText("");
            idText.requestFocus();
            return;
        }
        if (pwText.getText().toString().length() <= 3) {
            Toast.makeText(RegisterActivity.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
            idText.setText("");
            pwText.requestFocus();
            return;
        }
        if (nameText.getText().toString().length() <= 3) {
            Toast.makeText(RegisterActivity.this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
            nameText.setText("");
            nameText.requestFocus();
            return;
        }
        if (telText.getText().toString().length() <= 3) {
            Toast.makeText(RegisterActivity.this, "전화번호를 입력하세요", Toast.LENGTH_SHORT).show();
            telText.setText("");
            telText.requestFocus();
            return;
        }

        String uri = "/register";

        MemberVO vo = new MemberVO();
        vo.setMemberId(idText.getText().toString());
        vo.setMemberPw(pwText.getText().toString());
        vo.setMemberName(nameText.getText().toString());
        vo.setMemberTel(telText.getText().toString());

        RestTempleatAsyncTask restTempleatAsyncTask = new RestTempleatAsyncTask(uri, vo);
        restTempleatAsyncTask.execute();

        finish();

        /*회원가입 버튼 눌렀을때 유효성 검사 하고, HttpTask로 서버에 값 보내고 난 후에 결과 보내주기*/
    }
}
