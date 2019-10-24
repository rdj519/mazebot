
public class Maze {

	private char grid[][]; //physical representation
	private boolean state[][]; //state (if a coordinate should be explored)
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
	/* moves to left */
	public void moveLeft() throws Exception
	{
		try
		{
			if(getLeft())
			{
				update(currX-1, currY);
			}
		}
		catch(Exception e)
		{
			//nothing
		}
	}
	/* moves to right */
	public void moveRight() throws Exception
	{
		try
		{
			if(getRight())
			{
				update(currX+1, currY);
			}
		}
		catch(Exception e)
		{
			//nothing
		}
	}
	/* moves to up */
	public void moveUp() throws Exception
	{
		try
		{
			if(getUp())
			{
				update(currX, currY+1);
			}
		}
		catch(Exception e)
		{
			//nothing
		}
	}
	/* moves to down */
	public void moveDown() throws Exception
	{
		try
		{
			if(getDown())
			{
				update(currX, currY-1);
			}
		}
		catch(Exception e)
		{
			//nothing
		}
	}
	
	/* Determining environment around bot*/
	/* gets the left of the bot*/
	public boolean getLeft() throws Exception //NullPointerException
	{
		try
		{
			return state[currX-1][currY];
		}
		catch(Exception e)
		{
			return false; //means the bot is at the left edge 
		}
	}
	/* gets the right of the bot*/
	public boolean getRight() throws Exception //NullPointerException
	{
		try
		{
			return state[currX+1][currY];
		}
		catch(Exception e)
		{
			return false; //means the bot is at the right edge 
		}
	}
	/* gets the up of the bot*/
	public boolean getUp() throws Exception //NullPointerException
	{
		try
		{
			return state[currX][currY+1];
		}
		catch(Exception e)
		{
			return false; //means the bot is at the upper edge 
		}
	}
	/* gets the down of the bot*/
	public boolean getDown() throws Exception //NullPointerException
	{
		try
		{
			return state[currX][currY-1];
		}
		catch(Exception e)
		{
			return false; //means the bot is at the lower edge 
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
			System.out.println(i+1 + "  ");// 2 spaces
			for(int j=0; j<n; j++)
				System.out.print(grid[i][j] + " ");
			System.out.println(" " + i+1);
		}
		
		/* horizontal plane (x) */
		System.out.print("   "); //3 spaces
		for(int i = 0; i < n; i++)
			System.out.print(i+1 + " ");
		
		System.out.println();
	}

}
