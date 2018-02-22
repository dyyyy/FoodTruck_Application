package com.example.user.foodtruck;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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


public class ServiceQaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner, spinner2;
    ArrayAdapter<CharSequence> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_qa);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        final LoginPreference pref = new LoginPreference(this);
        /*로그인정보가 있는지 없는지 확인 후 로그인버튼을 보여줄지 사용자 정보를 보여줄지 결정*/
        boolean user = pref.getValue(LoginPreference.USER_INFO, false);

        //저장된 사용자 정보가 있을때
        if (user) {
        /*spinner1*/
            spinner = findViewById(R.id.qaspinner1);
            spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.scspinner1_arr, android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);
            spinner.setOnItemSelectedListener(this);

            spinner2 = findViewById(R.id.qaspinner2);


            final TextView titletextView = findViewById(R.id.qa_title_text);
            final TextView contenttextView = findViewById(R.id.qa_content_text);
            final TextView teltextView = findViewById(R.id.qa_tel_text);

        /*resetButton*/
            Button resetButton = findViewById(R.id.qa_reset_btn);
            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    titletextView.setText("");
                    contenttextView.setText("");
                    teltextView.setText("");
                }
            });

        /*submitButton*/
            NetworkAvailable networkAvailable = new NetworkAvailable(this);
            Button submitButton = findViewById(R.id.qa_submit_btn);

            if (networkAvailable.isNetworkAvailable()) {
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (titletextView.getText().toString().length() == 0) {
                            Toast.makeText(ServiceQaActivity.this, "제목을 입력하세요", Toast.LENGTH_SHORT).show();
                            titletextView.requestFocus();
                            return;
                        }
                        if (contenttextView.getText().toString().length() == 0) {
                            Toast.makeText(ServiceQaActivity.this, "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                            contenttextView.requestFocus();
                            return;
                        }
                        if (teltextView.getText().toString().length() == 0) {
                            Toast.makeText(ServiceQaActivity.this, "전화번호를 입력하세요", Toast.LENGTH_SHORT).show();
                            teltextView.requestFocus();
                            return;
                        }


                        MInquiryVO mInquiryVO = new MInquiryVO();


                        mInquiryVO.setMemId(pref.getValue(LoginPreference.MEMBER_ID, null));
                        mInquiryVO.setQaScTitle(titletextView.getText().toString());
                        mInquiryVO.setQaScContent(contenttextView.getText().toString());
                        mInquiryVO.setQaScCategory1(spinner.getSelectedItem().toString());
                        mInquiryVO.setQaScCategory2(spinner2.getSelectedItem().toString());
                        mInquiryVO.setQaScTel(teltextView.getText().toString());

                        String uri = "/user/memberinquiry";
                        RestTempleatAsyncTask restTempleatAsyncTask = new RestTempleatAsyncTask(uri, mInquiryVO);
                        restTempleatAsyncTask.execute();

                        finish();
                    /*문의하기 한 후 화면 종료한 후에 성공했다는 메시지 뿌려주기*/

                    }
                });
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
}
