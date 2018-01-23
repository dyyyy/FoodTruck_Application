package com.example.user.foodtruck;

import android.os.AsyncTask;

import java.util.Map;

/**
 * Created by bit-user on 2018-01-23.
 */

public class HttpAsyncTask extends AsyncTask<Map<String,String>, Integer, String> {

    @Override
    protected void onPreExecute() {
        String target = "127.0.0.1";
    }

    @Override
    protected String doInBackground(Map<String, String>[] maps) {



        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
