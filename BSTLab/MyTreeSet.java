import java.util.ArrayList;
import java.util.Iterator;

/**
 * MyTreeSet represents a set that is sorted in ascending order.
 * There is only one of each element present in the set.
 * It uses a binary search tree as its underlying data structure.
 * @author 		Aarav Borthakur
 * @version 	Jan 20, 2022
 */
public class MyTreeSet<E>
{
    private TreeNode root;
    private int size;
    private TreeDisplay display;

    /**
     * Constructs a MyTreeSet.
     */
    public MyTreeSet()
    {
        root = null;
        display = new TreeDisplay();
    }

    /**
     * Displays the BST behind the Set.
     */
    public void showTree()
    {
        display.displayTree(root);
    }

    /**
     * Gets the size of the MyTreeSet
     * by accessing its instance variable
     * size.
     * @return		The size of the set
     */
    public int size()
    {
        return size;
    }

    /**
     * Checks whether a certain object is 
     * in the set by walking down the BST
     * to where the object should be located
     * (left of node if obj is lesser, right
     * of node if obj is greater). 
     * @param obj		The object to search
     * 					for in the set
     * @return			Whether obj exists
     * 					in this set
     */
    public boolean contains(Object obj)
    {
        return BSTUtilities.contains(root, (Comparable) obj, display);
    }

    /**
     * Adds an object to the MyTreeSet
     * @param obj		The object to add to the set
     * @postcondition	obj is added to the set
     * @return 			true if the set was changed, false otherwise
     */
    public boolean add(E obj)
    {
        if (contains(obj))
        {
            return false;
        }
        root = BSTUtilities.insert(root, (Comparable) obj, display);
        size++;
        return true;
    }

    /**
     * Removes an object from the MyTreeSet
     * @param obj		The object to remove from the set
     * @postcondition	obj is removed from the set
     * @return 			true if the set was changed, false otherwise
     */
    public boolean remove(Object obj)
    {
        if (contains(obj))
        {	
            size--;
            root = BSTUtilities.delete(root, (Comparable) obj, display);
            return true;
        }
        return false;
    }

    /**
     * Gets the string representation of the set
     * @return 		the string representation of the set
     */
    public String toString()
    {
        return toString(root);
    }

    /**
     * Gets the string representation of the set rooted
     * at node t
     * @return		the string representation of the set
     * 				rooted at t
     */
    private String toString(TreeNode t)
    {
        String result = "[";
        Iterator<E> it = iterator();
        while (it.hasNext())
        {
            result += it.next() + ", ";
        }
        if (result.equals("["))
        {
            return result + "]";
        }
        return result.substring(0, result.length() - 2) + "]";
    }

    /**
     * Gets the iterator for the set 
     * @return		the Iterator for the set
     */
    public Iterator<E> iterator()
    {
        return new MyTreeSetIterator();
    }

    /**
     * MyTreeSetIterator is the iterator over the MyTreeSet
     */
    public class MyTreeSetIterator implements Iterator<E>
    {
        private ArrayList<E> list;
        private E val;
        private Iterator it;

        /**
         * Creates a MyTreeSetIterator object
         */
        public MyTreeSetIterator()
        {
            list = new ArrayList<E>();
            populateList(root);
            it = list.iterator();
        }

        /**
         * Fills the list instance variable with 
         * the items from the BST
         * @param t			The root of the BST
         * @postcondition	list has the values
         * 					from the BST
         */
        private void populateList(TreeNode t)
        {
            if (t != null)
            {
                populateList(t.getLeft());
                list.add((E) t.getValue());
                populateList(t.getRight());
            }
        }

        /**
         * Gets the next item in the set
         * @return 			the next item in the set
         * @postcondition	the index of this iterator is incremented
         * 					by one
         */
        public E next()
        {
            val = (E) it.next();
            return val;
        }

        /**
         * Checks whether there is a next value
         * with this iterator
         * @return 			whether there is a next value
         */
        public boolean hasNext()
        {
            return it.hasNext();
        }

        /**
         * Removes the last accessed value with this instance
         * of the iterator
         * @postcondition		val is removed form the set
         */
        public void remove()
        {
            MyTreeSet.this.remove(val);
            it.remove();
        }
    }
}