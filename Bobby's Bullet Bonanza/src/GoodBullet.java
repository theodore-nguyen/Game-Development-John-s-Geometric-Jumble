import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GoodBullet extends Ship{

	private Handler handler;
	private Color col;
	private int timer = 50;
	public GoodBullet(int x, int y, int h, ID id, Handler handler, Color col) {
		super(x, y, h, id);
		this.handler = handler;
		this.col = col;
		setSpeedY(-15);
	}
	public void tick() {
		x += speedX * 1;
		y += speedY * 1;
		timer ++;
		for(int i = 0; i < handler.actors.size(); i ++) {
			Actor temp = handler.actors.get(i);
			
			if(temp.id == ID.BadBullet || temp.id == ID.Boss || temp.id == ID.Enemy) {
				if (getBounds().intersects(temp.getBounds())) {
					handler.removeObject(this);
					temp.setHealth(temp.getHealth() - 10);
				}
			}
			
		}
	}

	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect(x, y, 10, 15);
	}

	public Rectangle getBounds() {
		Rectangle rect = new Rectangle(x, y, 10, 15);
		return rect;
	}

}
