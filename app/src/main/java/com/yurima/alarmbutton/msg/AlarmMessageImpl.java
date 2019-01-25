package com.yurima.alarmbutton.msg;

import android.location.Location;
import android.location.LocationManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Provider;
import java.sql.Time;
import java.text.DateFormat;
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
    private int id;
    private Date date;
    private Location location;

    public AlarmMessageImpl() {
        this.date = Calendar.getInstance().getTime();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("test ");
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
            json.put("id",id);
            json.put("long",location.getLongitude());
            json.put("lat",location.getLatitude());
            json.put("time", date.getTime());
        }catch (JSONException e){
            return null;
        }
        return json;
    }

    @Override
    public String toJsonString() {
        return toJson().toString();
    }

    @Override
    public boolean fromJson(JSONObject json) {
        try {
            this.id = json.getInt("id");
            double longitude = json.getDouble("long");
            double latitude = json.getDouble("lat");
            this.location = new Location(LocationManager.GPS_PROVIDER);
            this.location.setLongitude(longitude);
            this.location.setLatitude(latitude);
            this.date = new Date(json.getLong("time"));
        } catch(JSONException e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean fromJsonString(String jsonString) {
        try {
            JSONObject json = new JSONObject(jsonString);
            return fromJson(json);
        } catch (JSONException e){
            return false;
        }
    }


}
