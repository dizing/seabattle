package io.github.dizing.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import io.github.dizing.models.Field;
import io.github.dizing.models.Ship;
import io.github.dizing.views.View;

import java.awt.Point;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private GridPane fxGrid;
    @FXML
    private ChoiceBox<String> fxLengthShipBox;
    @FXML
    private ChoiceBox<String> fxDirectionShipBox;
    @FXML
    private Label fxShipCountLabel;

    Field field = new Field();

    View view;

    private void gridClickHandler(MouseEvent event){
        Point clickPoint = Field.calculateCoordsFromRaw(event.getX(), event.getY(), fxGrid.getWidth(), fxGrid.getHeight());
        int lengthShip = Field.parseLengthStringBox(fxLengthShipBox.getValue());
        String directionShip = fxDirectionShipBox.getValue();

        Ship ship = new Ship(clickPoint, lengthShip, directionShip);
        if (Ship.validateShipPoint(clickPoint, lengthShip, directionShip) & field.possibilityShipAdding(ship)){
            field.addShip(ship);
            view.placeShip(ship);
        } else {
            System.out.println("invalid coordinates");
        }
        View.setFieldStatus(fxShipCountLabel, field.getRemindShipCount());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Инициализация и передача контроля в View класс labelFx
        // Добавляем слушателя, по клику мышки на кнопку, выводит текст на консоль
        view = new View(fxGrid);
        fxGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, this::gridClickHandler);
        View.setFieldStatus(fxShipCountLabel, field.getRemindShipCount());

    }
}
