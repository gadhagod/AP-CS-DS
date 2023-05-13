import java.util.ArrayList;

/**
 * Represents a sentence of a text. Sentences are seperated with periods, 
 * exclamation marks, question marks.
 * @author  Aarav Borthakur
 * @version 5/12/23
 */
public class Sentence 
{
    private ArrayList<Phrase> phrases;

    /**
     * Constructs a Sentence with no phrases
     */
    public Sentence()
    {
        phrases = new ArrayList<Phrase>();
    }

    /**
     * Adds a phrase to the Sentence
     * @param phrase    The phrase to add to the Sentence
     * @postcondition   phrase is added to this Sentence
     */
    public void addPhrase(Phrase phrase)
    {
        phrases.add(phrase);
    }

    /**
     * Gets an ArrayList of Phrases in this Sentence
     * @return  An ArrayList of Phrases in this Sentence
     */
    public ArrayList<Phrase> getPhrases()
    {
        return phrases;
    }

    /**
     * Gets a String representation of this Sentence
     * @return  A String representation of this Sentence
     */
    public String toString()
    {
        String res = "";
        for (Phrase p : phrases)
        {
            res += p;
        }
        return res;
    }
}
