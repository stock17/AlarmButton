package com.yurima.alarmbutton;

import android.content.Context;

/**
 * Created by Yury on 23.01.2019.
 */

public class Manager {

    private Context context;
    private Caller caller;

    public Manager (Context context) {
        this.context = context;
        caller = new Caller(context);
    }

    public void onClickAlarmButton() {
        caller.phoneAlarm();
        //caller.smsAlarm();
    }

}
