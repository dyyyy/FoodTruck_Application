package com.example.user.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created by bit-user on 2018-02-20.
 */

public class LoginPreference {
    private final String PREF_NAME = "login";
    public final static String USER_INFO = "USER_INFO";

    public final static String MEMBER_ID = "MEMBER_ID";
    public final static String MEMBER_NAME = "MEMBER_NAME";
    public final static String MEMBER_TEL = "MEMBER_TEL";
    public final static String MEMBER_AUTH = "MEMBER_AUTH";
    public final static String MEMBER_MILEAGE = "MEMBER_MILEAGE";
    public final static String LICENSE_NO = "LICENSE_NO";


    static Context context;

    public LoginPreference(Context context) {
        this.context = context;
    }

    /*CREATE*/
    public void putUser(String key, Map<String, Object> map) throws IOException {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Log.d("value", "" + map.toString());
        Map<String, String> maps = (Map<String, String>) map.get("data");

        editor.putBoolean(USER_INFO, true);
        editor.putString(MEMBER_ID, String.valueOf(maps.get("memberId")));
        editor.putString(MEMBER_NAME, String.valueOf(maps.get("memberName")));
        editor.putString(MEMBER_TEL, String.valueOf(maps.get("memberTel")));
        editor.putInt(MEMBER_AUTH, Integer.parseInt(maps.get("memberAuth")));
        editor.putString(MEMBER_MILEAGE, String.valueOf(maps.get("mileage")));
        editor.putString(LICENSE_NO, maps.get("licenseNo"));
        editor.commit();
    }

    public void put(String key, String value) throws IOException {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(key, value);
        editor.commit();
    }

    public void put(String key, Boolean value) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void put(String key, int value) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /*READ*/
    public String getValue(String key, String defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        return pref.getString(key, defaultValue);
    }

    public int getValue(String key, int defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        return pref.getInt(key, defaultValue);
    }

    public boolean getValue(String key, boolean defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        return pref.getBoolean(key, defaultValue);
    }

    public Map<String, ?> getValue(String memberId) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        return pref.getAll();
    }


    /*UPDATE*/

    /*DELETE*/
    public void deleteValue(String key) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();
    }

    public void deleteAllValue() {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
