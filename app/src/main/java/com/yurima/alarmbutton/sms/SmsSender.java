package com.yurima.alarmbutton.sms;

import android.content.Context;

import com.yurima.alarmbutton.msg.AlarmMessage;
import com.yurima.alarmbutton.msg.AlarmMessageImpl;

/**
 * Created by Yury on 24.01.2019.
 */

public abstract class SmsSender {

    protected Context context;
    protected String phoneNo;

    public SmsSender (Context context, String phoneNo){
        this.context = context;
        this.phoneNo = phoneNo;
    }

    public abstract void Send(AlarmMessage msg);
}
