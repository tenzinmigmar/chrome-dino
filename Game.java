package src.code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Class with helper methods for facilitating set-up,
 * checking collisions, resetting positions, etc. 
 * @version January 2023
 * @author Tenzin Migmar, Minkila Bara, Kieran Norman 
 */

public class Game {
	Font PressStart2P = null;
	
	public Game() {
		try {
			FileInputStream fontStream = new FileInputStream("src/other-images/PressStart2P-Regular.ttf");
			PressStart2P = Font.loadFont(fontStream, 15);
			fontStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Entity[] bground() {
		Entity[] bgroundObjects = {new Entity(0, 400, 1803, 21, "track", 1803.0), new Entity(1803, 400, 1803, 21, "track", 1803.0),
				new Entity(800, 50, 84, 101, "cloud", 800.0), new Entity(1100, 150, 84, 101, "cloud", 900.0), new Entity(1300, 250, 84, 101, "cloud", 1000.0),
				new Entity(1500, 230, 84, 101, "cloud", 1100.0)};
		return bgroundObjects; 
	}
	
	public Dinosaur player() {
		Dinosaur dino = new Dinosaur(50, 350, 66, 70, 6); 
		return dino; 
	}
	
	public Entity[] obstacleObjects() {
		Entity[] obstacles = {new Entity(1200, 338, 36, 71.25, "cactus1", 0.0), new Entity(1200, 338, 74.25,  71.25, "cactus2", 0.0), new Entity(1200, 338, 74.25, 71.25, "cactus3", 0.0), 
				new Entity(1200, 356, 30, 53.25, "cactus4", 0.0), new Entity(1200, 356, 51, 53.25, "cactus5", 0.0), new Entity(1200, 356, 78.75, 53.25, "cactus6", 0.0), new Entity(1200, 320, 72.75, 51, "bird", 0.0), 
				new Entity(1200, 360, 72.75, 51, "bird", 0.0)};
		return obstacles;
	}
	
	public void setUpObstacles(ArrayList<Entity> obstacles) {
		obstacles.add(new Entity(800, 338, 36, 71.25, "cactus1", 0.0));
		// fix obstacles with the correct sizing 
		obstacles.add(new Entity(1300, 338, 36, 71.25, "cactus2", 0.0));
	}
	
	public void loopObjects(Entity[] bground) {
		for (int i = 0; i < bground.length; i++) {
			if (bground[i].getX() <= 0 - bground[i].getWidth()) {
				bground[i].setX(bground[i].getRestartXPos());
			}
		}
	}
	
	public void generateObstacles(ArrayList<Entity> obstacles, Entity[] obstacleObjects) {
		for (int i = 0; i < obstacles.size(); i++) {
			if (obstacles.get(i).getX() <= 0 - obstacles.get(i).getWidth()) {
				obstacles.remove(i);
				int n = new Random().nextInt(8);
				obstacles.add(new Entity(obstacleObjects[n].getX(), obstacleObjects[n].getY(), obstacleObjects[n].getWidth(), obstacleObjects[n].getHeight(), obstacleObjects[n].getFile(), obstacleObjects[n].getRestartXPos()));
			}
		}
	}
	
	public void moveObjects(Entity[] bgroundObjects, ArrayList<Entity> obstacles) {
		for (int i = 0; i < bgroundObjects.length; i++) {
			bgroundObjects[i].setX(bgroundObjects[i].getX() - Entity.xVelocity);
		}
		
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).setX(obstacles.get(i).getX() - Entity.xVelocity);
		}	
	}
	
	public void draw(GraphicsContext gc, Entity[] bgroundObjects, ArrayList<Entity> obstacles, Dinosaur dino, String score) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		gc.setFill(Color.rgb(83, 83, 83));	
		//Color.rgb(150, 0, 255)
		gc.setFont(PressStart2P);
		gc.fillText(score, 720, 50);
		
		for (int i = 0; i < bgroundObjects.length; i++) {
			Entity bground = bgroundObjects[i];
			gc.drawImage(bground.getImage(), bground.getX(), bground.getY(), bground.getWidth(), bground.getHeight());
		}
		
		for (int i = 0; i < obstacles.size(); i++) {
			Entity obstacle = obstacles.get(i);
			gc.drawImage(obstacle.getImage(), obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
		}

		gc.drawImage(dino.getImage(), dino.getX(),  dino.getY(), dino.getWidth(), dino.getHeight());
	}
	
	public void update(ArrayList<Entity> obstacles, Dinosaur dino) {
		dino.update();
		for (int i = 0; i < obstacles.size(); i++) {
			if (obstacles.get(i).getFile().equals("bird") && dino.isAlive()) {
				obstacles.get(i).update();
			}
		}

		for (int i = 0; i < obstacles.size(); i++) {
			if (dino.getBoundsInLocal().intersects(obstacles.get(i).getBoundsInLocal())) {
				dino.setDead();
			}
		}
	}
}

//public void start(Stage primaryStage) throws Exception {
//String currentFontFile = "English Gothic, 17th c..TTF";
//InputStream fontStream = CustomFontTest.class.getResourceAsStream(currentFontFile);
//if (fontStream != null) {
//  Font bgFont = Font.loadFont(fontStream, 36);
//  fontStream.close();
//
//  final Button button = new Button("Press me");
//  button.setFont(bgFont);
//
//  BorderPane root = new BorderPane();
//  root.setCenter(button);
//
//  Scene scene = new Scene(root, 500, 100);
//
//  primaryStage.setTitle("CustomFontTest");
//  primaryStage.setScene(scene);
//  primaryStage.show();
//} else {
//  throw new IOException("Could not create font: " + currentFontFile);
//}
//}