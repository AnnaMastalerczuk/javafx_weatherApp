package com.amastalerczuk.model;

import java.util.Date;

public class Weather {
    private String cityName;
    private double tempInCelsius;
    private double humidity;
    private double wind;
    private double pressure;
    private String weatherDescription;

    public Weather(String cityName, double temperature, double humidity, double wind, double pressure) {
        this.cityName = cityName;
        this.tempInCelsius = temperature;
        this.humidity = humidity;
        this.wind = wind;
        this.pressure = pressure;
    }

//    private String changeDateIntoString(Date date){
//        dateString = date.getDate();
//    }

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

    public double getPressure() {
        return pressure;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }



}
