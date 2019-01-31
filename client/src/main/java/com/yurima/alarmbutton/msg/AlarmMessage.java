package com.yurima.alarmbutton.msg;
import android.location.Location;

import org.json.JSONObject;

/**
 * Created by Yury on 25.01.2019.
 */

public interface AlarmMessage {

    JSONObject toJson();
    void setLocation(Location location);
}
