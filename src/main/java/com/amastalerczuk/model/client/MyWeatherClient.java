//package com.amastalerczuk.model.client;
//
//import com.amastalerczuk.model.Weather;
//
//import javafx.scene.image.Image;
//import net.aksingh.owmjapis.api.APIException;
//import net.aksingh.owmjapis.core.OWM;
//import net.aksingh.owmjapis.model.CurrentWeather;
//import net.aksingh.owmjapis.model.DailyUVIndexForecast;
//import net.aksingh.owmjapis.model.DailyWeatherForecast;
//import net.aksingh.owmjapis.model.HourlyWeatherForecast;
//import net.aksingh.owmjapis.model.param.WeatherData;
//
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.List;
//
//import static com.amastalerczuk.config.Config.API_KEY;
//
//public class MyWeatherClient implements WeatherClient{
//
//    OWM owm = new OWM(API_KEY);
//    private double temperature;
//    private double humidity;
//    private double wind;
//    private double pressure;
//    private Image image;
//    private String message;
//    private String iconLink;
//
//
//    public MyWeatherClient() {
//        owm.setUnit(OWM.Unit.METRIC);
//        owm.setLanguage(OWM.Language.POLISH);
//    }
//
//    @Override
//    public Weather getWeather(String cityName) {
//        try {
//            CurrentWeather cwd = owm.currentWeatherByCityName(cityName);
//            temperature = cwd.getMainData().getTempMax();
//            humidity = cwd.getMainData().getHumidity();
//            wind = cwd.getWindData().getSpeed();
//            pressure = cwd.getMainData().getPressure();
//            iconLink = cwd.getWeatherList().get(0).getIconLink();
//            message = cwd.getWeatherList().get(0).getDescription();
//
//        } catch (APIException e) {
//            throw new RuntimeException(e);
//        }
//
//        return new Weather(cityName, temperature, humidity, wind, pressure, message, iconLink);
//    }
//
//    public Weather getNextWeather(String cityName) {
//
//        return null;
////        List<DailyUVIndexForecast> dailyWeatherForecast;
////        try {
////            dailyWeatherForecast = owm.dailyUVIndexForecastByCoords(10, 10);
////        } catch (APIException e) {
////            throw new RuntimeException(e);
////        }
////        System.out.println(dailyWeatherForecast);
////        return null;
//
//
//    }
//
//}
