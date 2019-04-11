package com.example.r30_a.weatheractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.r30_a.weatheractivity.Utils.IconProvider;
import com.example.r30_a.weatheractivity.WeatherData.CityWeather;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class WeatherDetailActivity extends AppCompatActivity {
    //    氣象資訊卡資料
    private TextView txvCard_cityName;
    private TextView txvCard_description;
    private TextView txvCard_currentTemp;
    private TextView txvCard_maxTemp;
    private TextView txvCard_minTemp;
    private ImageView img_cardWeatherIcon;

    //    一周天氣資料
    private TextView txvDay1, txvDay2, txvDay3, txvDay4, txvDay5;
    private ImageView img_icon1, img_icon2, img_icon3, img_icon4, img_icon5;
    private TextView txvMaxTemp1, txvMaxTemp2, txvMaxTemp3, txvMaxTemp4, txvMaxTemp5;
    private TextView txvMinTemp1, txvMinTemp2, txvMinTemp3, txvMinTemp4, txvMinTemp5;

    //    氣象細節資料
    private TextView txvhumidity, txvhumidityData;
    private TextView txvSpeed, txvSpeedData;
    private TextView txvCloud, txvCloudData;
    private TextView txvPressure, txvPressureData;
    private ImageView img_humidity, img_speed, img_cloud, img_pressure;

    private CityWeather cityWeather;
    String[] namesOfDays = {
            "SAT", "SUN", "MON", "TUE", "WED", "THU", "FRI",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        initView();

        Bundle bundle = getIntent().getExtras();
        if (!bundle.isEmpty()) {
            cityWeather = (CityWeather) bundle.getSerializable("city");
            setCardData();
        }
    }

    private void setCardData() {
        txvCard_cityName.setText(cityWeather.getCity().getName() + ", " + cityWeather.getCity().getCountry());
        txvCard_description.setText(cityWeather.getWeatherData().get(0).getWeatherDetails().get(0).getLongDescription());
        txvCard_currentTemp.setText((int) cityWeather.getWeatherData().get(0).getTemp().getDay() + "°c");
        txvCard_maxTemp.setText((int) cityWeather.getWeatherData().get(0).getTemp().getMax() + "°c");
        txvCard_minTemp.setText((int) cityWeather.getWeatherData().get(0).getTemp().getMin() + "°c");


        txvhumidityData.setText((int) cityWeather.getWeatherData().get(0).getHumidity() + "%");
        txvSpeedData.setText((int) cityWeather.getWeatherData().get(0).getSpeed() + " m/s");
        txvCloudData.setText((int) cityWeather.getWeatherData().get(0).getClouds() + "%");
        txvPressureData.setText((int) cityWeather.getWeatherData().get(0).getPressure() + " hPa");

        String weatherStatus = cityWeather.getWeatherData().get(0).getWeatherDetails().get(0).getShortDescription();
        img_cardWeatherIcon.setBackgroundResource(IconProvider.getImageIcon(weatherStatus));

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();

        for (int i = 1; i < cityWeather.getWeatherData().size(); i++) {
            calendar.setTime(date);
            String day = namesOfDays[(calendar.get(Calendar.DAY_OF_WEEK) + i) % 7];

            switch (i) {
                case 0:
                    break;
                case 1:
//                    txvDay1.setText(day);
//                    weeklyDescription = cityWeather.getWeatherData().get(i).getWeatherDetails().get(i).getShortDescription();
//                    img_icon1.setBackgroundResource(IconProvider.getImageIcon(weeklyDescription));
//                    txvMinTemp1.setText((int) cityWeather.getWeatherData().get(i).getTemp().getMax() + "°C");
//                    txvMinTemp1.setText((int) cityWeather.getWeatherData().get(i).getTemp().getMin() + "°C");
                    setDailyInfo(1,txvDay1,day,img_icon1,txvMaxTemp1,txvMinTemp1);
                    break;
                case 2:
//                    txvDay2.setText(day);
//                    weeklyDescription = cityWeather.getWeatherData().get(i).getWeatherDetails().get(i).getShortDescription();
//                    img_icon2.setBackgroundResource(IconProvider.getImageIcon(weeklyDescription));
//                    txvMinTemp2.setText((int) cityWeather.getWeatherData().get(i).getTemp().getMax() + "°C");
//                    txvMinTemp2.setText((int) cityWeather.getWeatherData().get(i).getTemp().getMin() + "°C");
                    setDailyInfo(2,txvDay2,day,img_icon2,txvMaxTemp2,txvMinTemp2);
                    break;
                case 3:
//                    txvDay3.setText(day);
//                    weeklyDescription = cityWeather.getWeatherData().get(i).getWeatherDetails().get(i).getShortDescription();
//                    img_icon3.setBackgroundResource(IconProvider.getImageIcon(weeklyDescription));
//                    txvMinTemp3.setText((int) cityWeather.getWeatherData().get(i).getTemp().getMax() + "°C");
//                    txvMinTemp3.setText((int) cityWeather.getWeatherData().get(i).getTemp().getMin() + "°C");
                    setDailyInfo(3,txvDay3,day,img_icon3,txvMaxTemp3,txvMinTemp3);
                    break;
                case 4:
//                    txvDay4.setText(day);
//                    weeklyDescription = cityWeather.getWeatherData().get(i).getWeatherDetails().get(i).getShortDescription();
//                    img_icon4.setBackgroundResource(IconProvider.getImageIcon(weeklyDescription));
//                    txvMinTemp4.setText((int) cityWeather.getWeatherData().get(i).getTemp().getMax() + "°C");
//                    txvMinTemp4.setText((int) cityWeather.getWeatherData().get(i).getTemp().getMin() + "°C");
                    setDailyInfo(4,txvDay4,day,img_icon4,txvMaxTemp4,txvMinTemp4);
                    break;
                case 5:
//                    txvDay5.setText(day);
//                    weeklyDescription = cityWeather.getWeatherData().get(i).getWeatherDetails().get(i).getShortDescription();
//                    img_icon5.setBackgroundResource(IconProvider.getImageIcon(weeklyDescription));
//                    txvMinTemp5.setText((int) cityWeather.getWeatherData().get(i).getTemp().getMax() + "°C");
//                    txvMinTemp5.setText((int) cityWeather.getWeatherData().get(i).getTemp().getMin() + "°C");
                    setDailyInfo(5,txvDay5,day,img_icon5,txvMaxTemp5,txvMinTemp5);
                    break;

            }
        }

    }
    public void setDailyInfo(int index, TextView txvday, String day, ImageView imgIcon, TextView txvMaxTemp, TextView txvMinTemp){
        txvday.setText(day);
        String weeklyDescription = cityWeather.getWeatherData().get(index).getWeatherDetails().get(0).getShortDescription();
        imgIcon.setBackgroundResource(IconProvider.getImageIcon(weeklyDescription));
        txvMaxTemp.setText((int) cityWeather.getWeatherData().get(index).getTemp().getMax() + "°C");
        txvMinTemp.setText((int) cityWeather.getWeatherData().get(index).getTemp().getMin() + "°C");

    }

    private void initView() {
        txvCard_cityName = (TextView) findViewById(R.id.txvCard_cityname);
        txvCard_description = (TextView) findViewById(R.id.txvCard_description);
        txvCard_currentTemp = (TextView) findViewById(R.id.txvCard_currentTemp);
        txvCard_maxTemp = (TextView) findViewById(R.id.txvCard_maxTemp);
        txvCard_minTemp = (TextView) findViewById(R.id.txvCard_minTemp);
        img_cardWeatherIcon = (ImageView) findViewById(R.id.img_weather);

        txvDay1 = (TextView) findViewById(R.id.txvDay1);
        txvDay2 = (TextView) findViewById(R.id.txvDay2);
        txvDay3 = (TextView) findViewById(R.id.txvDay3);
        txvDay4 = (TextView) findViewById(R.id.txvDay4);
        txvDay5 = (TextView) findViewById(R.id.txvDay5);

        img_icon1 = (ImageView) findViewById(R.id.img_icon1);
        img_icon2 = (ImageView) findViewById(R.id.img_icon2);
        img_icon3 = (ImageView) findViewById(R.id.img_icon3);
        img_icon4 = (ImageView) findViewById(R.id.img_icon4);
        img_icon5 = (ImageView) findViewById(R.id.img_icon5);

        txvMaxTemp1 = (TextView) findViewById(R.id.txvMaxTemp1);
        txvMaxTemp2 = (TextView) findViewById(R.id.txvMaxTemp2);
        txvMaxTemp3 = (TextView) findViewById(R.id.txvMaxTemp3);
        txvMaxTemp4 = (TextView) findViewById(R.id.txvMaxTemp4);
        txvMaxTemp5 = (TextView) findViewById(R.id.txvMaxTemp5);

        txvMinTemp1 = (TextView) findViewById(R.id.txvMinTemp1);
        txvMinTemp2 = (TextView) findViewById(R.id.txvMinTemp2);
        txvMinTemp3 = (TextView) findViewById(R.id.txvMinTemp3);
        txvMinTemp4 = (TextView) findViewById(R.id.txvMinTemp4);
        txvMinTemp5 = (TextView) findViewById(R.id.txvMinTemp5);

//        txvhumidity = (TextView)findViewById(R.id.txvHumidity);
        txvhumidityData = (TextView) findViewById(R.id.txvHumidityData);
//        txvSpeed = (TextView)findViewById(R.id.txvSpeed);
        txvSpeedData = (TextView) findViewById(R.id.txvSpeedData);
//        txvCloud = (TextView)findViewById(R.id.txvCloud);
        txvCloudData = (TextView) findViewById(R.id.txvCloudData);
//        txvPressure = (TextView)findViewById(R.id.txvPressure);
        txvPressureData = (TextView) findViewById(R.id.txvPressureData);

        img_humidity = (ImageView) findViewById(R.id.img_humidity);
        img_speed = (ImageView) findViewById(R.id.img_Speed);
        img_cloud = (ImageView) findViewById(R.id.img_Cloud);
        img_pressure = (ImageView) findViewById(R.id.img_Pressure);


    }
}
