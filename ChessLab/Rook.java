import java.awt.Color;
import java.util.ArrayList;

/**
 * A Rook chess piece
 * @author  Aarav Borthakur
 * @version 4/3/23
 */
public class Rook extends Piece
{
    /**
     * Constructs a Rook piece
     * @param color     The Rook's color
     * @param fileName  The Rook's image
     */
    public Rook(Color color, String fileName)
    {
        super(color, fileName, 5);
    }

    /**
     * Gets the allowed destinations of a Move moving
     * this Piece
     * @return  A list of legal destinations of this Rook
     */
    public ArrayList<Location> destinations() 
    {
        ArrayList<Location> res = new ArrayList<Location>();
        sweep(res, Location.NORTH);
        sweep(res, Location.SOUTH);
        sweep(res, Location.EAST);
        sweep(res, Location.WEST);
        return res;
    }
}
