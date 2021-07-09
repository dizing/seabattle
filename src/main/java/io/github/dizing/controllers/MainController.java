package io.github.dizing.controllers;

import io.github.dizing.models.Field;
import io.github.dizing.models.Ship;
import io.github.dizing.models.UserFieldSingleton;
import io.github.dizing.models.BotModel;
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

    private boolean gameStop = false;

    private void rightFieldClickHandler(MouseEvent event){
        if (gameStop){
            return;
        }
        boolean isPlayerHit = true;
        boolean isBotHit = true;
        Point clickPoint = Field.calculateCoordsFromRaw(event.getX(), event.getY(), fxGridRight.getWidth(), fxGridRight.getHeight());
        isPlayerHit = playerMove(clickPoint);
        if(isPlayerHit) {
            checkFieldsOnEnd();
            return;
        }
        while (isBotHit) {
            isBotHit = botMove();
            checkFieldsOnEnd();
        }
        //fxStatusMoveLabel.setText(game.getMoveInfo);
    }


    private void drawField(Field field, FieldView view){
        for(Ship ship: field.shipsArray)
        view.placeShip(ship);
    }

    private boolean playerMove(Point clickPoint){
        boolean isPlayerMove = false;
        boolean modelResult = botField.placeMove(clickPoint);
        boolean viewResult;
        if (modelResult){
            viewResult = rightFieldView.placeMove(clickPoint);
            isPlayerMove = true;
        } else {
            viewResult = rightFieldView.placeSplash(clickPoint);
        }
        if(!viewResult){
            fxStatusMoveLabel.setText("Ход по этому месту уже был, сходите заново");
            isPlayerMove = true;
        } else {
            fxStatusMoveLabel.setText(" ");
        }
        return isPlayerMove;
    }

    private boolean botMove(){
        boolean viewResult = false;
        boolean modelResult;
        boolean isBotHit = false;
        Point point;
        while(!viewResult){
            point = BotModel.getBotMovePoint();
            modelResult = userField.placeMove(point);
            if (modelResult){
                viewResult = leftFieldView.placeMove(point);
                isBotHit = true;
            } else {
                viewResult = leftFieldView.placeSplash(point);
                isBotHit = false;
            }
            System.out.println("loop error 3");
        }
        return isBotHit;
    }

    private void checkFieldsOnEnd(){
        if (userField.shipsArray.size() == 0){
            fxStatusMoveLabel.setText("Победил бот!");
            gameStop = true;
        } else if(botField.shipsArray.size() == 0){
            fxStatusMoveLabel.setText("Победил игрок!");
            gameStop = true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leftFieldView = new FieldView(fxGridLeft);
        rightFieldView = new FieldView(fxGridRight);
        userField = UserFieldSingleton.getInstance().getUserField();
        botField = new Field();
        BotModel.placeShips(botField);
        drawField(userField, leftFieldView);
        //drawField(botField, rightFieldView);
        fxGridRight.addEventHandler(MouseEvent.MOUSE_CLICKED, this::rightFieldClickHandler);
    }
}
