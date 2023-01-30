import java.util.*;
/**
* comment this class completely, and in accordance with the style guide.
*/

public class MyHashSet<E>
{
	private static final int NUM_BUCKETS = 5;
	private LinkedList<E>[] buckets;
	private int size;
	
	public MyHashSet()
	{
		buckets = new LinkedList[NUM_BUCKETS];
		size = 0;
	}

	private int toBucketIndex(Object obj)
	{
		return Math.abs(obj.hashCode()) % NUM_BUCKETS;
	}

	public int size()
	{
		return size;
	}

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

	// if obj is not present in this set, adds obj and
	// returns true; otherwise returns false
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

	// if obj is present in this set, removes obj and
	// returns true; otherwise returns false
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

	public String toString()
	{
		String s = "";
		for (int i = 0; i < buckets.length; i++)
			if (buckets[i] != null && buckets[i].size() > 0)
				s += i + ":" + buckets[i] + " ";
		return s;
	}

	public Iterator <E> iterator()
	{
		return new MyHashSetIterator<E>();
	}

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

	public class MyHashSetIterator<E> implements Iterator<E> {
		private E lastAccessed;
		/*private*/ public int bucketIndex;
		/*private*/ public Iterator<E> bucketIt;

		public MyHashSetIterator() {
			bucketIndex = findBucketIndex(bucketIndex);
			bucketIt = (Iterator<E>) buckets[bucketIndex].iterator();
		}

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

		public boolean hasNext()
		{
			if (bucketIt.hasNext())
			{
				return true;
			}
			return findBucketIndex(bucketIndex + 1) != -1;
		}

		public void remove()
		{
			System.out.println("decrementing the size");
			buckets[bucketIndex].remove(lastAccessed);
		}
	}
}