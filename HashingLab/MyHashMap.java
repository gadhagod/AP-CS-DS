import java.util.*;

/**
 * MyHashMap acts like a map.
 * 
 * @author  
 * @version 
 * @param <K>   the type of key
 * @param <V>   the type of value
 */
public class MyHashMap<K, V> implements Map<K, V>
{
    private static final int NUM_BUCKETS = 5;
    private LinkedList<MapEntry<K, V>>[] buckets;
    private int size;

    /**
     * a constructor
     */
    public MyHashMap()
    {
        buckets = new LinkedList[NUM_BUCKETS];
    }
    
    /**
     * @param obj
     *            the object to find the bucket index for
     * @return the correct bucket index for that object
     */
    private int toBucketIndex(Object obj)
    {
        return Math.abs(obj.hashCode()) % NUM_BUCKETS;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

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


    public void putAll(Map<? extends K, ? extends V> m)
    {
        for (K key : m.keySet())
        {
            put(key, m.get(key));
        }
    }


    public void clear()
    {
        for (int i = 0; i < NUM_BUCKETS; i++)
        {
            buckets[i] = null;
        }
    }


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
}
