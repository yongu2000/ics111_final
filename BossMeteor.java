//Yong U
import java.util.Random;

public class BossMeteor {
	
	//variables for meteor pattern
	EZImage picture;		// Make a variable called picture to store EZImage
	int x, y;				// This stores the x and y position of the meteor
	int speed;				// Make a variable called speed to store speed of the meteor
	boolean ATTACKED = false;	// Make a variable called ATTACKED to make the damage calculation only once for each attack
	
	
	//Constructor of meteor attack
	BossMeteor() {				
		
		Random rg = new Random();		// Make a random number generator
		
		x = rg.nextInt(1500);			// Set the x position of the meteor to a random number between 0 and 1500
		y = -50;						// Set the y position to -50
		picture = EZ.addImage("meteor.png", x,y);	// Add the image of the meteor and place it at x,y
		
		speed = rg.nextInt(4)+1;		// Set the speed to be a number between 1 and 5
	}
	
	public void Fall() {
		x -= speed;					// Decrease x by the speed
		y += speed;					// Decrease y by the speed
		picture.translateTo(x, y);	// Move the meteor to x,y
		
		if (y > 720) {				// If y is bigger than 720, set a new random x and speed and move to y -50
			Random regenerate = new Random();
		x = regenerate.nextInt(1500);
		y = -50;
		speed = regenerate.nextInt(4)+1;
		}
	
	
}
	
	public boolean isPointInElement(int x, int y) {		//check whether picture has touched position (x,y)
		
	      if (picture.isPointInElement(x, y)) {
	    	  return true;
	      }
	    
	    return false;
	  }
	

}

