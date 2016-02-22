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
	private static final String O_WINS = "OOO";
	private static final String X_WINS = "XXX";
	//Creates a new game status
	TicTacToeGameStatus ticTacToeGameStatus = new TicTacToeGameStatus();			
	
	/**
	 *  Checks for a winner
	 */
	public String theWinner()
	{
		String returnValue = NOBODY; 
		String threeFields = "";
		for(int i = 1;i < 9; i++)
		{
			switch (i)
			{
				case 1: threeFields = ticTacToeGameStatus.getField(1)+ticTacToeGameStatus.getField(2)+ticTacToeGameStatus.getField(3); break; //First row
				case 2: threeFields = ticTacToeGameStatus.getField(4)+ticTacToeGameStatus.getField(5)+ticTacToeGameStatus.getField(6); break; //Second row
				case 3: threeFields = ticTacToeGameStatus.getField(7)+ticTacToeGameStatus.getField(8)+ticTacToeGameStatus.getField(9); break; //Third row
				case 4: threeFields = ticTacToeGameStatus.getField(1)+ticTacToeGameStatus.getField(4)+ticTacToeGameStatus.getField(7); break; //First column
				case 5: threeFields = ticTacToeGameStatus.getField(2)+ticTacToeGameStatus.getField(5)+ticTacToeGameStatus.getField(8); break; //Second column
				case 6: threeFields = ticTacToeGameStatus.getField(3)+ticTacToeGameStatus.getField(6)+ticTacToeGameStatus.getField(9); break; //Third column
				case 7: threeFields = ticTacToeGameStatus.getField(1)+ticTacToeGameStatus.getField(5)+ticTacToeGameStatus.getField(9); break; //Diagonal from the upper left corner
				case 8: threeFields = ticTacToeGameStatus.getField(3)+ticTacToeGameStatus.getField(5)+ticTacToeGameStatus.getField(7); break; //Diagonal from the upper right corner
			}			
			switch(threeFields)
			{
				case X_WINS: returnValue = PLAYER_X; break;
				case O_WINS: returnValue = PLAYER_O; break;
				default    : returnValue = NOBODY;   break;
			}
			if (returnValue != NOBODY)
			{
				//We have a winner!
				break;
			}
		}
		return returnValue;
	}
	
	/**
	 *  Checks if the move is valid
	 */
	public boolean isValidMove(Integer index)
	{
		Integer start           = Integers.NullValue(index, 1);
		Integer stop            = Integers.NullValue(index, 9);
		boolean returnValue = false;
		for(int loopIndex = start;loopIndex <= stop;loopIndex++)
		{			
			if(ticTacToeGameStatus.getField(loopIndex) != PLAYER_X 
			&& ticTacToeGameStatus.getField(loopIndex) != PLAYER_O)
			{
				//This is a valid move
				returnValue = true;
				break;
			}
		}
		return returnValue;
	}
	
	/**
	 *  Applies the rules before the move
	 */
	public void applyRulesBefore(Integer index)
	{
		//Is the current move valid?
		if(!isValidMove(index) && index != null)
		{
			//The current move is invalid
			ticTacToeGameStatus.setErrorMessage("Invalid move");
		}
		else
		{
			//No errors so far
			ticTacToeGameStatus.setErrorMessage(null);
		}
		//Has the game ended?
		if (ticTacToeGameStatus.getGameOver() == 1)
		{
			//Start a new game
			ticTacToeGameStatus = new TicTacToeGameStatus();
		}
	}
	
	/**
	 *  Applies the rules after the move
	 */
	public void applyRulesAfter(Integer index)
	{
		//Are there any valid moves left?
		if(!isValidMove(null))
		{
			//There are no valid moves left
			ticTacToeGameStatus.setGameOver(1);
			ticTacToeGameStatus.setWinner(NOBODY);
		}
		//Counts three-in-a-row
		String theWinner = theWinner();
		if (theWinner != NOBODY)
		{
			//We have a winner!
			ticTacToeGameStatus.setGameOver(1);
			ticTacToeGameStatus.setWinner(theWinner);
		}
	}
	
	/**
	 *  Places a marker
	 */
	public TicTacToeGameStatus placeMarker(int index)
	{
		//Applies the rules
		applyRulesBefore(index);
		if(ticTacToeGameStatus.getErrorMessage() == null && ticTacToeGameStatus.getGameOver() == 0)
		{
			//Makes a move
			ticTacToeGameStatus.setField(index, ticTacToeGameStatus.getPlayer());
			//Applies the rules
			applyRulesAfter(index);
			//Switches player
			ticTacToeGameStatus.switchPlayer();
		}
	return ticTacToeGameStatus;
	}
}

