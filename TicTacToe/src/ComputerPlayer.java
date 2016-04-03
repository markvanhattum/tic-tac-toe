import java.util.ArrayList;

/**
 * A computer player
 */

public class ComputerPlayer 
{
	/**
	 *  Performs a monkey move
	 */
	public static int performMonkeyMove(TicTacToeGame theGame)
	{
		int index = -1;
		String logString;
		//Retrieves a list of valid moves
		ArrayList<Integer> validMoves = theGame.getValidMoves(theGame.ticTacToeGameStatus);
		if(validMoves.size() > 0)
		{
			//Picks a field
			index = (int)(Math.random() * validMoves.size());
			index = validMoves.get(index);
		}
		//Logs
		logString = validMoves.size()+" valid moves; current move: "+index;
		theGame.ticTacToeGameStatus.setField(0, logString);
		//Returns the move 
		return index;
	}

	/**
	 *  Performs a calculated move
	 */
	public static ArrayList<TicTacToeGameStatus> performCalculatedMove(TicTacToeGame superParentGame, TicTacToeGameStatus parentGameStatus)
	{
		//Retrieves the game to which this game status belongs
		TicTacToeGame parentGame = parentGameStatus.getGame();                                   
		//Retrieves an array of valid moves
		ArrayList<Integer> validMoves = parentGame.getValidMoves(parentGame.ticTacToeGameStatus);                                 
		//Creates an array of child game statuses
		ArrayList<TicTacToeGameStatus> childGameStatuses = new ArrayList<TicTacToeGameStatus>(); 
		int currentMove    = -99;                                                                 //The current move
		int childGameValue = -99;                                                                 //The child game value
		int bestScore      = -99;                                                             
		int bestMove       = -99;
		//Makes a list of game values per move 1 to 9 - with index 0 as a dummy 
		int[] gameValues     = {-99,-99,-99,-99,-99,-99,-99,-99,-99,-99};
		for(int loopIndex = 0;loopIndex < validMoves.size();loopIndex++)
		{			
			//A child game status
			TicTacToeGameStatus childGameStatus = new TicTacToeGameStatus(parentGameStatus);			
			//Determines a move
			currentMove = validMoves.get(loopIndex);
			//Places a virtual marker in a virtual game
			childGameStatus.setField(currentMove, childGameStatus.getCurrentPlayer());
			//childGameStatus.getGame().placeMarker(currentMove,childGameStatus);
			//Calculates the child game's game value
			childGameStatus.getGame().theWinner(childGameStatus);
			//Retrieves the child game's game value
			childGameValue = childGameStatus.getGameValue();
			//Keeps the highest game value for this move
			gameValues[currentMove] = childGameValue;
			//Updates the superparent (the actual game we're playing)
			//This makes it possible to choose a move with the highest child game value
			superParentGame.ticTacToeGameStatus.setChildGameValues(gameValues);
			if(childGameValue > bestScore)
			{
				//Keeps the highest game value for this move
				bestScore = gameValues[currentMove];
				bestMove  = currentMove;
				//This is only correct for the first level (needs work)
				superParentGame.ticTacToeGameStatus.setBestMove(bestMove);
			}			
			//Adds this new game status to a list of child game statuses
			childGameStatuses.add(childGameStatus);
		}
		//Returns the best move 
		return childGameStatuses;
	}
}
