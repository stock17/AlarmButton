package com.yurima.alarmbutton.sms;

import android.content.Context;

import com.yurima.alarmbutton.msg.AlarmMessage;
import com.yurima.alarmbutton.msg.AlarmMessageImpl;

/**
 * Created by Yury on 24.01.2019.
 */

public abstract class SmsSender {

    protected Context context;


    public SmsSender (Context context){
        this.context = context;
    }

    public abstract void Send(String phoneNo, AlarmMessage msg);
}
