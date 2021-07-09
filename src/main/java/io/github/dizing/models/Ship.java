package io.github.dizing.models;

import java.awt.*;
import java.util.ArrayList;

public class Ship {
    public ArrayList<Point> shipPoints;
    public int length;
    public String direction;
    public ArrayList<Point> blastedShipPoints;

    static public boolean validateShipPoint(Point leftTop, int shipLength, String direction) {
        return direction.equals("horizontal") & leftTop.x + shipLength < 11 |
                direction.equals("vertical") & leftTop.y + shipLength < 11;
    }

    public boolean checkShipCollision(Ship ship) {
        int xDiff, yDiff;
        for (Point point1 : this.shipPoints) {
            for (Point point2 : ship.shipPoints) {
                xDiff = Math.abs(point1.x - point2.x);
                yDiff = Math.abs(point1.y - point2.y);

                if (xDiff < 2 & yDiff < 2 | (xDiff == 1 & yDiff == 1))
                    return false;
            }
        }
        return true;
    }

    public Ship(Point leftTop, int shipLength, String direction) {
        this.length = shipLength;
        this.direction = direction;
        shipPoints = new ArrayList<Point>();
        blastedShipPoints = new ArrayList<Point>();
        shipPoints.add(leftTop);
        int x = leftTop.x;
        int y = leftTop.y;
        for (int i = 1; i < shipLength; ++i) {
            if (direction.equals("horizontal")) {
                ++x;
            } else if (direction.equals("vertical")) {
                ++y;
            }
            shipPoints.add(new Point(x, y));
        }
    }

    public boolean checkBlasted(){
        return blastedShipPoints.containsAll(shipPoints) && shipPoints.containsAll(blastedShipPoints);
    }

    public void blast(Point point){
        blastedShipPoints.add(point);
    }

}
