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
	public SpawnSystem elite;
	public Event event;
	//ChangeEvent.Game (to skip the menu (easier debug))
	public Menu menu;

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
		this.addKeyListener( new KeyUser(handler) );
		this.addMouseListener(menu);

		elite = new SpawnSystem(handler);
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
		int frames = 0;
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
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick(){

		if (event == Event.Game) {
			handler.tick();
		}
		else if (event == Event.Menu || event == Event.Help) {
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

		Graphics g = bs.getDrawGraphics();
		Font health = new Font("Magneto",Font.BOLD, 20);
		Font norm = new Font("Times New Roman" , 1, 12);
		//BLACK CANVAS
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if (event == Event.Game) {
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
			//HEALTH CODE

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

				}
			}
			g.setColor(Color.gray);
			g.drawRect(10, HEIGHT - 60, 400, 30);


		} 
		else if (event == Event.Menu || event == Event.Help) {
			menu.render(g);
		}
		//Actors
		handler.render(g);

		g.dispose();
		bs.show();
	}


	public static void main(String[] args) {
		new Engine();
	}
}
