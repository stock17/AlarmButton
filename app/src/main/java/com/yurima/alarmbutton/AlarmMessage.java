package com.yurima.alarmbutton;

import android.location.Location;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("test ");
        if (location != null){
            builder.append("Longitude: ").append(location.getLongitude())
                    .append("\nLatitude: ").append(location.getLatitude());
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.US);
        builder.append(("\n " + format.format(date)));
        return builder.toString();
    }

}
