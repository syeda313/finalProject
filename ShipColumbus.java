package project;

import java.util.LinkedList;
import java.util.List;

public class ShipColumbus implements Ship {
	List<Observer> observers = new LinkedList<Observer>();
	OceanMap OceanMap;
	Point ShipLocation;

	public ShipColumbus(OceanMap oceamMap) {
		this.OceanMap = oceamMap;

	}

	public Point getShipLocation() {
		return ShipLocation;
	}
	
	
		public void goEast() {
		if (currentLocation.x < oceanMap.getDimensions() - 1
				&& oceanMap.isOcean(currentLocation.x + 1, currentLocation.y)) {
			currentLocation.x++;
		}
		notifyObservers();

	}

	public void goWest() {
		if (currentLocation.x > 0 && oceanMap.isOcean(currentLocation.x - 1, currentLocation.y)) {
			currentLocation.x--;
		}
		notifyObservers();
	}

	public void goNorth() {
		if (currentLocation.y > 0 && oceanMap.isOcean(currentLocation.x, currentLocation.y - 1)) {
			currentLocation.y--;
		}
		notifyObservers();
	}

	public void goSouth() {
		if (currentLocation.y < oceanMap.getDimensions() - 1
				&& oceanMap.isOcean(currentLocation.x, currentLocation.y + 1)) {
			currentLocation.y++;
		}
		notifyObservers();


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
