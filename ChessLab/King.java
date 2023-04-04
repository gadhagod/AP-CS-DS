import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A King chess piece
 * @author  Aarav Borthakur
 * @version 4/3/23
 */
public class King extends Piece
{
    /**
     * Constructs a King piece
     * @param color     The King's color
     * @param fileName  The King's image
     */
    public King(Color color, String fileName)
    {
        super(color, fileName, 100);
    }

    /**
     * Gets the allowed destinations of a Move moving
     * this Piece
     * @return  A list of legal destinations of this King
     */
    public ArrayList<Location> destinations() 
    {
        ArrayList<Location> res = getBoard().getValidAdjacentLocations(getLocation());
        Iterator<Location> iter = res.iterator();
        while (iter.hasNext())
        {
            Piece victim = getBoard().get(iter.next());
            if (victim != null && victim.getColor().equals(getColor()))
            {
                iter.remove();
            }
        }
        return res;
    }
}
