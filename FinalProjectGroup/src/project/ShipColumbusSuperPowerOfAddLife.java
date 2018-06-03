package project;

public class ShipColumbusSuperPowerOfAddLife extends ShipColumbus {

    Ship columbusShip;
    
    public ShipColumbusSuperPowerOfAddLife(Ship columbusShip) {
        this.columbusShip = columbusShip;
    }
    
    public int getLife() {
        return 1 + columbusShip.getLife();
    }

}
