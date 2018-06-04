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
	private int numberOfPirate;
	private Point[] islandLocation;
	private int numberOfIsland;
	private Point treasureLocation;
	
	private int shipLifeLeft;
	//TODO: remains to be initialized and valued

	private OceanMap(int numberOfPirate, int numberOfIsland) {
	    
		createGrid();
		
		this.numberOfPirate = numberOfPirate;
		pirateLocation = new Point[numberOfPirate];
		this.numberOfIsland = numberOfIsland;
		islandLocation = new Point[numberOfIsland];
		
		shipLocation = new Point(-1, -1);
		
		for (int i = 0; i < this.numberOfPirate; i++) {
		    pirateLocation[i] = new Point(-1, -1);
		}
		
		
	}

	public static OceanMap getInstance(int numberOfPirate, int numberOfIsland) {
		if (uniqueInstance == null) {
			uniqueInstance = new OceanMap(numberOfPirate, numberOfIsland);
		}
		return uniqueInstance;

	}

	private void createGrid() {
		grid = new int[dimensions][dimensions];
		for (int x = 0; x < dimensions; x++)
			for (int y = 0; y < dimensions; y++)
				grid[x][y] = 0;

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
	
	public int getShipLife() {
	    return shipLifeLeft;
	}
	
	public void updateShipLocation(Point shipLocation) {
	    if (this.shipLocation.x != -1) {
	        grid[this.shipLocation.x][this.shipLocation.y] = 0;
	    }
	    this.shipLocation = (Point) shipLocation.clone();          // NEVER FORGET THAT YOU CAN'T USE = BETWEEN OBJECTS
        grid[this.shipLocation.x][this.shipLocation.y] = 1;
        
        winCheck();
	}
	
    public void updatePirateLocation(Point pirateLocation, int pirateNumber) {
	    if (this.pirateLocation[pirateNumber].x != -1) {
            grid[this.pirateLocation[pirateNumber].x][this.pirateLocation[pirateNumber].y] = 0;
	    }
	    this.pirateLocation[pirateNumber] = (Point) pirateLocation.clone();
        grid[this.pirateLocation[pirateNumber].x][this.pirateLocation[pirateNumber].y] = 2;
        
        loseCheck(pirateNumber);
	}
	
    public void setTreasureLocation(Point treasureLocation) {
	    this.treasureLocation = treasureLocation;
	}

	public void setIslandLocation(Point islandLocation, int islandNumber) {
        this.islandLocation[islandNumber] = islandLocation;
        grid[this.islandLocation[islandNumber].x][this.islandLocation[islandNumber].y] = 3;
	}
	
	public void updateShipLife(int life) {
	    this.shipLifeLeft = life;
	}
	
	public boolean isOcean(int x, int y) {
		if (grid[x][y] == 0)
			return true;
		else
			return false;
	}

    public void setNumberOfPirate(int numberOfPirate) {
        this.numberOfPirate = numberOfPirate;    
    }

    public void setNumberOfIsland(int numberOfIsland) {
        this.numberOfIsland = numberOfIsland; 
    }

    private void winCheck() {
        if (shipLocation.equals(treasureLocation)) {
//          Scanner scanner = new Scanner(System.in);
//          String string = scanner.nextLine();
            winProcedure();
        }
    }
    
    private void loseCheck(int pirateNumber) {
        System.out.println("------");
        System.out.println("It's a sunny day.");
        System.out.println("Columbus has " + shipLifeLeft + " lifes left.");
        if (pirateLocation[pirateNumber].equals(shipLocation)) {
            System.out.println("Columbus collides with pirate.");
            if (shipLifeLeft == 1) {
                loseProcedure();
            } else {
                shipLifeLeft--;
                columbusReborn();
            }
            System.out.println("Columbus only has " + shipLifeLeft + " lifes left now.");
        }
    }
    
    private void winProcedure() {
        for (int i = 0; i <= 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(".");
            }
            System.out.println("");
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("COLUMBUS FOUND THE NEW CONTINENT. HISTORY DISCOVERED.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    
    private void loseProcedure() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Unfortunately, Mr.Columbus was caught by pirate.");
        System.exit(0);
    }

    private void columbusReborn() {
        int x;
        int y;
        while (true) {
            x = rand.nextInt(dimensions);
            y = rand.nextInt(dimensions);
            if (isOcean(x, y)) {
                shipLocation = new Point(x, y);
                System.out.println("Columbus is relocated at " + "(" + x + ", " + y + ")");
                break;
            } 
        }
        
    }
}
