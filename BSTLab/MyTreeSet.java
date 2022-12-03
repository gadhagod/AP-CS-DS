public class MyTreeSet<E>
{
	private TreeNode root;
	private int size;
	private TreeDisplay display;

	public MyTreeSet()
	{
		// Initialize all instance variables 
		display = new TreeDisplay();
	}

	public int size()
	{
		throw new RuntimeException("Implement me!");
	}

	public boolean contains(Object obj)
	{
		throw new RuntimeException("Implement me!");
	}

	// if obj is not present in this set, adds obj and
	// returns true; otherwise returns false
	public boolean add(E obj)
	{
		throw new RuntimeException("Implement me!");
	}

	// if obj is present in this set, removes obj and
	// returns true; otherwise returns false}
	public boolean remove(Object obj)
	{
		throw new RuntimeException("Implement me!");
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