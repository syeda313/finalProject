package project;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ShipColumbusSuperPowerOfAddLife extends ShipColumbus {

    List<Observer> observers = new LinkedList<Observer>();
    private OceanMap oceanMap;
    private Point shipLocation;
    private int dimensions;
    
    Ship columbusShip;
    
    public ShipColumbusSuperPowerOfAddLife(Ship columbusShip) {
        this.columbusShip = columbusShip;
        
        this.dimensions = columbusShip.getDimensions();
        this.observers = columbusShip.getObservers();
        this.oceanMap = columbusShip.getOceanMap();
        this.shipLocation = columbusShip.getShipLocation();
        
        System.out.println("Columbus adds 1 life. Now he has " + getLife() + " lifes!");
    }
    
    public void getName() {
        System.out.println("supercolumbus");
    }
    
    @Override
    public int getLife() {
        return 1 + columbusShip.getLife();
    }
    
    @Override
    public void updateShipLocation(Point newShipLocation) {
        this.shipLocation = (Point) newShipLocation.clone();
    }
    
    @Override
    public Point getShipLocation() {
        return shipLocation;
    }
    
    @Override
    public void goEast() {
        if (shipLocation.x < oceanMap.getDimensions() - 1
                && oceanMap.isOcean(shipLocation.x + 1, shipLocation.y)) {
            shipLocation.x++;
            oceanMap.updateShipLocation(shipLocation);
        }
        notifyObservers();

    }

    @Override
    public void goWest() {
        if (shipLocation.x > 0 && oceanMap.isOcean(shipLocation.x - 1, shipLocation.y)) {
            shipLocation.x--;
            oceanMap.updateShipLocation(shipLocation);
        }
        notifyObservers();
    }

    @Override
    public void goNorth() {
        if (shipLocation.y > 0 && oceanMap.isOcean(shipLocation.x, shipLocation.y - 1)) {
            shipLocation.y--;
            oceanMap.updateShipLocation(shipLocation);
        }
        notifyObservers();
    }

    @Override
    public void goSouth() {
        if (shipLocation.y < oceanMap.getDimensions() - 1
                && oceanMap.isOcean(shipLocation.x, shipLocation.y + 1)) {
            shipLocation.y++;
            oceanMap.updateShipLocation(shipLocation);
        }
        notifyObservers();
    }
    


    @Override
    public void registerObserver(Observer o) {
        observers.add(o);

    }

    @Override
    public void removeObserver(Observer o) {
        if (observers.contains(o))
            observers.remove(o);

    }

    @Override
    public void notifyObservers() {
        for (Observer pirateObserver : observers)
            pirateObserver.update();
    }

}
