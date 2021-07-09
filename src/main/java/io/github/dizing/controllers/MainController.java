package io.github.dizing.controllers;

import io.github.dizing.models.Field;
import io.github.dizing.models.Ship;
import io.github.dizing.models.UserFieldSingleton;
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
    Field userField;
    Field botField;

    private void rightFieldClickHandler(MouseEvent event){
        Point clickPoint = Field.calculateCoordsFromRaw(event.getX(), event.getY(), fxGridRight.getWidth(), fxGridRight.getHeight());
        playerMove(clickPoint);
        //fxStatusMoveLabel.setText(game.getMoveInfo);
        System.out.println("dsd");
    }


    private void drawField(Field field, FieldView view){
        for(Ship ship: field.shipsArray)
        view.placeShip(ship);
    }

    private void playerMove(Point clickPoint){
        boolean modelResult = botField.placeMove(clickPoint);
        boolean viewResult;
        if (modelResult){
            viewResult = rightFieldView.placeMove(clickPoint);
            System.out.println("move" + clickPoint.x + clickPoint.y);
        } else {
            viewResult = rightFieldView.placeSplash(clickPoint);
            System.out.println("splash" + clickPoint.x + clickPoint.y);
        }
        if(!viewResult){
            fxStatusMoveLabel.setText("Ход по этому месту уже был, сходите заново");
        } else {
            fxStatusMoveLabel.setText(" ");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leftFieldView = new FieldView(fxGridLeft);
        rightFieldView = new FieldView(fxGridRight);
        userField = UserFieldSingleton.getInstance().getUserField();
        botField = new Field(); // REFACTOR BOT FIELD
        drawField(userField, leftFieldView);
        drawField(botField, rightFieldView);
        fxGridRight.addEventHandler(MouseEvent.MOUSE_CLICKED, this::rightFieldClickHandler);
    }
}
