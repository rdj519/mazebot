
public class Maze {

	private char grid[][];
	private boolean state[][];
	private int n; //dimension
	public Maze(int n) 
	{
		this.n = n;
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
