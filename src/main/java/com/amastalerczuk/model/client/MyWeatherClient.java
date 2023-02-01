package com.amastalerczuk.model.client;

import com.amastalerczuk.model.Weather;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;

import java.time.LocalDate;
import java.util.Date;

import static com.amastalerczuk.config.Config.API_KEY;

public class MyWeatherClient implements WeatherClient{

    OWM owm = new OWM(API_KEY);
    private double temperature;
    private double humidity;
    private double wind;
    private Date date;

    public MyWeatherClient() {
        owm.setUnit(OWM.Unit.METRIC);
        owm.setLanguage(OWM.Language.POLISH);
    }

    @Override
    public Weather getWeather(String cityName) {
        try {
            CurrentWeather cwd = owm.currentWeatherByCityName(cityName);
            temperature = cwd.getMainData().getTempMax();
            humidity = cwd.getMainData().getHumidity();
            wind = cwd.getWindData().getSpeed();
            date = cwd.getDateTime();
        } catch (APIException e) {
            throw new RuntimeException(e);
        }

        return new Weather(cityName, temperature, humidity, wind, date);
    }

}
