import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ship extends Actor {
	private boolean ShieldCD = true;
	private int tim = 0;
	protected int maxHealth;
	public boolean Special;
	public int timtoo;
	private int diff;
	public Ship(int x, int y, int h, ID id) {
		super(x, y, h, id);
	}
	//Method used so it does not travel out of bounds
	protected static int restrict(int val, int min, int max) {
		if (val >= max) {
			return val = max;
		} else if (val <= min) {
			return val = min;
		} else
			return val;
	}
	
	public void tick() {
		x += speedX * 1;
		y += speedY * 1;
	}
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 40, 40);
	}
	public boolean canShield() 
	{
		return ShieldCD;
	}
	public void setShield(boolean cd) 
	{
		ShieldCD = cd;
	}
	public int getTim() 
	{
		return tim;
	}
	public int getMax()
	{
		return 0;
	}

	//Default Ship size
	public Rectangle getBounds() {
		Rectangle rect = new Rectangle(x, y, 40, 40);
		return rect;
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
	@Override
	public int colorValue() {
		return 0;
	}

}
