import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Actor {
	
	protected int x, y, speedX, speedY;
	protected ID id;
	public int health;
	
	public Actor(int x, int y, int h, ID id) {
		this.x = x;
		this.y = y;
		this.health = h;
		this.id = id;
	}
	
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY() {
		return y; 
	}
	
	public void setSpeedX(int speedX){
		this.speedX = speedX;
	}
	
	public void setSpeedY(int speedY){
		this.speedY = speedY;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int h) {
		this.health = h;
	}
	public int simpleHP() 
	{
		if(this.getHealth() != 0) {
			return this.getHealth()/this.getHealth();
		}
		else
			return 0;
	}
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public abstract boolean canShield();
	public abstract void setShield(int CD);
	public abstract int getTim ();

}
