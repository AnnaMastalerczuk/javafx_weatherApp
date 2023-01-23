package com.amastalerczuk.controller;

import com.amastalerczuk.model.WeatherService;
import com.amastalerczuk.model.WeatherServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController extends BaseController implements Initializable {

    private WeatherService weatherService;

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
    private Label currentLocationPressure;

    @FXML
    private Label currentLocationTemperature;

    @FXML
    private Label currentLocationWind;

    @FXML
    private ChoiceBox<?> listOfCurrentLocations;

    @FXML
    private ChoiceBox<?> listOfSelectedLocations;

    @FXML
    private Label selectedLocation;

    @FXML
    private Label selectedLocationDescription;

    @FXML
    private Label selectedLocationHumidity;

    @FXML
    private ImageView selectedLocationImage;

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
        weatherService = WeatherServiceFactory.createWeatherService();
    }
}
