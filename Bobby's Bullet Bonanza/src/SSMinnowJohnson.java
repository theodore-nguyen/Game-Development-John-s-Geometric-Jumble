import java.awt.Color;
import java.awt.Graphics;


public class SSMinnowJohnson extends Ship{

	public SSMinnowJohnson(int x, int y,int h, ID id) {
		super(x, y, h, id);
		// TODO Auto-generated constructor stub
	}
	//How fast SSMinnowJohnson is moving
	public void tick() {
		x += speedX * 3;
		y += speedY * 3;

		x = restrict(x, 5, Engine.WIDTH - 50);
		y = restrict(y, 5, Engine.HEIGHT - 75);
	}
	public void render(Graphics g)
	{	
		//booster left
		g.setColor(Color.MAGENTA);
		g.fillOval(x - 15, y + 10, 20, 40);

		//booster right
		g.setColor(Color.MAGENTA);
		g.fillOval(x + 34, y + 10, 20, 40);

		//shooter left
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y - 5, 10, 5);

		//shooter right
		g.setColor(Color.MAGENTA);
		g.fillRect(x + 30, y - 5, 10, 5);

		//box
		g.setColor(Color.white);
		g.fillRect(x, y, 40, 40);

		//inner design
		g.setColor(Color.MAGENTA);
		g.fillRect(x + 10, y + 10, 20, 20);
		
	}
}
