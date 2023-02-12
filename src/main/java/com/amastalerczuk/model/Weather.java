package com.amastalerczuk.model;

public class Weather {
    private String cityName;
    private double tempInCelsius;
    private double tempMax;
    private double tempMin;
    private double humidity;
    private double wind;
    private double pressure;
    private String weatherDescription;
    private String iconLink;
    private double totalPrecip;
    private double totalSnow;

    public Weather(String cityName, double temperature, double humidity, double wind, double pressure, String weatherDescription, String iconLink) {
        this.cityName = cityName;
        this.tempInCelsius = temperature;
        this.humidity = humidity;
        this.wind = wind;
        this.pressure = pressure;
        this.weatherDescription = weatherDescription;
        this.iconLink = iconLink;
    }

    public Weather(String cityName, String weatherDescription, String iconLink, double tempMax, double tempMin, double humidity, double wind, double totalPrecip, double totalSnow){
        this.cityName = cityName;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.humidity = humidity;
        this.wind = wind;
        this.weatherDescription = weatherDescription;
        this.iconLink = iconLink;
        this.totalPrecip = totalPrecip;
        this.totalSnow = totalSnow;
    }

    public String getCityName() {
        return cityName;
    }

    public double getTempInCelsius() {
        return Math.round(tempInCelsius);
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getTempMin() {
        return tempMin;
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

    public double getTotalPrecip() {
        return totalPrecip;
    }

    public double getTotalSnow() {
        return totalSnow;
    }

    public String getWeatherDescription() {
        return weatherDescription;

    }
    public String getIconLink(){
        return iconLink;
    }

}
