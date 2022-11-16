import java.lang.Integer;

/**
 * Represents a Card in a Solitaire game
 * @author 		Aarav Borthakur
 * @version 	11/10/22
 */
public class Card {
    private int rank;
    private String suit;
    private boolean isFaceUp;

    /**
     * Gets the String representation of a rank number
     * @param rank      The rank of the card
     * @return          The Card's rank as a string
     */
    private String getRank(int rank)
    {
        if (rank == 1)
        {
            return "a";
        }
        if (rank == 10)
        {
            return "t";
        }
        if (rank == 11)
        {
            return "j";
        }
        if (rank == 12)
        {
            return "q";
        }
        if (rank == 13)
        {
            return "k";
        }
        return Integer.toString(rank);
    }

    /**
     * Constructs a Card
     * @param rank      The rank of the card
     * @param suit      The suit of the card
     */
    public Card(int rank, String suit)
    {
        this.rank = rank;
        this.suit = suit;
        this.isFaceUp = false;
    }

    /**
     * Gets the rank of the card 
     * @return          The rank of the card
     */
    public int getRank()
    {
        return rank;
    }

    /**
     * Gets the suit of the card
     * @return          The card's suit
     */
    public String getSuit()
    {
        return suit;
    }

    /**
     * Returns whether the Card is red 
     * @return          Whether the card is red 
     */
    public boolean isRed()
    {
        return suit.equals("h") || suit.equals("d");
    }

    /**
     * Returns whether the Card is faced up
     * @return          Whether the card is faced up
     */
    public boolean isFaceUp()
    {
        return isFaceUp;
    }

    /**
     * Turns the card up
     * @postcondition    The card is face up
     */
    public void turnUp()
    {
        isFaceUp = true;
    }

    /**
     * Turns the card down
     * @postcondition    The card is face down
     */
    public void turnDown()
    {
        isFaceUp = false;
    }

    /**
     * Gets the file name of the image of the card
     * @return          The file path of the image
     */
    public String getFileName()
    {
        if (isFaceUp)
        {
            return "cards/" + getRank(rank) + getSuit() + ".gif";
        }
        return "cards/backapcsds.gif";
    }
}