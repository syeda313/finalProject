package project;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OceanExplorer extends Application {
	OceanMap oceanMap;
	int[][] islandMap;
	Pane root;
	int dimensions;
	final int scalingFactor = 20;
	Ship ship;
	Scene scene;

	Image shipImage;
	ImageView shipImageView;

	int numberofIslands = 10;
	Image[] Island;
	ImageView[] Islands;

	Image[] island;
	ImageView[] I;

	Image Pship;
	ImageView pirateShipView;

	int numberOfPirate = 10;
	// int numofIslands = 10;
	Pirate[] pirate;
	Image[] pirateImage;
	ImageView[] pirateImageView;

	ScaryMonster ScaryMonster;
	Thread mons;

	@Override
	public void start(Stage stage) throws Exception {
		oceanMap = OceanMap.getInstance();
		islandMap = oceanMap.getMap();
		dimensions = oceanMap.getDimensions();

		root = new AnchorPane();
		drawMap();

		ship = new ShipColumbus(oceanMap);

		pirate = new Pirate[numberOfPirate];
		for (int i = 0; i < numberOfPirate; i++) {
			pirate[i] = new SmallPirate(oceanMap, i);
			ship.registerObserver(pirate[i]);
		}

		loadShipImage();

		ScaryMonster = new ScaryMonster(scalingFactor);
		ScaryMonster.addToPane(root.getChildren());

		mons = new Thread(ScaryMonster);
		mons.start();

		scene = new Scene(root, 600, 600);
		stage.setTitle("Christopher Columbus Sails the Ocean Blue");
		stage.setScene(scene);
		stage.show();
		startSailing();

	}

	@SuppressWarnings("deprecation")
	@Override
	public void stop() {
		mons.stop();
	}

	private void loadShipImage() {
		shipImage = new Image("/project/ship.png", scalingFactor, scalingFactor, true, true);
		shipImageView = new ImageView(shipImage);

		shipImageView.setX(ship.getShipLocation().getX() * scalingFactor);
		shipImageView.setY(ship.getShipLocation().getY() * scalingFactor);
		root.getChildren().add(shipImageView);

		pirateImage = new Image[numberOfPirate];
		pirateImageView = new ImageView[numberOfPirate];
		for (int i = 0; i < numberOfPirate; i++) {

			pirateImage[i] = new Image("/project/pirateShip.png", scalingFactor, scalingFactor, true, true);
			pirateImageView[i] = new ImageView(pirateImage[i]);
			pirateImageView[i].setX(pirate[i].getPirateLocation().x * scalingFactor);
			pirateImageView[i].setY(pirate[i].getPirateLocation().y * scalingFactor);
			root.getChildren().add(pirateImageView[i]);
		}
	}

	public void drawMap() {

		for (int x = 0; x < dimensions; x++) {
			for (int y = 0; y < dimensions; y++) {
				Rectangle rect = new Rectangle(x * scalingFactor, y * scalingFactor, scalingFactor, scalingFactor);
				rect.setStroke(Color.BLACK);
				if (islandMap[x][y] == 0) {
					System.out.println("///////////");
					rect.setFill(Color.PALETURQUOISE);
					root.getChildren().add(rect);

				} //else if (islandMap[x][y] == 1) {
					//System.out.println("1111111111111");
					// Islands = new ImageView(Island);
					// Islands.setX(x * scalingFactor);
					// Islands.setY(y * scalingFactor);
					// root.getChildren().add(Islands);

				//} //else if (islandMap[x][y] == 2) {
					//System.out.println("+++++++++++");
					// I = new ImageView(Island);
					// I.setX(x * scalingFactor);
					// I.setY(y * scalingFactor);
					// root.getChildren().add(I);
				} else // Need to load an image
				{
					Rectangle rect1 = new Rectangle(x * scalingFactor, y * scalingFactor, scalingFactor, scalingFactor);
					rect1.setStroke(Color.BLACK);
					rect1.setFill(Color.RED);
					root.getChildren().add(rect1);
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

				for (int i = 0; i < numberOfPirate; i++) {
					pirateImageView[i].setX(pirate[i].getPirateLocation().x * scalingFactor);
					pirateImageView[i].setY(pirate[i].getPirateLocation().y * scalingFactor);
				}

			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
