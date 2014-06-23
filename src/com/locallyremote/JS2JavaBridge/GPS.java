package com.locallyremote.JS2JavaBridge;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Mitchell on 6/23/2014.
 */
public class GPS implements LocationListener {
    private Context mContext;
    public Location myLoc = new Location(LocationManager.GPS_PROVIDER);
    public GPS(Context context){
        mContext = context;
    }

    @Override
    public void onLocationChanged(Location location) {
        myLoc = location;
        Log.w("BroadPlex", "Location Changed");
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Toast.makeText(mContext, "Status Changed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(mContext, "GPS Enabled", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(mContext, "GPS Disabled", Toast.LENGTH_LONG).show();
    }
}
