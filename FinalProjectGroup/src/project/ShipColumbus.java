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

	public ShipColumbus(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		dimensions = oceanMap.getDimensions();
		shipLocation = placeShip();
		oceanMap.updateShipLocation(shipLocation);
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
        return new Point(x,y);
    }

	public Point getShipLocation() {
		return shipLocation;
	}
	
	
	public void goEast() {
    	if (shipLocation.x < oceanMap.getDimensions() - 1
    			&& oceanMap.isOcean(shipLocation.x + 1, shipLocation.y)) {
    		shipLocation.x++;
    		oceanMap.updateShipLocation(shipLocation);
    		winCheck();
    	}
    	notifyObservers();

	}

	public void goWest() {
		if (shipLocation.x > 0 && oceanMap.isOcean(shipLocation.x - 1, shipLocation.y)) {
			shipLocation.x--;
			oceanMap.updateShipLocation(shipLocation);
			winCheck();
		}
		notifyObservers();
	}

	public void goNorth() {
		if (shipLocation.y > 0 && oceanMap.isOcean(shipLocation.x, shipLocation.y - 1)) {
			shipLocation.y--;
			oceanMap.updateShipLocation(shipLocation);
			winCheck();
		}
		notifyObservers();
	}

	public void goSouth() {
		if (shipLocation.y < oceanMap.getDimensions() - 1
				&& oceanMap.isOcean(shipLocation.x, shipLocation.y + 1)) {
			shipLocation.y++;
			oceanMap.updateShipLocation(shipLocation);
			winCheck();
		}
		notifyObservers();
	}
	
	public void winCheck() {
	    if (shipLocation.equals(oceanMap.getTreasureLocation())) {
//	        Scanner scanner = new Scanner(System.in);
//	        String string = scanner.nextLine();
	        for (int i = 0; i <= 5; i++) {
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            for (int j = 0; j <= i; j++) {
	                System.out.print(".");
	            }
	            System.out.println("");
	        }
	        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	        try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
	        System.out.println("COLUMBUS FOUND THE NEW CONTINENT. HISTORY DISCOVERED.");
	        System.exit(0);
	        
	    }
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
		// TODO Auto-generated method stub

	}


}