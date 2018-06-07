import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BigBullet extends GoodBullet{

	private Handler handler;
	public BigBullet(int x, int y, int h, ID id, Handler handler, Color col) {
		super(x, y, h, id, handler, col);
		this.handler = handler;
	}
	public void tick() {
		y += speedY * 1;

		for(int i = 0; i < handler.actors.size(); i ++) {
			Actor temp = handler.actors.get(i);

			if (getBounds().intersects(temp.getBounds())) {
				if (temp.id == ID.BadBullet) {
					// Do nothin
				}
				if (temp.id == ID.Enemy) {
					int dmg = (this.getHealth() - temp.getHealth());
					temp.setHealth(temp.getHealth() - this.getHealth());
					this.setHealth(dmg);
				}
				if (temp.id == ID.Boss) {
					temp.setHealth(temp.getHealth() - this.getHealth());
					handler.removeObject(this);
				}
			}
		}
		if (this.getHealth() == 0){
			handler.removeObject(this);
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRoundRect(x, y, 100, 100, 100, 100);
		
		g.setColor(Color.BLACK);
		g.drawRoundRect(x, y, 100, 100, 100, 100);

	}
	public Rectangle getBounds() {
		Rectangle rect = new Rectangle(x, y, 100, 100);
		return rect;

	}



}
