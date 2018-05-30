package project;

import java.awt.Point;
import java.util.Random;

public class PirateDrake implements Pirate {

    OceanMap oceanMap;
	Point pirateLocation;
	ChaseStrategy chaseStrategy;
	int pirateID;
	int dimensions;
	Random rand = new Random();

	public PirateDrake(OceanMap oceanMap, int pirateID) {
		this.oceanMap = oceanMap;
		this.pirateID = pirateID;
		dimensions = oceanMap.getDimensions(); // set the attributes first, before it is used
		pirateLocation = placePirate();
		chaseStrategy = new ChaseStrategyGood(this);
	}

	public Point getPirateLocation() {
		return pirateLocation;
	}
	
	public int getPirateID() {
        return pirateID;
    }

	private Point placePirate() {
		boolean placedShip = false;
		int x = 0, y = 0;
		while (!placedShip) {
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if (oceanMap.getMap()[x][y] == 0) {
				placedShip = true;
			}
		}
		return new Point(x, y);
	}

	public void changeStrategy(ChaseStrategy ChaseStrategy) {
		this.chaseStrategy = ChaseStrategy;
		this.chaseStrategy.setPirate(this);
	}

	@Override
	public void update() {
		chaseStrategy.chase();

	}

	@Override
	public OceanMap getOceanMap() {
		return oceanMap;
	}


}
