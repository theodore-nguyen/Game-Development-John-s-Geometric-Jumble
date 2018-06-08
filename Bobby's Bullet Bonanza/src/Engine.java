import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

// Game Created by Theodore Nguyen, Wesley Wang, Ethan Phuong, Stephen Shen
/* Thanks to:
 * Influenced by Youtube Channel RealTutsGML 
 * Images used:
 * (anime)https://www.zerochan.net/1915298
 * (stars)https://www.pond5.com/stock-footage/23768280/simple-star-space-background-effect.html
 * (gameover)https://twitter.com/game_over_ports
 * (Game)https://chinchongcha.deviantart.com/art/Earth-chan-724480877
 */

public class Engine extends Canvas implements Runnable {

	private static final long serialVersionUID = 3051973764948958054L;

	public static final int WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;  //16:9 Ratio

	private Thread thread; 
	public boolean running = false;

	private Handler handler;

	public static int timer;
	private int alpha1 = 0;
	public static boolean paused = false;
	public static boolean reset = false;
	public static boolean level1;
	public static boolean level2;
	public static boolean level3;
	public int highschore;
	public Event event;
	//ChangeEvent.Game (to skip the menu (easier debug))
	public Menu menu;
	private BufferedImage background = null;
	private FatAlbert albert = new FatAlbert(0,0,0, ID.Albert);
	private SSMinnowJohnson john = new SSMinnowJohnson(0,0,0, ID.John);
	private SpeedyGonzales gon = new SpeedyGonzales(0,0,0, ID.Gon);


	public static void infoBox(String infoMessage, String titleBar)
	{
		JOptionPane.showMessageDialog(null, infoMessage, "" + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	public Engine(){
		infoBox("\"HELPPPP!!!!!\" \n-Unknown", "INCOMING TRANSMISSION!");
		event = Event.Menu;
		handler = new Handler();
		menu = new Menu(this, handler);
		new Window(WIDTH, HEIGHT, "John's Geometric Jumble", this);
		BufferedImageLoader loader = new BufferedImageLoader();
		background = loader.loadImage("/stars.jpeg");
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
					handler.tick();
				}
				if(level1){
					alpha1++;
					if(alpha1 == 255){
						level1 = false;
						alpha1 = 0;
					}
				}
				if(level2){
					alpha1++;
					if(alpha1 == 255){
						level2 = false;
						alpha1 = 0;
					}
				}
				if(level3){
					alpha1++;
					if(alpha1 == 255){
						level3 = false;
						alpha1 = 0;
					}
				}
			}
			else {
				event = Event.Death;
			}
		}
		else if (event == Event.Menu || event == Event.Help || event == Event.CharacterSelection || event == Event.Death || event == Event.Help2 || event == Event.Win || event==Event.Request) {
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
		int alpha = 190; 
		Color pause = new Color(0, 0, 0, alpha);
		Font title = new Font("Helvetica", 1, 60);
		Font small = new Font("Helvetica", 1, 25);

		Graphics g = bs.getDrawGraphics();
		Font health = new Font("Magneto",Font.BOLD, 20);
		Font norm = new Font("Times New Roman" , 1, 12);

		//BLACK CANVAS
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(background, 0, 0, null);
		if (event == Event.Game) {

			//icon for shield		
			g.setColor(Color.WHITE);
			g.fillRect(916, 640, 66, 66);
			g.setColor(Color.CYAN);
			g.fillRoundRect(921, 645, 55, 55, 55, 55);
			g.setColor(Color.WHITE);
			g.fillRoundRect(927, 650, 44, 44, 44, 44);
			g.setColor(Color.CYAN);
			g.drawRect(916, 640, 66, 66);



			//health bar
			g.setColor(Color.DARK_GRAY);
			g.fillRect(10, HEIGHT - 60, 400, 30);
			for(int i = 0; i < handler.actors.size(); i ++) {
				Actor temp = handler.actors.get(i);
				if (temp.id == ID.John || temp.id == ID.Albert || temp.id == ID.Gon){

					if(temp.id == ID.Gon){
						Color hpbar = new Color(255 - (temp.getHealth() + 55),temp.getHealth() ,0);
						g.setColor(hpbar);
						g.fillRect(10, HEIGHT - 60, temp.getHealth() * 2, 30);
						g.setFont(health);
						g.setColor(Color.LIGHT_GRAY);
						g.drawString("Health: " + temp.getHealth(), 10, HEIGHT - 60);
						g.setFont(norm);
						g.drawString("Space Warrior: Speedy Gonzales", 160, HEIGHT - 60);

						//icon for special
						g.setColor(Color.WHITE);
						g.fillRect(832, 640, 66, 66);
						g.setColor(Color.RED);
						g.drawRect(832, 640, 66, 66);
						g.setColor(Color.RED);
						g.fillRect(849, 659, 30, 30);
						g.setColor(Color.GREEN);
						g.drawRect(849, 659, 30, 30);	
						g.setColor(Color.RED);
						g.drawRoundRect(839, 649, 50, 50, 50, 50);
						g.setColor(Color.RED);
						g.drawRoundRect(839, 650, 50, 50, 50, 50);
						g.setColor(Color.RED);
						g.drawRoundRect(839, 650, 50, 50, 50, 50);
						g.setColor(Color.RED);
						g.drawRoundRect(840, 650, 50, 50, 50, 50);
						if(!temp.CanSpecial()) 
						{
							g.setColor(new Color(0, 0, 0, 190));
							g.fillRect(832, 640, 67, (temp.getTimtoo() / 3));
						}

					}
					if(temp.id == ID.John){
						
						Color hpbar = new Color(255 - temp.colorValue(), temp.colorValue() ,0);
						g.setColor(hpbar);
						g.fillRect(10, HEIGHT - 60, temp.getHealth()/3 * 4, 30);
						g.setFont(health);
						g.setColor(Color.LIGHT_GRAY);
						g.drawString("Health: " + temp.getHealth(), 10, HEIGHT - 60);
						g.setFont(norm);
						g.drawString("Space Warrior: S.S. Minnow Johnson", 160, HEIGHT - 60);

						g.setColor(Color.white);
						g.fillRect(832, 640, 66, 66);
						g.setColor(Color.MAGENTA);
						g.drawRect(832, 640, 66, 66);
						g.setColor(Color.MAGENTA);
						g.fillRoundRect(842, 650, 45, 45, 45, 45);
						g.setColor(Color.RED);
						g.drawRoundRect(842, 650, 45, 45, 45, 45);

						if(!temp.CanSpecial()) 
						{
							g.setColor(new Color(0, 0, 0, 190));
							g.fillRect(832, 640, 67, temp.getTimtoo() / 7);
						}

					}
					if(temp.id == ID.Albert){
						Color hpbar = new Color(255 - ((temp.getHealth() /2) + 55), (temp.getHealth()/2),0);
						g.setColor(hpbar);
						g.fillRect(10, HEIGHT - 60, temp.getHealth(), 30);
						g.setFont(health);
						g.setColor(Color.LIGHT_GRAY);
						g.drawString("Health: " + temp.getHealth(), 10, HEIGHT - 60);
						g.setFont(norm);
						g.drawString("Space Warrior: Fat Albert", 160, HEIGHT - 60);

						g.setColor(Color.white);
						g.fillRect(832, 640, 66, 66);
						g.setColor(Color.RED);
						g.fillRect(840, 670, 50, 10);
						g.setColor(Color.RED);
						g.fillRect(860, 650, 10, 50);
						g.setColor(Color.GREEN);
						g.drawRect(832, 640, 66, 66);

						if(!temp.CanSpecial()) 
						{
							g.setColor(new Color(0, 0, 0, 190));
							g.fillRect(832, 640, 67, (temp.getTimtoo() / 66));
						}
					}
					g.setFont(norm);
					if(!temp.canShield()) 
					{
						g.setColor(new Color(0, 0, 0, 190));
						g.fillRect(916, 640, 67, (temp.getTim() / 3) + 2);
					}
				}

			}
			g.setColor(Color.gray);
			g.drawRect(10, HEIGHT - 60, 400, 30);
			handler.render(g);

		} 
		else if (event == Event.Menu || event == Event.Help || event == Event.CharacterSelection || event == Event.Death || event == Event.Help2 || event == Event.Win || event == Event.Request) {
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
		if(level1){
			g.setColor(new Color(255,255,255, alpha1));
			g.setFont(title);
			g.drawString("Wave 1", 400, 200);
		}
		if(level2){
			g.setColor(new Color(255,255,255, alpha1));
			g.setFont(title);
			g.drawString("Wave 2", 400, 200);
		}
		if(level3){
			g.setColor(new Color(255,255,255, alpha1));
			g.setFont(title);
			g.drawString("Final Boss", 390, 200);
		}
		if(event == Event.Death) {
			g.setColor(Color.white);
			for(int i = 0; i < 20; i ++) {
				g.drawString("git gud", (int)(Math.random() * 1000), (int) (Math.random() * 700));
			}
			g.setColor(pause);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.WHITE);
			g.setFont(title);
			g.drawRect(200, 100, 600, 500);
			g.setColor(Color.RED); g.drawString("YOU HAVE FAILED!", 223, 200);
			g.setColor(Color.white);
			g.setFont(new Font(null, 1, 20));
			g.drawString("The universe will perish", 390, 230);
			g.setFont(new Font(null, Font.BOLD, 35));
			g.drawString("and it's all your fault", 330, 270);

			g.setColor(Color.white);
			g.setFont(small);
			g.drawRect(400, 300, 200, 64);
			g.drawString("Try Again", 442, 340);

			g.drawRect(400, 380, 200, 64);
			g.drawString("Menu", 465, 420);

			g.drawRect(400, 460, 200, 64);
			g.drawString("Quit", 470, 500);
			g.setFont(new Font(null, 1, 10));
			g.drawString("Are you going to give up on the world?", 407, 520);

		}
		g.dispose();
		bs.show();
	}
	public static void main(String[] args) {
		new Engine();
	}
}