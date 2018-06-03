package project;

import java.awt.Point;

public interface Ship extends Subject{

    public void goEast();

    public void goWest();

    public void goNorth();

    public void goSouth();

    public Point getShipLocation();
	
    public abstract int getLife();
    
//	public abstract int addLife();
	
	
	
	

}
