package com.amastalerczuk.controller;

import com.amastalerczuk.config.Config;
import com.amastalerczuk.model.DateService;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.util.converter.LocalDateTimeStringConverter;
import org.json.simple.parser.ParseException;


import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

public class MainViewController extends BaseController implements Initializable {

    private WeatherService weatherService;
    private DateService dateService;
    private Map<String, Integer> citiesMap;
    private String currentCityName;
    private String selectedCityName;

    @FXML
    private AnchorPane anchorPaneCurrentWeatherCurrentLocation;

    @FXML
    private AnchorPane anchorPaneCurrentWeatherSelectedLocation;

    @FXML
    private Label currentData;

    @FXML
    private Label currentLocation;

    @FXML
    private Label currentLocationDescription;

    @FXML
    private Label currentLocationHumidity;

    @FXML
    private Label alertCurrentLocation;

    @FXML
    private Label alertSelectedLocation;

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
        viewFactory.showNextWeatherView(currentCityName, selectedCityName);
        Stage stage = (Stage) currentData.getScene().getWindow();
        viewFactory.closeStage(stage);
//        if (anchorPaneCurrentWeatherCurrentLocation.isVisible() && anchorPaneCurrentWeatherSelectedLocation.isVisible()){
//            viewFactory.showNextWeatherView();
//            stage = (Stage) currentData.getScene().getWindow();
//            viewFactory.closeStage(stage);
//        } else {
//            alertCurrentLocation.setText("Podaj nazwę miejscowości");
//            alertSelectedLocation.setText("Podaj nazwę miejscowości");
//        }
    }

    @FXML
    void setCurrentLocation(ActionEvent event) {
        try{
            currentCityName = currentLocationInput.getText();
            anchorPaneCurrentWeatherCurrentLocation.setVisible(true);
            alertCurrentLocation.setVisible(false);
            weatherService = WeatherServiceFactory.createWeatherService();
            Weather weather = weatherService.getCurrentWeather(currentLocationInput.getText());
            displayCurrentWeather(weather);
        } catch (RuntimeException | ParseException e){
            anchorPaneCurrentWeatherCurrentLocation.setVisible(false);
            alertCurrentLocation.setVisible(true);
            alertCurrentLocation.setText("Błąd. Podaj nazwę jeszcze raz.");
            e.printStackTrace();
        }
    }

    @FXML
    void setSelectedLocation(ActionEvent event) {
        try{
            selectedCityName = selectedLocationInput.getText();
            anchorPaneCurrentWeatherSelectedLocation.setVisible(true);
            alertSelectedLocation.setVisible(false);
            weatherService = WeatherServiceFactory.createWeatherService();
            Weather weather = weatherService.getCurrentWeather(selectedLocationInput.getText());
            displaySelectedWeather(weather);
        } catch (RuntimeException | ParseException e){
            anchorPaneCurrentWeatherSelectedLocation.setVisible(false);
            alertSelectedLocation.setVisible(true);
            alertSelectedLocation.setText("Błąd. Podaj nazwę jeszcze raz.");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPaneCurrentWeatherCurrentLocation.setVisible(false);
        anchorPaneCurrentWeatherSelectedLocation.setVisible(false);
        alertCurrentLocation.setText("Podaj bieżącą lokalizację");
        alertSelectedLocation.setText("Podaj docelową lokalizację");

        dateService = new DateService();
        currentData.setText(dateService.setCurrentDate());
        System.out.println(dateService.setCurrentDate());

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
    }

    private void displayCurrentWeather(Weather weather) {
        currentLocation.setText(weather.getCityName().toUpperCase());
        currentLocationTemperature.setText(Double.toString(weather.getTempInCelsius()) + " °C");
        currentLocationHumidity.setText(Double.toString(weather.getHumidity()) + " %");
        currentLocationWind.setText(Double.toString(weather.getWind()) + " m/s");
        currentLocationPressure.setText(String.valueOf(weather.getPressure()) + " hPa");
        currentLocationDescription.setText((weather.getWeatherDescription()));
        currentLocationImage.setImage(new Image(weather.getIconLink()));
        System.out.println(weather.getIconLink());
    }

    private void displaySelectedWeather(Weather weather) {
        selectedLocation.setText(weather.getCityName().toUpperCase());
        selectedLocationTemperature.setText(Double.toString(weather.getTempInCelsius()) + " °C");
        selectedLocationHumidity.setText(Double.toString(weather.getHumidity()) + " %");
        selectedLocationWind.setText(Double.toString(weather.getWind()) + " m/s");
        selectedLocationPressure.setText(String.valueOf(weather.getPressure()) + " hPa");
        selectedLocationDescription.setText((weather.getWeatherDescription()));
        selectedLocationImage.setImage(new Image(weather.getIconLink()));
    }


}
