package com.amastalerczuk;

import com.amastalerczuk.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage){

        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showMainView();
    }

    public static void main(String[] args) {
        launch();
    }

}