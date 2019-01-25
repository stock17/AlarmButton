package com.yurima.alarmbutton.msg;
import org.json.JSONObject;

/**
 * Created by Yury on 25.01.2019.
 */

public interface AlarmMessage {

    JSONObject toJson();
    String toJsonString();
    boolean fromJson(JSONObject json);
    boolean fromJsonString(String jsonString);
}
