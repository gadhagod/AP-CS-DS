import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
 * My implementation of an ArrayList
 * @author Aarav Borthakur
 * @version Oct 22, 2022
 */
public class MyArrayList<E>
{
	private int size;
	private Object[] values; 
	private int modified; // Whether elements have been added/removed

	/*
	 * Creates a MyArrayList
	 */
	public MyArrayList()
	{
		size = 0;
		values = new Object[1];
	}

	/**
	 * Gets a string representation of this MyArrayList
	 * @return	The string representation of this list
	 */
	public String toString()
	{
		if (size == 0)
			return "[]";

		String s = "[";
		for (int i = 0; i < size - 1; i++)
			s += values[i] + ", ";
		return s + values[size - 1] + "]";
	}

	/**
	* Doubles the capapacity of the list
	* @postcondition replaces the array with one that is
	*               twice as long, and copies all of the
	*               old elements into it
	*/
	private void doubleCapacity()
	{
		Object[] newValues = new Object[values.length  * 2];
		for (int i = 0; i < values.length; i++)
		{
			newValues[i] = values[i];
		}
		values = newValues;
	}

	/**
	* Gets the capacity of this list
	* @return 		 The capacity of the list
	* @postcondition returns the length of the array
	*/
	public int getCapacity()
	{
		return values.length;
	}

	/**
	 * Gets the size of the list
	 * @return 		  The current size of the list
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Gets the value in the list at an index
	 * @param index		The index of the item to be retrieved
	 * @return 			The value at index
	 */
	public E get(int index)
	{
		return (E) values[index];
	}

	/** 
	* Replaces an element at a position
	* @param index		The index at which to place the replacement
	* @param obj		The item to place into the list
	* @return 			The previous value at index
	* @postcondition replaces the element at position index with obj
	*               returns the element formerly at the specified position
	*/
	public E set(int index, E obj)
	{
		modified++;
		Object prev = values[index];
		values[index] = obj;
		return (E) prev;
	}

	/**
	* Adds an item at an index 
	* @param obj 	 Adds an item to the end of the list
	* @return 		 true
	* @postcondition appends obj to end of list; returns true
	*/
	public boolean add(E obj)
	{
		modified++;
        add(size, obj);
        return true;
	}

	/**
	* Removed an item from the list given an index
	* @param index	 The index of the item to be removed
	* @return		 The object that was removed
	* @postcondition removes element from position index, moving elements
	*               at position index + 1 and higher to the left
	*               (subtracts 1 from their indices) and adjusts size
	*               returns the element formerly at the specified position
	*/
	public E remove(int index)
	{
		modified++;
		Object temp = values[index];
        for(int i = index + 1; i < size; i++) 
		{
            values[i - 1] = values[i];
        }
        size--;
        return (E) temp;
	}

	/**
	 * Gets an iterator for the list
	 * @return 		A unidirectional iterator that can remove values
	 */
	public Iterator<E> iterator()
	{
		return new MyArrayListIterator();
	}

	/**
	 * Gets a listIterator for the list
	 * @return		A bidirectional iterator that can add and remove elements
	 */
	public ListIterator<E> listIterator()
	{
		return new MyArrayListListIterator();
	}

	/**
	* Adds an item at index obj
	* @param index	 The index at which to add obj
	* @param obj	 The object ot be added
	* @precondition  0 <= index <= size
	* @postcondition inserts obj at position index,
	*               moving elements at position index and higher
	*               to the right (adds 1 to their indices) and adjusts size
	*/
	public void add(int index, E obj)
	{
		modified++;
		if(size >= getCapacity()) {
            doubleCapacity();
        }
        for(int i = size - 1; i >= index; i--) {
            values[i+1]=values[i];
        }
        values[index] = obj;
        size++;
	}

	/**
	 * MyArrayListIterator is a unidirectional iterator that can remove values
	 * @author Aarav Borthakur
	 * @version October 22, 2022 
	 */
	private class MyArrayListIterator implements Iterator<E>
	{
		//the index of the value that will be returned by next()
		private int nextIndex;
		private int itModified;

		/**
		 * Creates a MyArrayListIterator
		 */
		public MyArrayListIterator()
		{
			nextIndex = 0;
			itModified = modified;
		}
		/**
		 * Checks whether there is a next value in the list
		 * @return 		Whether there is a next value in the list
		 */
		public boolean hasNext()
		{
			return nextIndex < size;
		}

		/**
		 * Gets the next item in the list 
		 * @return 		  The next value in the list
		 * @postcondition Moves the index up by one
		 */
		public E next()
		{
			if(itModified != modified) {
                throw new ConcurrentModificationException("MyArrayList was modified outside of the iterator");
            }
            if(hasNext()) {
                nextIndex++;
                return (E) values[nextIndex-1];
            }
            throw new NoSuchElementException("Iterator is at end of list");
		}

		/*
		 * Removes the last accessed value from the list
		 * @postcondition removes the last element that was returned by next
		 */
		public void remove()
		{
			if (nextIndex == 0) {
                throw new IllegalStateException("next hasn't been called");
            }
            for (int i = nextIndex; i < size; i++) {
                values[i - 1] = values[i];
            }
            size--;
		}
	}

	/**
	 * MyArrayListListIterator is a bidirectional iterator that can add and remove elements
	 * @author Aarav Borthakur
	 * @version October 22, 2022 
	 */
	private class MyArrayListListIterator extends MyArrayListIterator implements ListIterator<E>
	{
		private int nextIndex;
		private int previousIndex;// Index of element that will be returned by the next call of next()
		private boolean forward; //direction of traversal
		private int itModified;

		/**
		 * Constructs a new MyArrayListListIterator
		 */
		public MyArrayListListIterator()
		{
			nextIndex = 0;
			previousIndex = -1;
			forward = true;
			itModified = modified;
		}

		/**
		 * Checks if the next index is in the list
		 * @return 		whether the next index is in the list
		 */
		public boolean hasNext()
		{
			return nextIndex < size;
		}
		
		/**
		 * 
		 * Returns next element in the list
		 * @postcondition 	moves pointer forward
		 * @return 			next Object in the myArrayList
		 */
		public E next()
		{
			if(itModified != modified) {
				throw new ConcurrentModificationException("MyArrayList was modified outside of the iterator");
			}
			
			if(hasNext()) {
				nextIndex++;
				previousIndex++;
				return (E) values[nextIndex - 1];
			}
			throw new NoSuchElementException("Iterator is at end of list");
		}

		/**
		 * Adds element before element that would be returned by next
		 * @param element to add
		 * @postcondition an element is added to the list
		 */
		public void add(E obj)
		{
			if (size == values.length)
			{
				doubleCapacity();
			}
			for (int i = size - 1; i >= nextIndex; i--)
			{
				values[i + 1] = values[i];
			}
			values[nextIndex] = obj;
			size++;
		}

		/**
		 * Determines whether there is another element in MyArrayList
		 * while traversing in the backward direction
		 * @return true if there is another element, false otherwise
		 */
		public boolean hasPrevious()
		{
			return previousIndex >= 0;
		}

		/**
		 * Retrieves the previous item in the list
		 * @return 		The previous item in the list
		 */
		public E previous()
		{
			if(hasPrevious()) {
                nextIndex--;
                previousIndex--;
                return (E) values[previousIndex + 1];
            }
            throw new NoSuchElementException("Iterator has reached beginning of list");
		}

		/**
		 * Returns index of the next element 
		 * @return index of element that would be 
		 *         returned by a call to next()
		 */
		public int nextIndex()
		{
			return nextIndex;
		}

		/**
		 * Gets the index of the previous element
		 * @return		The index of the previous index
		 */
		public int previousIndex()
		{
			return previousIndex;
		}

		/**
		 * Removes element that was returned by next() pr previous()
		 * USE direction FOR THIS
		 * @postcondition		an item is removed from the list
		 */
		public void remove()
		{
			if (nextIndex == 0)
			{
				throw new IllegalStateException("No next() has been called");
			}
			for (int i = nextIndex; i < size; i++)
			{
				values[i - 1] = values[i];
			}
			size--;
		}

		/**
		 * Sets the next item 
		 * @param obj		The object to set the next to
		 * @postcondition	The next item is set to obj
		 */
		public void set(E obj)
		{
            values[nextIndex - 1] = obj;
		}
	}
}