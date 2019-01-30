package com.yurima.alarmbutton;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

public class Caller {

    private Context context;

    public Caller (Context context) {
        this.context = context;
    }

    public void phoneAlarm(){
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        String phoneNo = "+79279826090";
        phoneIntent.setData(Uri.parse("tel:" + phoneNo));

        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
            //TODO make mistake notification
        }
        context.startActivity(phoneIntent);
    }


}
