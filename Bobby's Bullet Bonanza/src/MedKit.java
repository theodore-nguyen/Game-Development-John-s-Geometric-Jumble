import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class MedKit extends Ship{
	private Handler handler;
	public MedKit(int x, int y, int h, ID id, Handler handle)
	{
		super(x, y, h, id);
		// TODO Auto-generated constructor stub
		this.handler = handle;	
	}
	public void render (Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(x, y, 40, 40);

		g.setColor(Color.RED);
		g.fillRect(x + 3, y + 17, 35, 7);

		g.setColor(Color.RED);
		g.fillRect(x + 17, y + 3, 7, 35);
		
		g.setColor(Color.GREEN);
		g.drawRect(x, y, 40, 40);

	}



	public void tick() {
		for(int i = 0; i < handler.actors.size(); i ++) {
			Actor temp = handler.actors.get(i);
			if(temp.id == ID.John || temp.id == ID.Albert || temp.id == ID.Gon )
			{
				if (getBounds().intersects(temp.getBounds())){
					if(temp.getHealth() + 50 >= temp.getMax())
					{
						temp.setHealth(temp.getMax());
					}
					else{
						temp.setHealth(temp.getHealth() + 50);
						
					}
					handler.removeObject(this);
				}

			}

		}
	}


}

