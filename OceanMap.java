package project;

import java.util.Random;

public class OceanMap {
	private int[][] grid;
	private int dimensions = 50;
	Random rand = new Random();
	private static OceanMap uniqueInstance;

	private OceanMap() {
		createGrid();
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

	public int[][] getMap() {
		return grid;
	}

	public int getDimensions() {
		return dimensions;
	}

	public boolean isOcean(int x, int y) {
		if (grid[x][y] == 0)
			return true;
		else
			return false;
	}
}
