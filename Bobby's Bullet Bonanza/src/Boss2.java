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


		public void render(Graphics g) {
			g.setColor(Color.ORANGE);
			g.fillRect(x,y , 100, 100);

			g.setColor(Color.BLACK);
			g.fillRect( x + 17, y + 20, 15, 15);

			g.setColor(Color.BLACK);
			g.fillRect(x + 65, y + 20, 15, 15);

			g.setColor(Color.BLACK);
			g.fillRect(x + 15, y + 60, 60, 10);
		}

		public void shoot()
		{
			if (timer == 1500){
				for(int i = 0; i< 5; i++)
				{
					Enemy f = new Enemy( (int)(Math.random() * 900), (int) (Math.random() * 250) + 30, 50, ID.Enemy, handler);
					handler.addObject(f);
					
					Kamikaze w = new Kamikaze( (int)(Math.random() * 900), (int) (Math.random() * 300) + 30, 50, ID.Enemy, handler, 1);
					handler.addObject(w);
		
				}
				timer = 0;
			}
		}

	}
