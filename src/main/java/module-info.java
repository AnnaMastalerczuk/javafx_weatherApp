module com.amastalerczuk {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.google.gson;
    requires json.simple;

    opens com.amastalerczuk to javafx.fxml;
    opens com.amastalerczuk.controller to javafx.fxml;
    opens com.amastalerczuk.view to javafx.fxml;
    opens com.amastalerczuk.model to javafx.fxml;

//    opens com.amastalerczuk.model.client to javafx.fxml;
//    opens com.amastalerczuk.model.readers to javafx.fxml;
//    opens com.amastalerczuk.config to javafx.fxml;

    exports com.amastalerczuk;
    exports com.amastalerczuk.controller;
    exports com.amastalerczuk.view;
    exports com.amastalerczuk.model;
    exports com.amastalerczuk.model.client;
    exports com.amastalerczuk.model.readers;
    exports com.amastalerczuk.config;
}
