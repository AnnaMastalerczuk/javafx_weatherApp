package com.amastalerczuk.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.simple.parser.ParseException;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController extends BaseController implements Initializable {
//    private Map<String, Integer> citiesMap;
    private String currentCityName;
    private String selectedCityName;

    @FXML private NestedViewController nestedLeftViewController;
    @FXML private NestedViewController nestedRightViewController;
    @FXML
    private Label alertCurrentLocation;
    @FXML
    private Label alertSelectedLocation;
    @FXML
    private TextField currentLocationInput;
    @FXML
    private TextField selectedLocationInput;

    public MainViewController(String fxmlName) {
        super(fxmlName);
    }

    @FXML
    void setCurrentLocationAction(){
        try{
            nestedLeftViewController.setCurrentWeather(currentLocationInput.getText());
            nestedLeftViewController.setFutureWeather(currentLocationInput.getText());
            alertCurrentLocation.setVisible(false);
        } catch (RuntimeException | ParseException e) {
            nestedLeftViewController.switchOffWeatherView();
            alertCurrentLocation.setVisible(true);
            alertCurrentLocation.setText("Błąd. Podaj nazwę jeszcze raz. Nie używaj polskich znaków.");
            e.printStackTrace();
        }
    }

    @FXML
    void setSelectedLocationAction() {
        try{
            nestedRightViewController.setCurrentWeather(selectedLocationInput.getText());
            nestedRightViewController.setFutureWeather(selectedLocationInput.getText());
            alertSelectedLocation.setVisible(false);
        } catch (RuntimeException | ParseException e) {
            nestedRightViewController.switchOffWeatherView();
            alertSelectedLocation.setVisible(true);
            alertSelectedLocation.setText("Błąd. Podaj nazwę jeszcze raz. Nie używaj polskich znaków.");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alertCurrentLocation.setText("Podaj bieżącą lokalizację");
        alertSelectedLocation.setText("Podaj docelową lokalizację");
    }
}
