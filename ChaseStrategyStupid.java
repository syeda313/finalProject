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
        
        
    }

    @Override
    public void setPirate(Pirate pirate) {
        this.pirate = pirate;
        
    }

}
