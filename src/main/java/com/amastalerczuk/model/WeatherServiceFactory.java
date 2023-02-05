package com.amastalerczuk.model;

import com.amastalerczuk.model.client.MyAPIWeatherClient;
//import com.amastalerczuk.model.client.MyWeatherClient;
import com.amastalerczuk.model.client.WeatherClient;

public class WeatherServiceFactory {

//    public static WeatherService createWeatherService(){
//        return new WeatherService(createWeatherClient());
//    }
//
//    private static WeatherClient createWeatherClient(){
//        return new MyWeatherClient();
//    }
public static WeatherService createWeatherService(){
    return new WeatherService(createWeatherClient());
}

    private static WeatherClient createWeatherClient(){
        return new MyAPIWeatherClient();
    }
}
