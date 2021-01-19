import java.awt.Color;

import java.util.*;

public class main {

	// stage
	static int stage = 0;

	// character select
	static String playerClass;
	static EZImage warriorSelect;
	static EZImage mageSelect;

	// mouse cursor
	static EZImage cursor;

	// backgrounds
	static EZImage charaSelect;
	static EZImage background;
	static EZImage start;
	static EZImage howToPlay;
	static EZImage VICTORY;
	static EZImage DEFEAT;
	static EZSound BGM;

	// Boss related variables
	static int PATTERN;
	static int bossSpeed = 1;
	static int bossAttackSpeed = 3;

	// On Off Switch for functions to do only once in while loop
	static boolean BOSSATTACK = false;
	static boolean roared = false;
	static boolean roared2 = false;

	// Boss HP
	static EZText BossHP;

	// Boss pattern Sound
	static EZSound fireballSound;
	static EZSound explosionSound;
	static EZSound explosionSound2;
	static EZSound meteorSound;
	static EZSound bossRoar;
	static EZSound bossRoar2;
	static EZSound bossStep;
	static EZSound bossWing;

	// Warrior related variable
	static EZText WarriorHP;

	// Mage related variable
	static EZText MageHP;

	// extra sounds
	static EZSound defeat;
	static EZSound victory;

	static void setup() { // Initial setup for the game
		EZ.initialize(1024, 720);
		charaSelect = EZ.addImage("charaselect.png", 512, 360);
		background = EZ.addImage("background.png", 512, 360);
		howToPlay = EZ.addImage("howtoplay.png", 512, 360);
		howToPlay.hide();
		start = EZ.addImage("start.png", 512, 660);
		start.hide();
		background.hide();

		warriorSelect = EZ.addImage("warriorselect.png", 256, 590);
		mageSelect = EZ.addImage("mageselect.png", 768, 590);

		cursor = EZ.addImage("cursor.png", 0, 0);

		BossHP = EZ.addText(512, 25, "1000", new Color(255, 0, 0), 40);
		WarriorHP = EZ.addText(100, 700, "1000", new Color(255, 0, 0), 40);
		MageHP = EZ.addText(100, 700, "1000", new Color(255, 0, 0), 40);
		BossHP.setFont("FirecatMedium.ttf");
		WarriorHP.setFont("FirecatMedium.ttf");
		MageHP.setFont("FirecatMedium.ttf");

		VICTORY = EZ.addImage("gameclear.png", 512, 360);
		DEFEAT = EZ.addImage("gameover.png", 512, 360);
		VICTORY.hide();
		DEFEAT.hide();

		fireballSound = EZ.addSound("Fireball+3.wav");
		explosionSound = EZ.addSound("Fireball+2.wav");
		explosionSound2 = EZ.addSound("Fireball+1.wav");
		meteorSound = EZ.addSound("meteor.wav");
		bossRoar = EZ.addSound("bossroar.wav");
		bossRoar2 = EZ.addSound("bossroar2.wav");
		bossStep = EZ.addSound("bossstep.wav");
		bossWing = EZ.addSound("bosswing.wav");
		BGM = EZ.addSound("BGM.wav");
		BGM.loop();

		// add extra sounds here
		defeat = EZ.addSound("defeat.wav");
		victory = EZ.addSound("victory.wav");
	}

	public static void main(String[] args) {

		setup();
		// Constructor for boss and boss patterns(attacks)
		Boss boss = new Boss("bahamut768.png", 512, 100, 192, 192, 10);
		Boss rageboss = new Boss("bahamut2.png", 512, 100, 192, 192, 10);

		BossFireball fireball = new BossFireball("fireball1.png", 128, 128, 10);

		BossExplosion explosion = new BossExplosion("explosion.png", 192, 192, 5);
		BossExplosion2 explosion2 = new BossExplosion2("explosion.png", 192, 192, 5);
		BossExplosion3 explosion3 = new BossExplosion3("explosion.png", 192, 192, 5);
		BossExplosion4 explosion4 = new BossExplosion4("explosion.png", 192, 192, 5);
		BossExplosion5 explosion5 = new BossExplosion5("explosion.png", 192, 192, 5);
		BossExplosion6 explosion6 = new BossExplosion6("explosion.png", 192, 192, 5);
		BossExplosion7 explosion7 = new BossExplosion7("explosion.png", 192, 192, 5);
		BossExplosion8 explosion8 = new BossExplosion8("explosion.png", 192, 192, 5);

		// (ArrayList)
		ArrayList<BossMeteor> meteorList = new ArrayList<BossMeteor>();
		for (int i = 0; i < 10; i++) {
			BossMeteor aMeteor = new BossMeteor();
			meteorList.add(aMeteor);
		}
		// hide until the game start
		boss.hide();
		rageboss.hide();
		BossHP.hide();

		// Constructor for Warrior
		Warrior warrior = new Warrior("warrior2.png", 512, 600, 31, 48, 10);
		WarriorAttack sword = new WarriorAttack("Slash.png", 192, 192, 5);
		WarriorSpecial special = new WarriorSpecial("SlashIce2.png", 192, 192, 5);

		// hide until the game start
		warrior.hide();
		WarriorHP.hide();

		// Constructor for Mage and Mage attacks
		Mage mage = new Mage("squire_m.png", 512, 600, 32, 48, 10);

		MageHeal heal = new MageHeal("heal_spritesheet.png", 192, 192, 5);
		MagePattern spell = new MagePattern("projectile_spritesheet.png", 192, 192, 5);

		// hide until the game start
		mage.hide();
		MageHP.hide();

		while (true) {
			// make the cursor follow the mouse pointer
			int clickX = 0;
			int clickY = 0;
			clickX = EZInteraction.getXMouse();
			clickY = EZInteraction.getYMouse();
			cursor.translateTo(clickX, clickY);

			if (stage == 0) { // Character select
				if (EZInteraction.wasMouseLeftButtonPressed()) {
					// Select Warrior, move to stage 1
					if (warriorSelect.isPointInElement(clickX, clickY)) {
						playerClass = "warrior";
						stage = 1;
						warriorSelect.translateTo(-500, -500);
						mageSelect.translateTo(-500, -500);
						background.show();
					}
					// Select Mage, move to stage 1
					if (mageSelect.isPointInElement(clickX, clickY)) {
						playerClass = "mage";
						stage = 1;
						warriorSelect.translateTo(-500, -500);
						mageSelect.translateTo(-500, -500);
						background.show();
					}
				}
			}

			// Explain how to play
			if (stage == 1) {
				charaSelect.hide();
				howToPlay.show();
				start.show();
				if (EZInteraction.wasMouseLeftButtonPressed()) {
					// Select Warrior, move to stage 1
					if (start.isPointInElement(clickX, clickY)) {
						stage = 2;
						start.translateTo(-500, -500);
						howToPlay.translateTo(-500, -500);
						background.show();
					}
				}
			}

			if (stage == 2) { // Game start stage
				howToPlay.hide();

				// Main class related to Boss Class by YongU
				// Initial Boss Setting
				boss.show();
				BossHP.show();

				// Makes boss footstep sound to play once at a time
				if (!bossStep.isPlaying()) {
					bossStep.play();
				}
				// Makes roaring sound to only play once
				if (!bossRoar.isPlaying() && roared == false) {
					bossRoar.play();
					roared = true;
				}

				// Show the Boss's HP status
				if (boss.hitPoint == boss.hitPoint) {
					BossHP.setMsg("HP" + boss.hitPoint);
				}

				// Boss Rage Mode
				// Boss rage when Boss HP is 500
				if (boss.hitPoint <= 500) {
					boss.hide();
					// Makes roaring sound to only play once
					if (!bossRoar2.isPlaying() && roared2 == false) {
						bossRoar2.play();
						roared2 = true;
					}

					// Makes boss wing flapping sound to play once at a time
					if (!bossWing.isPlaying()) {
						bossWing.play();
					}
					// Change status setting of the boss
					bossStep.stop();
					rageboss.hitPoint = 500;
					rageboss.show();
					bossSpeed = 2;
					bossAttackSpeed = 6;

					// Makes sound to play once at a time
					if (!meteorSound.isPlaying()) {
						meteorSound.play();
					}

					// Make the meteor fall after boss rage
					for (int i = 0; i < 10; i++) {
						BossMeteor eachMeteor;
						eachMeteor = meteorList.get(i);
						eachMeteor.Fall();
						// Reset the attack counter when meteor is out of the screen
						if (eachMeteor.y == 700) {
							eachMeteor.ATTACKED = false;
						}
						// Give damage to Player when they got hit by meteor
						// warrior
						if (eachMeteor.isPointInElement(warrior.getXCenter(), warrior.getYCenter())) {
							if (eachMeteor.ATTACKED == false) {
								warrior.hitPoint -= 50;
								eachMeteor.ATTACKED = true;
							}
						}
						// mage
						if (eachMeteor.isPointInElement(mage.getXCenter(), mage.getYCenter())) {
							if (eachMeteor.ATTACKED == false) {
								mage.hitPoint -= 50;
								eachMeteor.ATTACKED = true;
							}
						}
					}
				}

				// Boss Pattern Task

				// Fireball
				// Shoot the fireball according to the direction where the boss is facing
				if (fireball.attackdirection == "down") {
					fireball.moveDown(bossAttackSpeed + 3);
				}
				if (fireball.attackdirection == "left") {
					fireball.moveLeft(bossAttackSpeed + 3);
				}
				if (fireball.attackdirection == "right") {
					fireball.moveRight(bossAttackSpeed + 3);
				}
				if (fireball.attackdirection == "up") {
					fireball.moveUp(bossAttackSpeed + 3);
				}

				// Explosion(Surrounding)
				if (explosion.attackdirection == "surround") {
					// play animation of explosion
					explosion.animate();
					explosion2.animate();
					explosion3.animate();
					explosion4.animate();
					explosion5.animate();
					explosion6.animate();
					explosion7.animate();
					explosion8.animate();

					Timer explosionTimer = new Timer(); // set a timer for a explosion
					TimerTask explosionTask = new TimerTask() { // set a timertask for a explosion
						public void run() { // Do function after the timer is over

							explosion.attackdirection = "not"; // trigger for stopping the animation
							explosionTimer.cancel(); // end explosion timertask
						}
					};
					explosionTimer.schedule(explosionTask, 2500);
				} // set the timer as 2.5 second, make the animation last for 2.5 second

				// Explosion(Shoot)
				if (explosion.attackdirection == "shoot") {
					// play animation of explosion and make it move to 4 side
					explosion.moveLeft(bossAttackSpeed);
					explosion3.moveDown(bossAttackSpeed);
					explosion5.moveRight(bossAttackSpeed);
					explosion7.moveUp(bossAttackSpeed);

					Timer explosionTimer2 = new Timer(); // set a timer for a explosion
					TimerTask explosionTask2 = new TimerTask() { // set a timertask for a explosion
						public void run() { // Do function after the timer is over

							explosion.attackdirection = "not"; // trigger for stopping the animation
							explosionTimer2.cancel(); // end explosion timertask
						}
					};
					explosionTimer2.schedule(explosionTask2, 2500);
				} // set the timer as 2.5 second, make the animation last for 2.5 second

				// Animation Stop for explosion
				if (explosion.attackdirection == "not") {
					explosion.stopAnimate();
					explosion2.stopAnimate();
					explosion3.stopAnimate();
					explosion4.stopAnimate();
					explosion5.stopAnimate();
					explosion6.stopAnimate();
					explosion7.stopAnimate();
					explosion8.stopAnimate();
				}

				// Trigger for Boss Pattern (Finite State Machine)
				if (BOSSATTACK == false) {
					// Make a random number, 0, 1, 2
					// When 0 -> fireball
					// When 1 -> explosion(surround)
					// When 2 -> explosion(shoot)
					// Move each spriteSheet to assigned x and y
					Random pattern = new Random();
					PATTERN = pattern.nextInt(3);
					BOSSATTACK = true;

					switch (PATTERN) {
					case 0:
						// Fireball
						fireball.ATTACKED = false;
						fireballSound.play();
						if (boss.direction == 0) {
							fireball.attackdirection = "down";
							fireball.translateTo(boss.x, boss.y - 5);
						}
						if (boss.direction == boss.spriteHeight) {
							fireball.attackdirection = "left";
							fireball.translateTo(boss.x - 100, boss.y - 25);

						}
						if (boss.direction == boss.spriteHeight * 2) {
							fireball.attackdirection = "right";
							fireball.translateTo(boss.x + 100, boss.y - 25);

						}
						if (boss.direction == boss.spriteHeight * 3) {
							fireball.attackdirection = "up";
							fireball.translateTo(boss.x, boss.y - 75);
						}

						break;
					case 1:
						// Explosion(Surround)
						explosion.ATTACKED = false;
						explosion.attackdirection = "surround";

						explosionSound.play();

						explosion.translateTo(boss.x - 300, boss.y);
						explosion2.translateTo(boss.x - 150, boss.y + 150);
						explosion3.translateTo(boss.x, boss.y + 300);
						explosion4.translateTo(boss.x + 150, boss.y + 150);
						explosion5.translateTo(boss.x + 300, boss.y);
						explosion6.translateTo(boss.x + 150, boss.y - 150);
						explosion7.translateTo(boss.x, boss.y - 300);
						explosion8.translateTo(boss.x - 150, boss.y - 150);
						break;

					case 2:
						// Explosion(Shoot)
						explosion.ATTACKED = false;
						explosion.attackdirection = "shoot";
						explosionSound2.play();
						explosion.translateTo(boss.x - 150, boss.y);
						explosion3.translateTo(boss.x, boss.y + 150);
						explosion5.translateTo(boss.x + 150, boss.y);
						explosion7.translateTo(boss.x, boss.y - 150);
						break;
					}

					Timer patternTimer = new Timer(); // set a timer for boss pattern
					TimerTask patternTask = new TimerTask() { // set a timertask for boss pattern
						public void run() {
							BOSSATTACK = false;
							patternTimer.cancel();
						}
					};
					patternTimer.schedule(patternTask, 5500);
				} // set the timer as 5.5 seconds, restart the pattern after 5.5 seconds

				// Boss attack damage for Mage
				// Fireball damage
				// Fireball -> if fireball hits the mage, give 200 damage to mage
				if (fireball.isPointInElement(mage.getXCenter(), mage.getYCenter())) {
					if (fireball.ATTACKED == false) { // Calculate the damage only once per attack
						mage.hitPoint -= 200;
						fireball.ATTACKED = true;
					}
				}

				// Explosion damage
				// Explosion -> if explosion hits the mage, give 100 damage to mage
				if (explosion.isPointInElement(mage.getXCenter(), mage.getYCenter())
						|| explosion2.isPointInElement(mage.getXCenter(), mage.getYCenter())
						|| explosion3.isPointInElement(mage.getXCenter(), mage.getYCenter())
						|| explosion4.isPointInElement(mage.getXCenter(), mage.getYCenter())
						|| explosion5.isPointInElement(mage.getXCenter(), mage.getYCenter())
						|| explosion6.isPointInElement(mage.getXCenter(), mage.getYCenter())
						|| explosion7.isPointInElement(mage.getXCenter(), mage.getYCenter())
						|| explosion8.isPointInElement(mage.getXCenter(), mage.getYCenter())) {
					if (explosion.ATTACKED == false) { // Calculate the damage only once per attack
						mage.hitPoint -= 100;
						explosion.ATTACKED = true;
					}
				}

				// Boss attack damage for Warrior
				// Fireball damage
				// Fireball -> if fireball hits the warrior, give 200 damage to warrior
				if (fireball.isPointInElement(warrior.getXCenter(), warrior.getYCenter())) {
					if (fireball.ATTACKED == false) { // Calculate the damage only once per attack
						warrior.hitPoint -= 200;
						fireball.ATTACKED = true;
					}
				}
				// Explosion damage
				// Explosion -> if explosion hits the warrior, give 100 damage to warrior
				if (explosion.isPointInElement(warrior.getXCenter(), warrior.getYCenter())
						|| explosion2.isPointInElement(warrior.getXCenter(), warrior.getYCenter())
						|| explosion3.isPointInElement(warrior.getXCenter(), warrior.getYCenter())
						|| explosion4.isPointInElement(warrior.getXCenter(), warrior.getYCenter())
						|| explosion5.isPointInElement(warrior.getXCenter(), warrior.getYCenter())
						|| explosion6.isPointInElement(warrior.getXCenter(), warrior.getYCenter())
						|| explosion7.isPointInElement(warrior.getXCenter(), warrior.getYCenter())
						|| explosion8.isPointInElement(warrior.getXCenter(), warrior.getYCenter())) {
					if (explosion.ATTACKED == false) { // Calculate the damage only once per attack
						warrior.hitPoint -= 100;
						explosion.ATTACKED = true;
					}
				}

				// Main class related to Warrior Class by Bruce
				if (playerClass == "warrior") {
					// Initial Warrior Setting
					cursor.hide();
					warrior.show();
					warrior.go();
					warrior.screen();

					// Show the Warrior's HP status
					WarriorHP.show();
					if (warrior.hitPoint == warrior.hitPoint) {
						WarriorHP.setMsg("HP" + " " + warrior.hitPoint);
					}

					// Make the boss always follow the character
					if (boss.x > warrior.x) {
						boss.moveLeft(bossSpeed);
						rageboss.moveLeft(bossSpeed);
					}
					if (boss.x < warrior.x) {
						boss.moveRight(bossSpeed);
						rageboss.moveRight(bossSpeed);
					}
					if (boss.y > warrior.y) {
						boss.moveUp(bossSpeed);
						rageboss.moveUp(bossSpeed);
					}
					if (boss.y < warrior.y) {
						boss.moveDown(bossSpeed);
						rageboss.moveDown(bossSpeed);
					}

					// Tirgger for Warrior Attack

					// Basic Slash
					// Basic Slash when 'm' is pressed
					if (EZInteraction.isKeyDown('m')) {
						sword.attackdirection = "basic";

						if (warrior.direction == 0) {
							sword.translateTo(warrior.x, warrior.y + 50);
						}
						if (warrior.direction == warrior.spriteHeight) {
							sword.translateTo(warrior.x - 50, warrior.y);
						}
						if (warrior.direction == warrior.spriteHeight * 2) {
							sword.translateTo(warrior.x + 50, warrior.y);
						}
						if (warrior.direction == warrior.spriteHeight * 3) {
							sword.translateTo(warrior.x, warrior.y - 50);
						}
					}

					// Ice Slash
					// Ice Slash when 'n' is pressed
					if (EZInteraction.isKeyDown("n")) {
						special.attackdirection = "ice";

						if (warrior.direction == 0) {
							special.translateTo(warrior.x, warrior.y + 50);
						}
						if (warrior.direction == warrior.spriteHeight) {
							special.translateTo(warrior.x - 50, warrior.y);

						}
						if (warrior.direction == warrior.spriteHeight * 2) {
							special.translateTo(warrior.x + 50, warrior.y);

						}
						if (warrior.direction == warrior.spriteHeight * 3) {
							special.translateTo(warrior.x, warrior.y - 50);

						}
					}

					// Warrior Attack Task

					// Basic slash
					// play slash animation according to the direction where the warrior is facing
					if (sword.attackdirection == "basic") {
						sword.animate();

						Timer swordTimer = new Timer(); // set a timer for slash
						TimerTask swordTask = new TimerTask() { // set a timertask for slash
							public void run() { // Do function after the timer is over

								sword.attackdirection = "not";
								swordTimer.cancel(); // end slash timertask
							}
						};
						swordTimer.schedule(swordTask, 500);
					}

					// Animation Stop for slash, switch sword.ATTACKED to false so that damage only
					// calculate once
					if (sword.attackdirection == "not") {
						sword.ATTACKED = false;
						sword.stopAnimate();
					}

					// Ice Slash
					// play Ice Slash animation according to the direction where the warrior is
					// facing
					if (special.attackdirection == "ice") {
						// play animation of Ice slash
						special.animate();

						Timer specialTimer = new Timer(); // set a timer for Ice slash
						TimerTask specialTask = new TimerTask() { // set a timertask for Ice slash
							public void run() { // Do function after the timer is over

								special.attackdirection = "not";
								specialTimer.cancel(); // end Ics slash timertask
							}
						};
						specialTimer.schedule(specialTask, 500);
					}
					// Animation Stop for Ice slash, switch special.ATTACKED to false so that damage
					// only calculate once
					if (special.attackdirection == "not") {
						special.stopAnimate();
						special.ATTACKED = false;
					}

					// Warrior attack damage for Boss

					// Slash -> if slash hits the boss, give 100 damage to boss
					if (sword.isPointInElement(boss.getXCenter(), boss.getYCenter())) {
						if (sword.ATTACKED == false) {
							boss.hitPoint -= 100;
							sword.ATTACKED = true;
						}
					}
					// Ice Slash -> if Ice slash hits the boss, give 200 damage to boss
					if (special.isPointInElement(boss.getXCenter(), boss.getYCenter())) {
						if (special.ATTACKED == false) {
							boss.hitPoint -= 200;
							special.ATTACKED = true;
						}
					}

					// if warrior touches boss, they get damaged
					if (warrior.getXCenter() == boss.getXCenter() && warrior.getYCenter() == boss.getYCenter()) {
						warrior.hitPoint -= 25;
					}
				}

				// Main class related to Mage Class by Kevin
				if (playerClass == "mage") {
					// Initial Warrior Setting
					mage.show();
					mage.go();
					mage.screen();

					// Show the Mage's HP status
					MageHP.show();
					if (mage.hitPoint == mage.hitPoint) {
						MageHP.setMsg("HP" + mage.hitPoint);
					}

					// Make the boss always follow the character
					if (boss.x > mage.x) {
						boss.moveLeft(bossSpeed);
						rageboss.moveLeft(bossSpeed);
					}
					if (boss.x < mage.x) {
						boss.moveRight(bossSpeed);
						rageboss.moveRight(bossSpeed);
					}
					if (boss.y > mage.y) {
						boss.moveUp(bossSpeed);
						rageboss.moveUp(bossSpeed);
					}
					if (boss.y < mage.y) {
						boss.moveDown(bossSpeed);
						rageboss.moveDown(bossSpeed);
					}
					// Trigger for Mage Attack

					// Spell sphere
					// Use spell sphere when 'i' is pressed
					if (EZInteraction.isKeyDown('i') && spell.coolDown == true) {
						// Switch spell.ATTACKED to false so that damage only calculate once
						spell.ATTACKED = false;
						spell.coolDown = false;
						if (mage.direction == 0) {
							spell.attackdirection = "down";
							spell.translateTo(mage.x, mage.y);
						}
						if (mage.direction == mage.spriteHeight) {
							spell.attackdirection = "left";
							spell.translateTo(mage.x, mage.y);

						}
						if (mage.direction == mage.spriteHeight * 2) {
							spell.attackdirection = "right";
							spell.translateTo(mage.x, mage.y);

						}
						if (mage.direction == mage.spriteHeight * 3) {
							spell.attackdirection = "up";
							spell.translateTo(mage.x, mage.y);
						}
					}

					// When the spell sphere is out of the screen, can reuse the spell sphere again
					if (spell.posx < 0 || spell.posx > 1024 || spell.posy < 0 || spell.posy > 720) {
						spell.coolDown = true;
					}

					// Heal
					// Heal 10HP for mage when 'o' is pressed
					if (EZInteraction.isKeyDown('o')) {
						if (heal.walkSequence == 29) {
							mage.hitPoint += 10;
						}
						heal.attackdirection = "self";
					}

					// Mage Attack Task

					// Spell sphere
					// Shoot spell sphere when 'i' is pressed according to the direction where the
					// mage is facing

					if (spell.attackdirection == "down") {
						spell.moveDown(6);
					}
					if (spell.attackdirection == "left") {
						spell.moveLeft(6);
					}
					if (spell.attackdirection == "right") {
						spell.moveRight(6);
					}
					if (spell.attackdirection == "up") {
						spell.moveUp(6);
					}

					// Heal
					// Play heal animation on Mage spriteSheet
					if (heal.attackdirection == "self") {
						heal.animate();
						heal.posx = mage.x;
						heal.posy = mage.y - 50;

						Timer healTimer = new Timer(); // set a timer for a heal
						TimerTask healTask = new TimerTask() { // set a timertask for a heal
							public void run() { // Do function after the timer is over

								heal.attackdirection = "not";
								healTimer.cancel(); // end heal timertask
							}
						};
						healTimer.schedule(healTask, 2500);
					}

					// Stop animation for heal
					if (heal.attackdirection == "not") {
						heal.ATTACKED = true;
						heal.stopAnimate();
					}

					// Mage attack damage for Boss

					// Spell Sphere -> if sphere hits the boss, give 150 damage to boss

					if (spell.isPointInElement(boss.getXCenter(), boss.getYCenter())) {
						if (spell.ATTACKED == false) {
							boss.hitPoint -= 150;
							spell.ATTACKED = true;
						}
					}

					// if mage touches boss, they get damaged
					if (mage.getXCenter() == boss.getXCenter() && mage.getYCenter() == boss.getYCenter()) {
						mage.hitPoint -= 50;
					}

				}

				// Victory
				if (boss.hitPoint == 0 || boss.hitPoint < 0) {
					stage = 3;
				}

				// Defeat
				if (warrior.hitPoint == 0 || warrior.hitPoint < 0 || mage.hitPoint == 0 || mage.hitPoint < 0) {
					stage = 4;
				}

			}
			if (stage == 3) { // VICTORY
				VICTORY.show();
				boss.hide();
				warrior.hide();
				mage.hide();
				rageboss.hide();
				BGM.stop();
				if (!victory.isPlaying()) { // play song when player wins
					victory.play();
					break;
				}
			}
			if (stage == 4) { // DEFEAT
				DEFEAT.show();
				boss.hide();
				warrior.hide();
				mage.hide();
				rageboss.hide();
				BGM.stop();
				if (!defeat.isPlaying()) { // play song when player loses
					defeat.play();
					break;
				}
			}
			EZ.refreshScreen(); // Refresh screen to make graphics move
		}
	}

}
