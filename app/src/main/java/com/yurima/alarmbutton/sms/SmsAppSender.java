package com.yurima.alarmbutton.sms;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.yurima.alarmbutton.AlarmMessage;




/**
 * Created by Yury on 24.01.2019.
 */

public class SmsAppSender extends SmsSender {
    public SmsAppSender(Context context, String phoneNo) {
        super(context, phoneNo);
    }

    @Override
    public void Send(AlarmMessage msg) {
        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + phoneNo));
        intent.putExtra( "sms_body", msg.getText() );
        context.startActivity(intent);
    }
}
