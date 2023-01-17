package src.code;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.util.ArrayList;

// 0 - DEAD
// 1 - DUCK 1
// 2 - DUCK 2
// 3 - JUMP
// 4 - RUN 1
// 5 - RUN 2
// 6 - START 

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
 
/**
 * Dinosaur class for dino player character 
 * @version January 2023
 * @author Tenzin Migmar, Minkila Bara, Kieran Norman 
 */

public class Dinosaur extends Rectangle { 

	private boolean isJumping = false;	
	private boolean isDucking = false;
	private double yVelocity = 6; 	
	static final double dy = 6;		
										
	private int counter;
	private Image image;
	private boolean isAlive = true;
	private int xVelocity;	
	
	ArrayList<Image> dinoImages = new ArrayList<Image>();
	
	public Dinosaur(double x, double y, double width, double height, int xVelocity) {
		super(x, y, width, height);
		for (int i = 1; i < 8; i++) {
			try {
				dinoImages.add(new Image(new FileInputStream("src/dino-images/dino" + i + ".png")));
			} catch (FileNotFoundException e) {
				System.out.println("The file for dino images was not found. Make sure the images are in the dino-images folder inside the src folder!");
			}
		}
		this.xVelocity = xVelocity; 
		this.image = dinoImages.get(6);
	}
	
	public void update() {
		if (this.isAlive()) {
			if (this.isJumping()) {
				//jump movement
				this.setY(this.getY() - this.yVelocity * 2);
				this.yVelocity -= 0.2;
				if (this.yVelocity < -this.dy) { 
					this.isJumping = false;
					this.yVelocity = this.dy; 
					
				}
				
				counter++;
				if (counter == 15) {
					counter = 0; 
					if (!(this.getImage().equals(dinoImages.get(3)))) {
//						this.setWidth(66);
//						this.setHeight(70.5);
						setImage(dinoImages.get(3));
					}
				}
			}
			
			else if (this.isDucking()) {
				counter++;
				if (counter == 15) {
					counter = 0;
					this.setY(380);
					if (!(this.getImage().equals(dinoImages.get(1)))) {
						this.setWidth(88.5);
						this.setHeight(45);
						setImage(dinoImages.get(1));
					}
					else if (this.getImage().equals(dinoImages.get(1))) {
						this.setWidth(87);
						this.setHeight(45);
						setImage(dinoImages.get(2));
					}
					else if (this.getImage().equals(dinoImages.get(2))) {
						this.setWidth(88.5);
						this.setHeight(45);
						setImage(dinoImages.get(1));
					}
				}
			}
			
			
			else {
				this.setY(350);
				counter++;
				if (counter == 15) {
					counter = 0; 
					if (!(this.getImage().equals(dinoImages.get(4)) || this.getImage().equals(dinoImages.get(5)))) {
						this.setWidth(65.25);
						this.setHeight(70.5);
						setImage(dinoImages.get(4));
					}
					else if (this.getImage().equals(dinoImages.get(4))) {
						this.setWidth(66);
						this.setHeight(70.5);
						setImage(dinoImages.get(5));
					}
					else {
						this.setWidth(65.25);
						this.setHeight(70.5);
						setImage(dinoImages.get(4));
					}
				}
			}
		}
		
		else {
			this.setWidth(72.75);
			this.setHeight(75.75);
			setImage(dinoImages.get(0));
		}
	}
	
	public void jump() {
		if (!this.isJumping) {
			this.isJumping = true;
			
		}

	}
	
	public void duck() {
		if (!isDucking()) {
			this.isDucking = true;
		}
		else {
			this.isDucking = false;
		}
	}
	
	public boolean isJumping() {
		return this.isJumping; 
	}
	
	public boolean isDucking() {
		return this.isDucking;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image newImage) {
		this.image = newImage;
	}
	
	public boolean isAlive() {
		return this.isAlive;
	}
	
	public void setDead() {
		this.isAlive = false;
	}
}