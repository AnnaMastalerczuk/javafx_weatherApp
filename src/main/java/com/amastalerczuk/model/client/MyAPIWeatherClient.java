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
    private APIConnector apiConnector = new APIConnector();
    private List<Weather> weatherForecastList = new ArrayList<>();
    private String datasFromApi;
    private String cityName;
    private String futureWeatherLink;
    private String currentWeatherLink;

    public MyAPIWeatherClient() {
    }

    public void setCurrentWeatherLink(String cityName) {
        if (cityName.contains(" ")) {
            String newName;
            newName = cityName.replace(" ", "%20");
            this.currentWeatherLink = "http://api.weatherapi.com/v1/current.json?key=" + Config.WEATHERAPI_KEY + "&q=" + newName + "&aqi=no";
        } else {
            this.currentWeatherLink = "http://api.weatherapi.com/v1/current.json?key=" + Config.WEATHERAPI_KEY + "&q=" + cityName + "&aqi=no";
        }
    }

    public void setFutureWeatherLink(String cityName) {
        if (cityName.contains(" ")){
            String newName;
            newName = cityName.replace(" ", "%20");
            this.futureWeatherLink = "http://api.weatherapi.com/v1/forecast.json?key=" + Config.WEATHERAPI_KEY +"&q="+ newName + "&days=5&aqi=no&alerts=no";
        }
        this.futureWeatherLink = "http://api.weatherapi.com/v1/forecast.json?key=" + Config.WEATHERAPI_KEY +"&q="+ cityName + "&days=5&aqi=no&alerts=no";
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
        // java obj
        Object javaObject = jsonParser.parse(responseBody);
        // convert to json obj
        JSONObject jsonObject = (JSONObject) javaObject;
        // extract datas
        JSONObject jsonObjectCurrentWeather = (JSONObject) jsonObject.get("current");
        JSONObject jsonObjectIcon = (JSONObject) jsonObjectCurrentWeather.get("condition");

        double temp = (double) jsonObjectCurrentWeather.get("temp_c");
        long humidity = (long) jsonObjectCurrentWeather.get("humidity");
        double wind = (double) jsonObjectCurrentWeather.get("wind_kph");
        double pressure = (double) jsonObjectCurrentWeather.get("pressure_mb");
        String iconLink = "http:" + (String) jsonObjectIcon.get("icon");
        String description = (String) jsonObjectIcon.get("text");
        System.out.println(iconLink);
        System.out.println(description);

        return new Weather(cityName, temp, humidity, wind, pressure, description, iconLink);
    }

    public List<Weather> parseNextWeather(String responseBody) throws ParseException {

        JSONParser jsonParser = new JSONParser();
        // java obj
        Object javaObject = jsonParser.parse(responseBody);
        // convert to json obj
        JSONObject jsonObject = (JSONObject) javaObject;
        // extract datas
        JSONObject jsonObjectFutureWeather = (JSONObject) jsonObject.get("forecast");
//        JSONObject jsonObjectForecast = (JSONObject) jsonObjectFutureWeather.get("forecastday");
        JSONArray forecastArray = (JSONArray) jsonObjectFutureWeather.get("forecastday");

        for (int i = 0; i < forecastArray.size(); i ++){
            JSONObject weatherForecastObj = (JSONObject) forecastArray.get(i);
            JSONObject forecastDatas = (JSONObject) weatherForecastObj.get("day");
            double tempMax = (double) forecastDatas.get("maxtemp_c");
            double tempMin = (double) forecastDatas.get("mintemp_c");
            double humidity = (double) forecastDatas.get("avghumidity");
            double wind = (double) forecastDatas.get("maxwind_kph");

            JSONObject conditions = (JSONObject) forecastDatas.get("condition");
            String iconLink = "http:" + (String) conditions.get("icon");
            String description = (String) conditions.get("text");

            Weather weather = new Weather(cityName, description, iconLink, tempMax, tempMin, humidity, wind);
            weatherForecastList.add(weather);
//            System.out.println(tempMax);
//            System.out.println(tempMin);
//            System.out.println(humidity);
//            System.out.println(wind);
//            System.out.println(iconLink);
//            System.out.println(description);
        }

        return weatherForecastList;
    }
}
