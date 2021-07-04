package io.github.dizing.controllers;

import io.github.dizing.models.Field;
import io.github.dizing.models.Ship;
import io.github.dizing.views.FieldView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private GridPane fxGridLeft;
    @FXML
    private GridPane fxGridRight;
    @FXML
    private Label fxStatusMoveLabel;
    FieldView leftFieldView;
    FieldView rightFieldView;


    private void rightFieldClickHandler(MouseEvent event){
        Point clickPoint = Field.calculateCoordsFromRaw(event.getX(), event.getY(), fxGridRight.getWidth(), fxGridRight.getHeight());
        //game.playerMove(clickPoint);
        //fxStatusMoveLabel.setText(game.getMoveInfo);
    }


    private void drawField(Field field, FieldView view){
        for(Ship ship: field.shipsArray)
        view.placeShip(ship);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leftFieldView = new FieldView(fxGridLeft);
        rightFieldView = new FieldView(fxGridRight);
        //drawField(game.getPlayerField, leftFieldView);
        //drawField(game.getBotField, rightFieldView);
        fxGridRight.addEventHandler(MouseEvent.MOUSE_CLICKED, this::rightFieldClickHandler);
    }
}
