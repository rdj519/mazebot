
public class Bot {

	private char symbol;
	private String state;
	public Bot() 
	{
		this.symbol = 'v';
		this.state = "down"; //this means that the node is facing down (v), all simulations start with this. 
	}
	
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
	
	public char getSymbol()
	{
		return symbol;
	}
	
	public String getDir()
	{
		return this.state;
	}

}
