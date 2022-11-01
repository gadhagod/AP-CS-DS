/**
 * DoubleNode represents a node of a doubly linked list
 * @version Oct 22, 2022
 * @author Aarav Borthakur
*/
public class DoubleNode
{
    private Object value;
    private DoubleNode previous;
    private DoubleNode next;
    /**
     * Creates a DoubleNode object
     * @param v     The value of the node stores
     */
    public DoubleNode(Object v)
    {
        value = v;
        previous = null;
        next = null;
    }
    /**
     * Gets the value stored in the node
     * @return      The value stored in the node
     */
    public Object getValue()
    {
        return value;
    }
    /**
     * Gets the previous node
     * @return      the previous node
     */
    public DoubleNode getPrevious()
    {
        return previous;
    }
    /**
     * Gets the next node in the list
     * @return      the next node
     */
    public DoubleNode getNext()
    {
        return next;
    }
    /**
     * Sets the value of the current node
     * @param v     the new value of the node
     * @postcondition the next node is set to v
     */
    public void setValue(Object v)
    {
        value = v;
    }
    /**
     * Sets the previous node
     * @param p     the reference to the previous node
     * @postcondition the previous node is set to p
     */
    public void setPrevious(DoubleNode p)
    {
        previous = p;
    }
    /**
     * Sets the next node in the list
     * @param n     the reference to the next node
     * @postcondition the next node is set to n
     */
    public void setNext(DoubleNode n)
    {
        next = n;
    }
}