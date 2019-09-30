package com.yurima.alarmbutton;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.yurima.alarmbutton.settings.SettingsDialogFragment;
import com.yurima.alarmbutton.settings.SettingsManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Manager manager;
    SettingsManager settingsManager;

    public static final int REQUEST_CODE_SEND_SMS_ = 1;

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
        settingsManager = new SettingsManager(this);
        checkSmsPermission();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_settings) {
            settingsManager.onClickSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showSettings(){
        SettingsDialogFragment dialogFragment = new SettingsDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "tag1");

    }

    private void checkSmsPermission(){
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS},
                        REQUEST_CODE_SEND_SMS_);
            }
        }
    }
}
