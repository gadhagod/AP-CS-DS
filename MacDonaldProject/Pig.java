/**
 * Represents a Pig Animal
 * @author Aarav Borthakur
 * @version October 10, 2022
 */
public class Pig extends Animal
{
    /**
     * Creates a Pig object
     */
    public Pig()
    {
        super("Sus", "pig");
    }

    /**
     * Makes a Pig say "oink" by printing
     */
    public void speak() {
        System.out.print("oink");
    }
}
