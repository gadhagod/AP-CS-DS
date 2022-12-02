/**
 * A node in a tree
 * @version		12/02/22
 * @author 		Aarav Borthakur
 */
public class TreeNode
{
	private Object value;
	private TreeNode left;
	private TreeNode right;
	/**
	 * Constructs a node in a tree with an initial value
	 */
	public TreeNode(Object initValue)
	{ 
		this(initValue, null, null);
	}
	/**
	 * Constructs a node in a tree given its value and its left and right nodes
	 */
	public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
	{ 
		value = initValue; 
		left = initLeft; 
		right = initRight; 
	}
	/**
	 * Gets the value of the node
	 * @return 		the value of the node
	 */
	public Object getValue() { 
		return value; 
	}
	/**
	 * Gets the left node of the tree
	 * @return 		the node of the tree
	 */
	public TreeNode getLeft() { 
		return left; 
	}
	/**
	 * Gest the right node of the tree
	 * @return		the right node of the tree
	 */
	public TreeNode getRight() { 
		return right;
	}
	/**
	 * Sets the value of the node
	 * @param		theNewValue	The new value of the node
	 * @postcondition			The value of the node is set to theNewValue
	 */
	public void setValue(Object theNewValue) { 
		value = theNewValue; 
	}
	/**
	 * Sets the left node of the tree
	 * @param		theNewLeft	The new left node 
	 * @postcondition			The left node is set to theNewLeft
	 */
	public void setLeft(TreeNode theNewLeft) { 
		left = theNewLeft; 
	}
	/**
	 * Sets the left node of the tree
	 * @param		theNewRight	The new right node 
	 * @postcondition			The right node is set to theNewRight
	 */
	public void setRight(TreeNode theNewRight) { 
		right = theNewRight; 
	}
}