import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A Pawn chess piece
 * @author  Aarav Borthakur
 * @version 4/3/23
 */
public class Pawn extends Piece
{
    private boolean moved;

    /**
     * Constructs a Pawn piece
     * @param color     The Pawn's color
     * @param fileName  The Pawn's image
     */
    public Pawn(Color color, String fileName, Board board)
    {
        super(color, fileName, 1);
    }

    /**
     * Gets the allowed destinations of a Move moving
     * this Piece
     * @return  A list of legal destinations of this Pawn
     */
    public ArrayList<Location> destinations() 
    {
        ArrayList<Location> res = new ArrayList<Location>();
        Location curr = getLocation();

        int displacement;
        if (getColor().equals(Color.BLACK))
        {
            displacement = 1;
        }
        else
        {
            displacement = -1;
        }
        res.add(new Location(curr.getRow() + displacement, curr.getCol()));

        Iterator<Location> it = res.iterator();
        while (it.hasNext())
        {
            Location next = it.next();
            if (!getBoard().isValid(next) ||
                (getBoard().get(next) != null && getBoard().get(next).getColor().equals(getColor()))
            )
            {
                it.remove();
            }
        }
        if (!moved)
        {
            if (getColor().equals(Color.BLACK))
            {
                displacement = 2;
            }
            else
            {
                displacement = -2;
            }
            Location loc = new Location(curr.getRow() + displacement, curr.getCol());
            if (getBoard().get(loc) == null)
            {
                res.add(loc);
            }
        }

        int e;
        int w;

        if (getColor().equals(Color.BLACK))
        {
            e = Location.SOUTHEAST;
            w = Location.SOUTHWEST;
        }
        else
        {
            e = Location.NORTHEAST;
            w = Location.NORTHWEST;
        }

        Location eLoc = curr.getAdjacentLocation(e);
        Location wLoc = curr.getAdjacentLocation(w);

        if (getBoard().isValid(eLoc) && getBoard().get(eLoc) != null)
        {
            res.add(eLoc);
        }
        if (getBoard().isValid(wLoc) && getBoard().get(wLoc) != null)
        {
            res.add(wLoc);
        }

        return res;
    }

    /**
     * Declares that this Pawn has moved so far
     * @postcondition   The pawn can no longer move two spaces
     */
    public void itHasMoved()
    {
        moved = true;
    }
}