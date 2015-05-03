
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;

public class DoubleBufferedPanel extends JPanel
{

	private BufferedImage bufferedScreen;
	
	private Graphics2D bufferedGraphics;
	
	
	/**
	 * Constructor.
	 */
	public DoubleBufferedPanel() {
		// Call super-class constructor;
		super(null);
		
		// Other initializations ...
		
		// Create the offscreen buffer for offline drawing;
		bufferedScreen = new BufferedImage(this.getWidth(), this.getHeight());
		// Get the graphics of the offscreen buffer;
		bufferedGraphics = (Graphics2D) bufferedScreen.createGraphics();
	}


	/**
	 * The paint method of this component.
	 */
	public void paint(Graphics g) {
		// Call super class's paint
		super.paint(g);
		// Do all drawing stuff on the offscreen image;
		render(bufferGraphics);
		// Now, draw the offscreen image to the screen like a normal image.
		g.drawImage(bufferedScreen, 0, 0, this);
	}
	
	
	/**
	 * The method to perform all graphical operations of the game.
	 */
	private void render(Graphics2D g2d) {
		//
		// Put all your game rendering code here ...
		//
	}
	

	/** 
	 * This is always required for good double-buffering.
	 * This will cause the component not to first wipe
	 * off previous drawings but to immediately repaint.
	 * The wiping off also causes flickering.
	 * Update is called automatically when repaint() is called.
	 */
	public void update(Graphics g) {
		paint(g);
	}
}

