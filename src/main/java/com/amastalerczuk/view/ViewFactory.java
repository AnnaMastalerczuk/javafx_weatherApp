package com.amastalerczuk.view;

import com.amastalerczuk.App;
import com.amastalerczuk.controller.BaseController;
import com.amastalerczuk.controller.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ViewFactory {

    public void showMainView() {
        BaseController controller = new MainViewController("MainView.fxml");
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
        scene.getStylesheets().add(Objects.requireNonNull(App.class.getResource("css/style.css")).toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }

}
