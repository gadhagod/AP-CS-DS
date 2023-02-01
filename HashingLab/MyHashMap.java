import java.util.*;

/**
 * MyHashMap is a hashing implementation
 * of the map data structure. It stores
 * key-value entries, and allows you to
 * access values given keys with O(1) order
 * complexity.
 * @author      Aarav Borthakur
 * @version     01/30/23
 */
public class MyHashMap<K, V> implements Map<K, V>
{
    private static final int NUM_BUCKETS = 5;
    private LinkedList<MapEntry<K, V>>[] buckets;
    private int size;

    /**
     * Constructs a MyHashMap object
     */
    public MyHashMap()
    {
        buckets = new LinkedList[NUM_BUCKETS];
    }
    
    /**
     * Gets the index of the bucket with an object's
     * hash code, by getting the absolute value of the
     * hash code, dividing by the number of buckets,
     * and finding that remainder.
     * @param obj	The object whose bucket index is
     * 				to be determined
     * @return		The index of the bucket
     */
    private int toBucketIndex(Object obj)
    {
        return Math.abs(obj.hashCode()) % NUM_BUCKETS;
    }

    /**
     * Gets the size of the MyHashMap.
     * @return		The size of the MyHasSet
     */
    public int size()
    {
        return size;
    }

    /**
     * Checks whether the MyHashMap has
     * any values stored in it.
     * @return      True if there are no
     *              values contained in 
     *              the MyHashMap, false
     *              otherwise
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * Checks whether a key exists in this
     * MyHashMap, by getting the bucket
     * index that corresponds to the key's hash
     * code and iterating through the LinkedList
     * rooted at that bucket until finding a 
     * MapEntry whose key equals the specified key.
     * The equals() method is used to test for key
     * equality.
     * @param key   They key to search for in the 
     *              MyHashMap
     * @return      True if the key exists in the 
     *              MyHashMap, false otherwise
     */
    @Override
    public boolean containsKey(Object key)
    {
        int index = toBucketIndex(key);
        if (buckets[index] == null)
        {
            return false;
        }
        for (MapEntry<K, V> entry : buckets[index])
        {
            if (entry.getKey().equals(key))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether a value is present in
     * this MyHashMap by iterating through 
     * each bucket and its values until finding
     * a MapEntry whose value equals the given
     * value to search for. Value equality is 
     * tested with the equals() method.
     * @param value The value to search for
     *              in the MyHashMap
     */
    public boolean containsValue(Object value)
    {
        for (LinkedList<MapEntry<K, V>> bucket : buckets)
        {
            if (bucket != null)
            {
                for (MapEntry<K, V> entry : bucket)
                {
                    if (entry.getValue().equals(value))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gets the value that corresponds to a specific
     * key in this MyHashMap, by getting the bucket
     * index that corresponds to the key's hash
     * code and iterating through the LinkedList
     * rooted at that bucket until finding a 
     * MapEntry whose key equals the specified key.
     * The equals() method is used to test for key
     * equality. Once the key is found, it's corresponding
     * value is returned.
     * @param key   The key of the value to look up
     * @return      The value that corresponds to the key,
     *              and null if the key is not found
     */
    public V get(Object key)
    {
        int index = toBucketIndex(key);
        if (buckets[index] == null)
        {
            return null;
        }
        for (MapEntry<K, V> entry : buckets[index])
        {
            if (entry.getKey().equals(key))
            {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Adds an item to the MyHashMap by getting 
     * the bucket index that corresponds to the 
     * key's hash code. If the bucket is null,
     * a new LinkedList rooted at that bucket 
     * is created. A new MapEntry with the given
     * key and value is added to the LinkedList
     * rooted at the correct bucket.
     * @param key     The key of the entry
     * @param value   The value of the entry
     * @return        null if the key wasn't
     *                in the MyHashMap before
     *                the execution of this method,
     *                otherwise returns the value
     *                formerly mapped to key
     * @postcondition A MapEntry with key key and
     *                value value is put into the
     *                MyHashMap.
     */
    public V put(K key, V value)
    {
        int index = toBucketIndex(key);
        if (buckets[index] == null)
        {
            buckets[index] = new LinkedList<MapEntry<K, V>>();
        }
        for (MapEntry<K, V> entry : buckets[index])
        {
            V previousVal = entry.getValue();
            if (entry.getKey().equals(key))
            {
                entry.setValue(value);
                return previousVal;
            }
        }
        buckets[index].add(new MapEntry<K, V>(key, value));
        size++;
        return null;
    }

    /**
     * Removes an entry from the MyHashMap, by getting 
     * the bucket index that corresponds to the key's 
     * hash code, and then iterating through the LinkedList
     * rooted at that bucket until an entry with a specified
     * key is found. If the key is found, the entry is removed.
     * @param key       The key of the entry to be removed
     * @return          The value of the entry that was removed,
     *                  if any entry was removed; else returns
     *                  null
     * @postcondition   The MapEntry with key key is not in
     *                  this MyHashMap.
     */
    public V remove(Object key)
    {
        int index = toBucketIndex(key);
        if (buckets[index] == null)
        {
            return null;
        }
        Iterator<MapEntry<K, V>> it = buckets[index].iterator();
        while (it.hasNext())
        {
            MapEntry<K, V> entry = it.next();
            if (entry.getKey().equals(key))
            {
                it.remove();
                size--;
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Copies all the MapEntry from a given Map
     * into this MyHashMap.
     * @param m         The Map to copy from
     * @postcondition   Entries from m are
     *                  copied into this 
     *                  MyHashSet, with     
     *                  conflicting keys
     *                  overrided by m. 
     */
    public void putAll(Map<? extends K, ? extends V> m)
    {
        for (K key : m.keySet())
        {
            put(key, m.get(key));
        }
    }

    /**
     * Removes all the entries of this MyHashMap.
     * @postcondition   This MyHashMap is empty
     */
    public void clear()
    {
        for (int i = 0; i < NUM_BUCKETS; i++)
        {
            buckets[i] = null;
        }
    }

    /**
     * Gets a Set of each key in this MyHashMap,
     * by iterating through each LinkedList of
     * MapEntries in each bucket.
     * @return  A Set containing all keys from 
     *          this MyHashSet
     */
    public Set<K> keySet()
    {
        Set<K> result = new HashSet<K>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets)
        {
            if (bucket != null)
            {
                for (MapEntry<K, V> entry : bucket)
                {
                    result.add(entry.getKey());
                }
            }
        }
        return result;
    }

    /**
     * Gets a Collection of the values in this
     * MyHashSet by iterating through each
     * LinkedList of MapEntries in each bucket.
     * @return  A Collection containing every
     *          value in the MyHashSets
     */
    public Collection<V> values()
    {
        Collection<V> result = new ArrayList<V>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets)
        {
            if (bucket != null)
            {
                for (MapEntry<K, V> entry : bucket)
                {
                    result.add(entry.getValue());
                }
            }
        }
        return result;
    }

    @Override
    /**
     * Gets a Set of entries present in this MyHashSet,
     * by iterating through each LinkedList rooted
     * at each bucket and adding them to a Set to
     * be returned.
     * @return      A set containing the map entries
     *              from this MyHashSet
     */
    public Set<java.util.Map.Entry<K, V>> entrySet()
    {
        Set<java.util.Map.Entry<K, V>> result = new HashSet<java.util.Map.Entry<K, V>>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets)
        {
            if (bucket != null)
            {
                for (MapEntry<K, V> entry : bucket)
                {
                    result.add(entry);
                }
            }
        }
        return result;
    }

    /**
     * Gets the String representation of the MyHashMap
     * iteratively
     * @return  The string representation of the 
     * 			MyHashSet (e.g "{Aarav=650,
     * 			John=480}")
     */
    public String toString()
    {
        String result = "{";
        for (LinkedList<MapEntry<K, V>> bucket : buckets)
        {
            if (bucket != null)
            {
                for (MapEntry<K, V> entry : bucket)
                {
                    result += entry.getKey() + "=" + entry.getValue() + ", ";
                }
            }
        }
        if (result.length() - 2 > -1)
        {
            result = result.substring(0, result.length() - 2);
        }
        result += "}";
        return result;
    }
}
