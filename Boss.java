//Yong U
import java.awt.Color;


public class Boss {

	//variables for Boss
	EZImage spriteSheet;	// Make a variable called spriteSheet to store EZImage
	int hitPoint;			//Make a variable called hitPoint to store HP of the Boss
	boolean ATTACK_STATE;	//Make a variable called ATTACK_STATE to check whether boss is attacking or not

	int x = 0;				// Position of Sprite
	int y = 0;
	int spriteWidth;		// Width of each sprite
	int spriteHeight;		// Height of each sprite
	int direction = 0;		// Direction character is walking in
	int walkSequence = 0;	// Walk sequence counter
	int cycleSteps;			// Number of steps before cycling to next animation step
	int counter = 0;		// Cycle counter
	
	//Constructor of Boss
	Boss(String imgFile, int startX, int startY, int width, int height, int steps) {
		hitPoint = 2000;
		x = startX;					// position of the sprite character on the screen
		y = startY;
		spriteWidth = width;		// Width of the sprite character
		spriteHeight = height;		// Height of the sprite character
		cycleSteps = steps;			// How many pixel movement steps to move before changing the sprite graphic
		spriteSheet = EZ.addImage(imgFile, x, y);	// Add Image to spriteSheet
		ATTACK_STATE = false;	// variable for damage calculation
		setImagePosition();		// use only one part of sprite sheet
	}
	

	
	public void hide() {	// hide spritesheet
		spriteSheet.hide();
	}
	public void show() {	// show spritesheet
		spriteSheet.show();
	}
	private void setImagePosition() {	// use one part of the sprite sheet to make the animation
		spriteSheet.translateTo(x, y);
		
		spriteSheet.setFocus(walkSequence * spriteWidth, direction,
				walkSequence * spriteWidth + spriteWidth, direction + spriteHeight);
	}

	public void moveDown(int stepSize) {	// make the spritesheet move down with animations
		y = y + stepSize;

		direction = 0;

		if ((counter % cycleSteps) == 0) {
			walkSequence++;
			if (walkSequence > 3)
				walkSequence = 0;
		}
		counter++;
		setImagePosition();
	}
	
	public void moveLeft(int stepSize) {	// make the spritesheet move left with animations
		x = x - stepSize;
		direction = spriteHeight;

		if ((counter % cycleSteps) == 0) {
			walkSequence--;
			if (walkSequence < 0)
				walkSequence = 3;
		}
		counter++;
		setImagePosition();
	}

	public void moveRight(int stepSize) {	// make the spritesheet move right with animations
		x = x + stepSize;
		direction = spriteHeight * 2;
		
		if ((counter % cycleSteps) == 0) {
			walkSequence++;
			if (walkSequence > 3)
				walkSequence = 0;
		}
		counter++;
		setImagePosition();
	}

	public void moveUp(int stepSize) {	// make the spritesheet move up with animations
		y = y - stepSize;
		direction = spriteHeight * 3;

		if ((counter % cycleSteps) == 0) {
			walkSequence--;
			if (walkSequence < 0)
				walkSequence = 3;
		}
		setImagePosition();
		counter++;
	}

	public int getXCenter() {	//get the x coordinate of center of the boss spritesheet
		return spriteSheet.getXCenter();
	}
	public int getYCenter() {	//get the y coordinate of center of the boss spritesheet
		return spriteSheet.getYCenter();
	}
	

}

