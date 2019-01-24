package com.yurima.alarmbutton;

import android.content.Context;
import android.widget.Toast;

import com.yurima.alarmbutton.sms.SmsAppSender;
import com.yurima.alarmbutton.sms.SmsManagerSender;
import com.yurima.alarmbutton.sms.SmsSender;

/**
 * Created by Yury on 23.01.2019.
 */

public class Manager {

    final private String phoneNo = "+79279826090";
    private Context context;
    private Caller caller;
    private GPSHandler gps;

    public Manager (Context context) {
        this.context = context;
        caller = new Caller(context);
        gps = new GPSHandler(context);
    }

    public void onClickAlarmButton() {
        //caller.phoneAlarm();

        SmsSender sender = new SmsAppSender(context, phoneNo);
        sender.Send(new AlarmMessage());

        /*String gpsText = gps.getTextLocation();
        if (gpsText == null)
            Toast.makeText(context, "GPS Error", Toast.LENGTH_SHORT).show();

        Toast.makeText(context, gps.getTextLocation(), Toast.LENGTH_SHORT).show();*/
    }

}
