package project;

import java.awt.Point;
import java.awt.print.Printable;
import java.util.Random;

public class OceanMap {
	private int[][] grid;
	private int dimensions = 30;
	private int scalingFactor = 20;
	Random rand = new Random();
	private static OceanMap uniqueInstance;
	
	private Point shipLocation;
	private Point[] pirateLocation;
	private Point[] islandLocation;
	private Point treasureLocation;

	private OceanMap() {
		createGrid();
		treasureLocation = placeTreasur();
		shipLocation = new Point(-1, -1);
		updateShipLocation(shipLocation);
	}

	public static OceanMap getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new OceanMap();
		}
		return uniqueInstance;

	}

	private void createGrid() {
		grid = new int[dimensions][dimensions];
		for (int x = 0; x < dimensions; x++)
			for (int y = 0; y < dimensions; y++)
				grid[x][y] = 0;

	}
	
    private Point placeTreasur() {
        boolean treasurePlaced = false;
        int x = 0, y = 0;
        while(!treasurePlaced){
            x = rand.nextInt(dimensions);
            y = rand.nextInt(dimensions);
            if(getMap()[x][y] == 0){
                treasurePlaced = true;
            }
        }
        System.out.println("Treasure located at (" + x + ", " + y + ")");
        return new Point(x,y);
    }

	public int[][] getMap() {
		return grid;
	}

	public int getDimensions() {
		return dimensions;
	}
	
	public int getScalingFactor() {
        return scalingFactor;
    }
	
	public Point getShipLocation() {
	    return shipLocation;
	}
	
	public Point getPirateLocation(int i) {
	    return pirateLocation[i];
	}
	
	public Point getTreasureLocation() {
	    return treasureLocation;
	}
	
	public void updateShipLocation(Point shipLocation) {
	    if (this.shipLocation.x != -1) {
	        grid[this.shipLocation.x][this.shipLocation.y] = 0;
	        grid[shipLocation.x][shipLocation.y] = 1;
	    }
	}
	
	public void updatePirateLocation(Point privateLocation, int privateNumber) {
	    
	}

	public boolean isOcean(int x, int y) {
		if (grid[x][y] == 0)
			return true;
		else
			return false;
	}

    
}
