/**
 * Executes the following operations on binary search trees:
 * contains
 * insert
 * delete
 * @author 	Aarav Borthakur
 * @version	12/02/22
 */
public abstract class BSTUtilities
{
	/**
	 * Compares to objects
	 * @param		first  The first object
	 * @param		second The second object
	 * @return			   Whether first is greater than second
	 */
	private static boolean isGreater(Comparable first, Object second)
	{
		return first.compareTo(second) > 0;
	}
	/**
	 * Checks whether a binary search tree contains a value
	 * @param t				The binary search tree
	 * @param x				The value to check for in t
	 * @param display		The TreeDisplay to visualize the tree during the operation
	 * @precondition  		t is a binary search tree in ascending order
	 * @postcondition 		returns true if t contains the value x;
	 * 						otherwise, returns false
	 */
	public static boolean contains(TreeNode t, Comparable x, TreeDisplay display)
	{
		if(t == null)
		{
			return false;
		}
		display.visit(t);
		if(x.equals(t.getValue()))
		{
			return true;
		}
		if(isGreater(x, t.getValue())) // t < x  x > t
		{
			return contains(t.getRight(), x, display);
		}
		return contains(t.getLeft(), x, display);
	}
	/**
	 * Inserts a value into a binary search tree
	 * @param t				The binary search tree
	 * @param x				The value to insert into t
	 * @param display		The TreeDisplay to visualize the tree during the operation
	 * @precondition		t is a binary search tree in ascending order
	 * @postcondition		if t is empty, returns a new tree containing x;
	 * 						otherwise, returns t, with x having been inserted
	 *                		at the appropriate position to maintain the binary
	 *               		search tree property; x is ignored if it is a
	 *               		duplicate of an element already in t; only one new
	 *               		TreeNode is created in the course of the traversal
	 */
	public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
	{
		if (t == null)
		{
			return new TreeNode(x);
		}
		display.visit(t);
		if (!x.equals(t.getValue()))
		{
			if(isGreater(x, t.getValue()))
			{
				t.setRight(insert(t.getRight(), x, display));
			}
			else
			{
				t.setLeft(insert(t.getLeft(), x, display));
			}
		}
		return t;
	}
	/**
	 * Deletes a given node from a binary search tree
	 * @param t				The node to delete
	 * @param display		The TreeDisplay to visualize the tree during the operation
	 * @precondition  		t is a binary search tree in ascending order
	 * @postcondition 		returns a pointer to a binary search tree,
	 *                		in which the value at node t has been deleted
	 *                		(and no new TreeNodes have been created)
	 */
	private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
	{
		TreeNode currentNode = t;
        if(currentNode.getLeft() == null && currentNode.getRight() == null) // leaf
		{
			return null;
		}
        if(currentNode.getLeft() == null) 				
		{
            return currentNode.getRight();
		}
        else if(currentNode.getRight() == null)
		{
            return currentNode.getLeft();
		}
        else 							
        {
			Comparable value = (Comparable) TreeUtil.leftmost(currentNode.getRight()); 
			currentNode.setValue(value);		
			currentNode.setRight(delete(currentNode.getRight(), value, display));	
			return currentNode;			
        }
	}
	/**
	 * Deletes a value from a binary search tree
	 * @param t				The binary search tree
	 * @param x				The value to delete
	 * @param display		The TreeDisplay to visualize the tree during the operation
	 * @precondition 		t is a binary search tree in ascending order
	 * @postcondition		returns a pointer to a binary search tree,
	 *      		        in which the value x has been deleted (if present)
	 * 		                (and no new TreeNodes have been created)
	 */
	public static TreeNode delete(TreeNode t, Comparable x, TreeDisplay display)
	{
		if (t == null)
		{
			return t;
		}
		display.visit(t);
		if(contains(t, x, display))
		{
			TreeNode currentNode = t;
			if(x.compareTo(currentNode.getValue()) > 0)
			{
				currentNode.setRight(delete(currentNode.getRight(), x, display));
			}
			else if(x.compareTo(currentNode.getValue()) < 0)
			{
				currentNode.setLeft(delete(currentNode.getLeft(), x, display));
			}
			else
			{
				return deleteNode(currentNode, display);   // once found call deleteNode
			}
		}       
		return t;
	}
}