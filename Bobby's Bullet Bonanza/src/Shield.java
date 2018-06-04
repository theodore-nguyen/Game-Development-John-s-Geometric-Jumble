import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Shield extends Ship{

	private Handler handler;
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
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, 60, 10);
	}
	public Rectangle getBounds() {
		Rectangle rect = new Rectangle(x, y, 60, 10);
		return rect;
	}

}
