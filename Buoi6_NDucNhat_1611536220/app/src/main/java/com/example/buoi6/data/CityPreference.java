package com.example.buoi6.data;

import android.app.Activity;
import android.content.SharedPreferences;

public class CityPreference {
    SharedPreferences preferences;

    public CityPreference(Activity activity)
    {
        preferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public  String getCity()
    {
        return preferences.getString("city","hanoi,vn");
    }
    public void  setCity(String city)
    {
          preferences.edit().putString("city",city).commit();
    }
}
