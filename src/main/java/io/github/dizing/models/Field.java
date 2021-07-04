package io.github.dizing.models;

import java.awt.*;
import java.util.ArrayList;


public class Field {

    public ArrayList<Ship> shipsArray;
    int[] lengthShipCounters = {0, 0, 0, 0};
    private static final int[] LENGTH_SHIP_COUNTER_MAXES = {4, 3, 2, 1};

    static public Point calculateCoordsFromRaw(double xRaw, double yRaw, double xMax, double yMax) {
        double xCellSize = xMax / 10;
        double yCellSize = yMax / 10;
        int xCoord = (int) (xRaw / xCellSize);
        int yCoord = (int) (yRaw / yCellSize);
        return new Point(xCoord, yCoord);
    }

    public boolean possibilityShipAdding(Ship newShip){
        if (lengthShipCounters[newShip.length - 1] >= LENGTH_SHIP_COUNTER_MAXES[newShip.length - 1])
            return false;
        for (Ship ship : shipsArray) {
            if (!newShip.checkShipCollision(ship))
                return false;
        }
        return true;
    }

    static public int parseLengthStringBox(String raw){
        return Integer.parseInt(raw.split(" ")[0]);
    }

    public void addShip(Ship ship){
        shipsArray.add(ship);
        ++(lengthShipCounters[ship.length-1]);
    }

    public Field() {
        shipsArray = new ArrayList<Ship>();
    }

    public String getRemainShipCount(){
        int[] remainShipCount = new int[4];
        for(int i = 0; i < 4; ++i){
            remainShipCount[i] = LENGTH_SHIP_COUNTER_MAXES[i] - lengthShipCounters[i];
        }

        return "У вас осталось:\n" +
                remainShipCount[0] + " однопалубных\n" +
                remainShipCount[1] + " двухпалубных\n" +
                remainShipCount[2] + " трехпалубных\n" +
                remainShipCount[3] + " четырехпалубных\n";
    }
}