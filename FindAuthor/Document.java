import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Document of text written by some author
 * @version 5/12/23
 * @author  Aarav Borthakur
 */
public class Document 
{
    private Scanner scanner; 
    private ArrayList<Sentence> sentences;
    private Token currentToken;

    private double averageWordLength;
    private double tokenTypeRatio;
    private double hapaxLegomanaRatio;
    private double averageWordsPerSentence;
    private double averageSentenceComplexity;

    /**
     * Constructs a Document given a scanner
     * @param scanner   The scanner to use to read the
     *                  document
     */
    public Document(Scanner scanner)
    {
        this.scanner = scanner;
        sentences = new ArrayList<Sentence>();
        eat(null);
        parseDocument();
        setStats();
    }

    /**
     * Takes in a Token and then compares it to the currentToken
     *  instance field. If they match, the method advances the
     * input stream by calling getNextToken(). Otherwise, it throws 
     * an IllegalArgumentException.
     * @param tok       The token to eat
     * @postcondition   The input stream is advanced if str matches
     *                  currentToken
     */
    private void eat(Token tok)
    {
        if (tok == null || tok.equals(currentToken))
        {
            currentToken = getNextToken();
        }
        else
        {
            throw new RuntimeException("eat failed; input token and current token do not match");
        }
    }

    /**
     * Gets the next token by requesting a Token object from the
     * Scanner
     * @return  The next token in the stream
     */
    private Token getNextToken()
    {
        return scanner.nextToken();
    }

    /**
     * Parses a phrase by parsing each token in the phrase
     * @return          The next phrase
     * @postcondition   The input stream is advanced until
     *                  the end of the phrase
     */
    private Phrase parsePhrase()
    {
        Phrase p = new Phrase();
        while (
            currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_PHRASE &&
            currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_FILE &&
            currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_SENTENCE
        )
        {
            p.addToken(currentToken);
            eat(currentToken);
        }

        if (currentToken.getType() == Scanner.TOKEN_TYPE.END_OF_PHRASE)
        {
            eat(currentToken);
        }

        return p;
    }

    /**
     * Parses a phrase by parsing each token in the phrase
     * @return          The next phrase
     * @postcondition   The input stream is advanced until
     *                  the end of the phrase
     */
    private Sentence parseSentence()
    {
        Sentence s = new Sentence();
        while (
            currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_FILE &&
            currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_SENTENCE
        )
        {
            s.addPhrase(parsePhrase());
        }

        if (currentToken.getType() == Scanner.TOKEN_TYPE.END_OF_SENTENCE)
        {
            eat(currentToken);
        }

        return s;
    }

    /**
     * Parses through each sentence in a document stores
     * an array of sentences in the sentences instance 
     * variable.
     * @postcondition   
     */
    private void parseDocument()
    {
        while (currentToken.getType() != Scanner.TOKEN_TYPE.WORD)
        {
            eat(currentToken);
        }
        while (
            currentToken.getType() != Scanner.TOKEN_TYPE.END_OF_FILE
        )
        {
            sentences.add(parseSentence());
        }
    }

    /**
     * Gets an ArrayList of all sentences in the document
     * @return  An ArrayList of all sentences in the document
     */
    public ArrayList<Sentence> getSentences()
    {
        return sentences;
    }

    /**
     * Sets the appropriate instance fields representing stats
     * of this document
     * @postcondition   The stats are set
     */
    private void setStats()
    {
        Set<Token> uniqueWords = new HashSet<Token>();
        Set<Token> wordsThatAppearMoreThanOnce = new HashSet<Token>();
        int numOfWordsThatAppearOnlyOnce = 0;
        int totalWordLength = 0;
        int totalNumWords = 0;
        int totalNumSentences = 0;
        int totalNumPhrases = 0;
        for (Sentence sentence : sentences)
        {
            totalNumSentences++;
            for (Phrase phrase: sentence.getPhrases())
            {
                totalNumPhrases++;
                for (Token token : phrase.getTokens())
                {
                    if (token.getType() == Scanner.TOKEN_TYPE.WORD)
                    {
                        totalNumWords++;
                        totalWordLength += token.getValue().length();
                        if (!uniqueWords.add(token))
                        {
                            wordsThatAppearMoreThanOnce.add(token);
                        }

                    }
                }
            }
        }
        numOfWordsThatAppearOnlyOnce = uniqueWords.size() - wordsThatAppearMoreThanOnce.size();

        averageWordLength = (double) totalWordLength / totalNumWords;
        tokenTypeRatio = (double) uniqueWords.size() / totalNumWords;
        hapaxLegomanaRatio = (double) numOfWordsThatAppearOnlyOnce / totalNumWords;
        averageWordsPerSentence = (double) totalNumWords / totalNumSentences;
        averageSentenceComplexity = (double) totalNumPhrases / totalNumSentences;
    }

    /**
     * Gets an array of stats of the document
     * @return  stats in the format [average word length, token type ratio,
     *          hapax legomana ratio, average words per sentence, sentence 
     *          complexity]
     */
    public double[] getStats()
    {
        double[] res = {
            averageWordLength, 
            tokenTypeRatio, 
            hapaxLegomanaRatio, 
            averageWordsPerSentence, 
            averageSentenceComplexity
        };

        return res;
    }
}
