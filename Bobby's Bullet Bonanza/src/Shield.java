import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Shield extends Ship{

	private Handler handler;
	private boolean canshield = true;
	private int tim = 0;
	public Shield(int x, int y, int h, ID id, Handler handler) {
		super(x, y, h, id);
		this.handler = handler;
	}
	public void tick() {
		for(int i = 0; i < handler.actors.size(); i ++) {
			Actor temp = handler.actors.get(i);
			if (temp.id == ID.John || temp.id == ID.Albert || temp.id == ID.Gon) {
				x = temp.getX() - 10;
				y = temp.getY() - 20;

			}
		}
		if (this.getHealth() == 0){
			handler.removeObject(this);
		}

	}
	public void render(Graphics g) {

		g.setColor(Color.CYAN);
		g.drawRoundRect(x - 15, y, 90, 90, 90, 90);

		g.setColor(Color.CYAN);
		g.drawRoundRect(x - 14, y, 90, 90, 90, 90);

		g.setColor(Color.CYAN);
		g.drawRoundRect(x - 16, y, 90, 90, 90, 90);

		g.setColor(Color.CYAN);
		g.drawRoundRect(x - 13, y, 90, 90, 90, 90);

		g.setColor(Color.CYAN);
		g.drawRoundRect(x - 17, y, 90, 90, 90, 90);

		g.setColor(Color.CYAN);
		g.drawRoundRect(x - 15, y + 1, 90, 90, 90, 90);

		g.setColor(Color.CYAN);
		g.drawRoundRect(x - 15, y + 2, 90, 90, 90, 90);

		g.setColor(Color.CYAN);
		g.drawRoundRect(x - 15, y - 2, 90, 90, 90, 90);

		g.setColor(Color.CYAN);
		g.drawRoundRect(x - 15, y - 1, 90, 90, 90, 90);

	}
	public Rectangle getBounds() {
		Rectangle rect = new Rectangle(x - 20 , y, 70, 70);
		return rect;
	}


}
