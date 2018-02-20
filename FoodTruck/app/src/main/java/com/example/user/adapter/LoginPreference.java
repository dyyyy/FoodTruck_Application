package com.example.user.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by bit-user on 2018-02-20.
 */

public class LoginPreference {
    private final String PREF_NAME ="login";
    static Context context;

    public LoginPreference(Context context) {
        this.context = context;
    }
    public void put(String key, String value){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public void put(String key, Boolean value){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    public void put(String key, int value){
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

}
