package project;

import java.util.Random;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class Monster {
	int a;
	int b;
	int scale;
	int radius = 5;
	Circle circle;

	Monster(int a, int b, int scale) {
		this.a = a;
		this.b = b;
		circle = new Circle();
		setPositionX(a);
		setPositionY(b);
		circle.setRadius(radius);
		this.scale = scale;
	}

	Circle getCircle() {
		return circle;
	}

	void setX(int a) {
		this.a = a;
		setPositionX(a);
	}

	void setY(int b) {
		this.b = b;
		setPositionY(b);
	}

	int getX() {
		return a;
	}

	int getY() {
		return b;
	}

	private void setPositionX(int a) {
		circle.setCenterX(a * scale + (scale / 2));
	}

	private void setPositionY(int b) {
		circle.setCenterY(b * scale + (scale / 2));
	}

	public void setLineColor(Circle circle, Color color) {
		circle.setStroke(color);
		circle.setFill(color);
	}

}

public class ScaryMonster implements Runnable {
	Boolean x = true;
	int radius;
	Random random = new Random();
	int scalingFactor;
	Monster[] monsters = new Monster[15];

	public ScaryMonster(int scalingFactor) {
		for (int j = 0; j < 15; j++) {
			int x = random.nextInt(20);
			int y = random.nextInt(20);
			monsters[j] = new Monster(x, y, scalingFactor);
		}
		this.radius = 5;
		this.scalingFactor = scalingFactor;
	}

	public void addToPane(ObservableList<Node> ans) {
		for (Monster result : monsters) {

			Circle circle = result.getCircle();
			System.out.println("Adding circle: " + circle.getCenterX() + " " + circle.getCenterY() + " " + radius);
			ans.add(circle);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (Monster result : monsters) {
				// move x
				int xMove = result.getX() + random.nextInt(3) - 1;
				if (xMove >= 0 && xMove <= 50)
					result.setX(xMove);
				// move y
				int yMove = result.getY() + random.nextInt(3) - 1;
				if (yMove >= 0 && yMove <= 50)
					result.setY(yMove);
			}
		}

	}

}
