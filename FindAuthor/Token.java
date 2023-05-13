/**
 * Represents a singular token in a text.
 * A token is defined as follows:
 * - A 'word' which is defined as a non-empty sequence of characters that
 *   begins with an alpha character and then consists of alpha characters, 
 *   numbers, the single quote character
 * - "'", or the hyphen character "-". Words are converted to lower case.
 * - An 'end of sentence' delimiter defined as any one of the characters 
 *   “.”, “?”, or “!”.
 * - An end-of-file token which is returned when the scanner is asked for 
 * a token and the input is at the end-of-file.
 * - A phrase separator which consists of one of the characters ",", : or
 * ";".
 * - A digit.
 * - Any other character not defined above.
 * @author  Aarav Borthakur
 * @version 5/12/23
 */
public class Token 
{
    private Scanner.TOKEN_TYPE type;
    private String value;

    /**
     * Constructs a Token
     * @param type  The type of token this is (e.g. word)
     * @param value The actual value of the token
     */
    public Token(Scanner.TOKEN_TYPE type, String value)
    {
        this.type = type;
        this.value = value;
    }

    /**
     * Gets the type of this Token of type Scanner.TOKEN_TYPE
     * @return The type of this Token (e.g WORD)
     */
    public Scanner.TOKEN_TYPE getType()
    {
        return type;
    }

    /**
     * Gets the value of this Token
     * @return  The value of this Token
     */
    public String getValue()
    {
        return value.toString();
    }

    /**
     * Checks whether this token is equal to another by
     * checking both Token type and value
     * @param other     The Token to compare to
     * @return          Whether the Tokens are equal
     */
    public boolean equals(Object other)
    {
        if (other instanceof Token)
        {
            Token otherToken = ((Token) other);
            return otherToken.type == type && otherToken.value.equals(value);
        }
        throw new IllegalArgumentException("other is not of type Token");
    }

    /**
     * Gets a String representation of the Token
     * @return   A String representation of the Token
     *           in the format "type: value"
     */
    public String toString()
    {
        return type + ": " + value;
    }

    /**
     * Gets the hash code of the the Token by getting the
     * hash code of its String representation
     */
    public int hashCode()
    {
        return toString().hashCode();
    }
}