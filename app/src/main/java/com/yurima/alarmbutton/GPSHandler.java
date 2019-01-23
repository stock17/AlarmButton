package com.yurima.alarmbutton;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Yury on 23.01.2019.
 */

public class GPSHandler implements LocationListener {

    private Context context;
    private LocationManager manager;
    private Location location;



    public GPSHandler(Context context){
        this.context = context;
    }

    public Location getLocation() {

        if(manager == null)
            manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(context, "GPS error", Toast.LENGTH_SHORT).show();
            return null;
        }

        try {
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 60 * 1, 10, this);
            location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        } catch (SecurityException e) {

        }

        return location;
    }

    public String getTextLocation () {
        location = getLocation();
        if (location == null)
            return null;
        StringBuilder builder = new StringBuilder()
                .append("Longitude: ").append(location.getLongitude())
                .append("\nLatitude: ").append(location.getLatitude());
        return builder.toString();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
