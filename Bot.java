
public class Bot {

	private char symbol;
	private String state;
	public Bot() 
	{
		this.symbol = 'O';
		this.state = "down"; //this means that the node is facing down (v), all simulations start with this. 
	}
	
	public char getSymbol()
	{
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
	}

}
