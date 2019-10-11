package com.example.buoi6.data;


import com.example.buoi6.model.Location;
import com.example.buoi6.model.Weather;
import com.example.buoi6.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONWeatherParser {
    public static Weather getWeather(String data) throws JSONException {
        Weather weather = new Weather();
        JSONObject jObj = new JSONObject(data);
        Location loc = new Location();

        //Get sys
        JSONObject sysObj = Utils.getObject("sys", jObj);
        loc.setCountryName(Utils.getString("country", sysObj));
        loc.setCityName(Utils.getString("name", jObj));
        weather.location = loc;

        //Get main
        JSONObject mainObj = Utils.getObject("main", jObj);
        weather.statePresent.setMoisture(Utils.getInt("humidity", mainObj));
        weather.statePresent.setAlmosphericPressure(Utils.getInt("pressure", mainObj));
        weather.temperature.setTp(Utils.getFloat("temp", mainObj));

        //Get Wind
        JSONObject windObj = Utils.getObject("wind", jObj);
        weather.wind.setSpeed(Utils.getFloat("speed", windObj));


        //Get Image
        JSONArray jArr = jObj.getJSONArray("weather");
        JSONObject JsonWeather = jArr.getJSONObject(0);
        weather.statePresent.setImageName(Utils.getString("icon", JsonWeather));
        return weather;
    }

    public String getWeatherData(String location) {
        HttpURLConnection con = null;
        InputStream is = null;
        try {
            con = (HttpURLConnection) (new URL(Utils.BASE_URL + location)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");
            is.close();
            con.disconnect();
            return buffer.toString();
        } catch (Throwable t) {

            t.printStackTrace();
        } finally {
            try {
                con.disconnect();
            } catch (Throwable t) {

            }
        }
        return null;
    }

}
