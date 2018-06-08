import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BadBullet extends Ship{
	private Handler handler;
	public BadBullet(int x, int y, int h, ID id, Handler handler, int bulletspeed) {
		super(x, y, h, id);
		this.handler = handler;
		health = h; 
		setSpeedY(bulletspeed);
	}
	public void tick() {
		x += speedX * 1;
		y += speedY * 1;

		for(int i = 0; i < handler.actors.size(); i ++) {
			Actor temp = handler.actors.get(i);
			
			if(temp.id == ID.GoodBullet || temp.id == ID.John || temp.id == ID.Albert || temp.id == ID.Gon || temp.id == ID.Shield) {
				if (getBounds().intersects(temp.getBounds())) {
				
					temp.setHealth(temp.getHealth() - 10);
					handler.removeObject(this);
				}
			}
		}
		
		if (this.getHealth() == 0){
			handler.removeObject(this);
		}

	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 30, 30);
		g.setColor(Color.GREEN);
		g.drawRect(x, y, 30, 30);
	}

	public Rectangle getBounds() {
		Rectangle rect = new Rectangle(x, y, 30, 30);
		return rect;
	}

}

