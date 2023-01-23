package com.amastalerczuk.model.client;

import com.amastalerczuk.model.Weather;

public class MyWeatherClient implements WeatherClient{
    @Override
    public Weather getWeather(String cityName) {
        return new Weather(cityName);
    }
}
