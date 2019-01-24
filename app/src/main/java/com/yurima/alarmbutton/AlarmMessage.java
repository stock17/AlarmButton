package com.yurima.alarmbutton;

import android.location.Location;

/**
 * Created by Yury on 24.01.2019.
 */

public class AlarmMessage {
    //TODO
    private Location location;

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getText() {
        StringBuilder builder = new StringBuilder("test ");
        if (location != null){
            builder.append("Longitude: ").append(location.getLongitude())
                    .append("\nLatitude: ").append(location.getLatitude());
        }
        return builder.toString();
    }

}
