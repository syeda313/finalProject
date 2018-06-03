package project;

import java.awt.Point;
import java.util.Random;

public class PirateDrake implements Pirate {

    private OceanMap oceanMap;
    private int dimensions;
    
    private Point pirateLocation;
    private ChaseStrategy chaseStrategy;
    private int pirateID;
    
    private Random rand = new Random();
    
    public PirateDrake(OceanMap oceanMap, int pirateID) {
        this.oceanMap = oceanMap;
        this.pirateID = pirateID;
        dimensions = oceanMap.getDimensions();    // set the attributes first before it is used!!!!!!!!!!!!!!
        pirateLocation = placePirate();
        chaseStrategy = new ChaseStrategyStupid(this);
        oceanMap.updatePirateLocation(getPirateLocation(), getPirateID());
    }
    
    public Point getPirateLocation() {
        return pirateLocation;
    }
    
    public int getPirateID() {
        return pirateID;
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
    
    public void changeStrategy(ChaseStrategy chaseStrategy) {
        this.chaseStrategy = chaseStrategy;
        this.chaseStrategy.setPirate(this);
    }

    @Override
    public void update() {
        chaseStrategy.chase();
    }

    @Override
    public OceanMap getOceanMap() {
        return oceanMap;
    }

}
