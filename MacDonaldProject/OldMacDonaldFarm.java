import java.util.ArrayList;

/**
 * Tests the different animal classes by singing a version of the MacDonald's Farm nursery rhyme
 * @author Aarav Borthakur
 * @version October 10, 2022
 */
public class OldMacDonaldFarm 
{
    private String farmerName;
    private ArrayList<Animal> farmAnimals;

    /**
     * Creates a OldMacDonaldFarm object
     */
    public OldMacDonaldFarm()
    {
        farmerName = "Old MacDonald";
        farmAnimals = new ArrayList<Animal>();
    }

    /**
     * Tests the various animals by singing a version of the MacDonald's farm nursery ryhme
     * @param args      The command line arguments
     */
    public static void main(String[] args)
    {
        OldMacDonaldFarm singer = new OldMacDonaldFarm();
        singer.farmAnimals.add(new Chicken());
        singer.singVerse();
        singer.farmAnimals.add(new Chick());
        singer.singVerse();
        singer.farmAnimals.add(new Rooster());
        singer.singVerse();
        singer.farmAnimals.add(new Pig());
        singer.singVerse();
    }

    /**
     * Sings a version of the MacDonald's farm nursery ryhme
     */
    public void singVerse()
    {
        String eieio = "E-I-E-I-O";
        System.out.println(farmerName + " had a farm, " + 
            eieio + " and on this farm he had some " + 
            farmAnimals.get(farmAnimals.size() - 1).getCommonName() + 
            "s, " + eieio
        );
        for (int i = farmAnimals.size() - 1; i >= 0; i--)
        {
            Animal currentAnimal = farmAnimals.get(i);
            System.out.print("With a ");
            currentAnimal.speak();
            System.out.print("-");
            currentAnimal.speak();
            System.out.print(" here, and a ");
            currentAnimal.speak();
            System.out.print("-");
            currentAnimal.speak();
            System.out.println(" there,");
            System.out.print("Here a ");
            currentAnimal.speak();
            System.out.print(", there a ");
            currentAnimal.speak();
            System.out.print(", everywhere a ");
            currentAnimal.speak();
            System.out.print("-");
            currentAnimal.speak();
            System.out.println(",");
        }
        System.out.println(farmerName + " had a farm, " + eieio + ".");
        System.out.println();
    }
}
