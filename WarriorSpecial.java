//Bruce
public class WarriorSpecial {

	int posx; // Position of Sprite
	int posy;
	static EZImage spriteSheet;
	boolean ATTACKED = false;

	
	int spriteWidth; // Width of each sprite
	int spriteHeight; // Height of each sprite
	int direction = 0; // Direction character is walking in
	int walkSequence = 0; // Walk sequence counter
	int stepspeed; // Number of steps before cycling to next animation step
	int counter = 0; // Cycle counter
	String attackdirection = "";
	
	public WarriorSpecial(String filename, int width, int height, int speed) {
		spriteSheet = EZ.addImage(filename, -500, -500);
		spriteWidth = width; // Width of the sprite character
		spriteHeight = height; // Height of the sprite character
		stepspeed = speed;
		setImagePosition();
	}

	private void setImagePosition() {
		spriteSheet.translateTo(posx, posy);

		spriteSheet.setFocus(walkSequence * spriteWidth, direction, walkSequence * spriteWidth + spriteWidth,
				direction + spriteHeight);
	}

public void animate() {
		
		direction = 0;
		

		if ((counter % stepspeed) == 0) {
			
			walkSequence--;
			if (walkSequence < 0) {
				walkSequence = 4;
			}
		}
		counter++;
		setImagePosition();
	}
	public void stopAnimate() {
		walkSequence = -1;
		spriteSheet.translateTo(-500, -500);
	}
	public void translateTo(int x, int y) {
		posx = x;
		posy = y;
	}

	public void hide() {
		spriteSheet.hide();
	}

	public void show() {
		spriteSheet.show();
	}
	public boolean isPointInElement(int x, int y) {
		
	    if (spriteSheet.isPointInElement(x, y)) {
	  	  return true;
	    }
	  
	  return false;
	}
}
