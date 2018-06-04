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
		this.pirateLocation = pirate.getPirateLocation();
		this.shipLocation = oceanMap.getShipLocation();

		this.dis_x = pirateLocation.x - shipLocation.x;
		this.dis_y = pirateLocation.y - shipLocation.y;

		if (rand.nextInt(2) == 1) {

			if (this.pirateLocation.x - this.shipLocation.x < 0)
				this.pirateLocation.x++;
			else
				this.pirateLocation.x--;

			if (this.pirateLocation.y - this.shipLocation.y < 0)
				this.pirateLocation.y++;
			else
				this.pirateLocation.y--;
		}
	}
			
        
        
    }

    @Override
    public void setPirate(Pirate pirate) {
        this.pirate = pirate;
        
    }

}
