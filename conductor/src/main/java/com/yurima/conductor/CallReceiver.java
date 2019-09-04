package com.yurima.conductor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.internal.telephony.*;
import com.yurima.alarmbuttonlib.msg.AlarmMessage;
import com.yurima.alarmbuttonlib.msg.AlarmMessageImpl;

import java.lang.reflect.Method;


public class CallReceiver extends BroadcastReceiver {
    private SenderThread mSenderThread;

    public CallReceiver(SenderThread senderThread) {
        super();
        this.mSenderThread = senderThread;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
        if ("IDLE".equals(stateStr))
            return;
        String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        Log.i("AAA", "RECEIVING CALL " + number);

        AlarmMessage alarmMessage = new AlarmMessageImpl(number);
        mSenderThread.sendMessage(alarmMessage.toJson().toString());

        rejectCall(context);

    }

    private void rejectCall(Context context){
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Class clazz = Class.forName(telephonyManager.getClass().getName());
            Method method = clazz.getDeclaredMethod("getITelephony");
            method.setAccessible(true);
            ITelephony telephonyService = (ITelephony) method.invoke(telephonyManager);
            telephonyService.endCall();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
