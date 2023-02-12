import java.util.Map.Entry;

/**
 * MapEntry represents a single
 * entry in a map consisting of
 * a key and a value
 * @author joelmanning
 * @version 1.0
 */
public class MapEntry<K, V> implements Entry<K, V>
{
    private K key;
    private V value;

    /**
     * Constructs a MapEntry given its key and
     * the value they key maps to
     * @param key       the key for the entry
     * @param value     the initial value for the entry
     */
    public MapEntry(K key, V value)
    {
        super();
        this.key = key;
        this.value = value;
    }

    /**
     * Gets the key of this MapEntry
     * @return      the key in the entry
     */
    public K getKey()
    {
        return key;
    }

    /**
     * Sets the key of this MapEntry
     * @param key   the key to set this entry's key to
     */
    public void setKey(K key)
    {
        this.key = key;
    }

    /**
     * Gets the value of this MapEntry
     * @return the value of the entry
     */
    public V getValue()
    {
        return value;
    }

    /**
     * Sets the value of this MapEntry
     * @return      the previous value in the entry
     * @param val   the value to set this entry's value to
     */
    public V setValue(V val)
    {
        V past = value;
        value = val;
        return past;
    }

    /**
     * Checks whether another MapEntry is equal to this
     * MapEntry by testing if their keys are equal with
     * the key's equals() method
     * @param me    the MapEntry to check if this is equal to
     * @return      whether it is equal to that MapEntry
     */
    public boolean equals(MapEntry me)
    {
        return key.equals(me.getKey());
    }
}