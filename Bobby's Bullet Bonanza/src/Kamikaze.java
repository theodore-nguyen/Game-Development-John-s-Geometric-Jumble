import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Kamikaze extends BadBullet{

	private Handler handler; 
	private int speed;
	public Kamikaze(int x, int y, int h, ID id, Handler handler, int speed) {
		super(x, y, h, id, handler, speed);
		this.handler = handler;
		this.speed = speed;
		// TODO Auto-generated constructor stub
	}

	public void tick(){
		for(int i = 0; i < handler.actors.size(); i ++) 
		{
			Actor temp = handler.actors.get(i);
			//Homing towards Player
			if (temp.id == ID.John || temp.id == ID.Albert || temp.id == ID.Gon)
			{
				if (temp.getX() > this.getX())
				{
					x += speed;
				}
				else if( temp.getX() < this.getX())
				{
					x -= speed; 
				}
				else{
					x += 0;
				}
				if (temp.getY() > this.getY()){
					y += speed;
				}
				else if( temp.getY() < this.getY()){
					y -= speed; 
				}
				else{
					y += 0;
				}

			}
			//Intersected 
			if(temp.id == ID.GoodBullet || temp.id == ID.John || temp.id == ID.Albert || temp.id == ID.Gon) {
				if (getBounds().intersects(temp.getBounds())) {

					temp.setHealth(temp.getHealth() - 40);
					if(temp.id == ID.John || temp.id == ID.Albert || temp.id == ID.Gon)
						handler.removeObject(this);
				}
			}
		}
		if (this.getHealth() <= 0){
			handler.removeObject(this);

			if ((int)(Math.random()  * 100) <= 25 ) 
			{
				handler.addObject(new MedKit(getX(), getY(), 5, ID.MedKit, handler));
			}
		}
	}

	public void render(Graphics g){

		g.setColor(Color.green);
		g.fillRect(x, y, 60, 60);

		g.setColor(Color.YELLOW);
		g.drawRect(x, y, 60, 60);

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
}
