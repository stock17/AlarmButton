package com.yurima.conductor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private ConductorService mConductorService;

    @BindView(R.id.button_start)
    Button startButton;
    @OnClick(R.id.button_start)
    void onClickStartButton(){
        mConductorService = new ConductorService();
        Intent serviceIntent = new Intent(getApplicationContext(), ConductorService.class);
        startService(serviceIntent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
