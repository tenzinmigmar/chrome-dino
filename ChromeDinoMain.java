package src.code;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main class for running chrome dino application 
 * @version January 2023
 * @author Tenzin Migmar, Minkila Bara, Kieran Norman 
 */
public class ChromeDinoMain extends Application {

	final int pauseDuration = 10; 

	
	//TODO:
	// Implement speed-up as game progresses 
	// Add sound
	// Add replay button 
	// Add main menu 
	// Add top 10 high score button 
	Game dinoGame = new Game();
	Entity[] bground = dinoGame.bground();
	Dinosaur dino = dinoGame.player();
	Entity[] obstacleObjects = dinoGame.obstacleObjects();
	ArrayList<Entity> obstacles = new ArrayList<Entity>();
	double score = 0;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void init() {

	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Chrome Dinosaur Runner");
		StackPane mainPane = new StackPane();
		Canvas canvas = new Canvas(800,500);
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		Scene scene = new Scene(mainPane);
		dinoGame.setUpObstacles(obstacles);

		// event handlers 
		scene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.SPACE) {
				dino.jump();
			}

			else if (event.getCode() == KeyCode.SHIFT) {
				dino.duck();
			}
			
		});

		scene.setOnKeyReleased(event -> {			
			if (event.getCode() == KeyCode.SHIFT) {
				dino.duck();
			}
		});

		// thread for running game 
		Thread game = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {	
					dinoGame.loopObjects(bground);
					dinoGame.generateObstacles(obstacles, obstacleObjects);

					if (dino.isAlive()) {
						score += 0.1;
						dinoGame.moveObjects(bground, obstacles);
					}
					
					update();
					draw(gc);
					try {
						Thread.sleep(pauseDuration);
					} catch (InterruptedException e) {
						System.out.println("The thread was interrupted while waiting or sleeping. Try running again."); 
					}
				}
			}
		});

		mainPane.getChildren().add(canvas);
		primaryStage.setScene(scene);	
		game.start();		
		primaryStage.show();
	}
	

	/**
	 * This is a good method to use to draw on the canvas
	 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/canvas/GraphicsContext.html
	 * @param gc The GraphicsContext from the canvas you wish to draw on.
	 */
	public void draw(GraphicsContext gc) {
		dinoGame.draw(gc, bground, obstacles, dino, Integer.toString((int)score));
	}
	
	/**
	 * This is a useful method to update character positions, text box information, etc.
	 * If your other classes have their own update methods you should call those here.
	 */
	public void update() {
		dinoGame.update(obstacles, dino);
	}
	
	/**
	 * This method gets called automatically whenever someone clicks the x to close the window or
	 * when Platform.exit() is used in your program to end the application.
	 * This is a good place to turn off any music or save any data.
	 */
	public void stop() {
		
	}
}