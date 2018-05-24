package project;

import java.util.LinkedList;
import java.util.List;

public class ShipColumbus implements Ship {
	List<Observer> observers = new LinkedList<Observer>();

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
