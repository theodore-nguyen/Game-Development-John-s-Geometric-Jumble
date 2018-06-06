import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class Boss2 extends Boss1 {

	public Boss2(int x, int y, int h, ID id, Handler handler) {
		super( x, y, h, id, handler);
		this.handler = handler;
		health = h;
		timer = 1500;
		timer2 = 0;
		// TODO Auto-generated constructor stub
	}

	public void tick() 
	{
		x = restrict(x, 5, Engine.WIDTH - 100);
		y = restrict(y, 5, Engine.HEIGHT - 75);
		shoot();
		timer++;
		timer2 ++;
		move() ;
		x = restrict(x, 5, Engine.WIDTH - 50);
		y = restrict(y, 5, Engine.HEIGHT - 75);
		if (this.getHealth() == 0){
			handler.removeObject(this);
		}
		x += speedX;
		y += speedY;
		if(this.getHealth() <= 0) 
		{
			handler.addObject(new Boss3(500, 20, 5000, ID.Enemy, handler));
		}







	}


	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(x,y , 100, 100);

		g.setColor(Color.BLACK);
		g.fillRect( x + 17, y + 20, 15, 15);

		g.setColor(Color.BLACK);
		g.fillRect(x + 65, y + 20, 15, 15);

		g.setColor(Color.BLACK);
		g.fillRect(x + 15, y + 60, 60, 10);

		g.setColor(Color.GREEN);
		g.fillRect(0 , 0 ,(this.getHealth()) / 3, 10);

		g.setColor(Color.GRAY);
		g.drawRect(0 , 0 , 1000, 10);
		
		g.setColor(Color.RED);
		g.drawRect(x , y , 100, 100);
	}


	public void shoot()
	{
		if (timer == 1500){
			for(int i = 0; i< 5; i++)
			{
				Enemy f = new Enemy( (int)(Math.random() * 900), (int) (Math.random() * 250) + 30, 50, ID.Enemy, handler);
				handler.addObject(f);
			}
			handler.addObject(new Kamikaze( (int)(Math.random() * 900), (int) (Math.random() * 300) + 30, 50, ID.Enemy, handler, 1));
			handler.addObject(new Kamikaze( (int)(Math.random() * 900), (int) (Math.random() * 300) + 30, 50, ID.Enemy, handler, 1));
			handler.addObject(new Kamikaze( (int)(Math.random() * 900), (int) (Math.random() * 300) + 30, 50, ID.Enemy, handler, 1));
			handler.addObject(new Kamikaze( 100, (int) (Math.random() * 300) + 340, 50, ID.Enemy, handler, 1));
			handler.addObject(new Kamikaze( 800, (int) (Math.random() * 300) + 340, 50, ID.Enemy, handler, 1));

			timer = 0;
		}
	}

}
