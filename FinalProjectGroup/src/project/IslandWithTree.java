package project;

import java.awt.Point;
import java.util.Random;

public class IslandWithTree implements Island {

    private OceanMap oceanMap;
    private Point islandLocation;
    private Random rand = new Random();
    private int dimensions;
    
    
    public IslandWithTree(OceanMap oceanMap) {
        this.oceanMap = oceanMap;
        dimensions = oceanMap.getDimensions();
        islandLocation = placeIsland();
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
