package firstPerson;

import java.util.ArrayList;

public class GameLogic 
{
	private static final int PLAYERSIZE = 50; //this is primarily used in birds eye view
	public static Player player = new Player(PLAYERSIZE, PLAYERSIZE, 24, 24); //arbitrary position
	public static ArrayList<Vector> rays = new ArrayList<Vector>();
	public static final int rayCount = 1000;
	public static Vector viewAngle;
	public static final int MAXLENGTH = 510;
	public static boolean loaded = false;

	private static double angle = 0.0;
	public static boolean leftTurn = false;
	public static boolean rightTurn = false;
	
	public static char[][] gameMap = new char[20][15];
	
	/**
	 * checks and changes values for every frame
	 */
	public static void logicManager()
	{
		if(!loaded) //only happens once
		{
			setMap();
			setVectors();
			loaded = true;
		}
		
		playerMove();
		turns();
	}
	
	/**
	 * moves the player
	 */
	private static void playerMove()
	{
		final int FORWARD = rayCount / 2; //index for the ray pointing forward
		Vector move = new Vector(rays.get(FORWARD)); //the x/y coordinates to move
		
		final double MOVEDISTANCE = 1.5;
		double a = Math.atan(move.y / move.x); //finds the angle
		move.x = (Math.cos(a) * MOVEDISTANCE); //finds x-leg length
		move.y = (Math.sin(a) * MOVEDISTANCE); //finds y-leg length
		
		//adjusts for negative directions
		if((rays.get(FORWARD).x < 0 && rays.get(FORWARD).y > 0) || 
		   (rays.get(FORWARD).x < 0 && rays.get(FORWARD).y < 0))
		{
			move.multiply(-1);
		}
		
		//move forward
		if(player.keyPressed[0]) //W
		{
			player.move(move.x, move.y);
		}
		//move backwards
		else if(player.keyPressed[2]) //A
		{
			player.move(-move.x, -move.y);
		}
		//move right
		if(player.keyPressed[3]) //S
		{
			player.move(-move.y, move.x);
		}
		//move left
		else if(player.keyPressed[1]) //D
		{			
			player.move(move.y, -move.x);
		}
	}

	/**
	 * turns changes the view angle
	 */
 	public static void turns()
	{
 		final int ROTATESPEED = 20;
 		
		if(rightTurn)
		{
			angle += ROTATESPEED;
			rays.clear();
			setVectors();
		}
		else if(leftTurn)
		{
			angle -= ROTATESPEED;
			rays.clear();
			setVectors();
		}
	}
	
 	/**
 	 * sets the values of the rays
 	 */
	public static void setVectors()
	{
		//for each ray, find it's (x,y) values
		for(int index = 0; index < rayCount; index++)
		{	
			rays.add(new Vector(Math.cos((angle + (index)) * Math.PI / (rayCount * 3)),
								Math.sin((angle + (index)) * Math.PI / (rayCount * 3))));
		}
		
		viewAngle = new Vector(rays.get(rayCount / 2)); //center ray
	}
	
	/**
	 * rayCheck checks if the rays hit a wall and determines the size of the rays
	 * @param v the vector/ray
	 * @return the ray of proper length
	 */
	public static Vector rayCheck(Vector v)
	{
		//Initializes ray to current player location (starting for the middle of the player)
		Vector ray = new Vector(player.getPositionX() + (player.getWidth() / 2),
								player.getPositionY() + (player.getHeight() / 2));
				
		//keeps rays from going past walls
		//this is not very efficient, however, it doesn't need to be
		if(v.x > 1 || v.y > 1)
		{
			v.x /= 3;
			v.y /= 3;
		}
		else if(v.x < -1 || v.y < -1)
		{
			v.x /= 3;
			v.y /= 3;
		}
		
		while(!isTouching(ray))
		{
			//if the ray isn't touching a wall, increment
			ray.x += v.x;
			ray.y += v.y;
			
			if(tooLong(ray)) break;
		}
				
		return ray;
	}
	
	private static boolean tooLong(Vector ray)
	{
		Vector temp = new Vector(ray.x - player.posX + (player.getWidth() / 2), 
				 ray.y - player.posY + (player.getHeight() / 2));
		
		if(temp.getLength() < -MAXLENGTH || temp.getLength() > MAXLENGTH) 
		{
			return true;
		}
		return false;
	}
	
	public static boolean isTouching(Vector ray)
	{
		Vector temp = new Vector(ray);
		
		final int BLOCKSIZE = 50;
		temp.x /= BLOCKSIZE;
		temp.y /= BLOCKSIZE;
		
		if(temp.x < gameMap.length && temp.y < gameMap[0].length)
		{
			if(gameMap[(int)temp.x][(int)temp.y] == '#')
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * initializes the map
	 */
	public static void setMap()
	{	
		final String strMap = 
								"###############"
							+ "#.....#.......#"
							+ "#.....#.#.#.#.#"
							+ "#.....#.......#"
							+ "#.....#.......#"
							+ "###.######.####"
							+ "#.....#.......#"
							+ "#.....#.......#"
							+ "#.............#"
							+ "############.##"
							+ "#.........#...#"
							+ "#.........#...#"
							+ "#...###...#...#"
							+ "#...#.#...#...#"
							+ "#...#.#.......#"
							+ "#...#.#.......#"
							+ "#...#.#########"
							+ "#.............#"
							+ "#...........#.#"
							+ "###############";
		
		int counter = 0;
		for(int index = 0; index < gameMap.length; index++)
		{
			for(int index2 = 0; index2 < gameMap[index].length; index2++)
			{
				gameMap[index][index2] = strMap.charAt(counter);
				counter++;
			}
		}
	}
	
	static class Player
	{
		private double posX; // X-axis position
		private double posY; //Y-axis position
		private int width; //player width
		private int height; //player height
		
		//key pressed booleans
		public boolean keyPressed[] = {false, false, false, false}; //WASD
		
		/**
		 * player constructor (to enforce required data)
		 * @param positionX: sets X-axis position of player
		 * @param positionY: sets Y-axis position of player
		 * @param width: sets player width
		 * @param height: sets player height
		 */
		public Player(int positionX, int positionY, int width, int height)
		{
			this.posX = positionX;
			this.posY = positionY;
			this.width = width;
			this.height = height;
		}
		
		/**
		 * move moves the player a given number of units
		 * @param x amount to move the player on the x-axis
		 * @param y amount to move the player on the y-axis
		 */
		public void move(double x, double y)
		{
			this.posX += x;
			this.posY += y;
		}
		
		public int getWidth()
		{
			return this.width;
		}
		
		public int getHeight()
		{
			return this.height;
		}
		
		public double getPositionX()
		{
			return this.posX;
		}
		
		public double getPositionY()
		{
			return this.posY;
		}
		
	}
	
}
