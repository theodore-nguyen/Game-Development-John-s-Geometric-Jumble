import java.awt.Color;
import java.awt.Graphics;

public class FatAlbert extends Ship {
	private int tim = 0;
	private int timtoo = 0;
	public static boolean ability = false;
	private int health;
	private int uptime = 1;
	private int regen;
	private boolean Special = true;

	public FatAlbert(int x, int y, int h, ID id) {
		super(x, y, h, id);
		this.health = h;
		// TODO Auto-generated constructor stub	
	}
	//How fast FatAlbert is moving
	public void tick() {
		x += speedX * 2;
		y += speedY * 2;

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

			if(timtoo == 3000) 
			{
				timtoo = 0;
				this.setSpecial(true);
			}
			//ABILTY
			regen = this.getHealth();
			if(ability) {
				uptime++;
				if(regen < health) regen ++;
				if(uptime < 200) {
					this.setHealth(regen);
				} else {
					uptime = 0;
					ability = false;
				}
			}
		}
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
	public int getTim() 
	{
		return tim;
	}
	
	public int getMax()
	{
		return 400;
	}
	public void render(Graphics g) {
		//booster left
		g.setColor(Color.green);
		g.fillOval(x - 15, y + 10, 20, 40);

		//booster right
		g.setColor(Color.green);
		g.fillOval(x + 34, y + 10, 20, 40);
		
		//booster right
		g.setColor(Color.BLACK);
		g.drawOval(x + 34, y + 10, 20, 40);
		
		g.setColor(Color.BLACK);
		g.drawOval(x - 15, y + 10, 20, 40);

		//box
		g.setColor(Color.white);
		g.fillRect(x, y, 40, 40);
		
		g.setColor(Color.GREEN);
		g.drawRect(x, y, 40, 40);

		//inner design
		g.setColor(Color.green);
		g.fillRect(x + 10, y + 10, 20, 20);

		//shooter left
		g.setColor(Color.green);
		g.fillRect(x, y - 5, 10, 5);

		//shooter right
		g.setColor(Color.green);
		g.fillRect(x + 30, y - 5, 10, 5);
	}

}
