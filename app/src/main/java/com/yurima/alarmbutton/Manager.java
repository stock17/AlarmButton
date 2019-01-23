package com.yurima.alarmbutton;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Yury on 23.01.2019.
 */

public class Manager {

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
        //caller.smsAlarm();
        String gpsText = gps.getTextLocation();
        if (gpsText == null)
            Toast.makeText(context, "GPS Error", Toast.LENGTH_SHORT).show();

        Toast.makeText(context, gps.getTextLocation(), Toast.LENGTH_SHORT).show();
    }

}
