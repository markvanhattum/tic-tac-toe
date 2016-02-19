/**
 * The game TicTacToe
 */
public class TicTacToeGame 
{
	//The game has two players
	public static final String PLAYER_O = "O";
	public static final String PLAYER_X = "X";
	//It's possible that nobody wins
	public static final String NOBODY = "nobody";
	//Creates a new game status
	GameStatus gameStatus = new GameStatus();			
	/**
	 * The current status of the game
	 */
	public class GameStatus
	{
		private String   currentPlayer;  // Describes who the current player is
		private String[] fields;		 // Describes the contents of the nine fields
		private String   winner;         // Describes who has won
		private int      gameOver;       // Describes whether the game is over or not
		/**
		 *  Constructor
	     */
		public GameStatus()
		{
			//Flips a coin
			int coinFlip = (int)(Math.random() * 2);			
			switch (coinFlip)
			{
				//Decides that O begins
				case 0: this.setPlayer(PLAYER_O);
				break;
				//Decides that X begins
				case 1: this.setPlayer(PLAYER_X);
				break;
				//Unexpected scenario: decides that X begins
				default: this.setPlayer(PLAYER_X);
				break;
			}
			//Resets the fields
			this.resetFields();
		}
		/**
		 *  Current player
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
		}
		public void switchPlayer()
		{
			//Sets who's turn it is
			switch(currentPlayer)
			{
				case PLAYER_O:	currentPlayer = PLAYER_X;
								break;
				case PLAYER_X:	currentPlayer = PLAYER_O;
								break;
				default:		currentPlayer = PLAYER_X;
			}
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
			this.fields = new String[10];
			for (int idx=1;idx < this.fields.length;idx++)
			{
				//Resets a field
				this.fields[idx] = new String("");
			}
			//A new game has begun!
			this.fields[0] = new String("A new game has begun!");
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
		public void setWinner(int newValue)
		{
			//Sets whether the game is over or not
			gameOver = newValue;
		}
	}	
	
	/**
	 * Launches the application
	 *
	 * @param args: Dummy string array
	 */
	public GameStatus getGameStatus(GameStatus givenGameStatus)
	{
		//Updates the given game status
		givenGameStatus.currentPlayer	= gameStatus.currentPlayer;
		givenGameStatus.fields			= gameStatus.fields;
		return givenGameStatus;
	}

	public int countMarkers()
	{
		int returnValue = 0;
		for(int index = 1;index < 10;index++)
		{
			if(gameStatus.fields[index] == PLAYER_X || gameStatus.fields[index] == PLAYER_O)
			{
				//Counts the markers
				returnValue = returnValue + 1;
			}
		}
		return returnValue;
	}

	public String theWinner()
	{
		String returnValue = "";
		String allThree    = "";
		String characterOne         = "";
		String characterTwo         = "";
		String characterThree       = "";
		int length         = 0;
		for(int i = 1;i < 9; i++)
		{
			switch (i)
			{
				case 1: allThree = gameStatus.fields[1]+gameStatus.fields[2]+gameStatus.fields[3]; break;
				case 2: allThree = gameStatus.fields[4]+gameStatus.fields[5]+gameStatus.fields[6]; break;
				case 3: allThree = gameStatus.fields[7]+gameStatus.fields[8]+gameStatus.fields[9]; break;
				case 4: allThree = gameStatus.fields[1]+gameStatus.fields[4]+gameStatus.fields[7]; break;
				case 5: allThree = gameStatus.fields[2]+gameStatus.fields[5]+gameStatus.fields[8]; break;
				case 6: allThree = gameStatus.fields[3]+gameStatus.fields[6]+gameStatus.fields[9]; break;
				case 7: allThree = gameStatus.fields[1]+gameStatus.fields[5]+gameStatus.fields[9]; break;
				case 8: allThree = gameStatus.fields[3]+gameStatus.fields[5]+gameStatus.fields[7]; break;
			}			
			//Determines the length 
			length = allThree.length();
			if(length > 0)
			{
				characterOne   = allThree.substring(0, 1);
			}
			if(length > 1)
			{
				characterTwo   = allThree.substring(1, 2);
			}
			if(length > 2)
			{
				characterThree = allThree.substring(2, 3);
				if(characterOne.equals(characterTwo))
				{
					 if(characterTwo.equals(characterThree))
					 {
						//We have a winner!
						returnValue = characterOne;
						break;
					 }
				}
			}
		}
		return returnValue;
	}

	public GameStatus applyRules(int index)
	{
		//Makes a move
		gameStatus.fields[index] = gameStatus.currentPlayer;
		//Counts the markers
		if(countMarkers() == 9)
		{
			//The board is full
			gameStatus.gameOver = 1;
			gameStatus.winner   = NOBODY;
		}
		//Counts three-in-a-row
		String theWinner = theWinner();
		if (!theWinner.isEmpty())
		{
			//We have a winner!
			gameStatus.gameOver = 1;
			gameStatus.winner   = theWinner;

		}
		//Switches player
		gameStatus.switchPlayer();
		//Returns the game status
		return gameStatus;
	}
}

