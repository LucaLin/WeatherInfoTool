package com.example.r30_a.weatheractivity.WeatherData;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CityWeather implements Serializable {

    private City city;
    @SerializedName("list")
    private List<WeatherData> weatherData;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<WeatherData> getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(List<WeatherData> weatherData) {
        this.weatherData = weatherData;
    }
}
