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
		int nSteps = 0;
		
		System.out.println("Enter size: ");
		input = sc.nextLine();
		nSize = Integer.parseInt(input);
		
		bot = new Bot();
		maze = new Maze(nSize, bot); //it means that the bot is already in the starting state
		
		maze.display();
		System.out.print("\n\nEnter coordinates for barricading (-1 to stop): ");
		x = sc.nextInt();
		y = sc.nextInt();
		try 
		{
			while(x != -1 && y != -1)
			{
				//wait() method 
				maze.wallify(x-1, y-1);
				maze.display();
				x = sc.nextInt();
				y = sc.nextInt();
			}
		}
		catch(Exception e)
		{
			
		}

	
		System.out.print("Press anything to start simulation\n ");
		while(maze.isFinished() == false)
		{
			try
			{
				Runtime.getRuntime().exec("cls"); //it should clear the screen every iteration but it doesnt work help
			}
			catch(Exception e)
			{
				
			}
				Thread.sleep(1000);
				maze.moveForward();
				nSteps++;
				maze.display(); 
			
		}
		
		System.out.println("Steps: " + nSteps);
		
		
		sc.close();
	}
	
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}  

}
