import java.awt.Color;
import java.awt.Graphics;


public class SSMinnowJohnson extends Ship{
	private int tim = 0;
	private int timtoo = 0;
	private boolean Special = true;

	public SSMinnowJohnson(int x, int y,int h, ID id) {
		super(x, y, h, id);
		// TODO Auto-generated constructor stub
	}
	//How fast SSMinnowJohnson is moving
	public void tick() {
		x += speedX * 3;
		y += speedY * 3;

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

			if(timtoo == 500) 
			{
				timtoo = 0;
				this.setSpecial(true);
			}

		}
		if(this.getHealth() <= 0) {
			AudioPlayer.getMusic("game").stop();
			AudioPlayer.getMusic("dead").play();
		}
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
	public int getMax()
	{
		return 300;
	}
	public void render(Graphics g)
	{	
		//booster left
		g.setColor(Color.MAGENTA);
		g.fillOval(x - 15, y + 10, 20, 40);

		//booster right
		g.setColor(Color.MAGENTA);
		g.fillOval(x + 34, y + 10, 20, 40);

		//booster left
		g.setColor(Color.BLACK);
		g.drawOval(x - 15, y + 10, 20, 40);

		//booster right
		g.setColor(Color.BLACK);
		g.drawOval(x + 34, y + 10, 20, 40);

		//shooter left
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y - 5, 10, 5);

		//shooter right
		g.setColor(Color.MAGENTA);
		g.fillRect(x + 30, y - 5, 10, 5);

		//box
		g.setColor(Color.white);
		g.fillRect(x, y, 40, 40);

		//box
		g.setColor(Color.MAGENTA);
		g.drawRect(x, y, 40, 40);

		//inner design
		g.setColor(Color.MAGENTA);
		g.fillRect(x + 10, y + 10, 20, 20);

	}
	public int colorValue() {
		if ((this.getHealth() - 45) >= 0) {
			return this.getHealth() - 45;
		}
		return 0;
	}
}
