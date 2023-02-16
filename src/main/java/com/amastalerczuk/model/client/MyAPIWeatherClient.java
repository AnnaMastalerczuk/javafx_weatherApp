package com.amastalerczuk.model.client;

import com.amastalerczuk.config.Config;
import com.amastalerczuk.model.Weather;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class MyAPIWeatherClient implements WeatherClient{
    private APIConnector apiConnector;
    private String datasFromApi;
    private String cityName;
    private String futureWeatherLink;
    private String currentWeatherLink;

    public MyAPIWeatherClient() {
        apiConnector = new APIConnector();
    }

    public void setCurrentWeatherLink(String cityName) {
        if (cityName.contains(" ")) {
            String newName;
            newName = cityName.replace(" ", "%20");
            this.currentWeatherLink = InfoApi.linkCurrentWeatherString + Config.WEATHERAPI_KEY + "&lang=" + InfoApi.language + "&q=" + newName + "&aqi=no";
        } else {
            this.currentWeatherLink = InfoApi.linkCurrentWeatherString + Config.WEATHERAPI_KEY + "&lang=" + InfoApi.language + "&q=" + cityName + "&aqi=no";
        }
    }

    public void setFutureWeatherLink(String cityName) {
        if (cityName.contains(" ")) {
            String newName;
            newName = cityName.replace(" ", "%20");
            this.futureWeatherLink = InfoApi.linkFutureWeatherString + Config.WEATHERAPI_KEY + "&lang=" + InfoApi.language + "&q="+ newName + "&days=" + InfoApi.daysNumber + "&aqi=no&alerts=no";
        } else {
            this.futureWeatherLink = InfoApi.linkFutureWeatherString + Config.WEATHERAPI_KEY + "&lang=" + InfoApi.language + "&q=" + cityName + "&days=" + InfoApi.daysNumber + "&aqi=no&alerts=no";
        }
    }

    public Weather getCurrentWeather(String cityName) throws ParseException {
        this.cityName = cityName;
        setCurrentWeatherLink(cityName);
        datasFromApi = apiConnector.fetchDataFromAPI(currentWeatherLink);
        return parseCurrentWeather(datasFromApi);
    }

    public List<Weather> getFutureWeather(String cityName) throws ParseException {
        this.cityName = cityName;
        setFutureWeatherLink(cityName);
        datasFromApi = apiConnector.fetchDataFromAPI(futureWeatherLink);
        return parseNextWeather(datasFromApi);
    }

    public Weather parseCurrentWeather(String responseBody) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        Object javaObject = jsonParser.parse(responseBody);
        JSONObject jsonObject = (JSONObject) javaObject;
        JSONObject jsonObjectCurrentWeather = (JSONObject) jsonObject.get("current");
        JSONObject jsonObjectIcon = (JSONObject) jsonObjectCurrentWeather.get("condition");

        double temp = (double) jsonObjectCurrentWeather.get("temp_c");
        long humidity = (long) jsonObjectCurrentWeather.get("humidity");
        double wind = (double) jsonObjectCurrentWeather.get("wind_kph");
        double pressure = (double) jsonObjectCurrentWeather.get("pressure_mb");
        String iconLink = "http:" + jsonObjectIcon.get("icon");
        String description = (String) jsonObjectIcon.get("text");

        return new Weather(cityName, temp, humidity, wind, pressure, description, iconLink);
    }

    public List<Weather> parseNextWeather(String responseBody) throws ParseException {
        List<Weather> weatherForecastList = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        Object javaObject = jsonParser.parse(responseBody);
        JSONObject jsonObject = (JSONObject) javaObject;
        JSONObject jsonObjectFutureWeather = (JSONObject) jsonObject.get("forecast");
        JSONArray forecastArray = (JSONArray) jsonObjectFutureWeather.get("forecastday");

        for (int i = 1; i < forecastArray.size(); i ++){
            JSONObject weatherForecastObj = (JSONObject) forecastArray.get(i);
            JSONObject forecastDatas = (JSONObject) weatherForecastObj.get("day");
            String date = (String) weatherForecastObj.get("date");
            double tempMax = (double) forecastDatas.get("maxtemp_c");
            double tempMin = (double) forecastDatas.get("mintemp_c");
            double humidity = (double) forecastDatas.get("avghumidity");
            double wind = (double) forecastDatas.get("maxwind_kph");
            double totalPrecip = (double) forecastDatas.get("totalprecip_mm");
            double totalsnow = (double) forecastDatas.get("totalsnow_cm");

            JSONObject conditions = (JSONObject) forecastDatas.get("condition");
            String iconLink = "http:" + (String) conditions.get("icon");
            String description = (String) conditions.get("text");

            Weather weather = new Weather(date, cityName, description, iconLink, tempMax, tempMin, humidity, wind, totalPrecip, totalsnow);
            weatherForecastList.add(weather);
        }
        return weatherForecastList;
    }
}
