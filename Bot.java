
public class Bot {

	private char symbol;
	private String state;
	public Bot() 
	{
		this.symbol = 'v';
		this.state = "down"; //this means that the node is facing down (v), all simulations start with this. 
	}
	
	public void updateSymbol()
	{
		if(this.state.equalsIgnoreCase("down"))
			this.symbol = 'v';
		else if(this.state.equalsIgnoreCase("up"))
			this.symbol = '^';
		else if(this.state.equalsIgnoreCase("left"))
			this.symbol = '>'; 	//from bot's perspective
		else if(this.state.equalsIgnoreCase("right"))
			this.symbol = '<';
	}
	public char getSymbol()
	{
		updateSymbol();
		return symbol;
	}
	
	public String getDir()
	{
		return this.state;
	}
	
	public void turn(String dir)
	{
		if(dir.equalsIgnoreCase("down"))
			this.state = "down";
		else if(dir.equalsIgnoreCase("up"))
			this.state = "up";
		else if(dir.equalsIgnoreCase("left"))
			this.state = "left";
		else if(dir.equalsIgnoreCase("right"))
			this.state = "right";
		
		updateSymbol();
	}

}
