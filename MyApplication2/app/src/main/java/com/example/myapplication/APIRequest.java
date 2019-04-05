package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class APIRequest extends AsyncTask<String, Void, String> {
    final int NUM_OF_HOURS = 5;
    final int NUM_OF_DAYS = 7;
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
        MainActivity.weatherData = new WeatherData();
        try {
            JSONObject resultdata = new JSONObject(result);
            JSONObject current = resultdata.getJSONObject("currently");
            JSONObject hourlydata = resultdata.getJSONObject("hourly");
            JSONObject dailydata = resultdata.getJSONObject("daily");
            JSONArray hourly = hourlydata.getJSONArray("data");
            JSONArray daily = dailydata.getJSONArray("data");
            MainActivity.weatherData.setCurrentTemp(current.getDouble("temperature"));
            setHourly(hourly);
            setDailyHigh(daily);
            setDailyLow(daily);
            MainActivity.UpdateUI();
        }catch (JSONException e) {
            Log.d("JSONException", "Invalid Parsing Data");
        }
    }

    public void setHourly(JSONArray hourly) throws JSONException{
        double[] Hours = new double[NUM_OF_HOURS];
        for(int i = 0; i < NUM_OF_HOURS; i++) {
            JSONObject hourdata = hourly.getJSONObject(i);
            Hours[i] = hourdata.getDouble("temperature");
        }
        MainActivity.weatherData.setHourly(Hours);
    }

    public void setDailyHigh(JSONArray daily) throws JSONException {
        double[] dailyhigh = new double[NUM_OF_DAYS];
        for (int i = 0; i < NUM_OF_DAYS; i++) {
            JSONObject dailydata = daily.getJSONObject(i);
            dailyhigh[i] = dailydata.getDouble("temperatureHigh");
        }
        MainActivity.weatherData.setDailyhigh(dailyhigh);
    }

    public void setDailyLow(JSONArray daily) throws JSONException {
        double[] dailylow = new double[NUM_OF_DAYS];
        for (int i = 0; i < NUM_OF_DAYS; i++) {
            JSONObject dailydata = daily.getJSONObject(i);
            dailylow[i] = dailydata.getDouble("temperatureLow");
        }
        MainActivity.weatherData.setDailyhigh(dailylow);
    }
}
