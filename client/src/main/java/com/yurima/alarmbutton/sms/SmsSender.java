package com.yurima.alarmbutton.sms;

import android.content.Context;

import com.yurima.alarmbuttonlib.msg.AlarmMessage;

public abstract class SmsSender {

    protected Context context;


    public SmsSender (Context context){
        this.context = context;
    }

    public abstract void Send(String phoneNo, AlarmMessage msg);
}
