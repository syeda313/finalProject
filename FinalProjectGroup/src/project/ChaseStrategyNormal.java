package project;

import java.awt.Point;
import java.util.Random;

public class ChaseStrategyNormal implements ChaseStrategy {
    
    Pirate pirate;
    OceanMap oceanMap;
    
    Point pirateLocation;
    Point shipLocation;
    int dimensions;
    int dis_x;
    int dis_y;
    
    Random rand;
    

    public ChaseStrategyNormal(Pirate pirate) {
        this.pirate = pirate;
        oceanMap = pirate.getOceanMap();
        rand = new Random();
        
        this.dimensions = oceanMap.getDimensions();
    }

    @Override
    public void chase() {
        this.pirateLocation = pirate.getPirateLocation();
        this.shipLocation = oceanMap.getShipLocation();
        
        this.dis_x = pirateLocation.x - shipLocation.x;
        this.dis_y = pirateLocation.y - shipLocation.y;
        
        int changed = 0;
        
        System.out.println("------");
        System.out.println("Mr.Columbus " + " is at (" + shipLocation.x + ", " + shipLocation.y + ")");
        System.out.println("Pirate No. " + pirate.getPirateID() + " is at (" + pirateLocation.x + ", " + pirateLocation.y + ")");
        System.out.println("dis_x = " + Math.abs(dis_x) + ", dis_y = " + Math.abs(dis_y));
        
        if (Math.abs(dis_x) + Math.abs(dis_y) == 1) {
            oceanMap.updatePirateLocation(shipLocation, pirate.getPirateID());
            return;
        }
        
        if (Math.abs(dis_x) > Math.abs(dis_y) || (Math.abs(dis_x) == Math.abs(dis_y) && rand.nextInt(2) == 0)) {                // go x-axis
            changed = pirateLocation.x - dis_x / Math.abs(dis_x);
            if (changed >= 0 && changed < dimensions) {
                if (oceanMap.isOcean(changed, pirateLocation.y) == true) {
                    pirate.getPirateLocation().x = changed;
                } else if (dis_y == 0) {
                    int change = rand.nextInt(2) * 2 - 1;
                    if (pirateLocation.y + change >= 0 && pirateLocation.y + change < dimensions 
                            && oceanMap.isOcean(pirateLocation.x, pirateLocation.y + change) == true) {
                        pirate.getPirateLocation().y = pirateLocation.y + change;
                    } else if (pirateLocation.y - change >= 0 && pirateLocation.y - change < dimensions 
                            && oceanMap.isOcean(pirateLocation.x, pirateLocation.y - change) == true) {
                        pirate.getPirateLocation().y = pirateLocation.y - change;
                    }
                } else if (pirateLocation.y - dis_y / Math.abs(dis_y) >= 0 && pirateLocation.y - dis_y / Math.abs(dis_y) < dimensions
                        && oceanMap.isOcean(pirateLocation.x, pirateLocation.y - dis_y / Math.abs(dis_y)) == true) {
                    pirate.getPirateLocation().y = pirateLocation.y - dis_y / Math.abs(dis_y);
                } else if (pirateLocation.y + dis_y / Math.abs(dis_y) >= 0 && pirateLocation.y + dis_y / Math.abs(dis_y) < dimensions
                        && oceanMap.isOcean(pirateLocation.x, pirateLocation.y + dis_y / Math.abs(dis_y)) == true) {
                    pirate.getPirateLocation().y = pirateLocation.y + dis_y / Math.abs(dis_y);
                } else if (pirateLocation.x + dis_x / Math.abs(dis_x) >= 0 && pirateLocation.x + dis_x / Math.abs(dis_x) < dimensions
                        && oceanMap.isOcean(pirateLocation.x + dis_x / Math.abs(dis_x), pirateLocation.y) == true) {
                    pirate.getPirateLocation().x = pirateLocation.x + dis_x / Math.abs(dis_x);
                } else {
                    System.out.println("I can't believe it... YOU GOT TRAPPED!!!");
                }
            }
        } else if (Math.abs(dis_x) < Math.abs(dis_y)  || (Math.abs(dis_x) == Math.abs(dis_y) && rand.nextInt(2) == 1)) {         // go y-axis
            changed = pirateLocation.y - dis_y / Math.abs(dis_y);
            if (changed >= 0 && changed < dimensions) {
                if (oceanMap.isOcean(pirateLocation.x, changed) == true) {
                    pirate.getPirateLocation().y = changed;
                } else if (dis_x == 0) {
                    int change = rand.nextInt(2) * 2 - 1;
                    if (pirateLocation.x + change >= 0 && pirateLocation.x + change < dimensions 
                            && oceanMap.isOcean(pirateLocation.x + change, pirateLocation.y) == true) {
                        pirate.getPirateLocation().x = pirateLocation.x + change;
                    } else if (pirateLocation.x - change >= 0 && pirateLocation.x - change < dimensions 
                            && oceanMap.isOcean(pirateLocation.x - change, pirateLocation.y) == true) {
                        pirate.getPirateLocation().x = pirateLocation.x - change;
                    }
                } else if (oceanMap.isOcean(pirateLocation.x - dis_x / Math.abs(dis_x), pirateLocation.y) == true) {
                    pirate.getPirateLocation().x = pirateLocation.x - dis_x / Math.abs(dis_x);
                } else if (oceanMap.isOcean(pirateLocation.x + dis_x / Math.abs(dis_x), pirateLocation.y) == true) {
                    pirate.getPirateLocation().x = pirateLocation.x + dis_x / Math.abs(dis_x);
                } else if (oceanMap.isOcean(pirateLocation.x, pirateLocation.y + dis_y / Math.abs(dis_y)) == true) {
                    pirate.getPirateLocation().y = pirateLocation.y + dis_y / Math.abs(dis_y);
                } else {
                    System.out.println("I can't believe it... YOU GOT TRAPPED!!!");
                }
            }
        } else {
            System.out.println("How did you get here????");
        }
        
        oceanMap.updatePirateLocation(pirate.getPirateLocation(), pirate.getPirateID());
    }

    @Override
    public void setPirate(Pirate pirate) {
        this.pirate = pirate;
        
    }
}
