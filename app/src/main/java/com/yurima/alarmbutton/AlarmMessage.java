package com.yurima.alarmbutton;

import android.location.Location;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yury on 24.01.2019.
 */

public class AlarmMessage {
    //TODO
    private int id;
    private Date date;
    private Location location;

    public AlarmMessage() {
        this.date = Calendar.getInstance().getTime();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getText() {
        StringBuilder builder = new StringBuilder("test ");
        if (location != null){
            builder.append("Longitude: ").append(location.getLongitude())
                    .append("\nLatitude: ").append(location.getLatitude());
        }
        builder.append((" " + date.getTime()));
        return builder.toString();
    }

}
