package com.amastalerczuk.controller;

import com.amastalerczuk.config.Config;
import com.amastalerczuk.model.DateService;
import com.amastalerczuk.model.Weather;
import com.amastalerczuk.model.WeatherService;
import com.amastalerczuk.model.WeatherServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.json.simple.parser.ParseException;


import java.net.URL;
import java.util.*;

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
    public MainViewController(String fxmlName, String currentCityName, String selectedCityName) {
        super(fxmlName);
        this.currentCityName = currentCityName;
        this.selectedCityName = selectedCityName;
    }

    @FXML
    void checkWeatherNextDays() {
//        viewFactory.showNextWeatherView(currentCityName, selectedCityName);
//        Stage stage = (Stage) currentData.getScene().getWindow();
//        viewFactory.closeStage(stage);

        if (anchorPaneCurrentWeatherCurrentLocation.isVisible() && anchorPaneCurrentWeatherSelectedLocation.isVisible()){
            viewFactory.showNextWeatherView(currentCityName, selectedCityName);
            Stage stage = (Stage) currentData.getScene().getWindow();
            viewFactory.closeStage(stage);
        } else {
            alertCurrentLocation.setText("Podaj nazwę miejscowości");
            alertSelectedLocation.setText("Podaj nazwę miejscowości");
        }
    }

    @FXML
    void setCurrentLocationAction() {
            try {
                currentCityName = currentLocationInput.getText();
                System.out.println(currentCityName);
                setCurrentLocationWeather();
            } catch (RuntimeException | ParseException e) {
                anchorPaneCurrentWeatherCurrentLocation.setVisible(false);
                alertCurrentLocation.setVisible(true);
                alertCurrentLocation.setText("Błąd. Podaj nazwę jeszcze raz.");
                e.printStackTrace();
            }
        }

        private void setCurrentLocationWeather() throws ParseException {
            anchorPaneCurrentWeatherCurrentLocation.setVisible(true);
            alertCurrentLocation.setVisible(false);
            weatherService = WeatherServiceFactory.createWeatherService();
            Weather weather = weatherService.getCurrentWeather(currentCityName);
            displayCurrentWeather(weather);
    }

    @FXML
    void setSelectedLocationAction() {
        try {
            selectedCityName = selectedLocationInput.getText();
            System.out.println(selectedCityName);
            setSelectedLocationWeather();
        } catch (RuntimeException | ParseException e) {
            anchorPaneCurrentWeatherSelectedLocation.setVisible(false);
            alertSelectedLocation.setVisible(true);
            alertSelectedLocation.setText("Błąd. Podaj nazwę jeszcze raz.");
            e.printStackTrace();
        }
    }

    private void setSelectedLocationWeather() throws ParseException {
        anchorPaneCurrentWeatherSelectedLocation.setVisible(true);
        alertSelectedLocation.setVisible(false);
        weatherService = WeatherServiceFactory.createWeatherService();
        Weather weather = weatherService.getCurrentWeather(selectedCityName);
        displaySelectedWeather(weather);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(currentCityName);
        anchorPaneCurrentWeatherCurrentLocation.setVisible(false);
        anchorPaneCurrentWeatherSelectedLocation.setVisible(false);
        alertCurrentLocation.setText("Podaj bieżącą lokalizację");
        alertSelectedLocation.setText("Podaj docelową lokalizację");

        if (currentCityName != null && selectedCityName != null) {
            try {
                setCurrentLocationWeather();
                setSelectedLocationWeather();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        dateService = new DateService();
        currentData.setText(dateService.setCurrentDate());
        System.out.println(dateService.setCurrentDate());

    }

    private void displayCurrentWeather(Weather weather) {
        currentLocation.setText(weather.getCityName().toUpperCase());
        currentLocationTemperature.setText((weather.getTempInCelsius()) + " °C");
        currentLocationHumidity.setText((weather.getHumidity()) + " %");
        currentLocationWind.setText((weather.getWind()) + " m/s");
        currentLocationPressure.setText((weather.getPressure()) + " hPa");
        currentLocationDescription.setText((weather.getWeatherDescription()));
        currentLocationImage.setImage(new Image(weather.getIconLink()));
        System.out.println(weather.getIconLink());
    }

    private void displaySelectedWeather(Weather weather) {
        selectedLocation.setText(weather.getCityName().toUpperCase());
        selectedLocationTemperature.setText((weather.getTempInCelsius()) + " °C");
        selectedLocationHumidity.setText((weather.getHumidity()) + " %");
        selectedLocationWind.setText((weather.getWind()) + " m/s");
        selectedLocationPressure.setText((weather.getPressure()) + " hPa");
        selectedLocationDescription.setText((weather.getWeatherDescription()));
        selectedLocationImage.setImage(new Image(weather.getIconLink()));
    }

}
