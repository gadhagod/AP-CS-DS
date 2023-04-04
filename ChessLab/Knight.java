import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A Knight chess piece
 * @author  Aarav Borthakur
 * @version 4/3/23
 */
public class Knight extends Piece
{
    /**
     * Constructs a Knight piece
     * @param color     The Knight's color
     * @param fileName  The Knight's image
     */
    public Knight(Color color, String fileName)
    {
        super(color, fileName, 3);
    }

    /**
     * Gets the allowed destinations of a Move moving
     * this Piece
     * @return  A list of legal destinations of this Knight
     */
    public ArrayList<Location> destinations() 
    {
        Location curr = getLocation();
        int currCol = curr.getCol();
        int currRow = curr.getRow();

        ArrayList<Location> res = new ArrayList<Location>();
        res.add(new Location(currRow + 2, currCol + 1));
        res.add(new Location(currRow + 2, currCol - 1));
        res.add(new Location(currRow - 2, currCol + 1));
        res.add(new Location(currRow - 2, currCol - 1));
        res.add(new Location(currRow + 1, currCol + 2));
        res.add(new Location(currRow + 1, currCol - 2));
        res.add(new Location(currRow - 1, currCol + 2));
        res.add(new Location(currRow - 1, currCol - 2));

        Iterator<Location> it = res.iterator();
        while (it.hasNext())
        {
            Location next = it.next();
            if (!getBoard().isValid(next) ||
                (getBoard().get(next) != null && getBoard().get(next).getColor().equals(getColor()))
            )
            {
                if (next.getRow() == 6 && getColor().equals(Color.BLACK))
                {
                    System.out.println(next);
                }
                it.remove();
            }
        }
        return res;
    }
}
