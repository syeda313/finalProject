package project;

import java.awt.Point;
import java.util.List;

public interface Ship extends Subject{

    public void goEast();

    public void goWest();

    public void goNorth();

    public void goSouth();

    public Point getShipLocation();
	
    public abstract int getLife();

    public void updateShipLocation(Point newShipLocation);

    public List<Observer> getObservers();

    public OceanMap getOceanMap();

    public int getDimensions();

    public void getName();
    
//	public abstract int addLife();
	
	
	
	

}
