package com.amastalerczuk.controller;

import com.amastalerczuk.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class NextWeatherViewController extends BaseController{

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
    void returnToMainView(ActionEvent event) {
        viewFactory.showMainView();
        Stage stage = (Stage) currenLocationDescription1.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
