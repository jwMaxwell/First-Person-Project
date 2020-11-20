
package firstPerson;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Launch
{
	//window sizes
	public static final int WIDTH = 1000;
	public static final int HEIGHT = WIDTH / 12 * 9;
	
	//window border values/sizes
	public static final int BORDERX = 6;
	public static final int BORDERY = 36;

	public static void main(String[] args)
	{
		JFrame jf = new JFrame();
		jf.setSize(WIDTH + BORDERX, HEIGHT + BORDERY); //window size
		jf.setResizable(false); //not resizeable
		jf.setLocationRelativeTo(null); //starts in center of the screen
		jf.setTitle("First Person Game"); //sets window title
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes on close
		jf.setVisible(true); //visible
		jf.add(new Draw());
		jf.addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				int key = e.getKeyCode();
				
				switch(key)
				{
					case KeyEvent.VK_W:
						GameLogic.player.keyPressed[0] = true;
						break;
					case KeyEvent.VK_S:
						GameLogic.player.keyPressed[2] = true;
						break;
					case KeyEvent.VK_A:
						GameLogic.player.keyPressed[1] = true;
						break;
					case KeyEvent.VK_D:
						GameLogic.player.keyPressed[3] = true;
						break;
					case KeyEvent.VK_LEFT:
						GameLogic.leftTurn = true;
						break;
					case KeyEvent.VK_RIGHT:
						GameLogic.rightTurn = true;
						break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) 
			{
				int key = e.getKeyCode();
						
				switch(key)
				{
					case KeyEvent.VK_W:
						GameLogic.player.keyPressed[0] = false;
						break;
					case KeyEvent.VK_S:
						GameLogic.player.keyPressed[2] = false;
						break;
					case KeyEvent.VK_A:
						GameLogic.player.keyPressed[1] = false;
						break;
					case KeyEvent.VK_D:
						GameLogic.player.keyPressed[3] = false;
						break;
					case KeyEvent.VK_LEFT:
						GameLogic.leftTurn = false;
						break;
					case KeyEvent.VK_RIGHT:
						GameLogic.rightTurn = false;
						break;
				}	
			}

			//this is needed to use keylistener, however, it is never needed
			@Override
			public void keyTyped(KeyEvent e){}
		});
	}
}

