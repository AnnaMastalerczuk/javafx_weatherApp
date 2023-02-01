package com.amastalerczuk.model;

import java.time.LocalDate;
import java.util.Date;

public class Weather {
    private String cityName;
    private double tempInCelsius;
    private double humidity;
    private double wind;
    private String weatherDescription;
    private Date date;

    public Weather(String cityName, double temperature, double humidity, double wind, Date date) {
        this.cityName = cityName;
        this.tempInCelsius = temperature;
        this.humidity = humidity;
        this.wind = wind;
        this.date = date;
    }

    public String getCityName() {
        return cityName;
    }

    public double getTempInCelsius() {
        return tempInCelsius;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWind() {
        return wind;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public Date getDate() {
        return date;
    }

}
