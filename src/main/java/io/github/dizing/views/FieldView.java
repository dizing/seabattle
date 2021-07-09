package io.github.dizing.views;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import io.github.dizing.models.Ship;
import io.github.dizing.App;

import java.awt.*;

public class FieldView {

    private final Image clearField;
    private final Image verticalTopShip;
    private final Image verticalMiddleShip;
    private final Image splash;
    private final Image brokenShip;
    private final Image horizontalMiddleShip;
    private final Image horizontalRightShip;
    private final Image verticalBottomShip;
    private final Image horizontalLeftShip;
    private final Image oneLengthShip;

    private Pane[][] panesArray = new Pane[10][10];
    private boolean[][] splashesArray = new boolean[10][10];
    private boolean[][] crashPointsArray = new boolean[10][10];

    private GridPane fxGrid;

    public void placeShip(Ship ship){
        switch (ship.length){
            case 1:
                switchPaneImage(oneLengthShip, ship.shipPoints.get(0).x, ship.shipPoints.get(0).y);
                break;
            case 2:
                if (ship.direction.equals("vertical")){
                    switchPaneImage(verticalTopShip, ship.shipPoints.get(0).x, ship.shipPoints.get(0).y);
                    switchPaneImage(verticalBottomShip, ship.shipPoints.get(1).x, ship.shipPoints.get(1).y);
                } else {
                    switchPaneImage(horizontalLeftShip, ship.shipPoints.get(0).x, ship.shipPoints.get(0).y);
                    switchPaneImage(horizontalRightShip, ship.shipPoints.get(1).x, ship.shipPoints.get(1).y);
                }
                break;
            case 3:
                if (ship.direction.equals("vertical")){
                    switchPaneImage(verticalTopShip, ship.shipPoints.get(0).x, ship.shipPoints.get(0).y);
                    switchPaneImage(verticalMiddleShip, ship.shipPoints.get(1).x, ship.shipPoints.get(1).y);
                    switchPaneImage(verticalBottomShip, ship.shipPoints.get(2).x, ship.shipPoints.get(2).y);
                } else {
                    switchPaneImage(horizontalLeftShip, ship.shipPoints.get(0).x, ship.shipPoints.get(0).y);
                    switchPaneImage(horizontalMiddleShip, ship.shipPoints.get(1).x, ship.shipPoints.get(1).y);
                    switchPaneImage(horizontalRightShip, ship.shipPoints.get(2).x, ship.shipPoints.get(2).y);
                }
                break;
            case 4:
                if (ship.direction.equals("vertical")){
                    switchPaneImage(verticalTopShip, ship.shipPoints.get(0).x, ship.shipPoints.get(0).y);
                    switchPaneImage(verticalMiddleShip, ship.shipPoints.get(1).x, ship.shipPoints.get(1).y);
                    switchPaneImage(verticalMiddleShip, ship.shipPoints.get(2).x, ship.shipPoints.get(2).y);
                    switchPaneImage(verticalBottomShip, ship.shipPoints.get(3).x, ship.shipPoints.get(3).y);
                } else {
                    switchPaneImage(horizontalLeftShip, ship.shipPoints.get(0).x, ship.shipPoints.get(0).y);
                    switchPaneImage(horizontalMiddleShip, ship.shipPoints.get(1).x, ship.shipPoints.get(1).y);
                    switchPaneImage(horizontalMiddleShip, ship.shipPoints.get(2).x, ship.shipPoints.get(2).y);
                    switchPaneImage(horizontalRightShip, ship.shipPoints.get(3).x, ship.shipPoints.get(3).y);
                }
                break;
        }
    }

    private Pane placePane(GridPane fxGrid, Image image, int x, int y){
        Pane pane = new Pane();
        pane.setStyle("-fx-background-image: url(" + image.getUrl() + "); -fx-background-size: 100% 100%;-fx-border-color: black;" +
                "-fx-border-width: 1 1 1 1");
        fxGrid.add(pane, x, y);
        return pane;
    }

    private void switchPaneImage(Image image, int x, int y){
        panesArray[x][y].setStyle("-fx-background-image: url(" + image.getUrl() + "); -fx-background-size: 100% 100%;-fx-border-color: black;" +
                "-fx-border-width: 1 1 1 1");
    }

    public boolean placeMove(Point point){
        if (!crashPointsArray[point.x][point.y]){
            crashPointsArray[point.x][point.y] = true;
            switchPaneImage(brokenShip, point.x, point.y);
            return true;
        }
        return false;
    }

    public boolean placeSplash(Point point){
        if (!splashesArray[point.x][point.y] & !crashPointsArray[point.x][point.y]){
            splashesArray[point.x][point.y] = true;
            System.out.println(point.x + " " + point.y);
            switchPaneImage(splash, point.x, point.y);
            return true;
        }
        return false;
    }

    public FieldView(GridPane fxGrid){
        clearField = new Image(String.valueOf(App.class.getResource("images/01.png")));
        verticalTopShip = new Image(String.valueOf(App.class.getResource("images/02.png")));
        verticalMiddleShip = new Image(String.valueOf(App.class.getResource("images/03.png")));
        splash = new Image(String.valueOf(App.class.getResource("images/04.png")));
        brokenShip = new Image(String.valueOf(App.class.getResource("images/05.png")));
        horizontalMiddleShip = new Image(String.valueOf(App.class.getResource("images/06.png")));
        horizontalRightShip = new Image(String.valueOf(App.class.getResource("images/07.png")));
        verticalBottomShip = new Image(String.valueOf(App.class.getResource("images/08.png")));
        horizontalLeftShip = new Image(String.valueOf(App.class.getResource("images/09.png")));
        oneLengthShip = new Image(String.valueOf(App.class.getResource("images/10.png")));

        this.fxGrid = fxGrid;
        for(int i = 0; i < 10; ++i){
            for(int j = 0; j < 10; ++j){
                panesArray[i][j] = placePane(fxGrid, clearField, i, j);
            }
        }
    }
}
