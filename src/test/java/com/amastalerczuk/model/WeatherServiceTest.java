package com.amastalerczuk.model;

import com.amastalerczuk.model.client.WeatherClient;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {
    @Mock
    private WeatherClient weatherClient;
    private WeatherService weatherService;

    @BeforeEach
    void setUp(){
        weatherService = new WeatherService(weatherClient);
    }
    @Test
    void shouldReturnCurrentWeatherForecast() throws ParseException {
        //given
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
        String city = "City";

        //when
        when(weatherClient.getCurrentWeather(city)).thenThrow(new ParseException(401));

        // then
        assertThrows(RuntimeException.class, () -> weatherService.getCurrentWeather(city));
    }

    @Test
    void shouldReturnFutureWeatherForecast() throws ParseException {
        //given
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
        String city = "City";

        //when
        when(weatherClient.getFutureWeather(city)).thenThrow(new ParseException(401));

        // then
        assertThrows(RuntimeException.class, () -> weatherService.getFutureWeather(city));
    }
}