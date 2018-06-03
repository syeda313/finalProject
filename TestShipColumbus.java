package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import project.OceanExplorer;
import project.OceanMap;
import project.ShipColumbus;

public class TestShipColumbus {

    OceanExplorer OceanExplorer = new OceanExplorer();
    OceanMap oceanMap = OceanMap.getInstance(2, 10);
    
    @Test
    public void testGetShipLocation() {
        ShipColumbus shipColumbus = new ShipColumbus(oceanMap);
        assertTrue(shipColumbus.getShipLocation().equals(oceanMap.getShipLocation()));
    }
}
