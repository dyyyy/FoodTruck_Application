package com.example.user.foodtruck;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.adapter.LoginPreference;
import com.example.user.networkutil.HttpAsyncTask;
import com.example.user.networkutil.NetworkAvailable;
import com.example.user.networkutil.RestTempleatAsyncTask;
import com.example.user.vo.MInquiryVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;


public class ServiceQaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private TextView titletextView, contenttextView, teltextView;
    private Button resetButton, submitButton;

    private Spinner spinner, spinner2;
    private ArrayAdapter<CharSequence> spinnerAdapter;

    private LoginPreference pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_qa);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        pref = new LoginPreference(this);

        /*로그인정보가 있는지 없는지 확인 후 로그인버튼을 보여줄지 사용자 정보를 보여줄지 결정*/
        boolean user = pref.getValue(LoginPreference.USER_INFO, false);

        //저장된 사용자 정보가 있을때
        if (user) {
            NetworkAvailable networkAvailable = new NetworkAvailable(this);
            if (networkAvailable.isNetworkAvailable()) {
        /*spinner1*/
                spinner = findViewById(R.id.qaspinner1);
                spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.scspinner1_arr, android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerAdapter);
                spinner.setOnItemSelectedListener(this);

                spinner2 = findViewById(R.id.qaspinner2);

                titletextView = findViewById(R.id.qa_title_text);
                contenttextView = findViewById(R.id.qa_content_text);
                teltextView = findViewById(R.id.qa_tel_text);

                /*resetButton*/
                resetButton = findViewById(R.id.qa_reset_btn);
                resetButton.setOnClickListener(this);

                /*submitButton*/
                submitButton = findViewById(R.id.qa_submit_btn);
                submitButton.setOnClickListener(this);
            } else {
                Toast.makeText(this, "network is not available", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(this, "로그인 되어있지 않습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                spinnerAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.scspinner2_arr1, android.R.layout.simple_spinner_dropdown_item);
                break;
            case 1:
                spinnerAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.scspinner2_arr2, android.R.layout.simple_spinner_dropdown_item);
                break;
            case 2:
                spinnerAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.scspinner2_arr3, android.R.layout.simple_spinner_dropdown_item);
                break;
            case 3:
                spinnerAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.scspinner2_arr4, android.R.layout.simple_spinner_dropdown_item);
                break;
            case 4:
                spinnerAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.scspinner2_arr5, android.R.layout.simple_spinner_dropdown_item);
                break;
        }
        spinner2.setAdapter(spinnerAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qa_reset_btn:
                titletextView.setText("");
                contenttextView.setText("");
                teltextView.setText("");
                break;
            case R.id.qa_submit_btn:
                if (!Pattern.matches(".{3,90}$", titletextView.getText().toString())) {
                    Toast.makeText(this, "제목을 입력하세요(1~ 30자)", Toast.LENGTH_SHORT).show();
                    titletextView.requestFocus();
                    return;
                }
                if (!Pattern.matches("[ㄱ-ㅎ가-힣a-zA-Z.~!@^&()]*$", titletextView.getText().toString())) {
                    Toast.makeText(this, "특수문자 입력이 불가능합니다.", Toast.LENGTH_SHORT).show();
                    titletextView.requestFocus();
                    return;
                }
                if (!Pattern.matches(".{3,3000}$", contenttextView.getText().toString())) {
                    Toast.makeText(this, "내용을 입력하세요(1 ~ 1000자)", Toast.LENGTH_SHORT).show();
                    contenttextView.requestFocus();
                    return;
                }
                if (!Pattern.matches("[ㄱ-ㅎ가-힣a-zA-Z.~!@^&()]*$", contenttextView.getText().toString())) {
                    Toast.makeText(this, "특수문자 입력 불가능합니다.", Toast.LENGTH_SHORT).show();
                    contenttextView.requestFocus();
                    return;
                }

                if (!Pattern.matches(".{10,14}$", teltextView.getText().toString())) {
                    Toast.makeText(this, "전화번호를 입력하세요(ex. 010-9999-9999), (10 ~ 14)", Toast.LENGTH_SHORT).show();
                    teltextView.requestFocus();
                    return;
                }
                if (!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", teltextView.getText().toString())) {
                    Toast.makeText(this, "올바른 핸드폰 번호가 아닙니다 (ex. 010-9999-9999)", Toast.LENGTH_SHORT).show();
                    teltextView.requestFocus();
                    return;
                }

                MInquiryVO vo = new MInquiryVO();
                vo.setMemId(pref.getValue(LoginPreference.MEMBER_ID, null));

                /*
                if(LoginPreference.){ //구매자가 문의할때
                    vo.setOrdNo("");

                }else if(){ //판매자가 문의할때
                    vo.setLicenseNo("");
                }
                */
                vo.setQaScTitle(titletextView.getText().toString());
                vo.setQaScContent(contenttextView.getText().toString());
                vo.setQaScCategory1(spinner.getSelectedItem().toString());
                vo.setQaScCategory2(spinner2.getSelectedItem().toString());
                vo.setQaScTel(teltextView.getText().toString());


                Log.d("memberinquiry:", " id : " + vo.getMemId() + " title : " + vo.getQaScTitle() + " content : " + vo.getQaScContent() + " category1 " + vo.getQaScCategory1() + " category2 : " + vo.getQaScCategory2() + " tel : " + vo.getQaScTel() + " stat : " + vo.getQaScStat());
                String uri = "/user/memberinquiry";
                RestTempleatAsyncTask restTempleatAsyncTask = new RestTempleatAsyncTask(uri, vo);

                try {
                    restTempleatAsyncTask.execute();
                    finish();
                    Toast.makeText(this, "문의하기가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "문의 실패!", Toast.LENGTH_SHORT).show();
                    break;
                }
            default:
                Toast.makeText(this, "없는 버튼입니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
