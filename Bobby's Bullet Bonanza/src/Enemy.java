import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends Ship{
	protected Handler handler;
	protected int timer;
	protected int timer2;
	protected int health;
	public int pointValue = 1000;
	public Enemy(int x, int y, int h, ID id, Handler handler) {
		super(x, y, h, id);
		this.handler = handler;
		health = h;
		// TODO Auto-generated constructor stub
	}
	public void tick() {
		//does not move out of canvas
		x = restrict(x, 5, Engine.WIDTH - 50);
		y = restrict(y, 5, Engine.HEIGHT - 75);
		timer++;
		shoot();
		timer2 ++;
		move();
		x = restrict(x, 5, Engine.WIDTH - 50);
		y = restrict(y, 5, Engine.HEIGHT - 75);

		if (this.getHealth() == 0){
			handler.removeObject(this);
			Engine.score += pointValue;
		}

		x += speedX;
		y += speedY;
	}
	public void render(Graphics g) {
		//Enemy Graphics
		g.setColor(Color.red);
		g.fillRect(x, y, 60, 60);

		g.setColor(Color.BLACK);
		g.fillRect(x + 10, y + 10, 15, 15);

		g.setColor(Color.BLACK);
		g.fillRect(x + 35, y + 10, 15, 15);

		g.setColor(Color.BLACK);
		g.fillRect(x + 15, y + 40, 40, 10);

		//HealthBar
		g.setColor(Color.GREEN);
		g.fillRect(x , y - 15 ,(this.getHealth()/5 * 6) , 10);

		g.setColor(Color.GRAY);
		g.drawRect(x, y - 15, 60, 10);

	}
	public Rectangle getBounds() {
		Rectangle rect = new Rectangle(x, y, 60, 60);
		return rect;
	}
	//Shoots out bad bullet traveling at a velocity of 6
	public void shoot(){
		if (timer == 30){
			BadBullet a = new BadBullet(this.getX() + 20, this.getY() + 55, 5, ID.BadBullet, handler,6);
			handler.addObject(a);
			timer = 0;
		}
	}
	public void move(){
		if(timer2 == 50){
			double k = Math.random();
			if(k < 0.5) this.setSpeedX(-2);
			else this.setSpeedX(2);
			timer2 = 0;
		}	
	}
}



