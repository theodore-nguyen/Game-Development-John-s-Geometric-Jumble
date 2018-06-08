import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//Influenced by RealTutsGML (Wizard Shooter Down Episode 5)
//https://www.youtube.com/watch?v=k78AyvTVTtY&index=5&list=PLWms45O3n--5vDnNd6aiu1CSWX3JlCU1n

public class BufferedImageLoader {
	
	private BufferedImage image;
	
	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
