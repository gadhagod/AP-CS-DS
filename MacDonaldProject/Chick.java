/**
 * Chick represents a chick animal (a baby chicken)
 * @author Aarav Borthakur
 * @version October 10, 2022
 */
public class Chick extends Chicken 
{
    /**
     * Creates a Chick
     */
    public Chick()
    {
        super("chick");
    }

    /**
     * Makes a Chick say "peep" by printing
     */
    public void speak()
    {
        System.out.print("peep");
    }
}
