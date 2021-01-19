//Kevin
public class MageHeal {
	
	int posx;				// Position of Sprite
	int posy;				//x and y 
	static EZImage spriteSheet;
	String attackdirection = "";
	boolean ATTACKED = false;
	
	int spriteWidth;		// Width of each sprite
	int spriteHeight;		// Height of each sprite
	int direction = 0;		// Direction character is walking in
	int walkSequence = 29;	// Walk sequence counter
	int stepspeed;			// Number of steps before cycling to next animation step
	int counter = 0;		// Cycle counter
	
	
	public MageHeal(String filename, int width, int height, int speed) {
		spriteSheet = EZ.addImage(filename, posx, posy);
		spriteWidth = width;		// Width of the sprite character
		spriteHeight = height;		// Height of the sprite character
		stepspeed = speed;		
		posx = -500;	//default position of sprite during setup
		posy = -500;	
		setImagePosition();
	}
	private void setImagePosition() { //set the position of spritesheet to specific x and y
		spriteSheet.translateTo(posx, posy);
		
		spriteSheet.setFocus(walkSequence * spriteWidth, direction,
				walkSequence * spriteWidth + spriteWidth, direction + spriteHeight);
}	
	public void stopAnimate() { //when this function is called, the sprite will stop animating
		walkSequence = -1;
		spriteSheet.translateTo(-500, -500);
	}
	public void animate () { //animates the healing sprite frame by frame; our spritesheet is 29 frames 
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

public void translateTo(int x, int y) { //move the sprite to a specific area
	posx = x;
	posy = y;
}

public void hide() { //hides heal sprite
	spriteSheet.hide();
}
public void show() {//shows heal sprite
	spriteSheet.show();
}
}

