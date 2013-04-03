import java.awt.Color;

import javax.swing.JFrame;

/* Object
 * Component
 * Container
 * Window
 * Frame
 * JFrame
 * */

public class Ventana extends JFrame{

	public Ventana(int w, int h, String t){
		this.setSize(w, h);
		this.setTitle(t);
		this.setLocationRelativeTo(null);
		// this.setUndecorated(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	
}
