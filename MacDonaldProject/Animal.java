/**
 * Chicken represents a chicken animal
 * @author Aarav Borthakur
 * @version October 10, 2022
 */
abstract class Animal implements Comparable 
{
    String latinName;
    String commonName;
    /**
     * Creates an Animal given its latin and common names
     * @param latinName     The latin name of the animal
     * @param commonName    The common name of the animal
     */
    public Animal(String latinName, String commonName) 
    {
        this.latinName = latinName;
        this.commonName = commonName;
    }
    /**
     * Gets the Animal's latin name
     * @return      The latin name of the Animal
     */
    public String getLatinName()
    {
        return latinName;
    }
    /**
     * Gets the Animal's common name
     * @return      The Animal's common name
     */
    public String getCommonName()
    {
        return commonName;
    }
    /**
     * Makes an Animal speak by printing 
     */
    public abstract void speak();
    /**
     * Compares two Animal common names
     * @param other     The Animal to compare this Animal to
     * @return          The comparison result between the two Animal's common names
     */
    public int compareTo(Object other) 
    {
        if(other instanceof Animal)
        {
            return ((Animal) other).getCommonName().compareTo(commonName);
        }
        throw new IllegalArgumentException("other must be of type Animal");
    }
}