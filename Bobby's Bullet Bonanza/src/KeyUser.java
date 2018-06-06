import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyUser implements KeyListener{

	private Handler h;
	private boolean w;
	private boolean a;
	private boolean s;
	private boolean d;
	private Engine engine;

	public KeyUser (Handler handler, Engine engine) {
		h = handler;
		w = false;
		a = false;
		s = false;
		d = false; 
		this.engine = engine;

	}

	public void keyPressed(KeyEvent e) {
		for(int i = 0; i < h.actors.size(); i ++) {
			Actor temp = h.actors.get(i);

			if (temp.id == ID.John || temp.id == ID.Albert || temp.id == ID.Gon)
			{

				if (e.getKeyCode() == KeyEvent.VK_W ) {
					temp.setSpeedY(-2);
					w = true;

				}
				if (e.getKeyCode() == KeyEvent.VK_A ) {
					temp.setSpeedX(-2);
					a = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_S ) {
					temp.setSpeedY(2); 
					s = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_D ) {
					temp.setSpeedX(2);
					d = true;
				}

			}
		}

	}
	@Override
	public void keyReleased(KeyEvent e) {

		for(int i = 0; i < h.actors.size(); i ++) {
			Actor temp = h.actors.get(i);
			int key = e.getKeyCode();

			if (temp.id == ID.John || temp.id == ID.Albert || temp.id == ID.Gon)
			{
				if (key == KeyEvent.VK_W) {
					w = false;
				}

				if (e.getKeyCode() == KeyEvent.VK_A) {
					a = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					s = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					d = false;
				}
				if(!w && !s) {
					temp.setSpeedY(0);
				}
				if (!a && !d) {
					temp.setSpeedX(0);
				}

				if (e.getKeyCode() == KeyEvent.VK_J) {
					if (temp.id == ID.John) h.addObject(new GoodBullet(temp.getX(), temp.getY() - 20, 5, ID.GoodBullet, h, Color.MAGENTA));
					if (temp.id == ID.Albert) h.addObject(new GoodBullet(temp.getX(), temp.getY() - 20, 5, ID.GoodBullet, h, Color.GREEN));
					if (temp.id == ID.Gon) h.addObject(new GoodBullet(temp.getX(), temp.getY() - 20, 5, ID.GoodBullet, h, Color.CYAN));
				}
				if (e.getKeyCode() == KeyEvent.VK_K) {
					if (temp.id == ID.John) h.addObject(new GoodBullet(temp.getX() + 30, temp.getY() - 20, 5, ID.GoodBullet, h, Color.MAGENTA));
					if (temp.id == ID.Albert) h.addObject(new GoodBullet(temp.getX() + 30, temp.getY() - 20, 5, ID.GoodBullet, h, Color.GREEN));
					if (temp.id == ID.Gon) h.addObject(new GoodBullet(temp.getX() + 30, temp.getY() - 20, 5, ID.GoodBullet, h, Color.CYAN));
				}
				if (e.getKeyCode() == KeyEvent.VK_Q) {
					
					if (temp.id == ID.John) h.addObject(new BigBullet(temp.getX(), temp.getY(), 300, ID.GoodBullet, h, null));
					if (temp.id == ID.Albert) FatAlbert.ability = true;
					if (temp.id == ID.Gon) SpeedyGonzales.ability = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) 
					if(temp.canShield())
					{
						h.addObject(new Shield(0, 0, 15, ID.Shield, h));
						temp.setShield(2);
					}				
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			if (engine.event == Event.Game) {
				if (Engine.paused) Engine.paused = false;
				else Engine.paused = true;	
			}
			else if(engine.event == Event.CharacterSelection) engine.event = Event.Menu;
			else if(engine.event == Event.Help) {
				Engine.reset = true;
				engine.event = Event.Menu;
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}