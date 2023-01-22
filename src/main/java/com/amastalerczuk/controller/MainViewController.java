package com.amastalerczuk.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MainViewController {
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
    private ChoiceBox<?> listOfLocations;

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

    @FXML
    void checkWeatherNextDays(ActionEvent event) {

    }

    @FXML
    void setLocation(ActionEvent event) {

    }

}
