import java.io.IOException;
import java.io.Reader;

/**
 * A Scanner is responsible for reading an input stream, one character at a
 * time, and separating the input into tokens.  A token is defined as:
 *  1. A 'word' which is defined as a non-empty sequence of characters that 
 *     begins with an alpha character and then consists of alpha characters, 
 *     numbers, the single quote character "'", or the hyphen character "-". 
 *  2. An 'end-of-sentence' delimiter defined as any one of the characters 
 *     ".", "?", "!".
 *  3. An end-of-file token which is returned when the scanner is asked for a
 *     token and the input is at the end-of-file.
 *  4. A phrase separator which consists of one of the characters ",",":" or
 *     ";".
 *  5. A digit.
 *  6. Any other character not defined above.
 * @author Mr. Page & Aarav Borthakur
 * @version 4/25/23
 */

public class Scanner
{
    private Reader in;
    private String currentChar;
    private boolean endOfFile;

    // define symbolic constants for each type of token
    public static enum TOKEN_TYPE{WORD, END_OF_SENTENCE, END_OF_FILE, 
        END_OF_PHRASE, DIGIT, UNKNOWN};
    /**
     * Constructor for Scanner objects.  The Reader object should be one of
     *  1. A StringReader
     *  2. A BufferedReader wrapped around an InputStream
     *  3. A BufferedReader wrapped around a FileReader
     *  The instance field for the Reader is initialized to the input parameter,
     *  and the endOfFile indicator is set to false.  The currentChar field is
     *  initialized by the getNextChar method.
     * @param in is the reader object supplied by the program constructing
     *        this Scanner object.
     */
    public Scanner(Reader in)
    {
        this.in = in;
        endOfFile = false;
        getNextChar();
    }


    /**
     * Advances the input stream if the input matches currentChar
     * @param str       The character to compare to currentChar
     * @postcondition   The input stream is advanced if str matches
     *                  currentChar
     */
    private void eat(String str)
    {
        if (str.equals(currentChar))
        {
            getNextChar();
        }
        else
        {
            throw new IllegalArgumentException(str + " is not equal to current char: " + currentChar);
        }
    }

    /**
     * Checks whether a character is a letter [a-z]
     * @param character The character to check
     * @return          Whether the character
     *                  is a letter
     */
    private boolean isLetter(String character)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        return alphabet.contains(character) || alphabet.toUpperCase().contains(character);
    }
    
    /**
     * Checks whether a character is a digit [0-9]
     * @param character The character to check
     * @return          Whether the character
     *                  is a digit
     */
    private boolean isDigit(String character)
    {
        return "0123456789".contains(character);
    }
    
    /**
     * Checks whether a character is a special character
     * @param character The character to check
     * @return          Whether the character
     *                  is a special character
     */
    private boolean isSpecial(String character)
    {
        return "-'".contains(character);
    }

    /**
     * Checks whether a character is a phrase terminator
     * @param character The character to check
     * @return          Whether the character
     *                  is a phrase terminator
     */
    private boolean isPhraseTerminator(String character)
    {
        return ",:;".contains(character);
    }
    
    /**
     * Checks whether a character is a sentence terminator
     * @param character The character to check
     * @return          Whether the character
     *                  is a sentence terminator
     */
    private boolean isSentenceTerminator(String character)
    {
        return ".?!".contains(character);
    }
    
    /**
     * Checks whether a character is a whitespace which includes space, newline, tab, carriage return
     * @param character The character to check
     * @return          Whether the character
     *                  is a space
     */
    private boolean isSpace(String character)
    {
        return character.equals(" ") || character.equals("\n") || character.equals("\t") || character.equals("\r");
    }

    /**
     * Checks whether there are more tokens in
     * the stream or it has reached the end of file
     * @return true if there are more tokens, false otherwise
     */
    public boolean hasNextToken()
    {
        return !endOfFile;
    }

    /**
     * Gets the next token in the stream. Skips the starting
     * whitespaces. 
     * @return        A Token representing the next token
     * @postcondition nextChar is after this token
     */
    public Token nextToken()
    {
        Token res;
        while (hasNextToken() && isSpace(currentChar))
        {
            eat(currentChar);
        }
        if (!hasNextToken())
        {
            res = new Token(TOKEN_TYPE.END_OF_FILE, "");
        }
        else if (isLetter(currentChar))
        {
            String word = "";
            while (isLetter(currentChar) || isDigit(currentChar) || isSpecial(currentChar))
            {
                word += currentChar;
                eat(currentChar);
            }
            res = new Token(TOKEN_TYPE.WORD, word);
            return res;
        }
        else if (isDigit(currentChar))
        {
            res = new Token(TOKEN_TYPE.DIGIT, currentChar);
        }
        else if (isSentenceTerminator(currentChar))
        {
            res = new Token(TOKEN_TYPE.END_OF_SENTENCE, currentChar);
        }
        else if (isPhraseTerminator(currentChar))
        {
            res = new Token(TOKEN_TYPE.END_OF_PHRASE, currentChar);
        }
        else
        {
            res = new Token(TOKEN_TYPE.UNKNOWN, currentChar);
        }
        eat(currentChar);
        return res;
    }

    /**
     * The getNextChar method attempts to get the next character from the input
     * stream.  It sets the endOfFile flag true if the end of file is reached on
     * the input stream.  Otherwise, it reads the next character from the stream
     * and converts it to a Java String object.
     * postcondition: The input stream is advanced one character if it is not at
     * end of file and the currentChar instance field is set to the String 
     * representation of the character read from the input stream.  The flag
     * endOfFile is set true if the input stream is exhausted.
     * @postcondition   char is set to the next character
     */
    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if(inp == -1) 
                endOfFile = true;
            else 
                currentChar = ("" + (char) inp);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}