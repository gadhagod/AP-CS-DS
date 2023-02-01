/**
 * Rectangle represents a rectangle with a 
 * length and width.
 * @author 	Aarav Borthakur
 * @version	01/30/23	
 */
public class Rectangle
{
	private int length;
	private int width;

	/**
	 * Constructs a Rectangle given the 
	 * rectangle's length and width
	 * @param len	The rectangle's length
	 * @param w		The rectangle's width
	 */
	public Rectangle(int len, int w)
	{
		length = len;
		width = w;
	}

	/**
	 * Gets the length of the Rectangle
	 * @return		The Rectangle's length
	 */
	public int getLength()
	{
		return length;
	}

	/**
	 * Gets the width of the Rectangle
	 * @return		The Rectangle's width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Gets the String representation
	 * of the Rectangle
	 * @return		The String representation
	 * 				of the Rectangle (e.g "2x3")
	 */
	public String toString()
	{
		return length + "x" + width;
	}

	/**
	 * Checks whether this Rectangle is
	 * equal to another Rectangle by
	 * comparing their lengths and widths
	 * @param other	The other Rectangle
	 * @return		Whether this Rectangle
	 * 				and other have equal 
	 * 				lengths and widths
	 */
	public boolean equals(Rectangle other)
	{
		return other.length == length && other.width == width;
	}

	/**
	 * Checks whether this Rectangle is
	 * equal to another Object, cast to
	 * a Rectangle, by comparing their 
	 * lengths and widths
	 * @param other	The other Object
	 * @return		Whether this Rectangle
	 * 				and other have equal 
	 * 				lengths and widths
	 */
	public boolean equals(Object other)
	{
		return ((Rectangle) other).length == length && ((Rectangle) other).width == width;
	}

	/**
	 * Gets the hash code of the given 
	 * function by multiplying the length
	 * by 11 and adding it to the width 
	 * times 9
	 * @return		The hash code of
	 * 				the rectangle
	 */
	public int hashCode()
	{
		return 11 * length + 9 * width;
	}
}