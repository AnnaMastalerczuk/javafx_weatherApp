package com.amastalerczuk.model;

import java.util.ArrayList;
import java.util.List;

class MyAPIWeatherClientStub {
    private String cityName;

    public static Weather getCurrentWeather(String cityName){
//        this.cityName = cityName;
        return parseCurrentWeather(cityName);
//        return new Weather(cityName, 10, 10, 10, 10, "text", "text");
    }

    public static List<Weather> getFutureWeather(String cityName){
        return parseNextWeather(cityName);
    }

    private static Weather parseCurrentWeather(String cityName){
        double temp = 10;
        long humidity = 10;
        double wind = 10;
        double pressure = 10;
        String iconLink = "test";
        String description = "test";

        return new Weather(cityName, temp, humidity, wind, pressure, description, iconLink);
    }

    private static List<Weather> parseNextWeather(String cityName) {
        List<Weather> weatherForecastList = new ArrayList<>();

        for (int i = 1; i < 3; i ++){
            double tempMax = 10 + i;
            double tempMin = 10 + i;
            double humidity = 10 + i;
            double wind = 10 + i;
            double totalPrecip = 10 + i;
            double totalsnow = 10 + i;

            String date = "data " + i;
            String iconLink = "test " + i;
            String description = "test " + i;

            Weather weather = new Weather(date, cityName, description, iconLink, tempMax, tempMin, humidity, wind, totalPrecip, totalsnow);
            weatherForecastList.add(weather);
        }
        return weatherForecastList;
    }
}
