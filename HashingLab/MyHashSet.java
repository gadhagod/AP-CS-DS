import java.util.*;
/**
 * MyHashSet is a set of unique values. It allows for
 * access and removal in O(1) order complexity.
 * This implementation of the set data structure uses 
 * a bucketed hash table as its underlying data structure,
 * to handle collisions well.
 * @arthor		Aarav Borthakur
 * @version		01/30/23
 */
public class MyHashSet<E>
{
    private static final int NUM_BUCKETS = 5;
    private LinkedList<E>[] buckets;
    private int size;
    
    /**
     * Constructs an empty MyHashSet with 5 buckets.
     */
    public MyHashSet()
    {
        buckets = new LinkedList[NUM_BUCKETS];
        size = 0;
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
     * Gets the size of the MyHashSet.
     * @return		The size of the MyHasSet
     */
    public int size()
    {
        return size;
    }

    /**
     * Checks whether an object is in the MyHashSet, by
     * searching in it's corresponding bucket for the
     * object. obj's hashCode function is used to determine
     * which bucket to search in. It then traverses the
     * LinkedList rooted at the bucket, searching for a
     * value equal to obj, using the equals() method. 
     * @param obj	The object to check if it's in the 
     * 				MyHashSet.
     * @return		Whether the obj is in the MyHashSet
     */
    public boolean contains(Object obj)
    {
        int index = toBucketIndex(obj);
        LinkedList<E> bucket = buckets[index];
        if (bucket == null)
        {
            return false;
        }
        for (E item : bucket)
        {
            if (item.equals(obj))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an item to the MyHashSet, by getting
     * the bucket to place the item in and adding it to
     * the LinkedList rooted there (and creating one if
     * not already present).
     * @param obj		The object to add to the MyHashSet
     * @return			Whether the MyHashSet was modified
     * 					as a result of this method
     * @postcondition	obj is added to the MyHashSet if
     * 					it wasn't already present. Checks
     * 					if it was already present with 
     * 					the equals() method
     */
    public boolean add(E obj)
    {
        int index = toBucketIndex(obj);
        if (buckets[index] == null)
        {
            buckets[index] = new LinkedList<E>();
        }
        for (E item : buckets[index])
        {
            if (item.equals(obj))
            {
                return false;
            }
        }
        size++;
        buckets[index].addLast(obj);
        return true;
    }

    /**
     * Removes an item from the MyHashSet, by getting
     * the bucket obj corresponds to and traversing the 
     * LinkedList rooted there until an object that 
     * equals obj is found (checked with the equals()
     * method).  
     * @param obj		The object to remove from the 
     * 					MyHashSet
     * @return			Whether the MyHashSet was modified
     * 					as a result of this method
     * @postcondition	obj is removed to the MyHashSet if
     * 					it was present. Checks if it was 
     * 					present with the equals() method
     */
    public boolean remove(Object obj)
    {
        int index = toBucketIndex(obj);
        if (buckets[index] == null)
        {
            return false;
        }
        Iterator<E> it = buckets[index].iterator();
        while (it.hasNext())
        {
            E curr = it.next();
            if (curr.equals((E) obj))
            {
                it.remove();
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the String representation of the MyHashSet,
     * also showing which buckets the values are in.
     * @return	The string representation of the 
     * 			set (e.g. "0:[1x1, 3x3, 2x2] 
     * 			1:[2x1, 4x3] 2:[3x1] 4:[2x3]")
     */
    public String toString()
    {
        String s = "";
        for (int i = 0; i < buckets.length; i++)
            if (buckets[i] != null && buckets[i].size() > 0)
                s += i + ":" + buckets[i] + " ";
        return s;
    }

    /**
     * Gets an iterator of the MyHashSet.
     * @return	An Iterator of the MyHashSet
     */
    public Iterator<E> iterator()
    {
        return new MyHashSetIterator<E>();
    }

    /**
     * Searches for the next non-empty bucket.
     * @param start		The index of the bucket
     * 					to start searching from
     * @return			The index of the first
     * 					non-empty bucket since
     * 					start (including start
     * 					itself). Returns -1
     * 					if remaining the buckets
     * 					are empty.
     */
    private int findBucketIndex(int start)
    {
        if (start == NUM_BUCKETS)
        {
            return -1;
        }
        if (buckets[start] == null)
        {
            return findBucketIndex(start + 1);
        }
        return start;
    }

    /**
     * The iterator over the MyHashSet. All methods run
     * with O(1) order complexity.
     */
    public class MyHashSetIterator<E> implements Iterator<E> {
        private E lastAccessed;
        private int bucketIndex;
        private Iterator<E> bucketIt;

        /**
         * Constructs a MyHashSetIterator by looking
         * for the first value in the MyHashSet.
         */
        public MyHashSetIterator() {
            bucketIndex = findBucketIndex(bucketIndex);
            if (bucketIndex == -1)
            {
                bucketIt = (new ArrayList()).iterator(); // create empty iterator
            }
            else
            {
                bucketIt = (Iterator<E>) buckets[bucketIndex].iterator();
            }
        }

        /**
         * Gets the next value in the iterator.
         * @return	The next value in the iterator
         */
        public E next()
        {
            if (bucketIt.hasNext())
            {
                lastAccessed = bucketIt.next();
                return lastAccessed;
            }
            bucketIndex = findBucketIndex(bucketIndex + 1);
            bucketIt = (Iterator<E>) buckets[bucketIndex].iterator();
            return next();
        }

        /**
         * Checks whether the iterator has not iterated
         * over all the values in the MyHashSet
         * @return		True if there are still values to
         * 				be accessed, false otherwise
         */
        public boolean hasNext()
        {
            if (bucketIt.hasNext())
            {
                return true;
            }
            return findBucketIndex(bucketIndex + 1) != -1;
        }

        /**
         * Removes the value last accessed from this iterator
         * @postcondition	The last accessed value is removed
         */
        public void remove()
        {
            buckets[bucketIndex].remove(lastAccessed);
        }
    }
}