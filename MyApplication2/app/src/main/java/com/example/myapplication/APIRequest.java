package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class APIRequest extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urls) { //url = "https://api.darksky.net/forecast/d1f231c034c0f2bb4a95c4bf057b224e/" + loc.getLatitude() + "," + loc.getLongitude();
        String returnstring = "";
        String line;
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((line = input.readLine()) != null) {
                returnstring += line + "\n";
            }

        } catch (MalformedURLException e) {
            Log.d("URL", "Invalid URL");
        } catch (IOException a) {
            Log.d("IOException", "Error");
        }
        return returnstring;
    }
    @Override
    protected void onPostExecute(String result) {

    }
}
