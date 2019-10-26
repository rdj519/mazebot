import java.util.*;
/* The chosen algorithm will be implemented here, as well as the simulation, and counting of moves etc.*/

/* JAVA IS RETARDED X AND Y BALIKTAD */

/* The actual traversing of the bot is based on the state[][] array, grid[][] is only a visual representation 
 * of the maze 
 */
public class Simulator 
{
	private Bot bot;
	private Maze maze;

		
	public void run() throws InterruptedException
	{
		Scanner sc = new Scanner(System.in);
		int x, y, nSize;
		String input;
		int nSteps = 0; //steps means if the bot moves from one step to another
		int nActions = 0; //includes steps AND other movement such as turning (for all: actions >= steps)
		
		System.out.println("Enter size: ");
		input = sc.nextLine();
		nSize = Integer.parseInt(input);
		
		bot = new Bot();
		maze = new Maze(nSize, bot); //it means that the bot is already in the starting state
		
		maze.display();
		System.out.print("\n\nEnter coordinates for barricading (-1 -1 to stop and start simulation): ");
		x = sc.nextInt();
		y = sc.nextInt();
		try 
		{
			while(x != -1 && y != -1)
			{
				maze.wallify(x-1, y-1);
				maze.display();
				x = sc.nextInt();
				y = sc.nextInt();
			}
		}
		catch(Exception e)
		{
			
		}

		System.out.print("Simulation Started\n ");
		while(maze.isFinished() == false)
		{
			try
			{
				Runtime.getRuntime().exec("cls"); //it should clear the screen every iteration but it doesnt work help
			}
			catch(Exception e)
			{
				
			}
				int currX = maze.getBotX(); //current bot position (x)
				int currY = maze.getBotY(); //current bot position (y)
				
				Thread.sleep(1000); //animation is 1 frame per second
				nActions += maze.moveForward(); //simulates the bot and adds the actions performed
				if(maze.getBotX() != currX || maze.getBotY() != currY) //if bot position changed (it moved)
					nSteps++;
				maze.display(); //display the board and bot
				System.out.println("Steps: " + nSteps);
				System.out.println("Actions: "+ nActions);
			
		}
		sc.nextLine();
		System.out.println("Display log? (y/n): "); input = sc.nextLine();
		if(input.equalsIgnoreCase("y"))
			System.out.println("\nMaze Bot Actions Log:\n\n" + maze.getLog()); //displays actions log
		
		sc.close();
	}
	

}
