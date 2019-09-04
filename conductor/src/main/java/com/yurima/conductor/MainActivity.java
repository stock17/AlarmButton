package com.yurima.conductor;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_RECEIVE_SMS = 1;
    private final int REQUEST_CODE_READ_PHONE = 2;
    private final int REQUEST_CODE_CALL_PHONE = 3;

    private SmsReceiver mSmsReceiver;
    private IntentFilter mSmsmIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

    private CallReceiver mCallReceiver;
    private IntentFilter mCallIntentFilter = new IntentFilter("android.intent.action.PHONE_STATE");

    private SenderThread mSenderThread;
    private String SENDER_THREAD_NAME = "SenderThread";

    @BindView(R.id.button_start)
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        checkSmsPermission();

        mSenderThread = new SenderThread(SENDER_THREAD_NAME);
        mSenderThread.start();
        mSenderThread.prepareHandler();

        mSmsReceiver = new SmsReceiver(mSenderThread);
        mSmsmIntentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        registerReceiver(mSmsReceiver, mSmsmIntentFilter);

        mCallReceiver= new CallReceiver(mSenderThread);
        mCallIntentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        registerReceiver(mCallReceiver, mCallIntentFilter);


    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mSmsReceiver);
        unregisterReceiver(mCallReceiver);
        mSenderThread.quit();
        super.onDestroy();
    }


    private void checkSmsPermission(){
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS},
                        REQUEST_CODE_RECEIVE_SMS);
            }

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_PHONE_STATE},
                        REQUEST_CODE_READ_PHONE);
            }

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE},
                        REQUEST_CODE_CALL_PHONE);
            }
        }
    }

    @OnClick(R.id.button_start)
    void onClickStartButton(){

    }
}
