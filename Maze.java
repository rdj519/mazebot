
public class Maze {

	private char grid[][]; //physical representation
	private boolean state[][]; //state (if a coordinate should be explored)
	//private boolean intersect[][]; //maps the coordinates that have more than one adjacent space
	private int n; //dimension
	private int currX, currY;
	private Bot b;
	
	public Maze(int n, Bot b) 
	{
		this.n = n;
		this.b = b;
		grid = new char[n][n]; //visual representation of maze ('#' if wall, ' ' if space)
		state = new boolean[n][n]; //obstacle representation of maze (true if space, false if wall)
		initialize();
	}
	
	/* creates wall by x and y coordinate*/
	public void wallify(int x, int y)
	{
		grid[x][y] = '#';
		state[x][y] = false;
	}
	
	/* if free space leads to dead end, use this function */
	public void falsify(int x, int y)
	{
		state[x][y] = false;
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
		grid[currX][currY] = b.getSymbol(); //starting point
	}
	
	/* new location of bot, and displays the updated */
	public void update(int x, int y)
	{
		grid[currX][currY] = ' ';
		currX = x;
		currY = y;
		grid[currX][currY] = b.getSymbol();
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
	public void moveForward() 
	{
		try 
		{
			if(getLeft()) //if there is space in the left
			{
				b.turn("left");
				if(b.getDir().equalsIgnoreCase("down")) // v
					update(currX, currY-1);
				else if(b.getDir().equalsIgnoreCase("up")) // ^
					update(currX, currY+1);
				else if(b.getDir().equalsIgnoreCase("left")) // <
					update(currX-1, currY);
				else if(b.getDir().equalsIgnoreCase("right")) // >
					update(currX+1, currY);
			}
			else if(getFront())
			{
				if(b.getDir().equalsIgnoreCase("down"))
					update(currX, currY-1);
				else if(b.getDir().equalsIgnoreCase("up"))
					update(currX, currY+1);
				else if(b.getDir().equalsIgnoreCase("left"))
					update(currX-1, currY);
				else if(b.getDir().equalsIgnoreCase("right"))
					update(currX+1, currY);
			}
			else
			{
				b.turn("right");
			}
			
		}
		catch(Exception e)
		{
			
		}
	}

	/* Determining environment around bot*/
	/* gets the left of the bot*/
	public boolean getLeft() //NullPointerException
	{
		try	
		{
			if(b.getDir().equalsIgnoreCase("down"))
				return state[currX+1][currY]; // v =>
			else if(b.getDir().equalsIgnoreCase("up"))
				return state[currX-1][currY]; // <= ^
			else if(b.getDir().equalsIgnoreCase("left"))
				return state[currX][currY-1];
			else if(b.getDir().equalsIgnoreCase("right"))
				return state[currX][currY+1];
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
			if(b.getDir().equalsIgnoreCase("down"))
				return state[currX][currY-1];
			else if(b.getDir().equalsIgnoreCase("up"))
				return state[currX][currY+1];
			else if(b.getDir().equalsIgnoreCase("left"))
				return state[currX-1][currY];
			else if(b.getDir().equalsIgnoreCase("right"))
				return state[currX+1][currY];
			else
				return false;
		}
		catch(Exception e)
		{
			return false; //means the bot is facing at the edge 
		}
	}

	
	
	/* gets the state of a grid coordinate (since grid and state array are the same) */
	/* if it is not either # or ' ', it is bot */
	public boolean getGridState(int x, int y)
	{
		return state[x][y];
	}
	
	/* display the maze */
	public void display()
	{
		
		/* horizontal plane (x) */
		System.out.print("   "); //3 spaces
		for(int i = 0; i < n; i++)
			System.out.print(i+1 + " ");
		
		System.out.println();
		
		/* vertical plane (y) */
		for(int i=0; i < n; i++)
		{
			System.out.print(i+1 + "  ");// 2 spaces
			for(int j=0; j<n; j++)
				System.out.print(grid[i][j] + " ");
			System.out.print("  ");
			System.out.println(i+1);
		}
		
		/* horizontal plane (x) */
		System.out.print("   "); //3 spaces
		for(int i = 0; i < n; i++)
			System.out.print(i+1 + " ");
		
		System.out.println();
	}

}
