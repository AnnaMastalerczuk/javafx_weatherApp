package com.amastalerczuk.model;

import java.time.LocalDate;

public class Weather {
    private String cityName;
    private double tempInCelsius;
    private double humidity;
    private double wind;
    private String weatherDescription;
    private LocalDate date;

    public Weather(String cityName) {
        this.cityName = cityName;
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

    public LocalDate getDate() {
        return date;
    }

}
