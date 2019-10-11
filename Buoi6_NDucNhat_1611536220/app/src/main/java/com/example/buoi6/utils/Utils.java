package com.example.buoi6.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String IMG_URL = "http://openweathermap.org/img/w/";
    public static JSONObject getObject(String tagName, JSONObject jObj)
            throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return  subObj;
    }
    public static  String getString(String tagName, JSONObject jObj)
            throws JSONException{
        return  jObj.getString(tagName);
    }
    public static  Float getFloat(String tagName, JSONObject jObj)
            throws JSONException{
        return  (float)jObj.getDouble(tagName);
    }
    public static  int getInt(String tagName, JSONObject jObj)
            throws JSONException{
        return  jObj.getInt(tagName);
    }

}
