import java.awt.Color;
import java.awt.Graphics;


public class SpeedyGonzales extends Ship{
	private int tim = 0;
	private int timToo = 0;
	public boolean Special = true;
	public static boolean ability = false;
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


		if(!canShield()) 
		{
			tim ++ ;
			if(tim == 200) 
			{
				this.setShield(true);
				tim = 0;
			}
		}
		if(!CanSpecial())
		{
			timtoo ++ ;

			if(timtoo == 200) 
			{
				timtoo = 0;
				this.setSpecial(true);
			}

		}
	}
	public int getMax()
	{
		return 200;
	}
	public int getTim() 
	{
		return tim;
	}
	public boolean CanSpecial() 
	{
		return Special;
	}
	public void setSpecial(boolean S) 
	{
		Special = S;
	}
	public int getTimtoo() 
	{
		return timtoo;
	}
	public void render(Graphics g)
	{	
		//booster left
		g.setColor(Color.CYAN);
		g.fillOval(x - 15, y + 10, 20, 40);

		//booster right
		g.setColor(Color.CYAN);
		g.fillOval(x + 34, y + 10, 20, 40);

		//booster left
		g.setColor(Color.BLACK);
		g.drawOval(x - 15, y + 10, 20, 40);

		//booster right
		g.setColor(Color.BLACK);
		g.drawOval(x + 34, y + 10, 20, 40);

		//shooter left
		g.setColor(Color.CYAN);
		g.fillRect(x, y - 5, 10, 5);

		//shooter right
		g.setColor(Color.CYAN);
		g.fillRect(x + 30, y - 5, 10, 5);

		//box
		g.setColor(Color.white);
		g.fillRect(x, y, 40, 40);

		//box
		g.setColor(Color.CYAN);
		g.drawRect(x, y, 40, 40);

		//inner design
		g.setColor(Color.CYAN);
		g.fillRect(x + 10, y + 10, 20, 20);
	}
	
}
