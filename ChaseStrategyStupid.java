package project;

import java.awt.Point;

public class ChaseStrategyStupid implements ChaseStrategy {
    
    Pirate pirate;
    OceanMap oceanMap;
    Random rand;
    
    
    
    public ChaseStrategyStupid(Pirate pirate) {
        this.pirate = pirate;
        oceanMap = pirate.getOceanMap();
	rand = new Random();
    }
    
    @Override
    public void chase() {
        if (rand.nextInt(2) == 1) {
		if (rand.nextInt(2) == 1) {
			if (pirate.getPirateLocation().x - oceanMap.getShipLocation().x < 0
					&& oceanMap.getMap()[pirate.getPirateLocation().x + 1][pirate.getPirateLocation().y] == 0) {
				pirate.getPirateLocation().x++;
				oceanMap.updatePirateLocation(pirate.getPirateLocation(), pirate.getPirateID());
			} else if (oceanMap.getMap()[pirate.getPirateLocation().x - 1][pirate.getPirateLocation().y] == 0) {
				pirate.getPirateLocation().x--;
				oceanMap.updatePirateLocation(pirate.getPirateLocation(), pirate.getPirateID());
			}
			if (pirate.getPirateLocation().y - oceanMap.getShipLocation().y < 0
					&& oceanMap.getMap()[pirate.getPirateLocation().x][pirate.getPirateLocation().y + 1] == 0) {
				pirate.getPirateLocation().y++;
				oceanMap.updatePirateLocation(pirate.getPirateLocation(), pirate.getPirateID());
			} else if (oceanMap.getMap()[pirate.getPirateLocation().x][pirate.getPirateLocation().y - 1] == 0) {
				pirate.getPirateLocation().y--;
				oceanMap.updatePirateLocation(pirate.getPirateLocation(), pirate.getPirateID());
			}
		}
			
        
        
    }

    @Override
    public void setPirate(Pirate pirate) {
        this.pirate = pirate;
        
    }

}
