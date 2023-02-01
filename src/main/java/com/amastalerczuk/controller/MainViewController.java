package com.amastalerczuk.controller;

import com.amastalerczuk.config.Config;
import com.amastalerczuk.model.Weather;
import com.amastalerczuk.model.WeatherService;
import com.amastalerczuk.model.WeatherServiceFactory;
import com.amastalerczuk.model.readers.AutoCompleteTextField;
import com.amastalerczuk.model.readers.JSONConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainViewController extends BaseController implements Initializable {

    private WeatherService weatherService;
    private Map<String, Integer> citiesMap;

    @FXML
    private Label currentData;

    @FXML
    private Label currentLocation;

    @FXML
    private Label currentLocationDescription;

    @FXML
    private Label currentLocationHumidity;

    @FXML
    private ImageView currentLocationImage;

    @FXML
    private TextField currentLocationInput;

    @FXML
    private Label currentLocationPressure;

    @FXML
    private Label currentLocationTemperature;

    @FXML
    private Label currentLocationWind;

    @FXML
    private Label selectedLocation;

    @FXML
    private Label selectedLocationDescription;

    @FXML
    private Label selectedLocationHumidity;

    @FXML
    private ImageView selectedLocationImage;

    @FXML
    private TextField selectedLocationInput;

    @FXML
    private Label selectedLocationPressure;

    @FXML
    private Label selectedLocationTemperature;

    @FXML
    private Label selectedLocationWind;

    public MainViewController(String fxmlName) {
        super(fxmlName);
    }

    @FXML
    void checkWeatherNextDays(ActionEvent event) {
        viewFactory.showNextWeatherView();
        Stage stage = (Stage) currentData.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void setCurrentLocation(ActionEvent event) {

    }

    @FXML
    void setSelectedLocation(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
//            JSONConverter jsonConverter = new JSONConverter();
//            citiesMap = jsonConverter.getCitiesMapFromJSON(Config.CITY_LIST_WITH_DATA);
//
//            AutoCompleteTextField.autoComplete(currentLocationInput, citiesMap);
//            AutoCompleteTextField.autoComplete(cityNameCurrent, citiesMap);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        weatherService = WeatherServiceFactory.createWeatherService();
        Weather weather = weatherService.getWeather("London");
        displayWeather(weather);

    }

    private void displayWeather(Weather weather) {
        currentLocation.setText(weather.getCityName());
        currentLocationTemperature.setText(Double.toString(weather.getTempInCelsius()));
        currentLocationHumidity.setText(Double.toString(weather.getHumidity()));
        currentLocationWind.setText(Double.toString(weather.getWind()));
        System.out.println(weather.getDate());
    }


}
