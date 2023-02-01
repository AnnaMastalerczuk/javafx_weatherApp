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
        return setDescriptionIntoPolish(weatherDescription);
    }

    public String getIconLink(){
        return iconLink;
    }

    public String setDescriptionIntoPolish(String text){
        String textInPolish = null;

        switch (text){
            case "clear sky": {
                textInPolish = "bezchmurnie";
                break;
            }
            case "few clouds": {
                textInPolish = "prawie bezchmurnie";
                break;
            }
            case "scattered clouds": {
                textInPolish = "lekkie zachmurzenie";
                break;
            }
            case "broken clouds": {
                textInPolish = "duże zachmurzenie";
                break;
            }
            case "overcast clouds": {
                textInPolish = "duże zachmurzenie";
                break;
            }
            case "shower rain": {
                textInPolish = "lekki deszcz";
                break;
            }
            case "rain": {
                textInPolish = "deszcz";
                break;
            }
            case "thunderstorm": {
                textInPolish = "burza";
                break;
            }
            case "snow": {
                textInPolish = "śnieg";
                break;
            }
            case "mist": {
                textInPolish = "gła";
                break;
            }
            default: break;
        }
        return textInPolish;
    }


}
