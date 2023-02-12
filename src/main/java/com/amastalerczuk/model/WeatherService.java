package com.amastalerczuk.model;

import com.amastalerczuk.model.client.WeatherClient;
import org.json.simple.parser.ParseException;

import java.util.List;

public class WeatherService {
    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

        public Weather getCurrentWeather(String cityName) throws ParseException {
        return weatherClient.getCurrentWeather(cityName);
    }

    public List<Weather> getFutureWeather(String cityName) throws ParseException {
        return weatherClient.getFutureWeather(cityName);
    }
}
