package com.amastalerczuk.controller;

import com.amastalerczuk.model.DateService;
import com.amastalerczuk.model.Weather;
import com.amastalerczuk.model.WeatherService;
import com.amastalerczuk.model.WeatherServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.simple.parser.ParseException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NestedViewController implements Initializable {
    private DateService dateService;
    @FXML
    private AnchorPane anchorPaneCurrentWeather;

    @FXML
    private HBox hboxFutureWeather;

    @FXML
    private Label currentData;

    @FXML
    private Label location;

    @FXML
    private Label locationDescription;

    @FXML
    private Label locationHumidity;

    @FXML
    private ImageView locationImage;

    @FXML
    private Label locationPressure;

    @FXML
    private Label locationTemperature;

    @FXML
    private Label locationWind;

    public NestedViewController(){
        dateService = new DateService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setCurrentWeather(String currentCityName) throws ParseException {
        WeatherService weatherService = WeatherServiceFactory.createWeatherService();
        Weather weather = weatherService.getCurrentWeather(currentCityName);
        displayCurrentWeather(weather);
    }

    public void setFutureWeather(String cityName) throws ParseException {
        List<Weather> weatherList;
        WeatherService weatherService = WeatherServiceFactory.createWeatherService();
        weatherList = weatherService.getFutureWeather(cityName);
        displayFutureWeather(weatherList);
    }

    private void displayCurrentWeather(Weather weather) {
        if (!anchorPaneCurrentWeather.isVisible()){
            anchorPaneCurrentWeather.setVisible(true);
        }
        currentData.setText(dateService.setCurrentDate());
        location.setText(weather.getCityName().toUpperCase());
        locationTemperature.setText((weather.getTempInCelsius()) + " °C");
        locationHumidity.setText("Wilgotność: " + (weather.getHumidity()) + " %");
        locationWind.setText("Wiatr: " + (weather.getWind()) + " m/s");
        locationPressure.setText("Ciśnienie: " + (weather.getPressure()) + " hPa");
        locationDescription.setText((weather.getWeatherDescription()));
        locationImage.setImage(new Image(weather.getIconLink()));
    }

    private void displayFutureWeather(List<Weather> weatherList) {
        hboxFutureWeather.getChildren().clear();
        if (!hboxFutureWeather.isVisible()){
            hboxFutureWeather.setVisible(true);
        }

        for (int i = 0; i < weatherList.size(); i++){
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(10));

            Image iconWeather = new Image(weatherList.get(i).getIconLink());
            Label date = new Label(weatherList.get(i).getDate());
            Label tempMax = new Label("Tmax: " + (weatherList.get(i).getTempMax()) + " °C");
            Label tempMin = new Label("Tmin: " + (weatherList.get(i).getTempMin()) + " °C");
            Label humidity = new Label(weatherList.get(i).getHumidity() + " %");
            Label wind = new Label((weatherList.get(i).getWind() + " km/h"));
            vbox.getChildren().add(new ImageView(iconWeather));
            vbox.getChildren().add(date);
            vbox.getChildren().add(tempMax);
            vbox.getChildren().add(tempMin);
            vbox.getChildren().add(humidity);
            vbox.getChildren().add(wind);
            hboxFutureWeather.getChildren().add(vbox);
        }
    }
    public void switchOffWeatherView(){
        anchorPaneCurrentWeather.setVisible(false);
        hboxFutureWeather.setVisible(false);
    }
 }
