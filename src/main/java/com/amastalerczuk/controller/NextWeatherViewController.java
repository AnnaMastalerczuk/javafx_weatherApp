package com.amastalerczuk.controller;

import com.amastalerczuk.model.DateService;
import com.amastalerczuk.model.Weather;
import com.amastalerczuk.model.WeatherService;
import com.amastalerczuk.model.WeatherServiceFactory;
import com.amastalerczuk.model.client.MyAPIWeatherClient;
//import com.amastalerczuk.model.client.MyWeatherClient;
import com.amastalerczuk.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NextWeatherViewController extends BaseController implements Initializable {

    private DateService dateService;
    private WeatherService weatherServiceCurrent;
    private WeatherService weatherServiceSelected;
    private List<Weather> weatherListCurrentCity = new ArrayList<>();
    private List<Weather> weatherListSelectedCity = new ArrayList<>();
    
    private String currentCityName;
    private String selectedCityName;

    @FXML
    private HBox hboxFutureWeather;

    @FXML
    private Label currentLocation;

    @FXML
    private Label selectedLocation;


    public NextWeatherViewController(String fxmlName, String currentCityName, String selectedCityName) {
        super(fxmlName);
        this.currentCityName = currentCityName;
        this.selectedCityName = selectedCityName;
    }

    @FXML
    void returnToMainView() {
        viewFactory.showMainView(currentCityName, selectedCityName);
        Stage stage = (Stage) currentLocation.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        setDates();
        
//        currentCityName = "Gdansk";
//        selectedCityName = "London";

        currentLocation.setText(currentCityName.toUpperCase());
        selectedLocation.setText(selectedCityName.toUpperCase());

        try {
            weatherServiceCurrent = WeatherServiceFactory.createWeatherService();
            weatherServiceSelected = WeatherServiceFactory.createWeatherService();
            weatherListCurrentCity = weatherServiceCurrent.getFutureWeather(selectedCityName);
            weatherListSelectedCity = weatherServiceSelected.getFutureWeather(selectedCityName);
            displayWeather(weatherListCurrentCity);
            displayWeather(weatherListSelectedCity);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
       
//        weatherService = WeatherServiceFactory.createWeatherService();
//        Weather weather = weatherService.getNextWeather("London");

//        MyAPIWeatherClient myAPIWeatherClient = new MyAPIWeatherClient();
//        try {
//            Weather weather = myAPIWeatherClient.getNextWeather("London");
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }


    }

    private void displayWeather(List<Weather> weatherList) {
//        currentLocation.setText(weatherList.get(0).getCityName());

        for (int i = 0; i < weatherList.size(); i++){
            VBox vbox = new VBox();

            Image iconWeather = new Image(weatherList.get(i).getIconLink());
            Label date = new Label(setDates(i));
            Label tempMax = new Label("Tmax: " + String.valueOf(weatherList.get(i).getTempMax()) + " °C");
            Label tempMin = new Label("Tmin: " + String.valueOf(weatherList.get(i).getTempMin()) + " °C");
            Label humidity = new Label(weatherList.get(i).getHumidity() + " %");
            Label wind = new Label((weatherList.get(i).getWind() + " km/h"));
//            Label totalPrecip = new Label(weatherList.get(i). getTotalPrecip() + " mm");
//            Label totalSnow = new Label(weatherList.get(i).getTotalSnow() + " cm");
            vbox.getChildren().add(new ImageView(iconWeather));
            vbox.getChildren().add(date);
            vbox.getChildren().add(tempMax);
            vbox.getChildren().add(tempMin);
            vbox.getChildren().add(humidity);
            vbox.getChildren().add(wind);
//            vbox.getChildren().add(totalPrecip);
//            vbox.getChildren().add(totalSnow);
            hboxFutureWeather.getChildren().add(vbox);
        }
    }

    private String setDates(int i) {
        dateService = new DateService();
        return dateService.setNextDate(i+1);
//        currentLocationData1.setText(dateService.setNextDate(1));
//        currentLocationData2.setText(dateService.setNextDate(2));
//        currentLocationData3.setText(dateService.setNextDate(3));
//        currentLocationData4.setText(dateService.setNextDate(4));
//
//        selectedLocationData1.setText(dateService.setNextDate(1));
//        selectedLocationData2.setText(dateService.setNextDate(2));
//        selectedLocationData3.setText(dateService.setNextDate(3));
//        selectedLocationData4.setText(dateService.setNextDate(4));
    }
}
