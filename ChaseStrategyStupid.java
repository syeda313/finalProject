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
		if (rand.nextInt(2) == 1) { // Slow down the cat
			if (pirate.getPirateLocation().x - oceanMap.getShipLocation().x < oceanMap.getDimensions() - 1) {
				pirate.getPirateLocation().x++;
				oceanMap.updatePirateLocation(pirate.getPirateLocation(), pirate.getPirateID());
				System.out.print("------east");
			} else {
				pirate.getPirateLocation().x--;
				oceanMap.updatePirateLocation(pirate.getPirateLocation(), pirate.getPirateID());
				System.out.print("+++++++west");
			}
			if (pirate.getPirateLocation().y - oceanMap.getShipLocation().y < oceanMap.getDimensions() - 1) {
				pirate.getPirateLocation().y++;
				oceanMap.updatePirateLocation(pirate.getPirateLocation(), pirate.getPirateID());
				System.out.print("========south");
			} else {
				pirate.getPirateLocation().y--;
				oceanMap.updatePirateLocation(pirate.getPirateLocation(), pirate.getPirateID());
				System.out.print("!!!!!!!!!north");
			}
		}
	}
			
        
        
    }

    @Override
    public void setPirate(Pirate pirate) {
        this.pirate = pirate;
        
    }

}
