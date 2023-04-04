import java.awt.Color;

/**
 * Represents a player of chess
 * @author  Aarav Borthakur
 * @version 4/3/23
 */
public abstract class Player 
{
    private String name;
    private Board board;
    private Color color;

    /**
     * Constructs a player
     * @param name  The name of the PLayer
     * @param board The chess board
     * @param color The color of the player (white or black)
     */
    public Player(String name, Board board, Color color)
    {
        this.name = name;
        this.board = board;
        this.color = color;
    }

    /**
     * Gets the name of the Player
     * @return  The name of the player
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the chess board that the Player plays on
     * @return  the chess board that the Player plays on
     */
    public Board getBoard()
    {
        return board;
    }

    /**
     * Gets the color of the Player
     * @return  White if white, black if black
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Gets the next move of the player
     * @return  the next move of the player
     */
    public abstract Move nextMove();
}
