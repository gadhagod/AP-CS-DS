import java.util.NoSuchElementException;

/**
 * Queue implementation with list nodes
 */
public class NodeQueue<E> {
    private ListNode<E> first;
    private ListNode<E> last;

    /**
     * Adds an Object to the the end of queue
     * @param obj       The Object to be added
     * @return          true
     * @postcondition   obj is at the end of the queue
     */
    public boolean add(E obj)
    {
        ListNode<E> newNode = new ListNode<E>(obj, null);
        if (first == null)
        {
            first = newNode;
        }
        else
        {
            last.setNext(newNode);
        }
        last = newNode;
        return true;
    }

    /**
     * Removes the Object at the front of the queue
     * @return          The Object that was removed
     * @postcondition   The Object at the front of the Queue is removed
     */
    public E remove()
    {
        if (first == null)
        {
            throw new NoSuchElementException();
        }
        E temp = first.getValue();
        first = first.getNext();
        return temp;
    }

    /**
     * Gets the value at the front of the queue
     * @return          The Object at the front of the Queue
     */
    public E peek()
    {
        if (first == null)
        {
            throw new NoSuchElementException();
        }
        return first.getValue();
    }

    /**
     * Checks whether the queue is empty
     * @return          Whether the queue is empty
     */
    public boolean isEmpty()
    {
        return first == null;
    }

    /**
     * Gets a String representation of the queue
     * @return          The string representation of the Queue
     */
    public String toString()
    {
        String result = "[";
        NodeQueue<E> temp = new NodeQueue<E>();
        while (!isEmpty())
        {
            E current = remove();
            temp.add(current);
            result += current + ", ";
        }
        while (!temp.isEmpty())
        {
            E current = temp.remove();
            add(current);
        }
        if (result.indexOf(",") != -1)
        {
            result = result.substring(0, result.length() - 2);
        }
        return result + "]";
    }
}