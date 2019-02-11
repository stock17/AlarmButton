package com.yurima.conductor;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Yury on 31.01.2019.
 */

public class ConductorService extends Service {
    private SmsReceiver mSmsReceiver;
    private IntentFilter mIntentFilter;

    @Override
    public void onCreate() {
        super.onCreate();
        mSmsReceiver = new SmsReceiver();
        mIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        mIntentFilter.setPriority(999);
        registerReceiver(mSmsReceiver, mIntentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mSmsReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
