package com.yurima.alarmbutton;

import android.content.Context;
import android.location.Location;
import android.media.audiofx.BassBoost;
import android.widget.Toast;

import com.yurima.alarmbuttonlib.msg.AlarmMessage;
import com.yurima.alarmbuttonlib.msg.AlarmMessageImpl;
import com.yurima.alarmbutton.settings.SettingsData;
import com.yurima.alarmbutton.sms.SmsAppSender;
import com.yurima.alarmbutton.sms.SmsManagerSender;
import com.yurima.alarmbutton.sms.SmsSender;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.data;
import static android.R.attr.id;

/**
 * Created by Yury on 23.01.2019.
 */

public class Manager {



    private Context context;
    private Caller caller;
    private GPSHandler gps;
    private SmsSender sms;


    public Manager (Context context) {
        this.context = context;
        caller = new Caller(context);
        gps = new GPSHandler(context);
        sms = new SmsAppSender(context);
    }

    public AlarmMessage createAlarmMessage(int key){
        AlarmMessage msg = new AlarmMessageImpl(key);
        Location location = gps.getLocation();
        if (location != null)
            msg.setLocation(location);
        return msg;
    }

    public void onClickAlarmButton() {
        SettingsData data = new SettingsData(context);
        int key = Integer.parseInt(data.getKey());
        AlarmMessage msg = createAlarmMessage(key);
        String jString = msg.toJson().toString();
        sms.Send(data.getPhone(), msg);


        //Simulate sending message
        try {
            JSONObject json = new JSONObject(jString);
            AlarmMessage a2 = new AlarmMessageImpl(json);
            Toast.makeText(context,
                    "SMS to:" + data.getPhone() + "\n" +
                    a2.toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
