/**
 * The current status of the game
 */
public class TicTacToeGameStatus
{
	private String   currentPlayer;        // Describes who the current player is
	private String   currentPlayerType;    // Describes whether the current player human is or computer
	private String   errorMessage ;        // Describes an error
	private String[] fields;		       // Describes the contents of the nine fields
	private int      gameOver;             // Describes whether the game is over or not
	private int      gameValue;            // Describes the value of the current game status
	private String   playerTypeO;          // Describes whether player O is a human or a computer
	private String   playerTypeX;          // Describes whether player X is a human or a computer
	private String   winner;               // Describes who has won

	/**
	 *  Constructor
     */
	public TicTacToeGameStatus()
	{
		//Flips a coin
		int coinFlip = (int)(Math.random() * 2);			
		switch (coinFlip)
		{
			//Decides that O begins
			case 0: 
				setPlayerTypeO(TicTacToeGUI.HUMAN);
				setPlayerTypeX(TicTacToeGUI.COMPUTER);
				setPlayer(TicTacToeGame.PLAYER_O); 
				break;
			//Decides that X begins
			case 1: 
				setPlayerTypeX(TicTacToeGUI.HUMAN);
				setPlayerTypeO(TicTacToeGUI.COMPUTER);
				setPlayer(TicTacToeGame.PLAYER_X); 
			break;
		}
		//There have no errors occurred yet
		errorMessage = "";
		//Resets the fields
		resetFields();
		//The game has just started
		setGameOver(0);
		//The game hasn't been won yet
		this.setWinner(TicTacToeGame.NOBODY);
	}
	
	/**
	 *  Player
     */
	public String getPlayer()
	{
		//Returns who's turn it is
		return currentPlayer;		
	}
	public void setPlayer(String newValue)
	{
		//Sets who's turn it is
		currentPlayer = newValue;
		switch(newValue)
		{
			case TicTacToeGame.PLAYER_O: currentPlayerType = getPlayerTypeO(); break;
			case TicTacToeGame.PLAYER_X: currentPlayerType = getPlayerTypeX(); break;
			default: currentPlayerType = TicTacToeGUI.HUMAN; break;
		}
	}
	public String getPlayerType()
	{
		//Returns who's turn it is
		return currentPlayerType;
	}
	public String getPlayerTypeO()
	{
		//Returns what type of player O is
		return playerTypeO;
	}
	public void setPlayerTypeO(String newValue)
	{
		//Sets what type of player O is
		playerTypeO = newValue;
		if(currentPlayer==TicTacToeGame.PLAYER_O)
		{
			currentPlayerType = newValue; 
		}
	}
	public String getPlayerTypeX()
	{
		//Returns what type of player X is
		return playerTypeX;
	}
	public void setPlayerTypeX(String newValue)
	{
		//Sets what type of player X is
		playerTypeX = newValue;
		if(currentPlayer==TicTacToeGame.PLAYER_X)
		{
			currentPlayerType = newValue; 
		}
	}
	public void switchPlayer()
	{
		//Sets who's turn it is
		switch(currentPlayer)
		{
			case TicTacToeGame.PLAYER_O:	setPlayer(TicTacToeGame.PLAYER_X); break;
			case TicTacToeGame.PLAYER_X:	setPlayer(TicTacToeGame.PLAYER_O); break;
			default:						setPlayer(TicTacToeGame.PLAYER_X); break;
		}
	}
	
	/**
	 *  Error message
     */
	public String getErrorMessage()
	{
		//Returns the error message
		return errorMessage;
	}
	public void setErrorMessage(String newValue)
	{
		//Sets the error message
		errorMessage = newValue;
		//Sets the statusbar
		fields[0] = errorMessage;
	}

	/**
	 *  Fields
     */
	public String getField(int index)
	{
		//Returns the contents of a field
		return fields[index];
	}
	public void setField(int index, String newValue)
	{
		//Sets the contents of a field
		fields[index] = newValue;
	}
	public void resetFields()
	{
		//Resets the fields array
		fields = new String[10];
		for (int idx=1;idx < fields.length;idx++)
		{
			//Resets a field
			fields[idx] = new String();
		}
		//A new game has begun!
		fields[0] = new String("A new game has begun! Player " + currentPlayer + " starts the game.");
	}		
	
	/**
	 *  Winner
     */
	public String getWinner()
	{
		//Returns who the winner is
		return winner;
	}
	public void setWinner(String newValue)
	{
		//Sets who the winner is
		winner = newValue;
	}
	
	/**
	 *  Game Over
     */
	public int getGameOver()
	{
		//Returns whether the game is over or not
		return gameOver;
	}
	public void setGameOver(int newValue)
	{
		//Sets whether the game is over or not
		gameOver = newValue;
	}

	/**
	 *  Game Value
     */
	public int getGameValue()
	{
		//Returns the game value
		return gameValue;
	}
	public void setGameValue(int newValue)
	{
		//Sets the game value
		gameValue = newValue;
	}
}	
