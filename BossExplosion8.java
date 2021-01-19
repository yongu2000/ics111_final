//Yong U
import java.util.*;

public class BossExplosion8 {
	
	//variables for explosion pattern
	int posx;				// Position of Sprite
	int posy;
	static EZImage spriteSheet;// Make a variable called spriteSheet to store EZImage
	String attackdirection = "";	// Make a variable called attackdirection to compare between different explosion pattern
	boolean ATTACKED = false;		// Make a variable called ATTACKED to make the damage calculation only once for each attack
	
	
	int spriteWidth;		// Width of each sprite
	int spriteHeight;		// Height of each sprite
	int direction = 0;		// Direction character is walking in
	int walkSequence = 29;	// Walk sequence counter
	int stepspeed;			// Number of steps before cycling to next animation step
	int counter = 0;		// Cycle counter
	
	//Constructor of Explosion attack
	public BossExplosion8(String filename, int width, int height, int speed) {
		spriteSheet = EZ.addImage(filename, 500, 500);
		spriteWidth = width;		// Width of the sprite character
		spriteHeight = height;		// Height of the sprite character
		stepspeed = speed;			// How many pixel movement steps to move before changing the sprite graphic
		setImagePosition();		// use only one part of sprite sheet
	}
	private void setImagePosition() {	// use one part of the sprite sheet to make the animation
		spriteSheet.translateTo(posx, posy);
		
		spriteSheet.setFocus(walkSequence * spriteWidth, direction,
				walkSequence * spriteWidth + spriteWidth, direction + spriteHeight);
}

	public void moveLeft(int stepSize) {	// make the spritesheet move left with animations
		posx = posx - stepSize;
		direction = 0;

		if ((counter % stepspeed) == 0) {	
			walkSequence--;
			if (walkSequence < 0)
				walkSequence = 29;
		}
		counter++;
		setImagePosition();
	}

	public void moveUp(int stepSize) {	// make the spritesheet move up with animations
		posy = posy - stepSize;
		direction = 0;

		if ((counter % stepspeed) == 0) {
			walkSequence--;
			if (walkSequence < 0)
				walkSequence = 29;
		}
		counter++;
		setImagePosition();
	}
	
	public void moveRight(int stepSize) {	// make the spritesheet move right with animations
		posx = posx + stepSize;
		direction = 0;

		if ((counter % stepspeed) == 0) {
			walkSequence--;
			if (walkSequence < 0)
				walkSequence = 29;
		}
		counter++;
		setImagePosition();
	}
	public void moveDown(int stepSize) {	// make the spritesheet move down with animations
		posy = posy + stepSize;
		direction = 0;

		if ((counter % stepspeed) == 0) {
			walkSequence--;
			if (walkSequence < 0)
				walkSequence = 29;
		}
		counter++;
		setImagePosition();
	}
	
	public void stopAnimate() {		//stop the animation and move sprite sheet to -500, -500
		walkSequence = -1;
		spriteSheet.translateTo(-500, -500);
	}
	public void animate () {		//make the spritesheet do the animation without moving
		
		direction = 0;
		if ((counter % stepspeed) == 0) {
			
			walkSequence--;
			if (walkSequence < 0) {
				walkSequence = 29;
			}
		}
		counter++;
		setImagePosition();
	}

public void translateTo(int x, int y) {		//move the spritesheet to assigned position
	posx = x;
	posy = y;
}

public void hide() {		//hide the spritesheet
	spriteSheet.hide();
}
public void show() {		//show the spritesheet
	spriteSheet.show();
}
public boolean isPointInElement(int x, int y) {		//check whether spritesheet has touched position (x,y)
	
    if (spriteSheet.isPointInElement(x, y)) {
  	  return true;
    }
  
  return false;
}
}
