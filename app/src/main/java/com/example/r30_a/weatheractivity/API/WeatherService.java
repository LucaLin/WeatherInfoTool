package com.example.r30_a.weatheractivity.API;

import com.example.r30_a.weatheractivity.WeatherData.CityWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("forecast/daily")
    Call<CityWeather> getWeatherCity(@Query("q") String city, @Query("APPID")String key,@Query("units") String units ,@Query("cnt") int days);
}
