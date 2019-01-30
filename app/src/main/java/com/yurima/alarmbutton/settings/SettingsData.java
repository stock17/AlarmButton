package com.yurima.alarmbutton.settings;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.ScaleGestureDetector;

/**
 * Created by Yury on 29.01.2019.
 */


public class SettingsData {

    public static final String SETTINGS = "settings";
    public static final String PHONE_NO = "phone_no";
    public static final String KEY = "key";



    public SettingsData(Context context){
        this.context = context;
        settings = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private Context context;

    public void savePhone(String phoneNo) {
        editor.putString(PHONE_NO, phoneNo);
        editor.commit();
    }

    public String getPhone(){
        return (settings.getString(PHONE_NO, "000"));
    }

    public void saveKey(String key) {
        editor.putString(KEY, key);
        editor.commit();
    }

    public String getKey(){
        return (settings.getString(KEY, "0"));
    }

}
