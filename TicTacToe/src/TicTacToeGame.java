import java.util.ArrayList;

/**
 * The game TicTacToe
 */
public class TicTacToeGame 
{
	//The game has two players
	public static  final String PLAYER_O       = "O";
	public static  final String PLAYER_X       = "X";
	private static final String TWO_TIMES_O    = "OO";
	private static final String TWO_TIMES_X    = "XX";
	private static final String O_WINS         = "OOO";
	private static final String X_WINS         = "XXX";
	//It's possible that nobody wins
	public static final String NOBODY = "nobody";
	//Creates a new game status
	TicTacToeGameStatus ticTacToeGameStatus = new TicTacToeGameStatus();			
	
	/**
	 *  Constructor
     */
	public TicTacToeGame()
	{
		//Sets this game as the parent of the game status
		ticTacToeGameStatus.setGame(this);
	}
	public TicTacToeGame(TicTacToeGame parentGame)
	{
		//Copies the object
		this.ticTacToeGameStatus = parentGame.ticTacToeGameStatus;
	}

	/**
	 *  Checks for a winner and determines the game value
	 */
	public String theWinner(TicTacToeGameStatus gameStatus)
	{
		int    gameValue    = 0;
		int    gameValueRow = 0;
		String currentPlayer;
		String rowPlayer;
		String returnValue = NOBODY; 
		String threeFields = "";
		ArrayList<String> gameValueStrings = new ArrayList<String>();
		gameValueStrings.add(PLAYER_X);
		gameValueStrings.add(PLAYER_O);
		gameValueStrings.add(TWO_TIMES_X);
		gameValueStrings.add(TWO_TIMES_O);
		gameValueStrings.add(X_WINS);
		gameValueStrings.add(O_WINS);
		for(int i = 1;i < 9; i++)
		{
			//Retrieves the current Player
			currentPlayer = gameStatus.getCurrentPlayer();
			switch (i)
			{
				case 1: threeFields = gameStatus.getField(1)+gameStatus.getField(2)+gameStatus.getField(3); break; //First row
				case 2: threeFields = gameStatus.getField(4)+gameStatus.getField(5)+gameStatus.getField(6); break; //Second row
				case 3: threeFields = gameStatus.getField(7)+gameStatus.getField(8)+gameStatus.getField(9); break; //Third row
				case 4: threeFields = gameStatus.getField(1)+gameStatus.getField(4)+gameStatus.getField(7); break; //First column
				case 5: threeFields = gameStatus.getField(2)+gameStatus.getField(5)+gameStatus.getField(8); break; //Second column
				case 6: threeFields = gameStatus.getField(3)+gameStatus.getField(6)+gameStatus.getField(9); break; //Third column
				case 7: threeFields = gameStatus.getField(1)+gameStatus.getField(5)+gameStatus.getField(9); break; //Diagonal from the upper left corner
				case 8: threeFields = gameStatus.getField(3)+gameStatus.getField(5)+gameStatus.getField(7); break; //Diagonal from the upper right corner
			}			
			if (returnValue == NOBODY)
			{
				switch(threeFields)
				{
				    case X_WINS: returnValue = PLAYER_X; break;
					case O_WINS: returnValue = PLAYER_O; break;
				}
			}
			if(gameValueStrings.contains(threeFields))
			{
				//Retrieves the player of the field
				rowPlayer = threeFields.substring(0,1);
				//Determines the value of the row
				gameValueRow = threeFields.length()*threeFields.length();
				if(currentPlayer.equals(rowPlayer) == false)
				{
					//Sees an opponent row value as a negative value
					gameValueRow = -gameValueRow;
				}
				//Determines the value of the current game situation
				gameValue = gameValue + gameValueRow;
				//Logs the game value to the status bar for fun
				//gameStatus.setField(0,"Game value = "+gameValue+" for player "+currentPlayer);
				//Sets the new game value
				gameStatus.setGameValue(gameValue);
			}
		}
		return returnValue;
	}
	
	/**
	 *  Checks if the move is valid
	 */
	public boolean isValidMove(Integer index, TicTacToeGameStatus gameStatus)
	{
		Integer start           = Integers.NullValue(index, 1);
		Integer stop            = Integers.NullValue(index, 9);
		boolean returnValue = false;
		for(int loopIndex = start;loopIndex >= 0 
				               && loopIndex <= stop 
				               && loopIndex <= 9;loopIndex++)
		{			
			if(gameStatus.getField(loopIndex) != PLAYER_X 
			&& gameStatus.getField(loopIndex) != PLAYER_O)
			{
				//This is a valid move
				returnValue = true;
				break;
			}
		}
		return returnValue;
	}
	
	/**
	 *  Retrieves the valid moves
	 */
	public ArrayList<Integer> getValidMoves(TicTacToeGameStatus gameStatus)
	{
		ArrayList<Integer> validMoves = new ArrayList<Integer>();
		//Loops through the possible moves 
		for(int loopIndex = 1;loopIndex <= 9;loopIndex++)
		{			
			//Checks if the move is valid
			if(isValidMove(loopIndex, gameStatus))
			{
				//Adds the move to the list of valid moves
				validMoves.add(loopIndex);
			}
		}
		return validMoves;
	}
	
	/**
	 *  Applies the rules before the move
	 */
	public void applyRulesBefore(Integer index, TicTacToeGameStatus gameStatus)
	{
		//Are we still playing?
		if (gameStatus.getGameOver() == 0)
		{
			//Is the current move valid?
			if(!isValidMove(index, gameStatus) && index != null)
			{
				//The current move is invalid
				gameStatus.setErrorMessage("Invalid move");
			}
			else
			{
				//No errors so far
				gameStatus.setErrorMessage(null);
			}
		}
	}
	
	/**
	 *  Applies the rules after the move
	 */
	public TicTacToeGameStatus applyRulesAfter(Integer index, TicTacToeGameStatus gameStatus)
	{
		//Are there any valid moves left?
		if(!isValidMove(null, gameStatus))
		{
			//There are no valid moves left
			gameStatus.setGameOver(1);
			gameStatus.setWinner(NOBODY);
		}
		//Has the game ended?
		if (gameStatus.getGameOver() == 1)
		{
			//Start a new game
			gameStatus = new TicTacToeGameStatus();
		}
		//Counts three-in-a-row
		String theWinner = theWinner(gameStatus);
		if (theWinner != NOBODY)
		{
			//We have a winner!
			gameStatus.setGameOver(1);
			gameStatus.setWinner(theWinner);
			//Updates the current status
			gameStatus.setField(0, gameStatus.getWinner() + " has won the game! ");
		}
		return gameStatus;
	}
	
	/**
	 *  Places a marker
	 */
	public TicTacToeGameStatus placeMarker(int index, TicTacToeGameStatus gameStatus)
	{
		//Applies the rules
		applyRulesBefore(index, gameStatus);
		if(gameStatus.getErrorMessage() == null && gameStatus.getGameOver() == 0)
		{
			//Makes a move
			gameStatus.setField(index, gameStatus.getCurrentPlayer());
			//Switches player
			gameStatus.switchPlayer();
		}
		//Applies the rules
		gameStatus = applyRulesAfter(index, gameStatus);
		return gameStatus;
	}
}

