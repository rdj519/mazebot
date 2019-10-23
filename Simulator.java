
/* The chosen algorithm will be implemented here, as well as the simulation, and counting of moves etc.*/
public class Simulator 
{
	private Bot bot;
	private Maze maze;
	
	/* where n is the grid */
	public Simulator(int n) 
	{
		bot = new Bot();
		maze = new Maze(n, bot); //it means that the bot is already in the starting state
	}

}
