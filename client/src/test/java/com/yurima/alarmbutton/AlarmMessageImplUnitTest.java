package com.yurima.alarmbutton;

import com.yurima.alarmbuttonlib.msg.AlarmMessage;
import com.yurima.alarmbuttonlib.msg.AlarmMessageImpl;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;


public class AlarmMessageImplUnitTest {

    @Test
    public void toJson_IsCorrect(){
        AlarmMessage msg = new AlarmMessageImpl(1);
        JSONObject jo = msg.toJson();
        try {
            AlarmMessage msg2 = new AlarmMessageImpl(jo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
