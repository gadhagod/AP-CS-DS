/**
* comment this class completely, and in accordance with the style guide.
*/
public class Rectangle
{
	private int length;
	private int width;

	public Rectangle(int len, int w)
	{
		length = len;
		width = w;
	}

	public int getLength()
	{
		return length;
	}

	public int getWidth()
	{
		return width;
	}

	public String toString()
	{
		return length + "x" + width;
	}

	public boolean equals(Rectangle other)
	{
		return other.length == length && other.width == width;
	}

	public int hashCode()
	{
		return 11 * length + 9 * width;
	}
}