package project;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.junit.experimental.theories.Theories;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OceanExplorer extends Application {
    
	private int[][] islandMap;
	private AnchorPane root;
	private int dimensions;
	private int scalingFactor;
	
	private OceanMap oceanMap;
	private Scene scene;

	private Image shipImage;
	private ImageView shipImageView;

	private Pirate[] pirate;
	private int numberOfPirate = 2;
    private Image[] pirateImage;
    private ImageView[] pirateImageView;

    private Island[] island;
    private int numberOfIsland = 20;
	private Image[] islandImage;
	private ImageView[] islandImageView;
	
	private Treasure treasure;
	private Image treasureImage;
	private ImageView treasureImageView;

	private Ship ship;
	
	private Monster monster;
    private Thread monstersThread;

	@Override
	public void start(Stage stage) throws Exception {
	    
	    oceanMap = OceanMap.getInstance(numberOfPirate, numberOfIsland);
        islandMap = oceanMap.getMap();
        dimensions = oceanMap.getDimensions();
        scalingFactor = oceanMap.getScalingFactor();
        
        ship = new ShipColumbus(oceanMap);
        addLifeToColumbus();
        
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
	    
		root = new AnchorPane();
		drawMap();
		
		monster = new Monster(dimensions, scalingFactor);
        monster.addToPane(root.getChildren());
        stage.show();
        
        monstersThread = new Thread(monster);
        monstersThread.start();

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
                ship.updateShipLocation(oceanMap.getShipLocation());
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
                
                shipImageView.setX(oceanMap.getShipLocation().x*scalingFactor);
                shipImageView.setY(oceanMap.getShipLocation().y*scalingFactor);
                
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
			    Rectangle rect = new Rectangle(x * scalingFactor, y * scalingFactor, scalingFactor, scalingFactor);
			    rect.setStroke(Color.BLACK);
			    rect.setFill(Color.PALETURQUOISE);
			    root.getChildren().add(rect);
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
	
	public void startGame() {
	    System.out.println("START GAME");
	    launch();
	}

    public void addLifeToColumbus() {
        ship = new ShipColumbusSuperPowerOfAddLife(ship);
        oceanMap.updateShipLife(ship.getLife());
    }

	public static void main(String[] args) {
//	    this.addLifeToColumbus();
		launch(args);
	}

}
