/*

This class, Bot, handles the physical representation of the Bot object.
Also, it contains the state of the bot.

*/

public class Bot {

	private String state; //the state of the bot (direction it is facing)
	private char symbol;  //the physical representation of the bot's state

	public Bot() 
	{
		this.symbol = 'v';
		this.state = "down"; //this means that the bot is initially facing down (v), all simulations start with this. 
	}
	
	/*	Note: Updates the symbol or physical appearance of the bot.	*/
	public void updateSymbol(String prevState, String dir)			
	{
		
		if(prevState.equalsIgnoreCase("down"))
		{
			if(dir.equalsIgnoreCase("right"))
				this.symbol = '<';
			else if(dir.equalsIgnoreCase("left"))
				this.symbol = '>';
		}
		else if(prevState.equalsIgnoreCase("up"))
		{
			if(dir.equalsIgnoreCase("right"))
				this.symbol = '>';
			else if(dir.equalsIgnoreCase("left"))
				this.symbol = '<';
		}
		else if(prevState.equalsIgnoreCase("left"))
		{
			if(dir.equalsIgnoreCase("right"))
				this.symbol = '^';
			else if (dir.equalsIgnoreCase("left"))
				this.symbol = 'v';
		}
		else if(prevState.equalsIgnoreCase("right"))
		{
			if(dir.equalsIgnoreCase("right"))
				this.symbol = 'v';
			else if(dir.equalsIgnoreCase("left"))
				this.symbol = '^';
		}
	}
	
	/*	Note: Updates the state of turns of the bot.	*/
	public void turn(String dir)										
	{
		String prevState = this.state;

		if(dir.equalsIgnoreCase("left"))
		{
			if(this.state.equalsIgnoreCase("down"))
			{
				this.state = "right";
			}
			else if(this.state.equalsIgnoreCase("up"))
			{
				this.state = "left";
			}
			else if(this.state.equalsIgnoreCase("left"))
			{
				this.state = "down";
			}
			else if(this.state.equalsIgnoreCase("right"))
			{
				this.state = "up";
			}
		}
		else if(dir.equalsIgnoreCase("right"))
		{
			if(this.state.equalsIgnoreCase("down"))
			{
				this.state = "left";
			}
			else if(this.state.equalsIgnoreCase("up"))
			{
				this.state = "right";
			}
			else if(this.state.equalsIgnoreCase("left"))
			{
				this.state = "up";
			}
			else if(this.state.equalsIgnoreCase("right"))
			{
				this.state = "down";
			}
		}
		
		updateSymbol(prevState, dir);
	}
	
	/* Note: returns the physical representation of the bot's state */
	public char getSymbol()
	{
		return symbol;
	}
	
	/* Note: returns the actual bot's state */
	public String getDir()
	{
		return this.state;
	}

}
