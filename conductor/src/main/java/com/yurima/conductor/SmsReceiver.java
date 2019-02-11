package com.yurima.conductor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

    SmsMessage[] smsMessages;

    @Override
    public void onReceive(Context context, Intent intent) {

        smsMessages = getSmsMessages(intent);
        if (smsMessages != null){
            for (SmsMessage msg : smsMessages){
                showMessage(context, msg);
            }
        }
    }

    private SmsMessage[] getSmsMessages(Intent intent){
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Object[] pdus = (Object[]) extras.get("pdus");
            if (pdus != null) {
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = getIncomingMessage(pdus[i], extras);
                }
                return messages;
            }
        }
        return null;
    }

    private void showMessage(Context context, SmsMessage msg){

        String address = msg.getOriginatingAddress();
        String text = msg.getMessageBody();
        Toast.makeText(context, "from: " + address + "\n" +
                        "text: " + text, Toast.LENGTH_LONG ).show();
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