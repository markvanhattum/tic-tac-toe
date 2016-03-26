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
		//Retrieves a list of valid moves
		ArrayList<Integer> validMoves = theGame.getValidMoves();
		if(validMoves.size() > 0)
		{
			//Picks a field
			index = (int)(Math.random() * validMoves.size());
			index = validMoves.get(index);
		}
		//Logs
		theGame.ticTacToeGameStatus.setField(0, validMoves.size()+" valid moves; current move: "+index);
		//Returns the move 
		return index;
	}

	/**
	 *  Performs a move
	 */
	public static void performMove(TicTacToeGame theGame)
	{
		ArrayList<Integer> validMoves = theGame.getValidMoves();
	}
}
