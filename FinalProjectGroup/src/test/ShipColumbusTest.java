package test;

import static org.junit.Assert.*;
import project.ShipColumbus;
import project.OceanMap;

import org.junit.Test;

public class ShipColumbusTest {

	OceanMap oceanMap = OceanMap.getInstance(2, 10);

	@Test
	public void testGetShipLocation() {
		ShipColumbus shipColumbus = new ShipColumbus(oceanMap);
		assertTrue(shipColumbus.getShipLocation().equals(oceanMap.getShipLocation()));
	}

	@Test
	public void testGetLife() {
		ShipColumbus shipColumbus = new ShipColumbus(oceanMap);
		assertNotNull(shipColumbus.getLife());
	}

	@Test
	public void testIfShipLocationsAreNotEqual() {
		ShipColumbus shipColumbus = new ShipColumbus(oceanMap);
		shipColumbus.getShipLocation();
		assertNotEquals(shipColumbus.getShipLocation(), oceanMap.getTreasureLocation());

	}

}