package com.yurima.alarmbutton;

import android.content.Context;

import com.yurima.alarmbutton.msg.AlarmMessage;

/**
 * Created by Yury on 25.01.2019.
 */

public class SettingsManager {

    MainActivity activity;

    public SettingsManager(MainActivity activity) {
        this.activity = activity;
    }

    public void onClickSettings(){
        activity.showSettings();
    }




}
