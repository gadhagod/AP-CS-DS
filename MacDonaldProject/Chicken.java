/**
 * Chicken represents a chicken animal
 * @author Aarav Borthakur
 * @version October 10, 2022
 */
public class Chicken extends Animal 
{
    /**
     * Creates a Chicken object
     */
    public Chicken() 
    {
        this("chicken");
    }

    /**
     * Creates a Chicken object with a type
     * @param chicken       The common name of the Chicken
     */
    public Chicken(String chickenType)
    {
        super("Gallus Gallus domesticus", chickenType);
    }

    /**
     * Makes a Chicken say "bawk" by printing
     */
    public void speak()
    {
        System.out.print("bawk");
    }
}
