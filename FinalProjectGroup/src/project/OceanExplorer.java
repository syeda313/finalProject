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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OceanExplorer extends Application {
    
	int[][] islandMap;
	AnchorPane root;
	int dimensions;
	int scalingFactor;
	
	OceanMap oceanMap;
	Scene scene;

	Image shipImage;
	ImageView shipImageView;

	Pirate[] pirate;
	int numberOfPirate = 2;
    Image[] pirateImage;
    ImageView[] pirateImageView;

    Island[] island;
    int numberOfIsland = 10;
	Image[] islandImage;
	ImageView[] islandImageView;
	
	Treasure treasure;
	Image treasureImage;
	ImageView treasureImageView;

	Ship ship;
	
	Monster monster;
    Thread monstersThread;

    
    
    
	@Override
	public void start(Stage stage) throws Exception {
		oceanMap = OceanMap.getInstance(numberOfPirate, numberOfIsland);
		islandMap = oceanMap.getMap();
		dimensions = oceanMap.getDimensions();
		scalingFactor = oceanMap.getScalingFactor();

		root = new AnchorPane();
		drawMap();
		
		monster = new Monster(dimensions, scalingFactor);
        monster.addToPane(root.getChildren());
        stage.show();
        
        monstersThread = new Thread(monster);
        monstersThread.start();

		ship = new ShipColumbus(oceanMap);
		
		System.out.println("------");
		System.out.println("Please choose which pirate you want:");
		pirate = new Pirate[numberOfPirate];
        for (int i = 0; i < numberOfPirate; i++) {
            pirate[i] = new PirateDrake(oceanMap, i);
            ship.registerObserver(pirate[i]);
        }

        island = new Island[numberOfIsland];
        for (int i = 0; i < numberOfIsland; i++) {
            island[i] = new IslandWithTree(oceanMap, i);
        }
        
        treasure = new TreasureNewContinent(oceanMap);
        
		loadImages();

		scene = new Scene(root, 600, 600);
		stage.setTitle("Christopher Columbus Sails the Ocean Blue");
		stage.setScene(scene);
		stage.show();
		startSailing();
	}

	private void startSailing(){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent ke) {
                switch(ke.getCode()){
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
//                mapReader();
                
                shipImageView.setX(ship.getShipLocation().x*scalingFactor);
                shipImageView.setY(ship.getShipLocation().y*scalingFactor);
                
                for (int i = 0; i < numberOfPirate; i++) {
                    pirateImageView[i].setX(pirate[i].getPirateLocation().x * scalingFactor);
                    pirateImageView[i].setY(pirate[i].getPirateLocation().y * scalingFactor);
                }
            }
        });
    }

	private void loadImages() {
	    // ship image setup
	    shipImage = new Image("image\\ship.png", scalingFactor - 2, scalingFactor - 2, true, true);
        shipImageView = new ImageView(shipImage);
        shipImageView.setX(ship.getShipLocation().getX() * scalingFactor + 1);
        shipImageView.setY(ship.getShipLocation().getY() * scalingFactor + 1);
        root.getChildren().add(shipImageView);
        
        // pirate image setup
        pirateImage = new Image[numberOfPirate];
        pirateImageView = new ImageView[numberOfPirate];
        for (int i = 0; i < numberOfPirate; i++) {
            pirateImage[i] = new Image("image\\pirateShip.png", scalingFactor - 2, scalingFactor - 2, true, true);
            pirateImageView[i] = new ImageView(pirateImage[i]);
            pirateImageView[i].setX(pirate[i].getPirateLocation().x * scalingFactor + 1);
            pirateImageView[i].setY(pirate[i].getPirateLocation().y * scalingFactor + 1);
            root.getChildren().add(pirateImageView[i]);
        }
        
        // island image setup
        islandImage = new Image[numberOfIsland];
        islandImageView = new ImageView[numberOfIsland];
        for (int i = 0; i < numberOfIsland; i++) {
            islandImage[i] = new Image("image\\island.jpg", scalingFactor - 2, scalingFactor - 2, true, true);
            islandImageView[i] = new ImageView(islandImage[i]);
            islandImageView[i].setX(island[i].getIslandLocation().x * scalingFactor + 1);
            islandImageView[i].setY(island[i].getIslandLocation().y * scalingFactor + 1);
            root.getChildren().add(islandImageView[i]);
        }
        
        // treasure image setup
        treasureImage = new Image("image\\treasure.jpg", 20, 20, true, true);
        treasureImageView = new ImageView(treasureImage);
        treasureImageView.setX(treasure.getTreasureLocation().getX() * scalingFactor);
        treasureImageView.setY(treasure.getTreasureLocation().getY() * scalingFactor);
        root.getChildren().add(treasureImageView);
	}

	private void drawMap() {
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

	@SuppressWarnings("deprecation")
    @Override
    public void stop(){
        monstersThread.stop();
    }
	
	private void mapReader() {
	    System.out.print("      ");
	    for (int i = 0; i < dimensions; i++) System.out.printf("%3d", i);
	    System.out.println();
        for (int i = 0; i < dimensions; i++) {
            System.out.printf("%3d | ", i);
            for (int j = 0; j < dimensions; j++) {
                if (oceanMap.getMap()[j][i] == 0) System.out.printf("%3s", ".");
                if (oceanMap.getMap()[j][i] == 1) System.out.printf("%3s", "o");
                if (oceanMap.getMap()[j][i] == 2) System.out.printf("%3s", "$");
                if (oceanMap.getMap()[j][i] == 3) System.out.printf("%3s", "A");
            }
            System.out.println();
        }
	}
	
	public void run() {
	    launch();
	}

//	public static void main(String[] args) {
//		launch(args);
//	}

}
