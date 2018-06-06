import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.lang.Thread.State;

import javax.swing.JOptionPane;


// Influenced by Youtube Channel RealTutsGML 

public class Engine extends Canvas implements Runnable {

	private static final long serialVersionUID = 3051973764948958054L;

	public static final int WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;  //16:9 Ratio

	private Thread thread; 
	public boolean running = false;

	private Handler handler;

	public static int timer;
	public static boolean paused = false;
	public static boolean reset = false;
	public static int score;
	public int highschore;
	public Event event;
	//ChangeEvent.Game (to skip the menu (easier debug))
	public Menu menu;
	private FatAlbert albert = new FatAlbert(0,0,0, ID.Albert);
	private SSMinnowJohnson john = new SSMinnowJohnson(0,0,0, ID.John);
	private SpeedyGonzales gon = new SpeedyGonzales(0,0,0, ID.Gon);
	
	

	public static void infoBox(String infoMessage, String titleBar)
	{
		JOptionPane.showMessageDialog(null, infoMessage, "" + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	public Engine(){
		//	Engine.infoBox("Press J and K to shoot \nWASD to move \nShoot enemies to win \nDon't die \nGIT GUD \nGIT GUD \nGIT GUD \nGIT GUD \nGIT GUD \nGIT GUD \nGIT GUD \nGIT GUD \nGIT GUD \nGIT GUD \nGIT GUD \nGIT GUD \nGIT GUD", "INSTRUCTIONS");
		event = Event.Menu;
		handler = new Handler();
		menu = new Menu(this, handler);
		new Window(WIDTH, HEIGHT, "John's Geometric Jumble", this);
		

		this.requestFocusInWindow();
		this.addKeyListener( new KeyUser(handler,this) );
		this.addMouseListener(menu);


	}

	public synchronized void start(){ 
		thread = new Thread(this);
		thread.start();
		running = true;

	}
	public synchronized void stop(){
		try{
			thread.join(); 
			running = false;

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	//Algorithm borrowed  
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (running){ 
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1){
				tick();
				delta --;
			}
			if(running){
				render(); 
			}
		}
		stop();
	}

	private void tick(){

		if (event == Event.Game) {
			if(handler.checkFor(albert) || handler.checkFor(john) || handler.checkFor(gon)){
				if (!paused) {
					score++;
					handler.tick();
				}
			}
		}
		else if (event == Event.Menu || event == Event.Help || event == Event.CharacterSelection) {
			menu.tick();
			handler.tick();
		}
	}
	private void render()
	{

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(3);
			return;
		}	
		//PAUSE STUFF
		int alpha = 190; // 50% transparent
		Color pause = new Color(0, 0, 0, alpha);
		Font title = new Font("Helvetica", 1, 60);
		Font small = new Font("Helvetica", 1, 25);

		Graphics g = bs.getDrawGraphics();
		Font health = new Font("Magneto",Font.BOLD, 20);
		Font norm = new Font("Times New Roman" , 1, 12);
		
		//BLACK CANVAS
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if (event == Event.Game) {
			//Score
			g.setColor(Color.white);
			g.drawString("Score: " + score, 100, 100);
			
			//STARS
			g.setColor(Color.yellow);
			int[] x1 = {15, 27, 20, 35, 50, 43, 55, 40, 35, 30, 20};
			int[] y1 = {30, 42, 60, 50, 60, 42, 30, 30, 15, 30, 30};
			g.fillPolygon(x1, y1, 11);

			g.setColor(Color.yellow);
			int[] x2 = {15, 27, 20, 35, 50, 43, 55, 40, 35, 30, 20};
			int[] y2 = {30 + 50, 42 + 50, 60 + 50, 50 + 50, 60 + 50, 42 + 50, 30 + 50, 30 + 50, 15 + 50, 30 + 50, 30 + 50};
			g.fillPolygon(x2, y2, 11);

			g.setColor(Color.yellow);
			int[] x3 = {15 + 50, 27 + 50, 20 + 50, 35 + 50, 50 + 50, 43 + 50, 55 + 50, 40 + 50, 35 + 50, 30 + 50, 20 + 50};
			int[] y3 = {30, 42, 60, 50, 60, 42, 30, 30, 15, 30, 30};
			g.fillPolygon(x3, y3, 11);

			g.setColor(Color.yellow);
			int[] x4 = {985, 973, 980, 965, 950, 957, 945, 960, 965, 970, 980};
			int[] y4 = {30, 42, 60, 50, 60, 42, 30, 30, 15, 30, 30};
			g.fillPolygon(x4, y4, 11);

			g.setColor(Color.yellow);
			int[] x5 = {985 - 50, 973 - 50, 980 - 50, 965 - 50, 950 - 50, 957 - 50, 945 - 50, 960 - 50, 965 - 50, 970 - 50, 980 - 50};
			int[] y5 = {30, 42, 60, 50, 60, 42, 30, 30, 15, 30, 30};
			g.fillPolygon(x5, y5, 11);

			g.setColor(Color.yellow);
			int[] x6 = {985, 973, 980, 965, 950, 957, 945, 960, 965, 970, 980};
			int[] y6 = {30 + 50, 42 + 50, 60 + 50, 50 + 50, 60 + 50, 42 + 50, 30 + 50, 30 + 50, 15 + 50, 30 + 50, 30 + 50};
			g.fillPolygon(x6, y6, 11);
			
			
			//icon for shield
			
			g.setColor(Color.WHITE);
			g.fillRect(916, 640, 66, 66);
			g.setColor(Color.CYAN);
			g.fillRect(927, 650, 44, 7);
			g.setColor(Color.CYAN);
			g.fillRect(927, 650, 7, 43);
			g.setColor(Color.CYAN);
			g.fillRect(927, 688, 44, 7);
			g.setColor(Color.CYAN);
			g.fillRect(965,650, 7, 45);


			//health bar
			g.setColor(Color.DARK_GRAY);
			g.fillRect(10, HEIGHT - 60, 400, 30);
			for(int i = 0; i < handler.actors.size(); i ++) {
				Actor temp = handler.actors.get(i);
				if (temp.id == ID.John || temp.id == ID.Albert || temp.id == ID.Gon){
					g.setColor(Color.green);
					g.fillRect(10, HEIGHT - 60, temp.getHealth() * 2, 30);
					g.setFont(health);
					g.setColor(Color.LIGHT_GRAY);
					g.drawString("Health: " + temp.getHealth(), 10, HEIGHT - 60);
					g.setFont(norm);
					if(!temp.canShield()) 
					{
						g.setColor(Color.GRAY);
						g.fillRect(916, 640, 66, temp.getTim() / 3);
					}
				}

			}
			
			g.setColor(Color.gray);
			g.drawRect(10, HEIGHT - 60, 400, 30);

		} 
		else if (event == Event.Menu || event == Event.Help || event == Event.CharacterSelection) {
			menu.render(g);
		}
		//Actors
		handler.render(g);
		
		// PAUSED
		if (paused) {
			//BLUR
			g.setColor(pause);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			//Pause Block
			g.setColor(Color.white);
			g.setFont(title);
			g.drawRect(300, 100, 400, 500);
			g.drawString("Paused", 395, 200);
			
			g.setFont(small);
			g.drawRect(400, 300, 200, 64);
			g.drawString("Resume", 450, 340);
			
			g.drawRect(400, 380, 200, 64);
			g.drawString("Reset", 465, 420);
			
			g.drawRect(400, 460, 200, 64);
			g.drawString("Menu", 465, 500);
			
		}

		g.dispose();
		bs.show();
	}


	public static void main(String[] args) {
		new Engine();
	}
}