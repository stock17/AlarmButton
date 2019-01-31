package com.yurima.conductor;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Yury on 31.01.2019.
 */

public class ConductorService extends IntentService {

    public ConductorService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

}
