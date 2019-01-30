package com.yurima.alarmbutton.msg;

import android.location.Location;
import android.location.LocationManager;

import com.yurima.alarmbutton.settings.SettingsData;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.lang.Long.getLong;

/**
 * Created by Yury on 24.01.2019.
 */

public class AlarmMessageImpl implements AlarmMessage {
    //TODO
    private int key;
    private Date date;
    private Location location;

    public AlarmMessageImpl(int key) {
        this.date = Calendar.getInstance().getTime();
        this.key = key;
    }

    public AlarmMessageImpl(JSONObject json) throws JSONException{
            this.key = json.getInt("key");
            this.date = new Date(json.getLong("time"));
            if (json.has("long") && json.has("lat")){
                double longitude = json.getDouble("long");
                double latitude = json.getDouble("lat");
                this.location = new Location(LocationManager.GPS_PROVIDER);
                this.location.setLongitude(longitude);
                this.location.setLatitude(latitude);
            }
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("test \n");
        builder.append("key: ").append(key);
        if (location != null){
            builder.append("\nLongitude: ").append(location.getLongitude())
                    .append("\nLatitude: ").append(location.getLatitude());
        }
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss", Locale.US);
        builder.append(("\n" + format.format(date)));
        return builder.toString();
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("key", key);
            if (location != null) {
                json.put("long",location.getLongitude());
                json.put("lat",location.getLatitude());
            }
            json.put("time", date.getTime());
        }catch (JSONException e){
            return null;
        }
        return json;
    }
}
