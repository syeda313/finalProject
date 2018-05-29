package project;

import java.awt.Point;
import java.util.Random;

public class PirateBlackBeard implements Pirate {

    OceanMap oceanMap;
    Point pirateLocation;
    ChaseStrategy chaseStrategy;
    int dimensions;
    Random rand = new Random();
    
    public PirateBlackBeard(OceanMap oceanMap) {
        this.oceanMap = oceanMap;
        pirateLocation = placePirate();
        chaseStrategy = new ChaseStrategyExcellent(this);
        dimensions = oceanMap.getDimensions();
    }
    
    public Point getPirateLocation() {
        return pirateLocation;
    }
    
    private Point placePirate() {
        boolean placedShip = false;
        int x=0,y=0;
        while(!placedShip){
            x = rand.nextInt(dimensions);
            y = rand.nextInt(dimensions);
            if(oceanMap.getMap()[x][y] == 0){
                placedShip = true;
            }
        }
        return new Point(x,y);
    }

    @Override
    public void update(Ship ship) {
        // TODO Auto-generated method stub
        
    }

}
