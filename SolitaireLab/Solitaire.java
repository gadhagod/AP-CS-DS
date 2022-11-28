import java.util.*;

/**
 * Represents a Solitaire game
 * @author 		Aarav Borthakur
 * @version 	11/10/22
 */
public class Solitaire
{
    /*
     * Starts a Solitaire game
     */
    public static void main(String[] args)
    {
        new Solitaire();
    }

    private Stack<Card> stock;
    private Stack<Card> waste;
    private Stack<Card>[] foundations;
    private Stack<Card>[] piles;
    private SolitaireDisplay display;

    /*
     * Initializes a Solitaire game
     * by constructing a Solitaire
     * object
     */
    public Solitaire()
    {
        stock = new Stack<Card>();
        waste = new Stack<Card>();
        foundations = new Stack[4];
        piles = new Stack[7];
        for (int i = 0; i < 7; i++)
        {
            if (i <= 3)
            {
                foundations[i] = new Stack<Card>();
            }
            piles[i] = new Stack<Card>();
        }
        display = new SolitaireDisplay(this);
        //display.selectFoundation(0);
        createStock();
        deal();
        int sum = 0;
        System.out.println(stock.size());
        System.out.println(waste.size());
        System.out.println(piles[0].size());
        System.out.println(piles[1].size());
        System.out.println(piles[2].size());
        System.out.println(piles[3].size());
        System.out.println(piles[4].size());
        System.out.println(piles[5].size());
        System.out.println(piles[6].size());

        sum += stock.size();
        sum += waste.size();
        for (int i = 0 ; i < 7; i++)
        {
            sum += piles[i].size();
        }
        System.out.println(sum);
    }

    /**
     * Gets a random number from min to max
     * @param min		The lower bound (inclusive)
     * @param max		The upper bound (inclusive)
     * @return			The random number
     */
    private static int getRandomNum(int min, int max)
    {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    private void createStock()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < 13; i++)
        {
            cards.add(new Card(i + 1, "s"));
        }
        for (int i = 0; i < 13; i++)
        {
            cards.add(new Card(i + 1, "h"));
        }
        for (int i = 0; i < 13; i++)
        {
            cards.add(new Card(i + 1, "c"));
        }
        for (int i = 0; i < 13; i++)
        {
            cards.add(new Card(i + 1, "d"));
        }

        int size = 52;
        for (int i = 0; i < 52; i++, size--)
        {
            int rand = getRandomNum(0, size - 1);
            Card temp = cards.get(rand);
            cards.remove(rand);
            stock.add(temp);
        }
    }

    private void deal()
    {
        for (int i = 0; i < 7; i++) // row
        {
            for (int j = i; j < 7; j++) // column
            {
                Card current = stock.pop();
                current.turnDown();
                piles[j].push(current);
            }
            piles[i].peek().turnUp();
        }
    }

    private void dealThreeCards()
    {
        for (int i = 0; i < 3 && !stock.isEmpty(); i++)
        {
            Card current = stock.pop();
            current.turnUp();
            waste.push(current);
        }
    }

    private void resetStock()
    {
        while (!waste.isEmpty())
        {
            Card current = waste.pop();
            current.turnDown();
            stock.push(current);
        }
    }

    private boolean canAddToPile(Card card, int pileIndex)
    {
        Stack<Card> pile = piles[pileIndex];
        if (pile.isEmpty())
        {
            return card.getRank() == 13;
        }
        Card topOfPile = pile.peek();
        if (topOfPile.isFaceUp())
        {
            if (topOfPile.isFaceUp())
            {
                return ((topOfPile.isRed() && !card.isRed()) || (!topOfPile.isRed() && card.isRed())) && card.getRank() == topOfPile.getRank() - 1;
            }

        }
        return false;
    }

    /**
     * Gets the face up cards in a pile
     * @param index		The index of pile
     * @precondition	0 <= index < 7
     * @postcondition   Removes all face-up cards on the top of
     * 					the given pile; returns a stack
     * 					containing these cards
     * @return			A stack of face up cards in th
     */
    private Stack<Card> removeFaceUpCards(int index)
    {
        Stack<Card> faceUpCards = new Stack<Card>();

        while (!piles[index].isEmpty() && piles[index].peek().isFaceUp())
        {
            faceUpCards.push(piles[index].pop());
        }

        return faceUpCards;
    }

    /**
     * Adds a stack of cards to a pile
     * @param cards 	The stack of cards
     * @param index		The index of the target pile
     * @precondition	0 <= index < 7
     * @postcondition	Removes elements from cards, and adds // them to the given pile.
     */
    private void addToPile(Stack<Card> cards, int index)
    {
        while (!cards.isEmpty())
        {
            piles[index].push(cards.pop());
        }
    }

    /**
     * Checks whether a card can legally be added to a foundation
     * @param card		The card to be added
     * @param index		The index of the foundation
     */
    private boolean canAddToFoundation(Card card, int index)
    {
        Stack<Card> thisFoundation = foundations[index];
        if (thisFoundation.isEmpty())
        {
            return card.getRank() == 1;
        }
        return card.getSuit().equals(card.getSuit()) && card.getRank() == thisFoundation.peek().getRank() + 1;
    }

    /**
     * Checks whether the game has been won
     * @return			Whether the game has been won
     */
    private boolean isGameWon()
    {
        for (int i = 0; i < piles.length; i++)
        {
            if (!piles[i].isEmpty())
            {
                return false;
            }
        }
        return stock.isEmpty() && waste.isEmpty();
    }

    /**
     * Gets the card at top of the stock
     * @return			The card at the top of the stock
     */
    public Card getStockCard()
    {
        if (stock.isEmpty())
        {
            return null;
        }
        return stock.peek();
    }

    /**
     * Gets the card at the top of the waste pile
     * @return			The top of the waste piles
     */
    public Card getWasteCard()
    {
        if (waste.isEmpty())
        {
            return null;
        }
        return waste.peek();
    }

    /**
     * Gets the card at the top of the given foundation
     * @param index		The index of the foundation
     * @return			The top card of the foundation
     * @precondtion		0 <= index < 4
     * @postcondition	returns the card on top of the given
     * 					foundation, or null if the foundation
     * 					is empty
     */
    public Card getFoundationCard(int index)
    {
        Stack<Card> thisFoundation = foundations[index];
        if (thisFoundation.isEmpty())
        {
            return null;
        }
        return (Card) thisFoundation.peek();
    }

    /**
     * Gets the pile at a given index
     * @param index 	The index of the pile
     * @return			A refernce to the pile's stack
     */
    public Stack<Card> getPile(int index)
    {
        return piles[index];
    }

    /**
     * Runs when the stock is clicked
     * @postcondition		If neither the waste is selected
     * 						or a pile, resets the stock if
     * 						the stock is empty and deals
     * 						three cards otherwise
     */
    public void stockClicked()
    {
        if (!display.isWasteSelected() && !display.isPileSelected())
        {
            if (stock.isEmpty())
            {
                resetStock();
            }
            else 
            {
                dealThreeCards();
            }
        }	
    }

    /**
     * Runs when the waste is clicked
     * @postcondition		If the waste is selected, unselects
     * 						If waste is not empty, waste isn't
     * 						selected, and pile not selected,
     * 						selects waste
     */
    public void wasteClicked()
    {
        if (!waste.isEmpty() && !display.isWasteSelected() && !display.isPileSelected())
        {
            display.selectWaste();	
        }
        else if (display.isWasteSelected())
        {
            display.unselect();
        }
    }

    /**
     * Runs when the foundation is clicked
     * @param index			The index of the clicked foundation
     * @precondition		0 <= index < 4
     */
    public void foundationClicked(int index)
    {
        Stack<Card> origin;
        if (display.isPileSelected())
        {
            origin = piles[display.selectedPile()];
        }
        else if (display.isWasteSelected())
        {
            origin = waste;
        }
        else
        {
            if (display.selectedFoundation() != -1)
            {
                display.unselect();
            }
            else if (!foundations[index].isEmpty())
            {
                display.selectFoundation(index);
            }
            return;
        }
        if (!origin.isEmpty() && canAddToFoundation(origin.peek(), index))
        {
            foundations[index].push(origin.pop());
            display.unselect();
            if (isGameWon())
            {
                System.out.println("YOU WIN!!!! GOOD JOB!!!!!!!!!!!!!!!!");
            }
        }
    }

    /**
     * Called when given pile is clicked
     * @param index		Index of the clicked pile
     * @precondition  0 <= index < 7
     */
    public void pileClicked(int index)
    {
        if (display.isWasteSelected() && canAddToPile(waste.peek(), index))
        {
            Card current = waste.pop();
            piles[index].push(current);
            display.unselect();
        }
        else if (display.selectedFoundation() != -1 && !foundations[display.selectedFoundation()].isEmpty() && canAddToPile(foundations[display.selectedFoundation()].peek(), index))
        {
            piles[index].push(foundations[display.selectedFoundation()].pop());
        }
        else if (display.selectedPile() == index)
        {
            display.unselect();
        }
        else if (display.selectedPile() != -1)
        {
            if (canAddToPile(piles[display.selectedPile()].peek(), index))
            {
                Stack<Card> faceUpCards = removeFaceUpCards(display.selectedPile());
                addToPile(faceUpCards, index);
                display.unselect();
            }
        }
        else if (!piles[index].isEmpty() && piles[index].peek().isFaceUp())
        {
            display.selectPile(index);
        }
        else if (!display.isPileSelected() && !display.isWasteSelected())
        {
            piles[index].peek().turnUp();
        }
    }
}