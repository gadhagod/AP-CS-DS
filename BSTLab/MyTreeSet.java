/* 
NOT TO BE SUBMITTED YET
public class MyTreeSet<E>
{
	private TreeNode root;
	private int size;
	private TreeDisplay display;

	public MyTreeSet()
	{
		display = new TreeDisplay();
		size = 0;
	}

	public int size()
	{
		return size;
	}

	public boolean contains(Object obj)
	{
		return BSTUtilities.contains(root, (Comparable) obj, display);
	}

	// if obj is not present in this set, adds obj and
	// returns true; otherwise returns false
	public boolean add(E obj)
	{
		TreeNode t = root;
		Comparable x = (Comparable) obj;
		if (t == null)
		{
			root = new TreeNode(x);
			return true;
		}
		display.visit(t);
		if (x.equals(t.getValue()))
		{
			return false;
		}
		else
		{
			if(x.compareTo(t) > 0)
			{
				t.setRight(BSTUtilities.insert(t.getRight(), x, display));
			}
			else
			{
				t.setLeft(BSTUtilities.insert(t.getLeft(), x, display));
			}
		}	
	}

	public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
	{
		if (t == null)
		{
			return new TreeNode(x);
		}
		display.visit(t);
		if (x.equals(t.getValue()))
		{
			return false;
		}
		else
		{
			if(x.compareTo(t) > 0)
			{
				t.setRight(insert(t.getRight(), x, display));
			}
			else
			{
				t.setLeft(insert(t.getLeft(), x, display));
			}
		}
	}

	// if obj is present in this set, removes obj and
	// returns true; otherwise returns false}
	public boolean remove(Object obj)
	{
		TreeNode res = BSTUtilities.delete(root, (Comparable) obj, display);
		if (res == null) 
		{
			return false;
		}
		size--;
		return true;
	}

	public String toString()
	{
		return toString(root);
	}

	private String toString(TreeNode t)
	{
		if (t == null)
			return " ";
		return toString(t.getLeft()) + t.getValue() + toString(t.getRight());
	}
}
*/