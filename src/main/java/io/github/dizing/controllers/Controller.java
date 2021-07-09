package io.github.dizing.controllers;

import io.github.dizing.App;
import io.github.dizing.models.UserFieldSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import io.github.dizing.models.Field;
import io.github.dizing.models.Ship;
import io.github.dizing.views.FieldView;

import java.awt.Point;
import java.io.IOException;
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

    Field field;

    FieldView view;

    int shipCounter = 0;

    private void gridClickHandler(MouseEvent event){
        Point clickPoint = Field.calculateCoordsFromRaw(event.getX(), event.getY(), fxGrid.getWidth(), fxGrid.getHeight());
        int lengthShip = Field.parseLengthStringBox(fxLengthShipBox.getValue());
        String directionShip = fxDirectionShipBox.getValue();

        Ship ship = new Ship(clickPoint, lengthShip, directionShip);
        if (Ship.validateShipPoint(clickPoint, lengthShip, directionShip) & field.possibilityShipAdding(ship)){
            field.addShip(ship);
            view.placeShip(ship);
            ++shipCounter;
            if(shipCounter == 10){
                UserFieldSingleton.getInstance().setUserField(field);
                try {
                    switchToMainGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("invalid coordinates");
        }
        fxShipCountLabel.setText(field.getRemainShipCount());

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Инициализация и передача контроля в View класс labelFx
        // Добавляем слушателя, по клику мышки на кнопку, выводит текст на консоль
        field = new Field();
        view = new FieldView(fxGrid);
        fxGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, this::gridClickHandler);
        fxShipCountLabel.setText(field.getRemainShipCount());
    }

    @FXML
    private void switchToMainGame() throws IOException {
        App.setRoot("mainGame");
    }
}
