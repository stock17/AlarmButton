package com.yurima.alarmbutton;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

public class Caller {

    private Context context;

    public Caller (Context context) {
        this.context = context;
    }

    public void PhoneAlarm (){
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:+79279826090"));

        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        context.startActivity(phoneIntent);

    }
}
