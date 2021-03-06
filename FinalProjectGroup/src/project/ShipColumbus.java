package project;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ShipColumbus implements Ship {
	List<Observer> observers = new LinkedList<Observer>();
	private OceanMap oceanMap;
	private Point shipLocation;
	private int dimensions;
	private Random rand = new Random();
	private int shipLifeLeft = 1;

	public ShipColumbus() {
	    
	}
	
	public ShipColumbus(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		dimensions = oceanMap.getDimensions();
		shipLocation = placeShip();
		oceanMap.updateShipLocation(shipLocation);
		oceanMap.updateShipLife(getLife());
	}
	
    private Point placeShip(){
        boolean placedShip = false;
        int x=0, y=0;
        while(!placedShip){
            x = rand.nextInt(dimensions);
            y = rand.nextInt(dimensions);
            if(oceanMap.getMap()[x][y] == 0){
                placedShip = true;
            }
        }
        System.out.println("Ship start at (" + x + ", " + y + ")");
        return new Point(x,y);
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
	public int getLife() {
	    return shipLifeLeft;
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

    
	@Override
	public OceanMap getOceanMap() {
	    return this.oceanMap;
	}
	
	@Override
	public int getDimensions() {
	    return this.dimensions;
	}

    @Override
    public List<Observer> getObservers() {
        return this.observers;
    }

    public void getName() {
        System.out.println("normal columbus");
    }

}