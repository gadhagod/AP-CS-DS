import java.awt.*;
import java.util.*;

/**
 * Represents a Piece
 */
public abstract class Piece
{
	//the board this piece is on
	private Board board;

	//the location of this piece on the board
	private Location location;

	//the color of the piece
	private Color color;

	//the file used to display this piece
	private String imageFileName;

	//the approximate value of this piece in a game of chess
	private int value;

    /**
     * Constructs a Piece given it's color, image, and value
     * @param col       The color of the Piece
     * @param fileName  The image of the Piece
     * @param val       The value of this Piece
     */
	public Piece(Color col, String fileName, int val)
	{
		color = col;
		imageFileName = fileName;
		value = val;
	}

    /**
     * Gets the board this piece is on
     * @return the board this piece is on
     */
	public Board getBoard()
	{
		return board;
	}

    /**
     * Gets the location of this piece on the board
     * @return the location of this piece on the board
     */
	public Location getLocation()
	{
		return location;
	}

    /**
     * Gets the color of this piece
     * @return  the color of this piece
     */
	public Color getColor()
	{
		return color;
	}

    /**
     * Gets the name of the file used to display this pience
     * @return the Piece's filename
     */
	public String getImageFileName()
	{
		return imageFileName;
	}

    /**
     * Gets the number representing the relative value of this piece
     * @return the number representing the relative value of this piece
     */
	public int getValue()
	{
		return value;
	}

    /**
     * Puts this piece into a board. If there is another piece at the given
     * location, it is removed. <br />
     * Precondition: (1) This piece is not contained in a grid (2)
     * <code>loc</code> is valid in <code>gr</code>
     * @param brd the board into which this piece should be placed
     * @param loc the location into which the piece should be placed
     */
    public void putSelfInGrid(Board brd, Location loc)
    {
        if (board != null)
            throw new IllegalStateException(
                    "This piece is already contained in a board.");

        Piece piece = brd.get(loc);
        if (piece != null)
            piece.removeSelfFromGrid();
        brd.put(loc, this);
        board = brd;
        location = loc;
    }

    /**
     * Removes this piece from its board. <br />
     * Precondition: This piece is contained in a board
     */
    public void removeSelfFromGrid()
    {
        if (board == null)
            throw new IllegalStateException(
                    "This piece is not contained in a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                    "The board contains a different piece at location "
                            + location + ".");

        board.remove(location);
        board = null;
        location = null;
    }

    /**
     * Moves this piece to a new location. If there is another piece at the
     * given location, it is removed. <br />
     * Precondition: (1) This piece is contained in a grid (2)
     * <code>newLocation</code> is valid in the grid of this piece
     * @param newLocation the new location
     */
    public void moveTo(Location newLocation)
    {
        if (board == null)
            throw new IllegalStateException("This piece is not on a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                    "The board contains a different piece at location "
                            + location + ".");
        if (!board.isValid(newLocation))
            throw new IllegalArgumentException("Location " + newLocation
                    + " is not valid.");

        if (newLocation.equals(location))
            return;
        board.remove(location);
        Piece other = board.get(newLocation);
        if (other != null)
        {
            other.removeSelfFromGrid();
            if (other instanceof King)
            {
                Game.getDisplay().endGame();
            }
        }
        location = newLocation;
        board.put(location, this);
    }

    /**
     * Checks whether the destination is valid 
     * (the location is within grid bounds and empty)
     * @param dest  The destination to check
     * @return      Whether the destination is valid
     */
    public boolean isValidDestination(Location dest)
    {
        return board.isValid(dest) && board.get(dest) == null;
    }

    /**
     * Gets all locations in a direction relative to the
     * Piece until a friendly piece is found or the
     * end of the grid is reached
     * @param dests     The array to add the Locations to
     * @param direction The direction to sweep
     */
    public void sweep(ArrayList<Location> dests, int direction)
    {
        Location curr = location.getAdjacentLocation(direction);
        boolean stop = false;
        while (board.isValid(curr) && !stop)
        {
            if (board.get(curr) != null)
            {
                if (board.get(curr).getColor().equals(board.get(location).getColor())) // friendly piece
                {
                    return;
                }
                // enemy piece
                stop = true;
            }
            dests.add(curr);
            curr = curr.getAdjacentLocation(direction);
        }
    }

    public abstract ArrayList<Location> destinations();
}