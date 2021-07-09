package io.github.dizing.models;

import java.awt.*;
import java.util.Random;

public class BotModel {
    final static Random random = new Random();

    static public void placeShips(Field field){
        Ship ship = generateRandShip(1);
        for(int i = 0; i < 4; ++i){
            while (!field.possibilityShipAdding(ship)){
                ship = generateRandShip(1);
            }
            field.addShip(ship);
        }
        for(int i = 0; i < 3; ++i){
            while (!field.possibilityShipAdding(ship)){
                ship = generateRandShip(2);
            }
            field.addShip(ship);
        }
        for(int i = 0; i < 2; ++i){
            while (!field.possibilityShipAdding(ship)){
                ship = generateRandShip(3);
            }
            field.addShip(ship);
        }
        while (!field.possibilityShipAdding(ship)){
            ship = generateRandShip(4);
        }
        field.addShip(ship);
    }

    static public Point getBotMovePoint(){
        return getRandomPoint();
    }

    static private Ship generateRandShip(int length){
        Point point = getRandomPoint();
        String direction = getRandomDirection();
        while (!Ship.validateShipPoint(point,length,direction)){
            point = getRandomPoint();
            direction = getRandomDirection();
        }
        return new Ship(point,length,direction);
    }

    static private Point getRandomPoint(){
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        return new Point(x,y);
    }

    static private String getRandomDirection(){
        if (random.nextInt(10) >= 5){
            return "vertical";
        } else {
            return "horizontal";
        }
    }
}
