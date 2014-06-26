package com.locallyremote.JS2JavaBridge;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;


public class GPS implements LocationListener {
    public Location location = new Location(LocationManager.GPS_PROVIDER);
    private Context mContext;
    private LocationManager mLocationManager;
    public GPS(Context context){
        mContext = context;
    }

    public String OneOff() {
        mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        final String[] retval = {""};

        mLocationManager.requestSingleUpdate(criteria, new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                // reverse geo-code location
                retval[0] = Double.toString(location.getLatitude()) + ", " + Double.toString(location.getLongitude());
                Toast.makeText(mContext, retval[0], Toast.LENGTH_LONG).show();

            }

            @Override
            public void onProviderDisabled(String provider) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProviderEnabled(String provider) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
                // TODO Auto-generated method stub

            }

        }, null);

        return retval[0];
    }


    public String getLocation() {
        Log.w("JSI", "JSI.getLocation() called");

        return Double.toString(location.getLatitude()) + ", " + Double.toString(location.getLongitude());

    }

    public void PrepareGPS(long time, float distance) {
        Log.w("JSI", "PrepareGPS() Called");
        mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!isGPSEnabled) {
            // GPS is not enabled
            Toast.makeText(mContext, "GPS Not Available", Toast.LENGTH_LONG).show();
            showGPSAlert();
        } else {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    time,
                    distance,
                    this
            );
        }
    }

    public void StopGPS() {
        try {
            mLocationManager.removeUpdates(this);
        } catch (NullPointerException npe) {
            Log.e("JSI", "removeUpdates called before updates received. ");
        }

    }

    public void showGPSAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle("GPS Settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
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
