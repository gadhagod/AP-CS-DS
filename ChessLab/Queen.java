import java.awt.Color;
import java.util.ArrayList;

/**
 * A Rook chess piece
 * @author  Aarav Borthakur
 * @version 4/3/23
 */
public class Queen extends Piece
{
    /**
     * Constructs a Queen piece
     * @param color     The Queen's color
     * @param fileName  The Queen's image
     */
    public Queen(Color color, String fileName)
    {
        super(color, fileName, 9);
    }

    /**
     * Gets the allowed destinations of a Move moving
     * this Piece
     * @return  A list of legal destinations of this Queen
     */
    public ArrayList<Location> destinations() 
    {
        ArrayList<Location> res = new ArrayList<Location>();
        
        sweep(res, Location.NORTH);
        sweep(res, Location.NORTHEAST);
        sweep(res, Location.NORTHWEST);
        sweep(res, Location.SOUTH);
        sweep(res, Location.SOUTHEAST);
        sweep(res, Location.SOUTHWEST);

        return res;
    }
}
