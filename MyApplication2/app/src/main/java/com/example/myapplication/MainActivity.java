package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView2, textView3, textView4, textView5, textView6, textView7;
    Location loc;
    static WeatherData weatherData;
    final String[] Days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        InitializeDays();
        ManageLocation locationListener = new ManageLocation();
        CheckPermission(locationListener);
        String url = "https://api.darksky.net/forecast/d1f231c034c0f2bb4a95c4bf057b224e/" + loc.getLatitude() + "," + loc.getLongitude();
        new APIRequest().execute(url);
    }

    public void InitializeDays() {
        int index = -1;
        Date now = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
        for(int i = 0; i < Days.length; i++) {
            if(Days[i].equals(simpleDateformat.format(now))) {
                index = i;
            }
        }
        int length = Days.length;
        int j = 0;
        for(int i = index; i < length; i++) {
            switch (j) {
                case 0:
                    textView.setText(Days[i]);
                    break;
                case 1:
                    textView2.setText(Days[i]);
                    break;
                case 2:
                    textView3.setText(Days[i]);
                    break;
                case 3:
                    textView4.setText(Days[i]);
                    break;
                case 4:
                    textView5.setText(Days[i]);
                    break;
                case 5:
                    textView6.setText(Days[i]);
                    break;
                case 6:
                    textView7.setText(Days[i]);
                    break;
            }
            j++;
            if(j == 7) {
                break;
            }
            if(i == length - 1) {
                i = -1;
                length = index;
            }
        }
    }

    public void CheckPermission(ManageLocation locationListener) {
        boolean isGPS = false;
        boolean isNetwork = false;
        String[] perm = {Manifest.permission.ACCESS_FINE_LOCATION};
        LocationManager manageLocation = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        isGPS = manageLocation.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetwork = manageLocation.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, perm, 1);
        }
        loc = locationListener.getLocation(manageLocation, isGPS, isNetwork);
    }

    public static void UpdateUI() {

    }
}
