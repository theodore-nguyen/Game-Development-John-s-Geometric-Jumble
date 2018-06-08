import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

//RealTutsGML
//https://www.youtube.com/watch?v=HRaJXVuZjRM
public class AudioPlayer {
	
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load() {
		try {
			musicMap.put("game", new Music("game.ogg"));
			musicMap.put("menu", new Music("menu.ogg"));
			musicMap.put("win", new Music("win.ogg"));
			musicMap.put("dead", new Music("dead.ogg"));
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}

	
	

}
