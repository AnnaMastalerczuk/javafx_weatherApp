package com.amastalerczuk.model;

import com.amastalerczuk.model.client.WeatherClient;
import org.json.simple.parser.ParseException;

import java.util.List;

public class WeatherService {
    private final WeatherClient weatherClient;
    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }
    public Weather getCurrentWeather(String cityName){
        try {
            return weatherClient.getCurrentWeather(cityName);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Weather> getFutureWeather(String cityName){
        try {
            return weatherClient.getFutureWeather(cityName);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
