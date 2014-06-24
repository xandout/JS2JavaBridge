package com.locallyremote.JS2JavaBridge;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;


public class GPS implements LocationListener {
    public Location location = new Location(LocationManager.GPS_PROVIDER);
    private Context mContext;
    public GPS(Context context){
        mContext = context;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        Log.w("JSI", "Location Changed");
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.w("JSI", "Status Changed");
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.w("JSI", "GPS Enabled");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.w("JSI", "GPS Disabled");
    }
}
