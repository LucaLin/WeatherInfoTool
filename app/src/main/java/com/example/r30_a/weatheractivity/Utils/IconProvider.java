package com.example.r30_a.weatheractivity.Utils;

import com.example.r30_a.weatheractivity.R;

public class IconProvider {

    public static int getImageIcon(String weatherDescription){
        int weatherIcon ;
        switch(weatherDescription) {
            case "Thunderstorm":
                weatherIcon = R.drawable.icons_thunderstorm;
                break;
            case "Drizzle":
                weatherIcon = R.drawable.icons_drizzle;
                break;
            case "Rain":
                weatherIcon = R.drawable.icons_rain;
                break;
            case "Snow":
                weatherIcon = R.drawable.icons_snow;
                break;
//            case "Atmosphere":
//                weatherIcon = R.mipmap.ic_atmosphere;
//                break;
//            case "Clear":
//                weatherIcon = R.mipmap.ic_clear;
//                break;
            case "Clouds":
                weatherIcon = R.drawable.icons_cloudy;
                break;
//            case "Extreme":
//                weatherIcon = R.mipmap.ic_extreme;
//                break;
            default:
                weatherIcon = R.drawable.ic_clear;
        }
        return weatherIcon;

    }
}
