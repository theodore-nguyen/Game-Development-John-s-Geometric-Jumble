import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends MouseAdapter{
	private Color col;
	private int red = 255;
	private int blue = 255;
	private int green = 255;
	private int timer = 0;
	private int tickspeed;
	private int nextRed;
	private int nextBlue;
	private int nextGreen;
	private Engine engine;
	private Handler handler;
	private BufferedImage menubackground = null;
	private BufferedImage background = null;
	//https://www.zerochan.net/1915298

	public Menu(Engine engine, Handler handler) {
		this.engine = engine;
		this.handler = handler;
		BufferedImageLoader loader = new BufferedImageLoader();
		menubackground = loader.loadImage("/background2.jpg");
		background = loader.loadImage("/backgroundsp.jpg");
	}

	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if(engine.event == Event.Menu) 
		{
			//Start Button
			if(inBounds(mouseX, mouseY, 350, 250, 300, 64 )) {
				engine.event = Event.CharacterSelection;
			}
			//Help Button
			if(inBounds(mouseX, mouseY, 350, 350, 300, 64 )) {
				engine.event = Event.Help;
				handler.addObject(new SSMinnowJohnson( Engine.WIDTH - 200 , Engine.HEIGHT - 200, 200, ID.John));
				Enemy enemy = new Enemy(Engine.WIDTH - 200 , 200, 50, ID.Enemy, handler);
				if(!handler.checkFor(enemy)) {
					handler.addObject(enemy);
				}
			}
			//Quit Button
			if(inBounds(mouseX, mouseY, 350, 450, 300, 64 )) {
				System.exit(0);
			}

		}
		else if(engine.event == Event.Help) {
			//BackButton
			if(inBounds(mouseX, mouseY, 40, 30, 120, 64)) {
				Engine.reset = true;
				engine.event = Event.Menu;
			}

		}
		else if(engine.event == Event.CharacterSelection) {
			//BackButton
			if(inBounds(mouseX, mouseY, 40, 30, 120, 64)) {
				Engine.reset = true;
				engine.event = Event.Menu;
			}
			//Fat Albert
			if(inBounds(mouseX, mouseY, 135, 295, 70, 55)) {
				engine.event = Event.Game;	
				handler.addObject(new FatAlbert( Engine.WIDTH/2 , Engine.HEIGHT - 100, 200, ID.Albert));
				handler.addObject(new Boss1(500, 20, 1000, ID.Boss, handler));
			}
			//S.S. Minnow Johnson
			if(inBounds(mouseX, mouseY, 450, 295, 70, 55)) {
				engine.event = Event.Game;	
				handler.addObject(new SSMinnowJohnson( Engine.WIDTH/2 , Engine.HEIGHT - 100, 200, ID.John));
				handler.addObject(new Boss1(500, 20, 1000, ID.Boss, handler));
			}
			//Speed Gonzales
			if(inBounds(mouseX, mouseY, 785, 295, 70, 55)) {
				engine.event = Event.Game;	
				handler.addObject(new SpeedyGonzales( Engine.WIDTH/2 , Engine.HEIGHT - 100, 200, ID.Gon));
				handler.addObject(new Boss1(500, 20, 1000, ID.Boss, handler));
			}
		}

		else if (Engine.paused){
			if (inBounds(mouseX, mouseY, 400, 300, 200, 64)) {
				Engine.paused = false;
			}
			if (inBounds(mouseX, mouseY, 400, 380, 200, 64)) {
				Engine.reset = true;
				Engine.paused = false;
				engine.event = Event.CharacterSelection;

				handler.addObject(new FatAlbert( Engine.WIDTH/2 , Engine.HEIGHT - 100, 200, ID.Albert));
				handler.addObject(new Boss3(500, 20, 1000, ID.Enemy, handler));
			}
		}
		if (inBounds(mouseX, mouseY, 400, 460, 200, 64)) {
			Engine.reset = true;
			Engine.paused = false;
			engine.event = Event.Menu;
		}
	}

	public void mouseReleased(MouseEvent e) {

	}
	private boolean inBounds(int mouseX, int mouseY, int x, int y, int width, int height) {
		if(mouseX > x && mouseX < x + width) {
			if(mouseY > y && mouseY < y + height) {
				return true;
			}	
		}
		return false;
	}
	public void tick() {

		//CHANGING COLORs BB
		timer ++;
		tickspeed ++;
		if (timer == 50){
			nextRed = (int)(Math.random() * 255);
			nextBlue = (int)(Math.random() * 255);
			nextGreen = (int)(Math.random() * 255);
			timer = 0;
		}
		if (red <= nextRed) red++;
		else red--;
		if (blue <= nextBlue) blue++;
		else blue --;
		if (green <= nextGreen) green++;
		else green --;
		//CHANGING COLORs BB
		if (engine.event == Event.Help) {
			Enemy enemy = new Enemy(Engine.WIDTH - 200 , 200, 50, ID.Enemy, handler);
			if(!handler.checkFor(enemy)) {
				handler.addObject(enemy);
			}
		}

	}
	public void render(Graphics g) {

		col = new Color(red,blue,green);
		Font font = new Font(null, 1, 42);
		Font title = new Font("Magneto",Font.BOLD, 60);
		Font instructions = new Font("Helvetica", 1, 60);
		Font small = new Font("Helvetica", 1, 25);
		if(engine.event == Event.Menu) {
			//Background
			g.drawImage(menubackground, 0, 0, null);
			//Title
			g.setColor(col);g.setFont(title);g.drawString("John's Gemometric Jumble", 60, 150);
			
			g.setFont(font);
			//Start Box
			g.drawRect(350, 250, 300, 64);g.drawString("Start", 450, 300);
			//Help Box
			g.drawRect(350, 350, 300, 64);g.drawString("Help", 450, 400);
			//Quit Box
			g.drawRect(350, 450, 300, 64);g.drawString("Quit", 450, 500);
		}
		if (engine.event == Event.Help) {

			g.setColor(col);
			g.setFont(font);
			//g.drawImage(background, -300, 0, null);

			//Back Button
			g.drawString("Back", 50, 80);g.drawRect(40, 30, 120, 64);

			//Title
			g.setColor(Color.white);g.setFont(instructions);g.drawString("Instructions", 50, 200);

			//WASD KEYS
			g.setFont(small);
			g.drawRect(140, 250, 50, 50);g.drawString("W", 154, 285);
			g.drawRect(80, 310, 50, 50);g.drawString("A", 94, 345);
			g.drawRect(140, 310, 50, 50);g.drawString("S", 156, 345);
			g.drawRect(200, 310, 50, 50);g.drawString("D", 217, 345);
			g.drawString("Movement", 275, 345);

			//Shoot keys 
			g.drawRect(110, 400, 50, 50);g.drawString("J",128, 435);
			g.drawRect(170, 400, 50, 50);g.drawString("K",186, 435);
			g.drawString("Shoot", 275, 435);
			
			//Spacebar
			g.drawRect(50, 480, 300, 50);
			g.drawString("Spacebar", 140, 515);
			g.drawString("Shield", 360, 515);
			
			//ability
			g.drawRect(50, 560, 50, 50);
			g.drawString("Q", 65, 595);
			g.drawString("Special Ability", 115, 595);
			
			//Escape
			g.drawRect(50, 640, 50, 50);
			g.drawString("Esc", 53, 675);
			g.drawString("Go back", 115, 675);
	
		}
		if (engine.event == Event.CharacterSelection) {

			g.setColor(col);
			g.setFont(font);
			//Back Button
			g.drawString("Back", 50, 80);
			g.drawRect(40, 30, 120, 64);
			
			g.setFont(title);
			g.drawString("Character Selection", 157, 180);
			
			g.setFont(small);
			//FAT ALBERT 
			g.setColor(Color.green);g.drawString("Fat Albert", 120, 250);
			//PIC OF Fat Albert
			g.setColor(Color.green);g.fillOval(150 - 15, 300 + 10, 20, 40);
			g.setColor(Color.green);g.fillOval(150 + 34, 300 + 10, 20, 40);
			g.setColor(Color.white);g.fillRect(150, 300, 40, 40);
			g.setColor(Color.green);g.fillRect(150 + 10, 300 + 10, 20, 20);
			g.setColor(Color.green);g.fillRect(150, 300 - 5, 10, 5);
			g.setColor(Color.green);g.fillRect(150 + 30,  300 - 5, 10, 5);
			
			g.drawString("Fat Albert is fat", 80, 380);
			g.drawString("Honestly a mammoth", 50, 405);
			g.drawString("If you pick him", 85, 430);
			g.drawString("You can expect", 80, 455);
			g.drawString("+Increased Health", 70, 480);
			g.drawString("+Quick Regeneration", 50, 505);
			g.drawString("However,", 115, 530);
			g.drawString("Let it be known", 80, 555);
			g.drawString("You'll experience", 70, 580);
			g.drawString("-Decreased speed", 60, 605);
			//BOUNDARIES-- g.drawRect(135, 295, 70, 55);
			//S.S. MINNOW JOHNSON
			g.setColor(Color.MAGENTA);g.drawString("S.S. Minnow", 410, 250);g.drawString("Johnson",430, 280);
			//PIC OF S.S. Minnow Johnson
			g.setColor(Color.MAGENTA);g.fillOval(465 - 15, 300 + 10, 20, 40);
			g.setColor(Color.MAGENTA);g.fillOval(465 + 34, 300 + 10, 20, 40);
			g.setColor(Color.white);g.fillRect(465, 300, 40, 40);
			g.setColor(Color.MAGENTA);g.fillRect(465 + 10, 300 + 10, 20, 20);
			g.setColor(Color.MAGENTA);g.fillRect(465, 300 - 5, 10, 5);
			g.setColor(Color.MAGENTA);g.fillRect(465 + 30,  300 - 5, 10, 5);
			
			g.drawString("Johnson is a normie", 360, 380);
			g.drawString("Just an everyday Joe", 353, 405);
			g.drawString("If you pick him", 390, 430);
			g.drawString("You can expect", 385, 455);
			g.drawString("+Giant Bullet", 395, 480);
			g.drawString("However,", 425, 505);
			g.drawString("Let it be known", 390, 530);
			g.drawString("You'll experience", 375, 555);
			g.drawString("Normal speed", 400, 580);
			g.drawString("Normal health", 400, 605);
			//BOUNDARIES -- g.drawRect(450, 295, 70, 55);
			
			//SPEEDY GONZALES
			g.setColor(Color.CYAN);g.drawString("Speedy", 775, 250);g.drawString("Gonzales", 765, 280);
			//PIC OF Speedy Gonzales
			g.setColor(Color.CYAN);g.fillOval(800 - 15, 300 + 10, 20, 40);
			g.setColor(Color.CYAN);g.fillOval(800 + 34, 300 + 10, 20, 40);
			g.setColor(Color.white);g.fillRect(800, 300, 40, 40);
			g.setColor(Color.CYAN);g.fillRect(800 + 10, 300 + 10, 20, 20);
			g.setColor(Color.CYAN);g.fillRect(800, 300 - 5, 10, 5);
			g.setColor(Color.CYAN);g.fillRect(800 + 30,  300 - 5, 10, 5);
			
			g.drawString("Gonzales is a Ferrari", 700, 380);
			g.drawString("Representing Mexico", 700, 405);
			g.drawString("If you pick him", 735, 430);
			g.drawString("You can expect", 730, 455);
			g.drawString("+Increased speed", 710, 480);
			g.drawString("+Wipeout bullets", 710, 505);
			g.drawString("However,", 765, 530);
			g.drawString("Let it be known", 730, 555);
			g.drawString("You'll experience", 720, 580);
			g.drawString("-Decreased health", 710, 605);
			//BOUNDARIES -- g.drawRect(785, 295, 70, 55);
			
		}
	}

}