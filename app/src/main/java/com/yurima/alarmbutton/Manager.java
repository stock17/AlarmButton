package com.yurima.alarmbutton;

import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import com.yurima.alarmbutton.msg.AlarmMessageImpl;
import com.yurima.alarmbutton.sms.SmsAppSender;
import com.yurima.alarmbutton.sms.SmsSender;

/**
 * Created by Yury on 23.01.2019.
 */

public class Manager {

    final private String phoneNo = "+79279826090";

    private Context context;
    private Caller caller;
    private GPSHandler gps;
    private SmsSender sms;

    public Manager (Context context) {
        this.context = context;
        caller = new Caller(context);
        gps = new GPSHandler(context);
        sms = new SmsAppSender(context, phoneNo);
    }

    public void onClickAlarmButton() {
        //caller.phoneAlarm();

//        AlarmMessageImpl msg = new AlarmMessageImpl();
//        Location l = gps.getLocation();
//        msg.setLocation(l);
//        sms.Send(msg);

        //testing part
        AlarmMessageImpl msg = new AlarmMessageImpl();
        Location l = gps.getLocation();
        msg.setLocation(l);
        String jString = msg.toJsonString();

        AlarmMessageImpl a2 = new AlarmMessageImpl();
        a2.fromJsonString(jString);
        Toast.makeText(context, a2.toString(), Toast.LENGTH_SHORT).show();
    }

}
