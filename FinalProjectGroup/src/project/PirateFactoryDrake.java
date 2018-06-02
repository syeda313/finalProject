package project;

import javafx.scene.image.Image;

public class PirateFactoryDrake implements PirateFactory {

    private OceanMap oceanMap;
    
    public PirateFactoryDrake(OceanMap oceanMap) {
        this.oceanMap = oceanMap;
    }
    
    @Override
    public Image setPirateImage() {
        return new Image("image\\ship.png", oceanMap.getScalingFactor() - 2, oceanMap.getScalingFactor() - 2, true, true);
    }

    @Override
    public String setPirateNationality() {
        return "Spain";
    }

    @Override
    public String setPirateBackground() {
        // TODO Auto-generated method stub
        return null;
    }

}
