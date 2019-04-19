package com.yurima.conductor;

import android.Manifest;
import android.content.Intent;
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

    private ConductorService mConductorService;
    private final int REQUEST_CODE_RECEIVE_SMS = 1;

    @BindView(R.id.button_start)
    Button startButton;
    @OnClick(R.id.button_start)
    void onClickStartButton(){

        checkSmsPermission();

        mConductorService = new ConductorService();
        Intent serviceIntent = new Intent(getApplicationContext(), ConductorService.class);
        startService(serviceIntent);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
