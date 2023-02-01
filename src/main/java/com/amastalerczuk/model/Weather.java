package com.amastalerczuk.model;

import java.util.Date;

public class Weather {
    private String cityName;
    private double tempInCelsius;
    private double humidity;
    private double wind;
    private double pressure;
    private String weatherDescription;
    private String iconLink;

    public Weather(String cityName, double temperature, double humidity, double wind, double pressure, String weatherDescription, String iconLink) {
        this.cityName = cityName;
        this.tempInCelsius = temperature;
        this.humidity = humidity;
        this.wind = wind;
        this.pressure = pressure;
        this.weatherDescription = weatherDescription;
        this.iconLink = iconLink;
    }

//    private String changeDateIntoString(Date date){
//        dateString = date.getDate();
//    }

    public String getCityName() {
        return cityName;
    }

    public double getTempInCelsius() {
        return Math.round(tempInCelsius);
    }

    public double getHumidity() {
        return Math.round(humidity);
    }

    public double getWind() {
        return Math.round(wind);
    }

    public double getPressure() {
        return pressure;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getIconLink(){
        return iconLink;
    }




}
