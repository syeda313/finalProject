package project;

import java.awt.Point;

public interface Pirate extends Observer{
	
    public Point getPirateLocation();

    public OceanMap getOceanMap();
    
    public int getPirateID();
	

}
