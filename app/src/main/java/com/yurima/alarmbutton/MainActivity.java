package com.yurima.alarmbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Caller caller;

    @BindView(R.id.alarm_button) ImageButton alarmButton;
    @OnClick (R.id.alarm_button)
    public void onClickAlarmButton (ImageButton ib) {
        //Toast.makeText(this, "Alarm! Help! Sos!", Toast.LENGTH_SHORT).show();
        //caller.phoneAlarm();
        caller.smsAlarm();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        caller = new Caller(this);

    }
}
