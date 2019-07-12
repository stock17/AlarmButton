package com.yurima.conductor;

import android.Manifest;
import android.content.Intent;
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

    private SmsReceiver mSmsReceiver;
    private IntentFilter mIntentFilter;

    @BindView(R.id.button_start)
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        checkSmsPermission();

        mSmsReceiver = new SmsReceiver();
        mIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        mIntentFilter.setPriority(999);
        registerReceiver(mSmsReceiver, mIntentFilter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mSmsReceiver);
        super.onDestroy();
    }


    private void checkSmsPermission(){
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS},
                        REQUEST_CODE_RECEIVE_SMS);
            }
        }
    }

    @OnClick(R.id.button_start)
    void onClickStartButton(){

    }
}
