package com.example.buoi6.model;

import java.io.Serializable;

public class Location implements Serializable  {
    private static  final  long serialVersion = 1L;
    private String CountryName;
    private String CityName;

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }
}

