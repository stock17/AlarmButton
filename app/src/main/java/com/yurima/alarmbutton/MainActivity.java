package com.yurima.alarmbutton;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.yurima.alarmbutton.settings.SettingsDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Manager manager;
    SettingsManager settingsManager;

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
}
