package project;

public class ChaseStrategyExcellent implements ChaseStrategy {
    
    Pirate pirate;

    public ChaseStrategyExcellent(Pirate pirate) {
        this.pirate = pirate;
    }

    @Override
    public void chase() {
        
        
    }

    @Override
    public void setPirate(Pirate pirate) {
        this.pirate = pirate;
        
    }
}
