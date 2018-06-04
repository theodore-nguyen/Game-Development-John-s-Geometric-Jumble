import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GoodBullet extends Ship{

	private Handler handler;
	public GoodBullet(int x, int y, int h, ID id, Handler handler) {
		super(x, y, h, id);
		this.handler = handler;
		setSpeedY(-10);
	}
	public void tick() {
		x += speedX * 1;
		y += speedY * 1;

		for(int i = 0; i < handler.actors.size(); i ++) {
			Actor temp = handler.actors.get(i);
			
			if(temp.id == ID.BadBullet || temp.id == ID.Boss || temp.id == ID.Enemy) {
				if (getBounds().intersects(temp.getBounds())) {
					handler.removeObject(this);
					temp.setHealth(temp.getHealth() - 5);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, 10, 15);
	}

	public Rectangle getBounds() {
		Rectangle rect = new Rectangle(x, y, 10, 15);
		return rect;
	}

}