package com.yurima.alarmbutton;

import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import com.yurima.alarmbutton.msg.AlarmMessage;
import com.yurima.alarmbutton.msg.AlarmMessageImpl;
import com.yurima.alarmbutton.settings.SettingsData;
import com.yurima.alarmbutton.sms.SmsAppSender;
import com.yurima.alarmbutton.sms.SmsManagerSender;
import com.yurima.alarmbutton.sms.SmsSender;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yury on 23.01.2019.
 */

public class Manager {

    private String phoneNo = "+79279826090";

    private Context context;
    private Caller caller;
    private GPSHandler gps;
    private SmsSender sms;

    public Manager (Context context) {
        this.context = context;
        caller = new Caller(context);
        gps = new GPSHandler(context);
        sms = new SmsManagerSender(context, phoneNo);
    }

    public AlarmMessage createAlarmMessage(){
        AlarmMessage msg = new AlarmMessageImpl();
        Location location = gps.getLocation();
        if (location != null)
            msg.setLocation(location);
        return msg;
    }

    public void onClickAlarmButton() {
        phoneNo = new SettingsData(context).getPhone();
        AlarmMessage msg = createAlarmMessage();
        String jString = msg.toJson().toString();
        sms.Send(msg);

        //Simulate sending message
        try {
            JSONObject json = new JSONObject(jString);
            AlarmMessage a2 = new AlarmMessageImpl(json);
            Toast.makeText(context,
                    "SMS to:" + phoneNo + "\n" +
                    a2.toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
