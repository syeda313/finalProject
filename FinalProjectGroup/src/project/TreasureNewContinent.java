package project;

import java.awt.Point;
import java.util.Random;

public class TreasureNewContinent implements Treasure {

    private OceanMap oceanMap;
    private Point treasureLocation;
    private Random rand = new Random();
    private int dimensions;
    
    
    public TreasureNewContinent(OceanMap oceanMap) {
        this.oceanMap = oceanMap;
        dimensions = oceanMap.getDimensions();
        treasureLocation = placeTreasure();
        oceanMap.setTreasureLocation(treasureLocation);
    }
    
    private Point placeTreasure() {
        boolean placedTreasure = false;
        int x = 0, y = 0;
        while(!placedTreasure){
            x = rand.nextInt(dimensions);
            y = rand.nextInt(dimensions);
            if(oceanMap.getMap()[x][y] == 0){
                placedTreasure = true;
            }
        }
        return new Point(x,y);
    }
    
    @Override
    public Point getTreasureLocation() {
        return treasureLocation;
    }


}
