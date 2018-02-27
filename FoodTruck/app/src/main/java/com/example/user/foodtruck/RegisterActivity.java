package com.example.user.foodtruck;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.user.networkutil.RestTempleatAsyncTask;
import com.example.user.vo.MemberVO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    RadioGroup radioGroup;
    MemberVO vo;
    EditText licenseText, idText, pwText, pwcheckText, nameText, telText;
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
        pwcheckText = findViewById(R.id.pwCheckText);
        nameText = findViewById(R.id.nameText);
        telText = findViewById(R.id.telText);

        Button registerBtn = findViewById(R.id.registerBtn);
        idCheckBtn = findViewById(R.id.idcheck);

        radioGroup = findViewById(R.id.registerRg);
        radioGroup.setOnCheckedChangeListener(this);
        registerBtn.setOnClickListener(this);
        idCheckBtn.setOnClickListener(this);

    }

    boolean idCheck;

    @Override
    public void onClick(View v) {
        String uri;

        switch (v.getId()) {
            case R.id.idcheck:
                uri = "/idcheck/{id}";
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("id", idText.getText().toString());

                    String result;
                    restTempleatAsyncTask = new RestTempleatAsyncTask(uri, jsonObject.toString());
                    try {
                        result = restTempleatAsyncTask.execute().get();

                        switch (result) {
                            case "id":
                                Toast.makeText(this, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                                break;
                            case "checked":
                                //Toast.makeText(this, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                        builder.setMessage("사용가능한 아이디입니다 사용하시겠습니까?.").setPositiveButton("예", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //idText.setFocusable(false);
                                                idText.setEnabled(false);
                                                //idText.invalidate();
                                                idCheck = true;
                                    }
                                }).setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        idText.requestFocus();
                                        //idText.setFocusable(true);
                                        idText.setEnabled(true);
                                        idCheck = false;
                                    }
                                });
                                builder.create().show();
                                break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;
            case R.id.registerBtn:


                if (radioGroup.getCheckedRadioButtonId() <= 0) {
                    Toast.makeText(RegisterActivity.this, "유형을 선택하세요", Toast.LENGTH_SHORT).show();
                    radioGroup.requestFocus();
                    return;
                }

                if (!Pattern.matches(".{15,120}$", idText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "이메일을 입력하세요(15~120자리)", Toast.LENGTH_SHORT).show();
                    idText.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(idText.getText().toString()).matches()) {
                    Toast.makeText(RegisterActivity.this, "이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show();
                    idText.requestFocus();
                    return;
                }

                if (!Pattern.matches(".{1,5}$", nameText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "이름을 입력하세요 (한글1~5, 영어1~20)", Toast.LENGTH_SHORT).show();
                    nameText.requestFocus();
                    return;
                }

                if (!Pattern.matches(".{10,14}$", telText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "전화번호를 입력하세요(10 ~ 14)", Toast.LENGTH_SHORT).show();
                    telText.requestFocus();
                    return;
                }


                if (!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", telText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "올바른 핸드폰 번호가 아닙니다 (ex. 010-9999-9999)", Toast.LENGTH_SHORT).show();
                    telText.requestFocus();
                    return;
                }

                if (!Pattern.matches(".{8,20}$", pwText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "비밀번호를 입력하세요 (8 ~ 20자리)", Toast.LENGTH_SHORT).show();
                    pwText.requestFocus();
                    return;
                }
                if (!Pattern.matches(".{8,20}$", pwcheckText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "비밀번호 확인을 입력하세요 (8 ~ 20자리)", Toast.LENGTH_SHORT).show();
                    pwcheckText.requestFocus();
                    return;
                }
                if (!pwText.getText().toString().equals(pwcheckText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                    pwcheckText.setText("");
                    pwcheckText.requestFocus();
                    return;
                }


                if (licenseText.getVisibility() == View.VISIBLE) {
                    if (!Pattern.matches("\\d{10,10}$", licenseText.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "라이센스번호를 입력하세요 (숫자 10 자리)", Toast.LENGTH_SHORT).show();
                        licenseText.requestFocus();
                        return;
                    } else {
                        vo.setLicenseNo(licenseText.getText().toString());
                    }
                }
                if (!idCheck) {
                    Toast.makeText(this, "아이디 중복체크를 해주세요.", Toast.LENGTH_SHORT).show();
                    idCheckBtn.setFocusable(true);
                    break;
                } else {

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
