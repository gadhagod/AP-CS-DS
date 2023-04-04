import java.awt.*;
import java.util.*;

/**
 * Represesents a rectangular game board, containing Piece objects.
 * @author  Aarav Borthakur
 * @version 4/3/23
 */
public class Board extends BoundedGrid<Piece>
{
	/**
	 * Constructs an 8x8 board
	 */
	public Board()
	{
		super(8, 8);
	}

	/**
	 * Executes a given move
	 * @param move		The move to execute
	 * @postcondition	The move is executed
	 */
	public void executeMove(Move move)
	{
		move.getPiece().moveTo(move.getDestination());
	}

	// Precondition:  move has already been made on the board
	// Postcondition: piece has moved back to its source,
	//                and any captured piece is returned to its location
	/**
	 * Undos a given move
	 * @param move 		The move to undo
	 * @precondition	move has already been made on the board
	 * @postcondition	piece has moved back to its source,
	 *                	and any captured piece is returned to its
	 * 					location
	 */
	public void undoMove(Move move)
	{
		Piece piece = move.getPiece();
		Location source = move.getSource();
		Location dest = move.getDestination();
		Piece victim = move.getVictim();

		piece.moveTo(source);

		if (victim != null)
			victim.putSelfInGrid(piece.getBoard(), dest);
	}

	/**
	 * Gets all available moves of the player of a given player
	 * @param color	The color of the player whose moves are to 
	 * 				be retrieved
	 */
	public ArrayList<Move> allMoves(Color color)
	{
		ArrayList<Move> res = new ArrayList<Move>();
		for (int row = 0; row < 8; row++)
		{
			for (int col = 0; col < 8; col++)
			{
				Piece piece = get(new Location(row, col));
				if (piece != null && piece.getColor().equals(color))
				{
					for (Location loc : piece.destinations())
					{
						res.add(new Move(piece, loc));
					}
				}
			}
		}
		return res;
	}
}