import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

//https://www.youtube.com/watch?v=1gir2R7G9ws
//Influenced by RealTutsGML 

public class Window {

	public Window (int width, int height, String title, Engine engine){
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.requestFocusInWindow();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(engine);
		frame.setVisible(true);
		engine.start();
	}
}
