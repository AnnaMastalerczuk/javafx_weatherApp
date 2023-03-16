package com.amastalerczuk.model.client;

import com.amastalerczuk.model.Weather;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyAPIWeatherClientTest {

    @Mock
    private APIConnector apiConnector;
    private MyAPIWeatherClient myAPIWeatherClient;

    @BeforeEach
    void setUp(){
        myAPIWeatherClient = new MyAPIWeatherClient(apiConnector);
    }

    @Test
    void shouldGetCurrentWeatherReturnWeather() throws IOException, ParseException {
        //given
        when(apiConnector.fetchDataFromAPI(anyString())).thenReturn(getCurrentWeatherFromJson());

        //when
        Weather weather = myAPIWeatherClient.getCurrentWeather("London");

        //then
        assertThat(weather.getCityName(), equalTo("London"));
        assertThat(weather.getHumidity(), equalTo(66.0));
        assertThat(weather.getTempInCelsius(), equalTo(7.0));
    }

    @Test
    void shouldGetCurrentWeatherThrowExceptionWrongCityNameNullJson(){
        //given
        when(apiConnector.fetchDataFromAPI(anyString())).thenReturn(null);

        //when then
        assertThrows(NullPointerException.class, () -> myAPIWeatherClient.getCurrentWeather("city"));
    }

    @Test
    void shouldGetCurrentWeatherThrowExceptionBrokenJson() throws IOException {
        //given
        when(apiConnector.fetchDataFromAPI(anyString())).thenReturn(getCurrentWeatherFromBrokenJson());

        //when then
        assertThrows(NullPointerException.class, () -> myAPIWeatherClient.getCurrentWeather("London"));
    }

    @Test
    void shouldGetFutureWeatherReturnWeather() throws IOException, ParseException {
        //given
        when(apiConnector.fetchDataFromAPI(anyString())).thenReturn(getFutureWeatherFromJson());

        //when
        List<Weather> weathers = myAPIWeatherClient.getFutureWeather("London");

        //then
        assertThat(weathers.get(0).getTempMax(), equalTo(13.1));
        assertThat(weathers.get(0).getDate(), equalTo("2023-03-16"));
        assertThat(weathers.get(1).getTempMax(), equalTo(13.4));
        assertThat(weathers.get(1).getDate(), equalTo("2023-03-17"));
        assertThat(weathers, hasSize(2));
    }

    @Test
    void shouldGetFutureWeatherThrowExceptionWrongCityNameNullJson() {
        //given
        when(apiConnector.fetchDataFromAPI(anyString())).thenReturn(null);

        //when then
        assertThrows(NullPointerException.class, () -> myAPIWeatherClient.getFutureWeather("city"));
    }

    @Test
    void shouldGetFutureWeatherThrowExceptionBrokenJson() throws IOException {
        //given
        when(apiConnector.fetchDataFromAPI(anyString())).thenReturn(getFutureWeatherFromBrokenJson());

        //when then
        assertThrows(ParseException.class, () -> myAPIWeatherClient.getFutureWeather("London"));
    }

    @Test
    void currentWeatherLinkContainsOneWordName(){
        //given
        String name = "London";

        //when
        String link = myAPIWeatherClient.setCurrentWeatherLink(name);

        //then
        assertThat(link, containsString(name));
    }

    @Test
    void currentWeatherLinkContainsTwoWordName(){
        //given
        String name = "Los Angeles";
        String nameToLink = "Los%20Angeles";

        //when
        String link = myAPIWeatherClient.setCurrentWeatherLink(name);

        //then
        assertThat(link, containsString(nameToLink));
    }

    @Test
    void futureWeatherLinkContainsOneWordName(){
        //given
        String name = "London";

        //when
        String link = myAPIWeatherClient.setFutureWeatherLink(name);

        //then
        assertThat(link, containsString(name));
    }

    @Test
    void futureWeatherLinkContainsTwoWordName(){
        //given
        String name = "Los Angeles";
        String nameToLink = "Los%20Angeles";

        //when
        String link = myAPIWeatherClient.setFutureWeatherLink(name);

        //then
        assertThat(link, containsString(nameToLink));
    }


    private String getCurrentWeatherFromJson() throws IOException {
        return new String(Files.readAllBytes(Path.of("src/test/resources/currentWeather.json")));
    }

    private String getCurrentWeatherFromBrokenJson() throws IOException {
        return new String(Files.readAllBytes(Path.of("src/test/resources/currentWeatherBroken.json")));
    }

    private String getFutureWeatherFromJson() throws IOException {
        return new String(Files.readAllBytes(Path.of("src/test/resources/futureWeather.json")));
    }
    private String getFutureWeatherFromBrokenJson() throws IOException {
        return new String(Files.readAllBytes(Path.of("src/test/resources/futureWeatherBroken.json")));
    }


}