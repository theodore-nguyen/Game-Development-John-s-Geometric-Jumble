import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	int timer;
	LinkedList<Actor> actors = new LinkedList<Actor>();

	public void tick() {

		for(int i = 0; i < actors.size(); i ++) {
			Actor temp = actors.get(i);
			if(Engine.reset) {
				removeObject(temp);
			}
			if(SpeedyGonzales.ability) {
				if(temp.id == ID.BadBullet) removeObject(temp);
			}
			if (temp.getHealth() <= 0) {
				removeObject(temp);
			}
			temp.tick();
			if (temp.id == ID.BadBullet || temp.id == ID.GoodBullet) 
			{
				if (temp.getY() <= 10 || temp.getY() >= Engine.HEIGHT) removeObject(temp);
			}
		}
		if(Engine.reset) {
			timer++;
			if (timer == 20) {
				Engine.reset = false;
				timer = 0;
			}
		}
		if(SpeedyGonzales.ability) {
			timer++;
			if (timer == 75) {
				SpeedyGonzales.ability = false;
				timer = 0;
			}
		}
	}
	public void render(Graphics g) {
		for(int i = 0; i < actors.size(); i ++) {
			Actor temp = actors.get(i);
			temp.render(g);
		}
	}
	public void addObject(Actor a) {
		this.actors.add(a);
	}

	public void removeObject(Actor a) {
		this.actors.remove(a);
	}
	public boolean checkFor(Actor a) {
		for(int i = 0; i < actors.size(); i ++) {
			Actor temp = actors.get(i);
			if(temp.id == a.id) {
				return true;
			}
		}
		return false;
	}
	
}