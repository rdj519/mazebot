
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
		grid[0][0] = b.getSymbol(); //starting point
		this.currX = 0; //current x coordinate of bot
		this.currY = 0; //current y coordinate of bot
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
	
	/* returns the coordinates of the bot*/
	public int getBotX()
	{
		return currX;
	}
	public int getBotY()
	{
		return currY;
	}
	
	/* gets the state of a grid coordinate (since grid and state array are the same) */
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
