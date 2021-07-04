package io.github.dizing.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private GridPane fxGridLeft;
    @FXML
    private GridPane fxGridRight;
    @FXML
    private Label fxStatusMoveLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}
