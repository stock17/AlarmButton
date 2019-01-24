package com.yurima.alarmbutton;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
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
    private SmsSender sms;

    public Manager (Context context) {
        this.context = context;
        caller = new Caller(context);
        gps = new GPSHandler(context);
        sms = new SmsAppSender(context, phoneNo);
    }

    public void onClickAlarmButton() {
        //caller.phoneAlarm();

        AlarmMessage msg = new AlarmMessage();
        Location l = gps.getLocation();
        msg.setLocation(l);
        sms.Send(msg);

        /*String gpsText = gps.getTextLocation();
        if (gpsText == null)
            Toast.makeText(context, "GPS Error", Toast.LENGTH_SHORT).show();

        Toast.makeText(context, gps.getTextLocation(), Toast.LENGTH_SHORT).show();*/
    }

}
