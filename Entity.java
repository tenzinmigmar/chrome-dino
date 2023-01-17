package src.code;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * Entity class to create all non-player entities such
 * as cacti, clouds, track, and birds
 * @version January 2023
 * @author Tenzin Migmar, Minkila Bara, Kieran Norman 
 */

public class Entity extends Rectangle{
	
	private int counter; 
	private Image image;
	public static int xVelocity = 5; 
	private String filePath;
	private double restartXPos;	
	ArrayList<Image> birdImages = new ArrayList<Image>();
	
	public Entity(double x, double y, double width, double height, String filePath, double restartXPos) {
		super(x, y, width, height);
		this.filePath = filePath;
		this.restartXPos = restartXPos; 
		if (filePath.equals("bird")) {
			for (int i = 1; i < 3; i++) {
				try {
					birdImages.add(new Image(new FileInputStream("src/bird-images/bird" + i + ".png")));
				} catch (FileNotFoundException e) {
					System.out.println("The file for bird images was not found. Make sure the images are in the bird-images folder inside the src folder!");
				}
			}
			this.image = birdImages.get(0);
		}
		else {
			try {
				this.image = new Image(new FileInputStream("src/other-images/" + filePath + ".png"));
			} catch (FileNotFoundException e) {
				System.out.println("The file " + filePath + " was not found. Make sure the images are in the other-images folder inside the src folder!");
			}
		}
	}
	
	// only gets called for bird entities 
	public void update() {
		counter++;
		if (counter == 25) {
			counter = 0;
			if (this.getImage().equals(birdImages.get(0))) {
				this.setWidth(69.75);
				this.setHeight(46.5);
				this.setImage(birdImages.get(1));
			}
			else {
				this.setWidth(72.75);
				this.setHeight(51);
				this.setImage(birdImages.get(0));
			}
		}
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image newImage) {
		this.image = newImage;
	}
	
	public int getXVelocity() {
		return this.xVelocity;
	}
	
	public String getFile() {
		return this.filePath;
	}
	
	public double getRestartXPos() {
		return this.restartXPos;
	}
}


