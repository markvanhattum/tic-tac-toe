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
	public TicTacToeGame()
	{

		
	}

	/**
	 * Launches the application
	 *
	 * @param args: Dummy string array
	 */
	public GameStatus getGameStatus(GameStatus givenGameStatus)
	{
		//Updates the given game status
		givenGameStatus.currentPlayer = gameStatus.currentPlayer;
		givenGameStatus.fields       = gameStatus.fields;
		return givenGameStatus;
	}
	
	/**
	 * Launches the application
	 *
	 * @param args: Dummy string array
	 */
	public static void main(String[] args) 
	{
		
		System.out.println("A new game has begun!");
	}

}

