package project;

import java.awt.Point;
import java.util.Random;

public class IslandWithTree implements Island {

    private OceanMap oceanMap;
    private int dimensions;
    
    private Point islandLocation;
    private int islandID;
    
    private Random rand = new Random();
    
    
    public IslandWithTree(OceanMap oceanMap, int islandID) {
        this.oceanMap = oceanMap;
        this.islandID = islandID;
        dimensions = oceanMap.getDimensions();
        islandLocation = placeIsland();
        oceanMap.setIslandLocation(islandLocation, islandID);
    }
    
    private Point placeIsland() {
        boolean placedIsland = false;
        int x = 0, y = 0;
        while(!placedIsland){
            x = rand.nextInt(dimensions);
            y = rand.nextInt(dimensions);
            if(oceanMap.getMap()[x][y] == 0){
                placedIsland = true;
            }
        }
        return new Point(x,y);
    }
    
    @Override
    public Point getIslandLocation() {
        return islandLocation;
    }

}
