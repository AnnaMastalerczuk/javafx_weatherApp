package com.amastalerczuk.model.client;

import com.amastalerczuk.model.Weather;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface WeatherClient {
//    Weather getWeather(String cityName);
    Weather getCurrentWeather(String cityName) throws ParseException;
    List<Weather> getFutureWeather(String cityName) throws ParseException;
}
