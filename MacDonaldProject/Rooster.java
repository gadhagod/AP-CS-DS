/**
 * Represents a Rooster animal
 * @author Aarav Borthakur
 * @version October 10, 2022
 */
public class Rooster extends Chicken
{
    /**
     * Creates a Rooster object
     */
    public Rooster()
    {
        super("rooster");
    }

    /**
     * Makes a Rooster say "cock-a-doodle-do" by printing
     */
    public void speak()
    {
        System.out.print("cock-a-doodle-do");
    }
}