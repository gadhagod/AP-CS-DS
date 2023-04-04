import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Represents a Chess game
 */
public class Game
{
    private static final int DELAY = 1000;
    private static BoardDisplay display;

    /**
     * Gets the current game's display
     */
    public static BoardDisplay getDisplay()
    {
        return display;
    }

    /**
     * Executes the next turn in the game
     * @param board     The board of the Chess game
     * @param display   The display of the Chess game
     * @param player    The current player whose turn it is
     * @postcondition   player's turn is done
     */
    private static void nextTurn(Board board, BoardDisplay display, Player player)
    {
        display.setTitle(player.getName());
        Move nextMove = player.nextMove();
        board.executeMove(nextMove);
        if (nextMove.getPiece() instanceof Pawn)
        {
            ((Pawn) (nextMove.getPiece())).itHasMoved();
        }
        display.clearColors();
        try 
        {
            Thread.sleep(DELAY);
        } 
        catch(InterruptedException e) 
        {
            System.out.println(e);
        }
    }

    /**
     * Starts the game
     * @param args  The command line arguments
     */
    public static void main(String[] args) 
    {
        Board board = new Board();
        
        /* Display */
        display = new BoardDisplay(board);
        placePieces(board);

        play(board, display, new SmartPlayer(board, Color.white), new HumanPlayer(display, "Black",  board, Color.black));
    }

    /**
     * Places pieces on the board, should be run at
     * the start of the game
     * @param board     The chess board
     * @postcondition   The board has pieces placed on them
     */
    private static void placePieces(Board board)
    {
        /* Kings */
        Piece blackKing = new King(Color.BLACK, "black_king.gif");
        blackKing.putSelfInGrid(board, new Location(0, 4));
        Piece whiteKing = new King(Color.WHITE, "white_king.gif");
        whiteKing.putSelfInGrid(board, new Location(7, 4));

        /* Queens */
        Piece blackQueen = new Queen(Color.BLACK, "black_queen.gif");
        blackQueen.putSelfInGrid(board, new Location(0, 3));
        Piece whiteQueen = new Queen(Color.WHITE, "white_queen.gif");
        whiteQueen.putSelfInGrid(board, new Location(7, 3));

        /* Rooks */
        Piece blackRookOne = new Rook(Color.BLACK, "black_rook.gif");
        blackRookOne.putSelfInGrid(board, new Location(0, 0));
        Piece blackRookTwo = new Rook(Color.BLACK, "black_rook.gif");
        blackRookTwo.putSelfInGrid(board, new Location(0, 7));

        Piece whiteRookOne = new Rook(Color.WHITE, "white_rook.gif");
        whiteRookOne.putSelfInGrid(board, new Location(7, 0));
        Piece whiteRookTwo = new Rook(Color.WHITE, "white_rook.gif");
        whiteRookTwo.putSelfInGrid(board, new Location(7, 7));

        /* Knights */
        Piece blackKnightOne = new Knight(Color.BLACK, "black_knight.gif");
        blackKnightOne.putSelfInGrid(board, new Location(0, 1));
        Piece blackKnightTwo = new Knight(Color.BLACK, "black_knight.gif");
        blackKnightTwo.putSelfInGrid(board, new Location(0, 6));

        Piece whiteKnightOne = new Knight(Color.WHITE, "white_knight.gif");
        whiteKnightOne.putSelfInGrid(board, new Location(7, 1));
        Piece whiteKnightTwo = new Knight(Color.WHITE, "white_knight.gif");
        whiteKnightTwo.putSelfInGrid(board, new Location(7, 6));

        /* Bishops */
        Piece blackBishopOne = new Bishop(Color.BLACK, "black_bishop.gif");
        blackBishopOne.putSelfInGrid(board, new Location(0, 2));
        Piece blackBishopTwo = new Bishop(Color.BLACK, "black_bishop.gif");
        blackBishopTwo.putSelfInGrid(board, new Location(0, 5));

        Piece whiteBishopOne = new Bishop(Color.WHITE, "white_bishop.gif");
        whiteBishopOne.putSelfInGrid(board, new Location(7, 2));
        Piece whiteBishopTwo = new Bishop(Color.WHITE, "white_bishop.gif");
        whiteBishopTwo.putSelfInGrid(board, new Location(7, 5));

        /* Pawns */
        Piece blackPawnOne = new Pawn(Color.BLACK, "black_pawn.gif", board);
        Location blackPawnOneLocation = new Location(1, 0);
        blackPawnOne.putSelfInGrid(board, blackPawnOneLocation);
        ArrayList<Location> blackPawnLocations = new ArrayList<Location>();
        blackPawnOne.sweep(blackPawnLocations, Location.EAST);
        for (Location loc : blackPawnLocations)
        {
            new Pawn(Color.BLACK, "black_pawn.gif", board).putSelfInGrid(board, loc);;
        }

        Piece whitePawnOne = new Pawn(Color.WHITE, "white_pawn.gif", board);
        Location WhitePawnOneLocation = new Location(6, 0);
        whitePawnOne.putSelfInGrid(board, WhitePawnOneLocation);
        ArrayList<Location> whitePawnLocations = new ArrayList<Location>();
        whitePawnOne.sweep(whitePawnLocations, Location.EAST);
        for (Location loc : whitePawnLocations)
        {
            new Pawn(Color.WHITE, "white_pawn.gif", board).putSelfInGrid(board, loc);;
        }
    }

    /**
     * Starts the chess game by executing players' turns
     * @param board     The chess board
     * @param display   The chess display
     * @param first     The first player
     * @param second    The second player
     */
    public static void play(
        Board board, 
        BoardDisplay display, 
        Player first, 
        Player second
    ) {
        while (true)
        {
            nextTurn(board, display, first);
            nextTurn(board, display, second);
        }
    }
}