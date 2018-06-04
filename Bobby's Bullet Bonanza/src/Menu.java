import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;


public class Menu extends MouseAdapter{
	private Color col;
	private int red = 255;
	private int blue = 255;
	private int green = 255;
	private int timer = 0;
	private int nextRed;
	private int nextBlue;
	private int nextGreen;
	private Engine engine;
	private Handler handler;
	
	public Menu(Engine engine, Handler handler) {
		this.engine = engine;
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if(engine.event == Event.Menu) 
		{
			//Start Button
			if(inBounds(mouseX, mouseY, 350, 250, 300, 64 )) {
				engine.event = Event.Game;	
				handler.addObject(new FatAlbert( Engine.WIDTH/2 , Engine.HEIGHT - 100, 200, ID.Albert));
				handler.addObject(new Boss1(500, 20, 1000, ID.Enemy, handler));
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
				engine.event = Event.Menu;
				handler.removeAll();
			}
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
		Font font = new Font("Because I am Happy", 1, 42);
		Font title = new Font("Magneto",Font.BOLD, 60);
		Font instructions = new Font("Helvetica", 1, 60);
		Font small = new Font("Helvetica", 1, 25);
		Font medium = new Font("Helvetica", 1, 40);
		if(engine.event == Event.Menu) {
			g.setColor(col);
			
			g.setFont(title);
			g.drawString("John's Gemometric Jumble", 60, 150);
			g.setFont(font);
			//Start Box
			g.drawRect(350, 250, 300, 64);
			g.drawString("Start", 450, 300);
			//Help Box
			g.drawRect(350, 350, 300, 64);
			g.drawString("Help", 450, 400);
			//Quit Box
			g.drawRect(350, 450, 300, 64);
			g.drawString("Quit", 450, 500);
		}
		if (engine.event == Event.Help) {
			
			g.setColor(col);
			g.setFont(font);
			
			//Back Button
			g.drawString("Back", 50, 80);
			g.drawRect(40, 30, 120, 64);
			
			//Title
			g.setColor(Color.white);
			g.setFont(instructions);
			g.drawString("Instructions", 50, 200);
			
			//WASD KEYS
			g.setFont(small);
			g.drawRect(140, 250, 50, 50);
			g.drawString("W", 154, 285);
			g.drawRect(80, 310, 50, 50);
			g.drawString("A", 94, 345);
			g.drawRect(140, 310, 50, 50);
			g.drawString("S", 156, 345);
			g.drawRect(200, 310, 50, 50);
			g.drawString("D", 217, 345);
			
			//Shoot keys 
			g.drawRect(110, 400, 50, 50);
			g.drawString("J",128, 435);
			g.drawRect(170, 400, 50, 50);
			g.drawString("K",186, 435);
			
		}
	}

}
