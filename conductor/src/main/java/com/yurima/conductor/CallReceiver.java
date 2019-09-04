package com.yurima.conductor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class CallReceiver extends BroadcastReceiver {
    private SenderThread mSenderThread;

    public CallReceiver(SenderThread senderThread) {
        super();
        this.mSenderThread = senderThread;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("AAA", "RECEIVING CALL");
    }
}
