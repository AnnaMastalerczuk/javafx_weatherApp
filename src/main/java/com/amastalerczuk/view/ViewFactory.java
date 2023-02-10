package com.amastalerczuk.view;

import com.amastalerczuk.App;
import com.amastalerczuk.controller.BaseController;
import com.amastalerczuk.controller.MainViewController;
import com.amastalerczuk.controller.NextWeatherViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    public void showMainView() {
        BaseController controller = new MainViewController("MainView.fxml");
        initializeStage(controller);
    }

    public void showMainView(String currentCityName, String selectedCityName) {
        BaseController controller = new MainViewController("MainView.fxml", currentCityName, selectedCityName);
        initializeStage(controller);
    }

    public void showNextWeatherView(String currentCityName, String selectedCityName) {
        BaseController controller = new NextWeatherViewController("NextWeatherView.fxml", currentCityName, selectedCityName);
        initializeStage(controller);
    }

    private void initializeStage(BaseController controller) {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);
        Parent parent;

        try{
            parent = fxmlLoader.load();
        } catch (IOException e){
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }

}
