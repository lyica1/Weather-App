package com.example.myapplication;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class ManageLocation implements LocationListener {
    Location loc;
    @Override
    public void onLocationChanged(Location location) {
        loc = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        
    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public Location getLocation(LocationManager locationManager, boolean isGPS, boolean isNetwork) {
        try {
            if (isGPS) {
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        1000,
                        1, this);
                if (locationManager != null) {
                    loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
            } else if (isNetwork) {
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        1000,
                        1, this);
                if (locationManager != null) {
                    loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return loc;
    }
}
