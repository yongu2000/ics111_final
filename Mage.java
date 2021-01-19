//Kevin
public class Mage {

	EZImage spriteSheet; // creates a new variable called spriteSheet of type EZImage
	int hitPoint; // make a variable of type integer for the mage's health
	int movementSpeed; // variable that stores the mage's movement speed
	String attackState;

	int x = 0; // Position of Sprite
	int y = 0;
	int spriteWidth; // Width of each sprite
	int spriteHeight; // Height of each sprite
	int direction = 0; // Direction character is walking in
	int walkSequence = 0; // Walk sequence counter
	int cycleSteps; // Number of steps before cycling to next animation step
	int counter = 0; // Cycle counter

	// mage constructor
	Mage(String imgFile, int startX, int startY, int width, int height, int steps) {
		hitPoint = 1000;
		x = startX; // position of the sprite character on the screen
		y = startY;
		spriteWidth = width; // Width of the sprite character
		spriteHeight = height; // Height of the sprite character
		cycleSteps = steps; // How many pixel movement steps to move before changing the sprite graphic
		spriteSheet = EZ.addImage(imgFile, x, y);
		setImagePosition();
	}

	public void screen() {
		if ((x >= 1020)) {

			x = 1020;
			spriteSheet.translateTo(x, y);
		} // if the mage gets to the right side of the screen, set the x value of car to
			// 1024 so that the car won't go out of the screen
		if ((x <= 4)) {

			x = 4;
			spriteSheet.translateTo(x, y);
		} // if the mage gets to the left side of the screen, set the x value of car to 0
			// so that the car won't go out of the screen
		if ((y >= 716)) {

			y = 716;
			spriteSheet.translateTo(x, y);
		} // if the mage gets to the down side of the screen, set the y value of car to
			// 720 so that the car won't go out of the screen
		if ((y <= 4)) {

			y = 4;
			spriteSheet.translateTo(x, y);
		}
		setImagePosition();
	}

	public void hide() { // hides the mage
		spriteSheet.hide();
	}

	public void show() { // shows the mage
		spriteSheet.show();
	}

	public void moveTo(int x, int y) {
		spriteSheet.translateTo(x, y);
	}

	private void setImagePosition() {

		// Move the entire sprite sheet
		spriteSheet.translateTo(x, y);

		// Show only a portion of the sprite sheet.
		// Portion is determined by setFocus which takes 4 parameters:
		// The 1st two numbers is the top left hand corner of the focus region.
		// The 2nd two numbers is the bottom right hand corner of the focus region.
		spriteSheet.setFocus(walkSequence * spriteWidth, direction, walkSequence * spriteWidth + spriteWidth,
				direction + spriteHeight);
	}

	public void moveDown(int stepSize) {// animates the spritesheet when the mage moves down
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

	public void moveLeft(int stepSize) { // animates the spritesheet when the mage moves left
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

	public void moveRight(int stepSize) { // animates the spritesheet when the mage moves right
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

	public void moveUp(int stepSize) { // animates the spritesheet when the mage moves up
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

	public void go() { // make character move using the WASD keys
		if (EZInteraction.isKeyDown('w')) {
			moveUp(5);
		} else if (EZInteraction.isKeyDown('a')) {
			moveLeft(5);
		} else if (EZInteraction.isKeyDown('s')) {
			moveDown(5);
		} else if (EZInteraction.isKeyDown('d')) {
			moveRight(5);
		}
	}

	public int getXCenter() { // obtains the x coordinate of our mage
		return spriteSheet.getXCenter();
	}

	public int getYCenter() { // obtains the y coordinate of our mage
		return spriteSheet.getYCenter();
	}

}
