import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Turret extends Enemy
{
	
	public Turret(int x, int y, int h, ID id, Handler handler) {
		super(x, y, h, id, handler);
		this.handler = handler;
		health = h;
		// TODO Auto-generated constructor stub
	}
	public void tick() {
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
		}
		x += speedX;
		y += speedY;

	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 40, 60);

	}
	public Rectangle getBounds() {
		Rectangle rect = new Rectangle(x, y, 60, 60);
		return rect;
	}
	public void shoot()
	{
		if (timer == 70){


			BadBullet a = new BadBullet(this.getX() + 10, this.getY() - 10, 30, ID.BadBullet, handler,-6);
			handler.addObject(a);
			timer = 0;
		}
	}
	
	public void move()
		{
			if(timer2 == 50)
			{
				double k = Math.random();
				if(k < 0.5)
				{
					this.setSpeedX(-2);
				}
				else
				{
					this.setSpeedX(2);
				}
				timer2 = 0;
			}
			
		}
	}

