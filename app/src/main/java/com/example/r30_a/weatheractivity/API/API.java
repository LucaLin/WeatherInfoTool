package com.example.r30_a.weatheractivity.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MartinRuiz on 8/20/2017.
 */
public class API {
    //氣象資料API網址與key，目前只能接收羅馬拼音的資料
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String KEY = "79badf94102e008963c2d50b6cfa43f2";

    private static Retrofit retrofit = null;

    public static Retrofit getApi(){
        if(retrofit == null){
            retrofit =new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
