module com.amastalerczuk {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.amastalerczuk to javafx.fxml;
    opens com.amastalerczuk.controller to javafx.fxml;
    opens com.amastalerczuk.view to javafx.fxml;
    opens com.amastalerczuk.model;
    opens com.amastalerczuk.model.client;

    exports com.amastalerczuk;
    exports com.amastalerczuk.controller;
    exports com.amastalerczuk.view;
    exports com.amastalerczuk.model;
    exports com.amastalerczuk.model.client;
}
