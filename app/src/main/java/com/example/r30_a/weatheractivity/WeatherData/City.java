package com.example.r30_a.weatheractivity.WeatherData;

import java.io.Serializable;
//使用序列化可將datamodel內存的對象保存到文件中或數據庫中，在存取或讀取時能更快速獲取正確的資料或易於網路傳送
public class City implements Serializable {

    private String name;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
