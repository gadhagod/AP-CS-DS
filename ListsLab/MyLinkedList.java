import java.util.Iterator;

public class MyLinkedList<E>
{
	private DoubleNode first;
	private DoubleNode last;
	private int size;

	/*
	 * Creates a MyLinkedList
	 */
	public MyLinkedList()
	{
		first = null;
		last = null;
		size = 0;
	}

	/*
	 * Gets a string representation of the list
	 * @return		The string representation
	 */
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
	* Gets a value by traversing nodes from the start of list
	* @param index 		The index of the desired value
	* @precondition  0 <= index <= size / 2
	* @postcondition starting from first, returns the node
	*               with given index (where index 0
	* 				returns first)
	*/               
	private DoubleNode getNodeFromFirst(int index)
	{
		DoubleNode curr = first;
        for (int i = 0; i < index; i++)
        {
            curr = curr.getNext();
        }
        return curr;
	}

	/** 
	* Gets a value by traversing nodes from the end of list
	* @param index 		The index of the desired value
	* @precondition  size / 2 <= index < size
	* @postcondition starting from last, returns the node
	*               with given index (where index size-1
					returns last)
	*/               
	private DoubleNode getNodeFromLast(int index)
	{
		DoubleNode curr = last;
        for (int i = size - 1; i > index; i--)
        {
            curr = curr.getPrevious();
        }
        return curr;
	}

	/** 
	* Gets the node at a given index
	* @param index 		The index of the desired node
	* @precondition  0 <= index < size
	* @postcondition starting from first or last (whichever
	*               is closer), returns the node with given
	* 				index
	*/               
	private DoubleNode getNode(int index)
	{
		if (index > size / 2) 
        { 
            return getNodeFromLast(index);
        }
        return getNodeFromFirst(index);
	}

	/*
	 * Gets the size of the list
	 * @return 		The size of the list
	 */
	public int size()
	{
		return size;
	}

	/*
	 * Gets the item at position index
	 * @return 			the value at given index
	 * @precondition 	index is within bounds of the list
	 */
	public E get(int index)
	{
		return (E) this.getNode(index).getValue();
	}

	/** 
	* Replaces the element at position index with obj
	* @precondition: index is within bounds of the MyLinkedList
	* @postcondition replaces the element at position index with obj
    * @return the element formerly at the specified position
	*/
	public E set(int index, E obj)
	{
		DoubleNode node = this.getNode(index);
        Object temp = node.getValue();
        node.setValue(obj);
        return (E) temp;
	}

	/*
	 * Appends obj to the end of the list
	 * @return 		  true
	 * @postcondition obj is added to end of list
	 */
	public boolean add(E obj)
	{
		this.add(size, obj);
		return true;
	}

	/** 
	* Removes element from position index, moving elements at 
	* position index + 1 and higher to the left (subtracts 1 from their indices) 
	* and adjusts size
	* @precondition: index is within bounds of the MyLinkedList
	* @postcondition removes element from position index, moving elements
	*               at position index + 1 and higher to the left
	*               (subtracts 1 from their indices) and adjusts size
	* @return the element formerly at the specified position
	*/
	public E remove(int index)
	{
		DoubleNode node = this.getNode(index);
        Object formerVal = node.getValue();
        if (first == node && node == last)
        {
			first = null;
            last = null;
        }
        else if (first == node)
        {
			node.getNext().setPrevious(null);
            first = first.getNext();
        }
        else if (last == node)
        {
            node.getPrevious().setNext(null);
            last = last.getPrevious();
        }
		else
		{
			node.getPrevious().setNext(node.getNext());
			node.getNext().setPrevious(node.getPrevious());
		}
		size--;
        return (E) formerVal;
	}

	/** 
	* Adds an object to a given index in the list
	* @param index	 The index at which to add the item
	* @param obj	 The object to insert at the list
	* @precondition  0 <= index <= size
	* @postcondition inserts obj at position index,
	*                moving elements at position index and higher
	*                to the right (adds 1 to their indices) and adjusts size
	*/
	public void add(int index, E obj)
	{
		if (index == 0) 
        {
			this.addFirst(obj);
        }
        else if (index == size) 
        {
			this.addLast(obj);
        }
        else 
        {
            DoubleNode nextNode = getNode(index);
            DoubleNode previousNode = nextNode.getPrevious();
            DoubleNode addedNode = new DoubleNode(obj);
            nextNode.setPrevious(addedNode);
            addedNode.setNext(nextNode);
            addedNode.setPrevious(previousNode);
            previousNode.setNext(addedNode);
            size++;
        }
	}

	/*
	 * Adds an object to the start of the list
	 * @param obj		the value the first item in the list is to be
	 * @return 			the address of the object removed
	 * @postcondition 	obj added to start of list, other items shifted right
	 */
	public void addFirst(E obj)
	{
		size++;
		DoubleNode added = new DoubleNode(obj);
		if (first == null)
		{
			first = added;
			last = added;

		}
		else
		{
			added.setNext(first);
			first.setPrevious(added);
			first = added;
		}
	}

	/*
	 * Adds an object to the end of the list
	 * @param obj		the value the end item in the list is to be
	 * @return 			the address of the object removed
	 * @postcondition 	obj added to end of list
	 */
	public void addLast(E obj)
	{
		size++;
		DoubleNode added = new DoubleNode(obj);
		if (last == null)
		{
			first = added;
			last = added;
		}
		else
		{
			last.setNext(added);
			added.setPrevious(last);
			last = added;
		}
	}

	/**
	 * Gets the first item in the list
	 * @return 		the first item in the list
	 */
	public E getFirst()
	{
		return (E) first.getValue();
	}

	/**
	 * Gets the last item in the list
	 * @return 		the last item in the list
	 */
	public E getLast()
	{
		return (E) last.getValue();
	}

	/**
	 * Removes the first item in the list
	 * @return 			The value formerly at the start of the list
	 * @postcondition	The first item in the list is removed
	 */
	public E removeFirst()
	{
		size--;
		if (size > 1)
		{
			Object temp = first.getValue();
			first = first.getNext();
			first.setPrevious(null);
			return (E) temp;
		}
		else if (size == 1)
		{
			Object temp = first.getValue();
			first = null;
			last = null;
			return (E) temp;
		}
		else
		{
			throw new IndexOutOfBoundsException("No elements to remove");
		}
	}

	/**
	 * Removes the last element in the list
	 * @postcondition 	last item removed
	 * @return 			the address of the removed object 
	 */
	public E removeLast()
	{
		size--;
		if (size > 1)
		{
			Object temp = last.getValue();
			last = last.getPrevious();
			last.setNext(null);
			return (E) temp;
		}
		else if (size == 1)
		{
			Object temp = last.getValue();
			first = null;
			last = null;
			return (E) temp;
		}
		else
		{
			throw new IndexOutOfBoundsException("No elements to remove");
		}
	}

	/**
	 * Gets an iterator for the list
	 */
	public Iterator<E> iterator()
	{
		return new MyLinkedListIterator();
	}

	/**
	 * MyArrayListIterator is a unidirectional iterator that can remove values
	 * @author Aarav Borthakur
	 * @version October 22, 2022 
	 */
	private class MyLinkedListIterator implements Iterator<E>
	{
		private DoubleNode nextNode;

		/**
		 * Constructs a MyLinkedListIterator object
		 */
		public MyLinkedListIterator()
		{
			nextNode = first;
		}

		/**
		 * Checks whether there is a next value in the list
		 * @return 		Whether there is a next value in the list
		 */
		public boolean hasNext()
		{
			return nextNode != null;
		}

		/**
		 * Gets the next item in the list 
		 * @return 		  The next value in the list
		 * @postcondition Moves the index up by one
		 */
		public E next()
		{
			DoubleNode temp = nextNode;
            nextNode = nextNode.getNext();
            return (E) temp.getValue();
		}

		/*
		 * Removes the last accessed value from the list
		 * @postcondition removes the last element that was returned by next
		 */
		public void remove()
		{
			if(size == 0)
			{
				first = null;
				last = null;
				return;
			} 
			DoubleNode currentNode = nextNode.getPrevious();
			if(last == currentNode)
			{
				last = nextNode.getPrevious();
			} 
			else 
			{
				nextNode.setPrevious(currentNode.getPrevious());
			}
			if(first == currentNode)
			{
				first = nextNode;
			} 
			else 
			{
				currentNode.getPrevious().setNext(nextNode);
			}
			size--;
		}
	}
}