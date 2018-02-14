package com.example.user.networkutil;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by bit-user on 2018-01-31.
 */

public class NetworkAvailable {
    private Activity activity;


    public NetworkAvailable(Activity activity) {
        this.activity = activity;
    }

    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE); //모바일 데이터 여부
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI); //와이파이 여부

        return mobile.isConnected() || wifi.isConnected(); //결과값을 리턴
    }

}