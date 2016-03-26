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
	 *  Checks for a winner and determines the game value
	 */
	public String theWinner()
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
			currentPlayer = ticTacToeGameStatus.getCurrentPlayer();
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
				//ticTacToeGameStatus.setField(0,"Game value = "+gameValue+" for player "+currentPlayer);
				//Sets the new game value
				ticTacToeGameStatus.setGameValue(gameValue);
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
		for(int loopIndex = start;loopIndex >= 0 
				               && loopIndex <= stop 
				               && loopIndex <= 9;loopIndex++)
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
	 *  Retrieves the valid moves
	 */
	public ArrayList<Integer> getValidMoves()
	{
		ArrayList<Integer> validMoves = new ArrayList<Integer>();
		//Loops through the possible moves 
		for(int loopIndex = 1;loopIndex <= 9;loopIndex++)
		{			
			//Checks if the move is valid
			if(isValidMove(loopIndex))
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
			String currentStatus = "";
			//Checks whether the game is over or not
			if (ticTacToeGameStatus.getGameOver() == 1)
			{
				currentStatus  = ticTacToeGameStatus.getWinner() + " has won the game! ";
			}
			//Start a new game
			ticTacToeGameStatus = new TicTacToeGameStatus();
			//Retrieves the new status
			String newStatus = ticTacToeGameStatus.getField(0);
			//Updates the current status
			ticTacToeGameStatus.setField(0, currentStatus+newStatus);
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
		final int NO_MOVE = -1;
		int currentMove = index;
		//Retrieves the current player type
		String currentPlayerType = ticTacToeGameStatus.getCurrentPlayerType();
		switch(currentPlayerType)
		{
		case TicTacToeGUI.COMPUTER: 			
			//Let's the computer place a marker
			currentMove = ComputerPlayer.performMonkeyMove(this);
			break;
		case TicTacToeGUI.HUMAN: 			
			//Let's the human place a marker
			currentMove = index;
			break;
		}
		//Applies the rules
		applyRulesBefore(currentMove);
		if(ticTacToeGameStatus.getErrorMessage() == null && ticTacToeGameStatus.getGameOver() == 0)
		{
			//Makes a move
			ticTacToeGameStatus.setField(currentMove, ticTacToeGameStatus.getCurrentPlayer());
			//Applies the rules
			applyRulesAfter(currentMove);
			//Switches player
			ticTacToeGameStatus.switchPlayer();
			//Retrieves the current player type
			currentPlayerType = ticTacToeGameStatus.getCurrentPlayerType();
			if(currentPlayerType == TicTacToeGUI.COMPUTER)
			{
				//Performs a computer move 
				ticTacToeGameStatus = placeMarker(NO_MOVE);
			}
		}
	return ticTacToeGameStatus;
	}
}

