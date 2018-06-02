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
		setPositionA(a);
		setPositionB(b);
		circle.setRadius(radius);
		this.scale = scale;
	}

	Circle getCircle() {
		return circle;
	}

	void setA(int a) {
		this.a = a;
		setPositionA(a);
	}

	void setB(int b) {
		this.b = b;
		setPositionB(b);
	}

	int getA() {
		return a;
	}

	int getB() {
		return b;
	}

	private void setPositionA(int a) {
		circle.setCenterX(a * scale + (scale / 2));
	}

	private void setPositionB(int b) {
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
				// move a
				int MoveA = result.getA() + random.nextInt(3) - 1;
				if (MoveA >= 0 && MoveA <= 50)
					result.setA(MoveA);
				// move b
				int MoveB = result.getB() + random.nextInt(3) - 1;
				if (MoveB >= 0 && MoveB <= 50)
					result.setB(MoveB);
			}
		}

	}

}
