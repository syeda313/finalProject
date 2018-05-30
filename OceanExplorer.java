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
	int dimensions;
	final int scalingFactor = 20;
	// final int islandCount = 10;
	OceanMap oceanMap;
	Ship ship;
	Scene scene;
	// Thread monstersThread;

	// ArrayList<PirateShip> p = new ArrayList<PirateShip>();

	Image shipImage;
	ImageView shipImageView;

	Image Pship;
	ImageView pirateShipView;

	Image Island;
	ImageView Islands;

	int numberOfPirate = 10;
	Pirate[] pirate;
	Image[] pirateImage;
	ImageView[] pirateImageView;

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

		scene = new Scene(root, 600, 600);
		stage.setTitle("Christopher Columbus Sails the Ocean Blue");
		stage.setScene(scene);
		stage.show();
		startSailing();
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
