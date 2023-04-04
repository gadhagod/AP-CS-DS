import java.awt.Color;
import java.util.ArrayList;

/**
 * A player manually controlled by a human
 * @author  Aarav Borthakur
 * @version 3/4/23
 */
public class HumanPlayer extends Player
{
    private BoardDisplay display;

    /**
     * Constructs a HumanPlayer
     * @param display   The game display
     * @param name      The name of the player
     * @param board     The chess board
     * @param color     The color of this player (Color.BLACK or COLOR.WHITE)
     */
    public HumanPlayer(BoardDisplay display, String name, Board board, Color color)
    {
        super(name, board, color);
        this.display = display;
    }
    
    /**
     * Asks the user for the next move until a valid
     * move is recieved
     * @return  The chosen move
     */
    public Move nextMove() 
    {
        ArrayList<Move> validMoves = getBoard().allMoves(getColor());
        Move nextMove = display.selectMove();
        display.clearColors();
        if (validMoves.contains(nextMove))
        {
            return nextMove;
        }
        return nextMove();
    }
}
