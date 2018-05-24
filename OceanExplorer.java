package project;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OceanExplorer extends Application {
	int[][] islandMap;
	Pane root;
	final int dimensions = 50;
	final int scalingFactor = 40;
	// final int islandCount = 10;
	OceanMap oceanMap;
	Scene scene;
	// Thread monstersThread;

	// ArrayList<PirateShip> p = new ArrayList<PirateShip>();

	Image shipImage;
	ImageView shipImageView;

	Image Pship;
	ImageView pirateShipView;

	Image Island;
	ImageView Islands;

	Image s;
	ImageView sShip;
	Ship ship;
	// PirateShip pirateShip;
	// PirateShip pirate;

	@Override
	public void start(Stage stage) throws Exception {
		oceanMap = OceanMap.getInstance();
		islandMap = oceanMap.getMap();

		root = new AnchorPane();
		drawMap();

		// ship = new Ship(oceanMap);
		// pirateShip = new PirateShip(oceanMap);
		// pirate = new PirateShip(oceanMap);

		// p.add(pirate);
		// p.add(pirateShip);

		// for (PirateShip pirate : p)
		// ship.addObserver(pirate);
		loadShipImage();
		// ship.notifyObservers();

		scene = new Scene(root, 900, 900);
		stage.setTitle("Christopher Columbus Sails the Ocean Blue");
		stage.setScene(scene);
		stage.show();
		startSailing();
	}
	// TODO Auto-generated method stub

	private void startSailing() {
		// TODO Auto-generated method stub

	}

	private void loadShipImage() {
		// TODO Auto-generated method stub

	}

	public void drawMap() {
		for (int x = 0; x < dimensions; x++) {
			for (int y = 0; y < dimensions; y++) {

				if (islandMap[x][y] == 0) {
					Rectangle rect = new Rectangle(x * scalingFactor, y * scalingFactor, scalingFactor, scalingFactor);
					rect.setStroke(Color.BLACK);
					rect.setFill(Color.PALETURQUOISE);
					root.getChildren().add(rect);
				} else // Need to load an image
				{
					Rectangle rect = new Rectangle(x * scalingFactor, y * scalingFactor, scalingFactor, scalingFactor);
					rect.setStroke(Color.BLACK);
					rect.setFill(Color.RED);
					root.getChildren().add(rect);
				}
			}
		}
	}
	
		private void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				switch (ke.getCode()) {
				case RIGHT:
					ship.goEast();
					break;
				case LEFT:
					ship.goWest();
					break;
				case UP:
					ship.goNorth();
					break;
				case DOWN:
					ship.goSouth();
					break;
				default:
					break;
				}

				shipImageView.setX(ship.getShipLocation().x * scalingFactor);
				shipImageView.setY(ship.getShipLocation().y * scalingFactor);

				//pirateShipView.setX(pirateShip.getPirateShipLocation().x * scalingFactor);
				//pirateShipView.setY(pirateShip.getPirateShipLocation().y * scalingFactor);

				//sShip.setX(pirate.getPirateShipLocation().x * scalingFactor);
				//sShip.setY(pirate.getPirateShipLocation().y * scalingFactor);
			}
		});
	}

	

	public static void main(String[] args) {
		launch(args);
	}

}
