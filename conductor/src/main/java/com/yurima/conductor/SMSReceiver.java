package com.yurima.conductor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by Yury on 11.02.2019.
 */

public class SMSReceiver extends BroadcastReceiver {

    SmsMessage[] smsMessages;

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        if (extras != null) {
            Object[] pdus = (Object[]) extras.get("pdus");
            if (pdus != null) {
                smsMessages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    smsMessages[i] = getIncomingMessage(pdus[i], extras);
                }
            }
        }
    }

    private SmsMessage getIncomingMessage(Object aObject, Bundle bundle) {
        SmsMessage currentSMS;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String format = bundle.getString("format");
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject, format);
        } else {
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject);
        }
        return currentSMS;
    }
}