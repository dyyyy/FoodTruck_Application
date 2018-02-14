package com.example.user.networkutil;

import android.os.AsyncTask;
import android.util.Log;

import com.example.user.vo.MInquiryVO;
import com.example.user.vo.MemberVO;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bit-user on 2018-02-09.
 */

public class RestTempleatAsyncTask extends AsyncTask<String, Integer, String> {
    String baseUri;
    RestTemplate restTemplate;
    String uri;
    String jsonString;
    String result = null;
    Object vo;

    HttpHeaders httpHeaders;

    public RestTempleatAsyncTask(String uri) {
        this.uri = uri;
    }

    public RestTempleatAsyncTask(String uri, String jsonString) {
        this.uri = uri;
        this.jsonString = jsonString;
    }

    public RestTempleatAsyncTask(String uri, Object vo) {
        this.uri = uri;
        this.vo = vo;
    }

    @Override
    protected void onPreExecute() {
        baseUri = "http://192.168.1.34:8090/android" + uri;
        restTemplate = new RestTemplate();

        httpHeaders = new HttpHeaders();
        //httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        //httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //httpHeaders.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        /*전송 인코딩타입*/
        MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
        httpHeaders.setContentType(mediaType);
    }

    @Override
    protected String doInBackground(String... strings) {

        switch (uri) {
            case "/notice":
            case "/event":
                result = restTemplate.getForObject(baseUri, String.class);
                return result;
            case "/noticecount":
            case "/eventcount":
            case "/user/memberinquiry":
                restTemplate.put(baseUri, vo);
                return result;
            case "/register":
                result = restTemplate.postForObject(baseUri, vo, String.class);
                Log.d("androidregister", ":" + result);
                return result;
            case "/user/inqueryinfo/{id}":
                result = restTemplate.getForObject(baseUri, String.class, jsonString);
                Log.d("androidregister", ":" + result);
                return result;
            case "/login":
                System.out.println("vo : " + vo.toString());
                /*전송 인코딩 설정*/
                HttpEntity<String> stringHttpEntity = new HttpEntity<String>(String.valueOf(vo), httpHeaders);
                result = restTemplate.postForObject(baseUri, stringHttpEntity, String.class);
                Log.d("login", ":" + result);

                return result;
            default:
                return result;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


}
