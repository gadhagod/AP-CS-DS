import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents a Bishop game pience
 * @author  Aarav Borthakur
 * @version 4/3/23
 */
public class Bishop extends Piece
{
    /**
     * Constructs a Bishop given its color and image filename
     */
    public Bishop(Color color, String fileName)
    {
        super(color, fileName, 3);
    }

    /**
     * Gets all the possible destinations this Bishop can move to
     * @return An ArrayList of destinations this Bishop can move to
     */
    public ArrayList<Location> destinations() 
    {
        ArrayList<Location> res = new ArrayList<Location>();
        sweep(res, Location.NORTHEAST);
        sweep(res, Location.NORTHWEST);
        sweep(res, Location.SOUTHEAST);
        sweep(res, Location.SOUTHWEST);
        return res;
    }
}
