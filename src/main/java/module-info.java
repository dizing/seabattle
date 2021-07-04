module io.github.dizing {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens io.github.dizing to javafx.fxml;
    opens io.github.dizing.controllers to javafx.fxml;
    opens io.github.dizing.views to javafx.fxml;
    exports io.github.dizing;
    exports io.github.dizing.controllers;
    exports io.github.dizing.views;
}
