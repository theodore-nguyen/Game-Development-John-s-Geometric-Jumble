import java.awt.Color;
import java.awt.Graphics;

public class FatAlbert extends Ship {
	public FatAlbert(int x, int y, int h, ID id) {
		super(x, y, h, id);
		// TODO Auto-generated constructor stub	
	}
	//How fast FatAlbert is moving
	public void tick() {
		x += speedX * 2;
		y += speedY * 2;
		
		x = restrict(x, 5, Engine.WIDTH - 50);
		y = restrict(y, 5, Engine.HEIGHT - 75);
	}
	public void render(Graphics g) {
		//box
		g.setColor(Color.white);
		g.fillRect(x, y, 40, 40);

		//inner design
		g.setColor(Color.green);
		g.fillRect(x + 10, y + 10, 20, 20);

		//booster left
		g.setColor(Color.white);
		g.fillOval(x - 15, y + 10, 20, 40);

		//booster right
		g.setColor(Color.white);
		g.fillOval(x + 34, y + 10, 20, 40);

		//shooter left
		g.setColor(Color.green);
		g.fillRect(x, y - 5, 10, 5);

		//shooter right
		g.setColor(Color.green);
		g.fillRect(x + 30, y - 5, 10, 5);

	}


}
