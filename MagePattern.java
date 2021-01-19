//Kevin 
public class MagePattern {
	int posx; // Position of Sprite
	int posy; // x and y
	static EZImage spriteSheet;
	String attackdirection = "";
	boolean ATTACKED = false;
	boolean coolDown = true;

	int spriteWidth; // Width of each sprite
	int spriteHeight; // Height of each sprite
	int direction = 0; // Direction character is walking in
	int walkSequence = 0; // Walk sequence counter
	int stepspeed; // Number of steps before cycling to next animation step
	int counter = 0; // Cycle counter

	public MagePattern(String filename, int width, int height, int speed) {
		spriteSheet = EZ.addImage(filename, posx, posy);
		spriteWidth = width; // Width of the sprite character
		spriteHeight = height; // Height of the sprite character
		stepspeed = speed;
		posx = -500; // default position of sprite during setup
		posy = -500;
		setImagePosition();
	}

	private void setImagePosition() { // set the position of spritesheet to specific x and y
		spriteSheet.translateTo(posx, posy);

		spriteSheet.setFocus(walkSequence * spriteWidth, direction, walkSequence * spriteWidth + spriteWidth,
				direction + spriteHeight);
	}

	public void moveLeft(int stepSize) { // animate spell sprite and make it move left
		posx = posx - stepSize;
		direction = 0;

		if ((counter % stepspeed) == 0) {
			walkSequence--;
			if (walkSequence < 0)
				walkSequence = 19;
		}
		counter++;
		setImagePosition();
	}

	public void moveUp(int stepSize) { // animate spell sprite and make it move up
		posy = posy - stepSize;
		direction = 0;

		if ((counter % stepspeed) == 0) {
			walkSequence--;
			if (walkSequence < 0)
				walkSequence = 19;
		}
		setImagePosition();
		counter++;
	}

	public void moveRight(int stepSize) { // animate spell sprite and make it move right
		posx = posx + stepSize;
		direction = 0;

		if ((counter % stepspeed) == 0) {
			walkSequence++;
			if (walkSequence > 19)
				walkSequence = 0;
		}
		counter++;
		setImagePosition();
	}

	public void moveDown(int stepSize) { // animate spell sprite and make it move down
		posy = posy + stepSize;
		direction = 0;

		if ((counter % stepspeed) == 0) {
			walkSequence++;
			if (walkSequence > 19)
				walkSequence = 0;
		}
		counter++;
		setImagePosition();
	}

	public void translateTo(int x, int y) { // move the sprite to a given area
		posx = x;
		posy = y;
	}

	public void hide() { // hides the spell sprite
		spriteSheet.hide();
	}

	public void show() { // shows the spell sprite
		spriteSheet.show();
	}

	public boolean isPointInElement(int x, int y) { // checks if the sprite touches another object

		if (spriteSheet.isPointInElement(x, y)) {
			return true;
		}

		return false;
	}
}
