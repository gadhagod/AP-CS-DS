import java.util.Iterator;
import java.util.ListIterator;

public class MyLinkedList<E>
{
	private DoubleNode first;
	private DoubleNode last;
	private int size;

	public MyLinkedList()
	{
		first = null;
		last = null;
		size = 0;
	}

	public String toString()
	{
		DoubleNode node = first;
		if (node == null)
			return "[]";
		String s = "[";
		while (node.getNext() != null)
		{
			s += node.getValue() + ", ";
			node = node.getNext();
		}
		return s + node.getValue() + "]";
	}

	/** 
	* @precondition  0 <= index <= size / 2
	* @postcondition starting from first, returns the node
	*               with given index (where index 0
	*/               returns first)
	private DoubleNode getNodeFromFirst(int index)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	/** 
	* @precondition  size / 2 <= index < size
	* @postcondition starting from last, returns the node
	*               with given index (where index size-1
	*/               returns last)
	private DoubleNode getNodeFromLast(int index)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	/** 
	* @precondition  0 <= index < size
	* @postcondition starting from first or last (whichever
	*               is closer), returns the node with given
	*/               index
	private DoubleNode getNode(int index)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	public int size()
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	public E get(int index)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");

		//(You will need to promise the return value is of type E.)
	}

	/** 
	* @postcondition replaces the element at position index with obj
	               returns the element formerly at the specified position
	*/
	public E set(int index, E obj)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");

		//(You will need to promise the return value is of type E.)
	}

	/**
	* @postcondition appends obj to end of list; returns true
	*/
	public boolean add(E obj)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	/** 
	* @postcondition removes element from position index, moving elements
	*               at position index + 1 and higher to the left
	*               (subtracts 1 from their indices) and adjusts size
	               returns the element formerly at the specified position
	*/
	public E remove(int index)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");

		//(You will need to promise the return value is of type E.)
	}

	/** 
	* @precondition  0 <= index <= size
	* @postcondition inserts obj at position index,
	*                moving elements at position index and higher
	*                to the right (adds 1 to their indices) and adjusts size
	*/
	public void add(int index, E obj)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	public void addFirst(E obj)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	public void addLast(E obj)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	public E getFirst()
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");

		//(You will need to promise the return value is of type E.)
	}

	public E getLast()
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");

		//(You will need to promise the return value is of type E.)
	}

	public E removeFirst()
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");

		//(You will need to promise the return value is of type E.)
	}

	public E removeLast()
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");

		//(You will need to promise the return value is of type E.)
	}

	public Iterator<E> iterator()
	{
		return new MyLinkedListIterator();
	}

	private class MyLinkedListIterator implements Iterator<E>
	{
		private DoubleNode nextNode;

		public MyLinkedListIterator()
		{
			throw new RuntimeException("INSERT MISSING CODE HERE");
		}

		public boolean hasNext()
		{
			throw new RuntimeException("INSERT MISSING CODE HERE");
		}

		public E next()
		{
			throw new RuntimeException("INSERT MISSING CODE HERE");

			//(You will need to promise the return value is of type E.)
		}

		//@postcondition removes the last element that was returned by next
		public void remove()
		{
			throw new RuntimeException("INSERT MISSING CODE HERE");
		}
	}
}