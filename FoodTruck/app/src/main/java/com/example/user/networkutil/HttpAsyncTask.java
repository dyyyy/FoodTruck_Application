package com.example.user.networkutil;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpAsyncTask extends AsyncTask<String, Integer, String> {
    String inputUrl;
    String jsonString;

    public HttpAsyncTask(String inputUrl) {
        this.inputUrl = inputUrl;
    }

    public HttpAsyncTask(String inputUrl, String jsonString) {
        this.inputUrl = inputUrl;
        this.jsonString = jsonString;
    }

    @Override
    protected String doInBackground(String[] strings) {
        URL url = null;
        HttpURLConnection urlConnection = null;

        BufferedReader br = null;
        String result = null;
        OutputStream outputStream;


        try {
            url = new URL("http://192.168.1.18:8090/" + inputUrl);
            urlConnection = (HttpURLConnection) url.openConnection();


            //url 설정
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Accept-Charset", "utf-8");
            urlConnection.setRequestProperty("Context_Type", "application/x-www-form-urlencoded; charset=UTF-8");
            //서버에서 받아오는 데이터를 json형식 타입으로 요청
            urlConnection.setRequestProperty("Accept", "application/json");
            //requestBody 전달시 json으로 서버에 전달
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.connect();

            /*보낼 값이 있을때*/
            if(jsonString != null){
                outputStream = urlConnection.getOutputStream();
                outputStream.write(jsonString.getBytes());
                outputStream.flush();
            }

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
            } else {

                br = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream(), "utf-8"));

            }

            String inputLine;
            StringBuffer resp = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                resp.append(inputLine);
            }

            result = resp.toString();


            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {

            urlConnection.disconnect();
        }
        /*결과값 return 하면 post 메소드에서 처리한다..*/
        System.out.println("result: " + result);
        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println("s : " + s);
        super.onPostExecute(s);
    }


}
