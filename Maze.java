
/* LEFT IS Y AND RIGHT IS X -- JAVA IS RETARDED*/
public class Maze {

	private char grid[][]; //physical representation
	private boolean state[][]; //state (if a coordinate should be explored)
	//private boolean intersect[][]; //maps the coordinates that have more than one adjacent space (not important anymore)
	private int n; //dimension
	private int currX, currY;
	private Bot b;
	private String log;
	
	public Maze(int n, Bot b) 
	{
		this.log = "";
		this.n = n;
		this.b = b;
		grid = new char[n][n]; //visual representation of maze ('#' if wall, ' ' if space)
		state = new boolean[n][n]; //obstacle representation of maze (true if space, false if wall)
		initialize();
	}
	
	/* creates wall by x and y coordinate*/
	public void wallify(int x, int y)
	{
		grid[y][x] = '#';
		state[y][x] = false;
	}
	
	/* if free space leads to dead end, use this function (might not be useful anymore)*/
	public void falsify(int x, int y)
	{
		state[y][x] = false;
	}
	
	/* initialize all grid board to be spaces */
	public void initialize()
	{
		for(int i=0; i < n; i++)
			for(int j=0; j < n; j++)
			{
				grid[i][j] = ' ';
				state[i][j] = true;
			}
		this.currX = 0; //current x coordinate of bot
		this.currY = 0; //current y coordinate of bot
		grid[currY][currX] = b.getSymbol(); //starting point
	}
	
	/* new location of bot, and displays the updated */
	public void update(int x, int y)
	{
		grid[currY][currX] = ' ';
		currX = x;
		currY = y;
		grid[y][x] = b.getSymbol();
		display();
	}
	
	/* determines if the bot has reached the goal (n,n) */
	public boolean isFinished()
	{
		if( (currX == n-1) && (currY == n-1) )
			return true;
		else
			return false;
	}
	/* returns the coordinates of the bot*/
	public int getBotX()
	{
		return currX;
	}
	public int getBotY()
	{
		return currY;
	}
	
	/* Moving the bot in different directions*/
	/* moves forward*/
	public int moveForward() 
	{
		int nActions = 0;
		try 
		{
			if(getLeft()) //if there is space in the left
			{
				b.turn("left");
				nActions++;
				log = log + "Turned left\n";
				if(b.getDir().equalsIgnoreCase("right")) // > will move forward to the right
				{
					update(currX+1, currY); 
					nActions++;
					log = log + "Moved forward\n";
				}
				else if(b.getDir().equalsIgnoreCase("down")) // v will move downwards -1 y
				{
					update(currX, currY+1); //swapped Y (explanation: in this orientation, adding of Y means going down)
					nActions++;
					log = log + "Moved forward\n";
				}
				else if(b.getDir().equalsIgnoreCase("up")) // ^
				{
					update(currX, currY-1); //swapped Y
					nActions++;
					log = log + "Moved forward\n";
				}
					
				else if(b.getDir().equalsIgnoreCase("left")) // <
				{
					update(currX-1, currY);
					nActions++;
					log = log + "Moved forward\n";
				}
					
			}
			else if(getFront())
			{
				if(b.getDir().equalsIgnoreCase("down"))
				{
					update(currX, currY+1); //swapped Y
					nActions++;
					log = log + "Moved forward\n";
				}
					
				else if(b.getDir().equalsIgnoreCase("up"))
				{
					update(currX, currY-1); //swapped Y
					nActions++;
					log = log + "Moved forward\n";
				}
					
				else if(b.getDir().equalsIgnoreCase("left"))
				{
					update(currX-1, currY);
					nActions++;
					log = log + "Moved forward\n";
				}
					
				else if(b.getDir().equalsIgnoreCase("right"))
				{
					update(currX+1, currY);
					nActions++;
					log = log + "Moved forward\n";
				}
			}
			else
			{
				b.turn("right");
				nActions++;
				log = log + "Turned right\n";
				update(currX, currY);
			}
			return nActions;
			
		}
		catch(Exception e)
		{
			return nActions;
		}
	}

	/* Determining environment around bot*/
	/* gets the left of the bot*/
	public boolean getLeft() //NullPointerException
	{
		try	
		{
			/* if corners */
			if((currY == 0 && b.getDir().equalsIgnoreCase("right")))
				return false;
			else if((currX == n-1 && b.getDir().equalsIgnoreCase("down")))
				return false;
			else if(currX == 0 && b.getDir().equalsIgnoreCase("up"))
				return false;
			else if(currY == n-1 && b.getDir().equalsIgnoreCase("left"))
				return false;
			
			if(b.getDir().equalsIgnoreCase("down"))
				return state[currY][currX+1]; // v =>
			else if(b.getDir().equalsIgnoreCase("up"))
				return state[currY][currX-1]; // <= ^
			else if(b.getDir().equalsIgnoreCase("left"))
				return state[currY+1][currX]; //swapped Y
			else if(b.getDir().equalsIgnoreCase("right"))
				return state[currY-1][currX]; //swapped Y
			else return false;
		}
		catch(Exception e)
		{
			return false; //means the bot's left is at the edge 
		}
	}
	/* gets the front of the bot*/
	public boolean getFront()  //NullPointerException
	{
		try
		{
			/* if corners */
			if((currY == 0 && b.getDir().equalsIgnoreCase("up")))
				return false;
			else if(currX == n-1 && b.getDir().equalsIgnoreCase("right"))
				return false;
			else if(currX == 0 && b.getDir().equalsIgnoreCase("left"))
				return false;
			else if(currY == n-1 && b.getDir().equalsIgnoreCase("down"))
				return false;
			
			if(b.getDir().equalsIgnoreCase("down"))
				return state[currY+1][currX]; //swapped Y
			else if(b.getDir().equalsIgnoreCase("up"))
				return state[currY-1][currX]; //swapped Y
			else if(b.getDir().equalsIgnoreCase("left"))
				return state[currY][currX-1];
			else if(b.getDir().equalsIgnoreCase("right"))
				return state[currY][currX+1];
			else
				return false;
		}
		catch(Exception e)
		{
			return false; //means the bot is facing at the edge 
		}
	}

	/* gets the movement log of the bot*/
	public String getLog()
	{
		return log;
	}
	/* gets the state of a grid coordinate (since grid and state array are the same) */
	/* if it is not either # or ' ', it is bot */
	public boolean getGridState(int x, int y)
	{
		return state[y][x];
	}
	
	/* display the maze */
	public void display()
	{
		/* horizontal plane (x) */
		System.out.print("   "); //3 spaces
		for(int i = 0; i < n; i++)
		{
			if(i < 9)
			{
				System.out.print(i+1);
			}
			else 
			{
				System.out.print((i+1)/10);
			}
			System.out.print(" ");
		}
		
		System.out.println();
		
		/* vertical plane (y) */
		for(int i=0; i < n; i++)
		{
			System.out.print(i+1 + "  ");// 2 spaces
			for(int j=0; j<n; j++)
				System.out.print(grid[i][j] + " ");
			System.out.println("  ");
			//System.out.println(i+1);
		}
		
		/* horizontal plane (x) */
		System.out.print("   "); //3 spaces
		for(int i = 0; i < n; i++)
		{
			if(i < 9)
			{
				System.out.print(i+1);
			}
			else 
			{
				System.out.print((i+1)/10);
			}
			System.out.print(" ");
		}
		
		System.out.println();
		
		System.out.println("State: " + b.getDir());
		
	}

}
