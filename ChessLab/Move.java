/**
 * Represents a single move, in which a piece moves to a destination location.
 * Since a move can be undone, also keeps track of the source location and any 
 * captured victim.
 * @author  Aarav Borthakur
 * @version 4/3/23
 */
public class Move
{
	private Piece piece;          // the piece being moved
	private Location source;      // the location being moved from
	private Location destination; // the location being moved to
	private Piece victim;         // any captured piece at the destination

	/**
	 * Constructs a new move for moving the given piece to the given destination.
	 * @param piece		  The peice being moved
	 * @param destination The destination of this Move
	 */
	public Move(Piece piece, Location destination)
	{
		this.piece = piece;
		this.source = piece.getLocation();
		this.destination = destination;
		this.victim = piece.getBoard().get(destination);

		if (source.equals(destination))
			throw new IllegalArgumentException("Both source and dest are " + source);
	}

	/**
	 * Gets the piece being moved
	 * @return The Piece being moved
	 */
	public Piece getPiece()
	{
		return piece;
	}

	/**
	 * Gets the location being moved from
	 * @return 	The location being moved from
	 */
	public Location getSource()
	{
		return source;
	}

	/**
	 * Gets the location being moved to
	 * @return 	The location being moved to
	 */
	public Location getDestination()
	{
		return destination;
	}

	/**
	 * Gets the piece being captured at the destination, if any
	 * @return The piece being captured at the destination, if any
	 */
	public Piece getVictim()
	{
		return victim;
	}

	/**
	 * Gets a String representation of the move
	 * @return 	The String representation of the move
	 */
	public String toString()
	{
		return piece + " from " + source + " to " + destination + " containing " + victim;
	}

	/**
	 * Checks whether this Move is equivalent to another
	 * @param x		The other Object to compare this Move to
	 * @return 		true if this move is equivalent to x,
	 * 				false otherwise
	 */
	public boolean equals(Object x)
	{
		Move other = (Move)x;
		return piece == other.getPiece() && source.equals(other.getSource()) &&
			destination.equals(other.getDestination()) && victim == other.getVictim();
	}

	/**
	 * Gets a hash code for this move, such that equivalent moves 
	 * have the same hash code
	 * @return	This Move's hash code
	 */
	public int hashCode()
	{
		return piece.hashCode() + source.hashCode() + destination.hashCode();
	}
}