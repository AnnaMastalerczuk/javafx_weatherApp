package com.amastalerczuk.model.client;

import com.amastalerczuk.model.Weather;

public interface WeatherClient {
    Weather getWeather(String cityName);
}
