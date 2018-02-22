package com.example.user.foodtruck;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    RadioGroup radioGroup;
    MemberVO vo;
    EditText licenseText, idText, pwText, nameText, telText;
    Button idCheckBtn;
    RestTempleatAsyncTask restTempleatAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        vo = new MemberVO();

        licenseText = findViewById(R.id.licenseText);
        licenseText.setVisibility(View.GONE);

        idText = findViewById(R.id.idText);
        pwText = findViewById(R.id.pwText);
        nameText = findViewById(R.id.nameText);
        telText = findViewById(R.id.telText);

        Button registerBtn = findViewById(R.id.registerBtn);
        idCheckBtn = findViewById(R.id.idcheck);

        radioGroup = findViewById(R.id.registerRg);
        radioGroup.setOnCheckedChangeListener(this);
        registerBtn.setOnClickListener(this);
        idCheckBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String uri;
        switch (v.getId()) {
            case R.id.idcheck:
                uri = "/idcheck/{id}";
                String id = idCheckBtn.getText().toString();
                String result;
                restTempleatAsyncTask = new RestTempleatAsyncTask(uri, id);
                try {
                    result = restTempleatAsyncTask.execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.registerBtn:


                if (radioGroup.getCheckedRadioButtonId() <= 0) {
                    Toast.makeText(RegisterActivity.this, "유형을 선택하세요", Toast.LENGTH_SHORT).show();
                    radioGroup.requestFocus();
                    return;
                }
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

                if (licenseText.getVisibility() == View.VISIBLE) {
                    Log.d("라이센스", "텍스트 보여짐");
                    int license = licenseText.getText().toString().length();
                    if (license < 9 && license >= 10) {
                        Toast.makeText(RegisterActivity.this, "라이센스번호를 입력하세요(11자리)", Toast.LENGTH_SHORT).show();
                        licenseText.requestFocus();
                        return;
                    } else {
                        vo.setLicenseNo(licenseText.getText().toString());
                    }
                }

                uri = "/register";

                vo.setMemberId(idText.getText().toString());
                vo.setMemberPw(pwText.getText().toString());
                vo.setMemberName(nameText.getText().toString());
                vo.setMemberTel(telText.getText().toString());

                Log.d("vo : ", "id: " + vo.getMemberId() + " name: " + vo.getMemberName() + " password : " + vo.getMemberPw() + " tel: " + vo.getMemberTel() + " license: " + vo.getLicenseNo());
                restTempleatAsyncTask = new RestTempleatAsyncTask(uri, vo);
                restTempleatAsyncTask.execute();
                finish();
                break;
        }


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        RadioButton customer = findViewById(R.id.customer);
        RadioButton seller = findViewById(R.id.seller);

        switch (checkedId) {
            case R.id.customer:
                vo.setMemberAuth("3");
                licenseText.setVisibility(View.GONE);
                idText.requestFocus();
                break;
            case R.id.seller:
                vo.setMemberAuth("2");
                licenseText.setVisibility(View.VISIBLE);
                licenseText.requestFocus();
                break;
            default:
                Toast.makeText(this, "유형이 선택되지 안았습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
