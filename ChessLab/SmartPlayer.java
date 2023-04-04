import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents a smart, automated Player that computes
 * the best possible moves to execute (looks two moves ahead)
 * @author  Aarav Borthakur
 * @version 4/3/23
 */
public class SmartPlayer extends Player
{
    /**
     * Constructs a SmartPlayer
     * @param board     The chess board
     * @param color     The color of the Player (white or black)
     */
    public SmartPlayer(Board board, Color color)
    {
        super("Smarty", board, color);
    }

    /**
     * Gets the color that this Player does not have
     * @return  black if this Player is white, else white 
     */
    private Color getOtherColor()
    {
        if (getColor().equals(Color.BLACK))
        {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    /**
     * Counts the total value of pieces of a color
     * @param color     The color whose score to count
     */
    private int score(Color color)
    {
        Board board = getBoard();
        int score = 0;
        for (int r = 0; r < board.getNumRows(); r++)
        {
            for (int c = 0; c < board.getNumCols(); c++)
            {
                Piece piece = board.get(new Location(r, c));
                if (piece != null && piece.getColor().equals(color))
                {
                    score += piece.getValue();
                }
            }
        }
        return score;
    }

    /**
     * Gets the score if a certain move is executed
     * @param move    The move to test
     * @return        The score after move is executed
     */
    private int getScoreWithEnemyResponse(Move move)
    {
        getBoard().executeMove(move);
        int standings =  score(getColor()) - score(getOtherColor());
        getBoard().undoMove(move);
        return standings;
    }

    /**
     * Gets the score after a move, and it's least favorable enemy
     * response, is executed.
     * @param move  The move to test
     * @return      The score after the move, and it's least favorable 
     *              enemy response, is executed.
     */
    private int getScoreWithMove(Move move)
    {
        // standing represented by (SmartPlayer's score - other player's score)
        getBoard().executeMove(move);

        ArrayList<Move> enemiesResponses = getBoard().allMoves(getOtherColor());
        Move enemysHighestScoringResponse = enemiesResponses.get(0);
        int highestScoringMoveScore = getScoreWithEnemyResponse(enemysHighestScoringResponse);

        for (Move m : enemiesResponses)
        {
            int scoreWithMove = getScoreWithEnemyResponse(m);
            if (scoreWithMove < highestScoringMoveScore)
            {
                enemysHighestScoringResponse = m;
                highestScoringMoveScore = scoreWithMove;
            }
        }

        getBoard().undoMove(move);

        return highestScoringMoveScore;
    }

    /**
     * Gets the next move of this SmartPlayer
     * @return  The next move of this SmartPlayer
     */
    public Move nextMove() 
    {
        ArrayList<Move> moves = getBoard().allMoves(getColor());
        if (moves.size() > 0)
        {
            Move highestScoringMove = moves.get(0);
            int highestScoringMoveScore = getScoreWithMove(highestScoringMove);
            for (Move m : moves)
            {
                int scoreWithMove = getScoreWithMove(m);
                if (scoreWithMove > highestScoringMoveScore)
                {
                    highestScoringMove = m;
                    highestScoringMoveScore = scoreWithMove;
                }
            }
            return highestScoringMove;
        }
        {
            // stalemate
            return null;
        }
    }
    
}
