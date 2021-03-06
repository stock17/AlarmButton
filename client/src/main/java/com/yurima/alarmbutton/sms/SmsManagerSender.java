package com.yurima.alarmbutton.sms;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.yurima.alarmbuttonlib.msg.AlarmMessage;
import com.yurima.alarmbuttonlib.msg.AlarmMessageImpl;

/**
 * Created by Yury on 24.01.2019.
 */

public class SmsManagerSender extends SmsSender {

    public SmsManagerSender(Context context){
        super(context);
    }

    @Override
    public void Send(String phoneNo, AlarmMessage msg) {
        try {
            String text = msg.toJson().toString();
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, text, null, null);
            Toast.makeText(context.getApplicationContext(), "Message Sent",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(context.getApplicationContext(),ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}
