package project;

import java.awt.Point;

public class ChaseStrategyStupid implements ChaseStrategy {
    
    Pirate pirate;
    OceanMap oceanMap;
    
    
    
    public ChaseStrategyStupid(Pirate pirate) {
        this.pirate = pirate;
        oceanMap = pirate.getOceanMap();
    }
    
    @Override
    public void chase() {
        if (rand.nextInt(2) == 1) {
			if (big.getPirateLocation().x - position.x < 0
					&& oceanMap.getMap()[big.getPirateLocation().x + 1][big.getPirateLocation().y] == 0) {
				big.getPirateLocation().x++;
			} else if (oceanMap.getMap()[big.getPirateLocation().x - 1][big.getPirateLocation().y] == 0) {
				big.getPirateLocation().x--;
			}
			if (big.getPirateLocation().y - position.y < 0
					&& oceanMap.getMap()[big.getPirateLocation().x][big.getPirateLocation().y + 1] == 0) {
				big.getPirateLocation().y++;
			} else if (oceanMap.getMap()[big.getPirateLocation().x][big.getPirateLocation().y - 1] == 0) {
				big.getPirateLocation().y--;
			}
			if (small.getPirateLocation().x - position.x < 0
					&& oceanMap.getMap()[small.getPirateLocation().x + 1][small.getPirateLocation().y] == 0) {
				small.getPirateLocation().x++;
			} else if (oceanMap.getMap()[small.getPirateLocation().x - 1][small.getPirateLocation().y] == 0) {
				small.getPirateLocation().x--;
			}
			if (small.getPirateLocation().y - position.y < 0
					&& oceanMap.getMap()[small.getPirateLocation().x][small.getPirateLocation().y + 1] == 0) {
				small.getPirateLocation().y++;
			} else if (oceanMap.getMap()[small.getPirateLocation().x][small.getPirateLocation().y - 1] == 0) {
				small.getPirateLocation().y--;
			}
		}
        
        
    }

    @Override
    public void setPirate(Pirate pirate) {
        this.pirate = pirate;
        
    }

}
