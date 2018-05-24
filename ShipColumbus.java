package project;

import java.util.LinkedList;
import java.util.List;

public class ShipColumbus implements Ship {
	List<Observer> observers = new LinkedList<Observer>();
	OceanMap OceanMap;
	Point ShipLocation;

	public ShipColumbus(OceanMap oceanMap) {
		this.OceanMap = oceanMap;

	}

	public Point getShipLocation() {
		return ShipLocation;
	}
	
	public void goEast() {
		if (ShipLocation.x < OceanMap.getDimensions() - 1 && OceanMap.isOcean(ShipLocation.x + 1, ShipLocation.y)) {
			ShipLocation.x++;
		}
		notifyObservers();

	}

	public void goWest() {
		if (ShipLocation.x > 0 && OceanMap.isOcean(ShipLocation.x - 1, ShipLocation.y)) {
			ShipLocation.x--;
		}
		notifyObservers();
	}

	public void goNorth() {
		if (ShipLocation.y > 0 && OceanMap.isOcean(ShipLocation.x, ShipLocation.y - 1)) {
			ShipLocation.y--;
		}
		notifyObservers();
	}

	public void goSouth() {
		if (ShipLocation.y < OceanMap.getDimensions() - 1 && OceanMap.isOcean(ShipLocation.x, ShipLocation.y + 1)) {
			ShipLocation.y++;
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
			pirateObserver.update(this);
		// TODO Auto-generated method stub

	}

}
