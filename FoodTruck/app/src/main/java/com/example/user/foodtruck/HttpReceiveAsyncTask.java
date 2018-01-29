package com.example.user.foodtruck;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HttpReceiveAsyncTask extends AsyncTask<String, Integer, String> {
    String inputUrl;

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String[] inputUrl) {
        this.inputUrl = inputUrl[0];
        URL url = null;
        HttpURLConnection urlConnection = null;

        BufferedReader br = null;
        String result = null;

        try {
            url = new URL("http://192.168.1.18:8090/" + inputUrl[0]);
            urlConnection = (HttpURLConnection) url.openConnection();


            //url 설정
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Accept-Charset", "utf-8");
            urlConnection.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;charset=UTF-8");

            urlConnection.connect();

            /*보내고싶은 제이슨 object 를 만들어 보낼 수 있다.*/
            /*
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phoneNum","010..");
            jsonObject.put("name","홍길");
            jsonObject.put("address","주소");

            outputStream = urlConnection.getOutputStream();
            outputStream.write(jsonObject.toString().getBytes());
            outputStream.flush();
*/
            /*보낼 데이터가 있을때 보내기
            if(maps != null){
                params.putAll(maps[0]);
                urlConnection.setConnectTimeout(5000);
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));

            }
*/

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
        System.out.println("result: "+result);
        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println("s : "+s);
        super.onPostExecute(s);
    }
}
