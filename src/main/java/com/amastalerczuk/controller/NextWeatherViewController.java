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
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NextWeatherViewController extends BaseController implements Initializable {

    private DateService dateService;
    private WeatherService weatherService;
    private List<Weather> weatherList = new ArrayList<>();
    
    private String currentCityName;
    private String selectedCityName;

    @FXML
    private Label currenLocationDescription1;

    @FXML
    private Label currenLocationDescription2;

    @FXML
    private Label currenLocationDescription3;

    @FXML
    private Label currenLocationDescription4;

    @FXML
    private Label currentLocation;

    @FXML
    private Label currentLocationData1;

    @FXML
    private Label currentLocationData2;

    @FXML
    private Label currentLocationData3;

    @FXML
    private Label currentLocationData4;

    @FXML
    private ImageView currentLocationImg1;

    @FXML
    private ImageView currentLocationImg2;

    @FXML
    private ImageView currentLocationImg3;

    @FXML
    private ImageView currentLocationImg4;

    @FXML
    private Label currentLocationPressure1;

    @FXML
    private Label currentLocationPressure2;

    @FXML
    private Label currentLocationPressure3;

    @FXML
    private Label currentLocationPressure4;

    @FXML
    private Label currentLocationTemp1;

    @FXML
    private Label currentLocationTemp2;

    @FXML
    private Label currentLocationTemp3;

    @FXML
    private Label currentLocationTemp4;

    @FXML
    private Label selectedLocation;

    @FXML
    private Label selectedLocationData1;

    @FXML
    private Label selectedLocationData2;

    @FXML
    private Label selectedLocationData3;

    @FXML
    private Label selectedLocationData4;

    @FXML
    private Label selectedLocationDescription1;

    @FXML
    private Label selectedLocationDescription2;

    @FXML
    private Label selectedLocationDescription3;

    @FXML
    private Label selectedLocationDescription4;

    @FXML
    private ImageView selectedLocationImg1;

    @FXML
    private ImageView selectedLocationImg2;

    @FXML
    private ImageView selectedLocationImg3;

    @FXML
    private ImageView selectedLocationImg4;

    @FXML
    private Label selectedLocationPressure1;

    @FXML
    private Label selectedLocationPressure2;

    @FXML
    private Label selectedLocationPressure3;

    @FXML
    private Label selectedLocationPressure4;

    @FXML
    private Label selectedLocationTemp1;

    @FXML
    private Label selectedLocationTemp2;

    @FXML
    private Label selectedLocationTemp3;

    @FXML
    private Label selectedLocationTemp4;

    public NextWeatherViewController(String fxmlName) {
        super(fxmlName);
    }

    @FXML
    void returnToMainView() {
        viewFactory.showMainView();
        Stage stage = (Stage) currenLocationDescription1.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDates();
        
        currentCityName = "Gdansk";
        selectedCityName = "London";        

        try {
            weatherService = WeatherServiceFactory.createWeatherService();
            weatherList = weatherService.getFutureWeather(currentCityName);
            displayWeather(weatherList);
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

        currentLocation.setText(weatherList.get(0).getCityName().toUpperCase());
        currentLocationTemp1.setText(String.valueOf(weatherList.get(0).getTempMax()));
        currentLocationPressure1.setText(String.valueOf(weatherList.get(0).getPressure()));
        currentLocationImg1.setImage(new Image(weatherList.get(0).getIconLink()));

//        currentLocationTemperature.setText(Double.toString(weather.getTempInCelsius()) + " °C");
//        currentLocationHumidity.setText(Double.toString(weather.getHumidity()) + " %");
//        currentLocationWind.setText(Double.toString(weather.getWind()) + " m/s");
//        currentLocationPressure.setText(String.valueOf(weather.getPressure()) + " hPa");
//        currentLocationDescription.setText((weather.getWeatherDescription()));
//        currentLocationImage.setImage(new Image(weather.getIconLink()));
//        System.out.println(weather.getIconLink());
    }

    private void setDates() {
        dateService = new DateService();
        currentLocationData1.setText(dateService.setNextDate(1));
        currentLocationData2.setText(dateService.setNextDate(2));
        currentLocationData3.setText(dateService.setNextDate(3));
        currentLocationData4.setText(dateService.setNextDate(4));

        selectedLocationData1.setText(dateService.setNextDate(1));
        selectedLocationData2.setText(dateService.setNextDate(2));
        selectedLocationData3.setText(dateService.setNextDate(3));
        selectedLocationData4.setText(dateService.setNextDate(4));
    }
}
