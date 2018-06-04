import java.awt.Color;
import java.awt.Graphics;


public class SpeedyGonzales extends Ship{

	public SpeedyGonzales(int x, int y,int h, ID id) {
		super(x, y, h, id);
		// TODO Auto-generated constructor stub
	}
	//How fast SpeedyGonzales is moving
	public void tick() {
		x += speedX * 4;
		y += speedY * 4;

		x = restrict(x, 5, Engine.WIDTH - 50);
		y = restrict(y, 250, Engine.HEIGHT - 100);
	}
	public void render(Graphics g)
	{	
		//booster left
		g.setColor(Color.blue);
		g.fillOval(x - 15, y + 10, 20, 40);

		//booster right
		g.setColor(Color.blue);
		g.fillOval(x + 34, y + 10, 20, 40);

		//shooter left
		g.setColor(Color.blue);
		g.fillRect(x, y - 5, 10, 5);

		//shooter right
		g.setColor(Color.blue);
		g.fillRect(x + 30, y - 5, 10, 5);

		//box
		g.setColor(Color.white);
		g.fillRect(x, y, 40, 40);

		//inner design
		g.setColor(Color.blue);
		g.fillRect(x + 10, y + 10, 20, 20);
	}
}
