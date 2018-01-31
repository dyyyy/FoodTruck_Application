package com.example.user.foodtruck;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by bit-user on 2018-01-31.
 */

public class NetworkAvailable {
    private Activity activity;

    public boolean isNetworkAvailable(Activity context) {
        boolean available = false;
        this.activity = context;
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            available = true;
        }

        return available;
    }

}