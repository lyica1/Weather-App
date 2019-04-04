package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class LandingPage extends AppCompatActivity {
    private Handler LandingWait = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LandingWait.postDelayed(new Runnable() {
            @Override
            public void run() {
                //The following code will execute after the 5 seconds.
                try {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish(); // marks Landing Page complete, thus non-navigable...
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 5000); //changed to 1000 for debugging speed //5000);  // LandingWait.postDelay()
    } // onCreate()
}
