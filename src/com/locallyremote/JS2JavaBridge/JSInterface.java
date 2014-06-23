package com.locallyremote.JS2JavaBridge;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Mitchell on 6/16/2014.
 */
public class JSInterface {

    Context mContext;
    GPS here;
    LocationManager locationManager;
    JSInterface(Context c) {
        mContext = c;

    }


    public void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    public void SavePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String LoadPreferences(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharedPreferences.getString(key, "");
    }

    public void Vibrate(long milliseconds) {
        Vibrator v = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(milliseconds);
    }

    public void SendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    public String getLocation() {
        Log.w("JSI", "JSI.getLocation() called");
        return Double.toString(here.myLoc.getLatitude());

    }

    public void PrepareGPS(long time, float distance){
        here = new GPS(mContext);
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                time,
                distance,
                here
        );

    }

    public void StopGPS(){
        locationManager.removeUpdates(here);
        here = null;
    }


}
