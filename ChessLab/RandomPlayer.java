import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents an automated Player that executes random
 * moves.
 * @author  Aarav Borthakur
 * @version 4/3/23
 */
public class RandomPlayer extends Player
{
    /**
     * Constructs a RandomPlayer
     * @param board     The chess board
     * @param color     The color of the player (white
     *                  or black)
     */
    public RandomPlayer(Board board, Color color)
    {
        super("Robot", board, color);
    }

    /**
     * Gets the next, randomized move of the RandomPlayer
     * @return  A random move of the player
     */
    public Move nextMove() 
    {
        ArrayList<Move> moves = getBoard().allMoves(getColor());
        int rand = (int) (Math.random() * moves.size());
        return moves.get(rand);
    }
    
}
