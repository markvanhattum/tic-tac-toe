/**
 * The current status of the game
 */
public class TicTacToeGameStatus
{
	private int           bestMove;             // Describes the best move
	private int[]         childGameValues;      // Describes the values of the child game statuses
	private String        currentPlayer;        // Describes who the current player is
	private String        currentPlayerType;    // Describes whether the current player human is or computer
	private String        errorMessage ;        // Describes an error
	private String[]      fields;		        // Describes the contents of the nine fields
	private int           gameOver;             // Describes whether the game is over or not
	private int           gameValue;            // Describes the value of the current game status
	private String        playerTypeO;          // Describes whether player O is a human or a computer
	private String        playerTypeX;          // Describes whether player X is a human or a computer
	private TicTacToeGame theGame;              // The game to which this game status belongs to 
	private String        winner;               // Describes who has won

	/**
	 *  Constructors
     */
	public TicTacToeGameStatus()
	{
		this.setBestMove(0);
		int[] newChildGameValues = {-99, -99, -99, -99, -99, -99, -99, -99, -99, -99};
		this.setChildGameValues(newChildGameValues);
		if(currentPlayer==null)
		{
			//Flips a coin
			int coinFlip = (int)(Math.random() * 2);			
			switch (coinFlip)
			{
				//Decides that O begins
				case 0: 
					setPlayerTypeO(TicTacToeGUI.HUMAN);
					setPlayerTypeX(TicTacToeGUI.COMPUTER);
					setCurrentPlayer(TicTacToeGame.PLAYER_O); 
					break;
				//Decides that X begins
				case 1: 
					setPlayerTypeX(TicTacToeGUI.HUMAN);
					setPlayerTypeO(TicTacToeGUI.COMPUTER);
					setCurrentPlayer(TicTacToeGame.PLAYER_X); 
				break;
			}
		}
		//There have no errors occurred yet
		errorMessage = "";
		//Resets the fields
		resetFields();
		//The game has just started
		setGameOver(0);
		//The game has little value yet
		setGameValue(-99);
		//The game hasn't been won yet
		this.setWinner(TicTacToeGame.NOBODY);
	}

	public TicTacToeGameStatus(TicTacToeGameStatus parentGameStatus)
	{
		//Copies the object
		this.bestMove = parentGameStatus.getBestMove();
		this.childGameValues = parentGameStatus.getChildGameValues();
		this.currentPlayer = parentGameStatus.getCurrentPlayer();
		this.currentPlayerType = parentGameStatus.getCurrentPlayerType();
		this.errorMessage = parentGameStatus.getErrorMessage();
		this.fields = parentGameStatus.getFields();
		this.gameOver = parentGameStatus.getGameOver();
		this.gameValue = parentGameStatus.getGameValue();
		this.playerTypeO = parentGameStatus.getPlayerTypeO();
		this.playerTypeX = parentGameStatus.getPlayerTypeX();
		this.winner = parentGameStatus.getWinner();
		this.theGame = new TicTacToeGame(parentGameStatus.theGame);
	}
	
	/**
	 *  Best moves
     */
	public int getBestMove()
	{
		//Returns who's turn it is
		return bestMove;		
	}
	public void setBestMove(int newValue)
	{
		//Sets who's turn it is
		this.bestMove = newValue;
	}

	/**
	 *  Child game values
     */
	public int getChildGameValue(int index)
	{
		//Returns the contents of a child game value
		return childGameValues[index];
	}
	public void setChildGameValue(int index, int newValue)
	{
		//Sets the contents of a child game value
		this.childGameValues[index] = newValue;
	}
	public int[] getChildGameValues()
	{
		int[] returnValue = new int[this.childGameValues.length];
		//Gets the contents of the child game values
		System.arraycopy(this.childGameValues, 0, returnValue, 0, this.childGameValues.length);
		//Returns the child game values
		return returnValue;
	}
	public void setChildGameValues(int[] newValue)
	{
		this.childGameValues = new int[newValue.length];
		//Sets the contents of the childGameValues
		System.arraycopy(newValue, 0, this.childGameValues, 0, newValue.length);
	}

	/**
	 *  Player
     */
	public String getCurrentPlayer()
	{
		//Returns who's turn it is
		return currentPlayer;		
	}
	public void setCurrentPlayer(String newValue)
	{
		//Sets who's turn it is
		this.currentPlayer = newValue;
		switch(newValue)
		{
			case TicTacToeGame.PLAYER_O: currentPlayerType = getPlayerTypeO(); break;
			case TicTacToeGame.PLAYER_X: currentPlayerType = getPlayerTypeX(); break;
			default: currentPlayerType = TicTacToeGUI.HUMAN; break;
		}
	}
	public String getCurrentPlayerType()
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
		this.playerTypeO = newValue;
		if(currentPlayer==TicTacToeGame.PLAYER_O)
		{
			this.currentPlayerType = newValue; 
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
		this.playerTypeX = newValue;
		if(currentPlayer==TicTacToeGame.PLAYER_X)
		{
			this.currentPlayerType = newValue; 
		}
	}
	public void switchPlayer()
	{
		//Sets who's turn it is
		switch(currentPlayer)
		{
			case TicTacToeGame.PLAYER_O:	setCurrentPlayer(TicTacToeGame.PLAYER_X); break;
			case TicTacToeGame.PLAYER_X:	setCurrentPlayer(TicTacToeGame.PLAYER_O); break;
			default:						setCurrentPlayer(TicTacToeGame.PLAYER_X); break;
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
		this.errorMessage = newValue;
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
		this.fields[index] = newValue;
	}
	public String[] getFields()
	{
		//Declares a return value
		String[] returnValue = new String[this.fields.length];
		//Gets the contents of the fields
		System.arraycopy(this.fields, 0, returnValue, 0, this.fields.length);
		//Returns the fields
		return returnValue;
	}
	public void setFields(String[] newValue)
	{
		this.fields = new String[newValue.length];
		//Sets the contents of the fields
		System.arraycopy(newValue, 0, this.fields, 0, newValue.length);
	}
	public void resetFields()
	{
		//Resets the fields array
		fields = new String[10];
		for (int idx=1;idx < fields.length;idx++)
		{
			//Resets a field
			this.setField(idx,"");
		}
		//A new game has begun!
		this.setField(0, "Player " + currentPlayer + " starts a new game.");
	}		
	
	/**
	 *  The Game
     */
	public TicTacToeGame getGame()
	{
		//Returns the game
		return theGame;		
	}
	public void setGame(TicTacToeGame newValue)
	{
		//Sets the Game
		this.theGame = newValue;
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
		this.winner = newValue;
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
		this.gameOver = newValue;
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
		this.gameValue = newValue;
	}
}	
