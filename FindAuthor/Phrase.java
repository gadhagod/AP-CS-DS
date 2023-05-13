import java.util.ArrayList;

/**
 * Repesents a phrase in a Document. Phrases are defined as non-empty sections 
 * of sentences that are separated by colons, commas, or semi-colons. 
 * @version 5/12/23
 * @author  Aarav Borthakur
 */
public class Phrase 
{
    private ArrayList<Token> tokens;

    /**
     * Constructs a Phrase with no tokens
     */
    public Phrase()
    {
        tokens = new ArrayList<Token>();
    }

    /**
     * Adds a token to the phrase
     * @param tok       The Token to add to the phrase
     * @postcondition   tok is added to this Sentence
     */
    public void addToken(Token tok)
    {
        tokens.add(tok);
    }

    /**
     * Gets an ArrayList of Tokens in this Phrase
     * @return An ArrayList of Tokens in this Phrase
     */
    public ArrayList<Token> getTokens()
    {
        return tokens;
    }

    /**
     * Gets a String representation of this Phrase
     * @return The String representation of this Phrase
     */
    public String toString()
    {
        String res = "";
        for (Token tok : tokens)
        {
            res += tok + " ";
        }
        return res;
    }
}
