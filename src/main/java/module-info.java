module com.amastalerczuk {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.amastalerczuk to javafx.fxml;
    opens com.amastalerczuk.controller to javafx.fxml;
    exports com.amastalerczuk;
    exports com.amastalerczuk.controller;
}
