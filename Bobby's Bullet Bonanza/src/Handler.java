import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<Actor> actors = new LinkedList<Actor>();

	public void tick() {
		for(int i = 0; i < actors.size(); i ++) {
			Actor temp = actors.get(i);
			
			if (temp.getHealth() <= 0) {
				removeObject(temp);
			}
			temp.tick();
			if (temp.id == ID.BadBullet || temp.id == ID.GoodBullet) 
			{
				if (temp.getY() <= 10 || temp.getY() >= Engine.HEIGHT) removeObject(temp);
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
	public void removeAll() {
		for(int i = 0; i < actors.size(); i ++) {
			Actor temp = actors.get(i);
			removeObject(temp);
		}
	}
}
