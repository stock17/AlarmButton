package com.yurima.alarmbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Manager manager;

    @BindView(R.id.alarm_button) ImageButton alarmButton;
    @OnClick (R.id.alarm_button)
    public void onClickAlarmButton (ImageButton ib) {
        manager.onClickAlarmButton();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        manager = new Manager(this);

    }
}
