package com.example.r30_a.weatheractivity.WeatherData;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CityWeather implements Serializable {

    private City city;
    @SerializedName("list")//使用序列化tag可以讓讀取資料時更快找到相對應的資料，並對到資料變數上
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
