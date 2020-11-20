package firstPerson;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Draw extends JPanel
{
	private static final long serialVersionUID = 5507853623282451784L; //to fix the warning

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setBackground(Color.black);
		GameLogic.logicManager();
		
		//////////////////////
		//for birds eye view//
		//////////////////////
//		drawMap(g);
//		
//		for(Vector temp : GameLogic.rays)
//		{
//			g.setColor(Color.yellow);
//			g.drawLine((int)GameLogic.player.getPositionX() + (GameLogic.player.getWidth() / 2),
//					   (int)GameLogic.player.getPositionY() + (GameLogic.player.getHeight() / 2),
//					   (int)GameLogic.rayCheck(temp).x, (int)GameLogic.rayCheck(temp).y);
//		}
//		
//		g.setColor(Color.black);
//		g.fillOval((int)GameLogic.player.getPositionX(), (int)GameLogic.player.getPositionY(),
//				   GameLogic.player.getWidth(), GameLogic.player.getHeight());
		
		drawVision(g);
		
		try
		{
			Thread.sleep(8); //I found this to be the most playable
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		repaint();
	}
	
	private void drawVision(Graphics g)
	{
		final int WHITE = 255; //the white value for walls
		final int RECTWIDTH = (Launch.WIDTH / GameLogic.rayCount);
		
		int rectIndex = 0;
		for(Vector v : GameLogic.rays)
		{
			Vector temp = GameLogic.rayCheck(v); //value > reference
			
			//determines distance
			int dist;
			if(temp.getLength() > 0)
			{
				temp.x -= GameLogic.player.getPositionX() + (GameLogic.player.getWidth() / 2);
				temp.y -= GameLogic.player.getPositionY() + (GameLogic.player.getHeight() / 2);
				
				dist = (int)temp.getLength();
			}
			else
			{
				dist = -1 * (int)temp.getLength();
			}
			
			//determines wall brightness
			int whiteness = 0;
			while(dist > 0)
			{
				whiteness++;
				dist -= (int)(((double)GameLogic.MAXLENGTH / (double)WHITE) / (double)1.5);
			}
			if(whiteness > 255)
			{
				whiteness = 255;
			}
			
			//draws
			dist = (int)temp.getLength();
			double propDist = dist;
			g.setColor(new Color(WHITE - whiteness, WHITE - whiteness, WHITE - whiteness));
			if(dist != 0)
			{
				g.fillRect(rectIndex * RECTWIDTH,
						  (int)(Launch.HEIGHT / 2 - (GameLogic.MAXLENGTH - propDist) * .9), 
						  RECTWIDTH, (int)(2 * (GameLogic.MAXLENGTH - propDist * 1.1)));
			}
			rectIndex++;
		}
	}
	
	//////////////////////
	//for birds eye view//
	//////////////////////
//	private void drawMap(Graphics g)
//	{
//		final int BLOCKSIZE = 50;
//		GameLogic.logicManager();
//		
//		g.setColor(Color.darkGray);
//		
//		int x = 0;
//		int y = 0;
//		for(int index = 0; index < GameLogic.gameMap.length; index++)
//		{
//			for(int index2 = 0; index2 < GameLogic.gameMap[index].length; index2++)
//			{
//				if(GameLogic.gameMap[index][index2] == '#')
//				{
//					g.fillRect(x, y, (int)BLOCKSIZE, (int)BLOCKSIZE);
//				}
//				
//				y += BLOCKSIZE;
//			}
//			
//			y = 0;
//			x += BLOCKSIZE;
//		}
//	}
}
