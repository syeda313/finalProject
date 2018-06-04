import static org.junit.Assert.*;
import project.OceanMap;
import project.OceanExplorer;

import org.junit.Test;
import project.TreasureNewContinent;

public class TreasureNewContinentTest {

	OceanExplorer OceanExplorer = new OceanExplorer();
	OceanMap oceanMap = OceanMap.getInstance(2, 10);

	@Test
	public void testTreasure() {
		TreasureNewContinent treasure = new TreasureNewContinent(oceanMap);
		assertTrue(treasure.getTreasureLocation().equals(oceanMap.getTreasureLocation()));
	}

	@Test
	public void testTreasureNotAtOtherLocation() {
		TreasureNewContinent treasure = new TreasureNewContinent(oceanMap);
		assertTrue(!(treasure.getTreasureLocation().equals(oceanMap.getShipLocation())));
	}

	@Test
	public void testLocations() {
		TreasureNewContinent treasure = new TreasureNewContinent(oceanMap);
		treasure.getTreasureLocation();
		assertEquals(treasure.getTreasureLocation(), oceanMap.getTreasureLocation());

	}
