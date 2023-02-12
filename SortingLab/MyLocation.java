/**
 * MyLocation represents location with an x
 * and y coordinate.
 * @author  Aarav Borthakur
 * @version 2/11/23
 */
public class MyLocation implements Comparable
{
    private int row;
    private int col;

    /**
     * Creates a MyLocation object with the given row & col
     * @param r     row of this MyLocation
     * @param c     column of this MyLocaiton
     */
    public MyLocation(int r, int c)
    {
        row = r;
        col = c;
    }

    /**
     * Gets the row of this location
     * @return      the row of this MyLocationn
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Gets the column of this location
     * @return      the column of this MyLocation
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Checks whether an object is equal to this MyLocation,
     * by testing whether the other ibject has the same row 
     * and column as this MyLocation
     * @param other     The other object which will be
     *                  cast to MyLocation to be compared
     * @return          Whether other other equals this 
     *                  MyLocation
     */
    public boolean equals(Object other)
    {
        MyLocation otherLocation = (MyLocation) other;
        return otherLocation.getRow() == row && otherLocation.getCol() == col;
    }

    /**
     * Gets the string representation of this MyLocation
     * in "(row, column)" format
     * @return          the string representation of this
     *                  MyLocation (e.g. "(1, 4)")
     */
    public String toString()
    {
        return "(" + row + ", " + col + ")";
    }

    /**
     * Compares an object to this MyLocation. The object
     * with the higher row is greater. If two objects have 
     * the same row, the one with the higher column
     * is greater
     * @param x     The other object which will be cast
     *              to my Location to be compared
     * @return      A value > 0 if this MyLocation is 
     *              greater, a value == 0 if this 
     *              MyLocation is equal to other,
     *              and a value < 0 if this MyLocation
     *              is smaller
     */
    public int compareTo(Object x)
    {
        MyLocation other = (MyLocation) x;
        if (row > other.getRow())
        {
            return 1;
        }
        if (row < other.getRow())
        {
            return -1;
        }
        if (col > other.getCol())
        {
            return 1;
        }
        if (col < other.getCol())
        {
            return -1;
        }
        return 0;
    }
}