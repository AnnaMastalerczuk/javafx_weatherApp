package com.amastalerczuk.model;

import com.amastalerczuk.model.client.WeatherClient;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class WeatherServiceTest {

    @Test
    void shouldReturnCurrentWeatherForecast() throws ParseException {
        //given
        WeatherClient weatherClient = mock();
        WeatherService weatherService = new WeatherService(weatherClient);

        String city = "City";
        Weather expectedWeather = MyAPIWeatherClientStub.getCurrentWeather(city);
        when(weatherClient.getCurrentWeather(city))
                .thenReturn(expectedWeather);
        //when
        Weather currentWeather = weatherService.getCurrentWeather(city);

        //then
        assertThat(currentWeather, sameInstance(expectedWeather));
        assertThat(currentWeather.getCityName(), equalTo(city));
        assertThat(currentWeather.getHumidity(), equalTo(10.0));
    }

    @Test
    void shouldThrowExceptionCurrentWeatherForecast() throws ParseException {
        //given
        WeatherClient weatherClient = mock();
        WeatherService weatherService = new WeatherService(weatherClient);

        String city = "City";

        //when
        when(weatherClient.getCurrentWeather(city)).thenThrow(new ParseException(401));

        // then
        assertThrows(RuntimeException.class, () -> weatherService.getCurrentWeather(city));
    }

    @Test
    void shouldReturnFutureWeatherForecast() throws ParseException {
        //given
        WeatherClient weatherClient = mock();
        WeatherService weatherService = new WeatherService(weatherClient);

        String city = "City";
        List<Weather> expectedWeather = MyAPIWeatherClientStub.getFutureWeather(city);
        when(weatherClient.getFutureWeather(city))
                .thenReturn(expectedWeather);
        //when
        List<Weather> currentWeather = weatherService.getFutureWeather(city);

        //then
        assertThat(currentWeather, sameInstance(expectedWeather));
        assertThat(currentWeather.get(0).getHumidity(), equalTo(11.0));
        assertThat(currentWeather.get(1).getHumidity(), equalTo(12.0));
    }

    @Test
    void shouldThrowExceptionFutureWeatherForecast() throws ParseException {
        //given
        WeatherClient weatherClient = mock();
        WeatherService weatherService = new WeatherService(weatherClient);

        String city = "City";

        //when
        when(weatherClient.getFutureWeather(city)).thenThrow(new ParseException(401));

        // then
        assertThrows(RuntimeException.class, () -> weatherService.getFutureWeather(city));
    }


}