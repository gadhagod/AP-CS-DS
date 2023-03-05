import java.util.ArrayList;

/**
 * MyBoundedGrid represents a grid of Objects
 * @author  Aarav Borthakur
 * @version 03/05/23
 */
public class MyBoundedGrid<E>
{
    private Object[][] grid;

    /**
     * Constructs a MyBoundedGrid
     * @param rows  The number of rows in a grid
     * @param cols  The number of columns in a grid
     */
    public MyBoundedGrid(int rows, int cols)
    {
        grid = new Object[rows][];
        for (int i = 0; i < rows; i++)
        {
            grid[i] = new Object[cols];
        }
    }

    /**
     * Gets the number of rows in this grid
     * @return  The number of rows in this MyBoundedGrid
     */
    public int getNumRows()
    {
        return grid.length;
    }

    /**
     * Gest the number of columns in this grid
     * @return  The number of columns in this MyBoundedGrid
     */
    public int getNumCols()
    {
        return grid[0].length;
    }

    /**
     * Checks whether a location is within bounds of this
     * grid
     * @param loc   The location to test
     * @return      true if loc is withing bounds of this MyBoundedGrid,
     *              false otherwise
     */
    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 &&
               loc.getCol() >= 0 &&
               loc.getRow() < grid.length && 
               loc.getCol() < grid[0].length;
    }

    /**
     * Puts an object into the grid
     * @param loc       The location to place the object into
     * @param obj       The object to place into to grid
     * @postcondition   The Location loc at this MyBoundedGrid contains obj
     */
    public E put(Location loc, E obj)
    {
        E prev = (E) grid[loc.getRow()][loc.getCol()];
        grid[loc.getRow()][loc.getCol()] = obj;
        return (E) prev;
    }

    /**
     * Removes the object at a given location
     * @param loc       The location in the grid to clear
     * @return          The object previously at location (null if it was empty)
     * @postcondition   At location loc in this MyBounded Grid, there are no objects
     */
    public E remove(Location loc)
    {
        Object last = grid[loc.getRow()][loc.getCol()];
        grid[loc.getRow()][loc.getCol()] = null;
        return (E) last;
    }

    /**
     * Gets the object at a given location
     * @param loc   The location containing the object to retrieve
     * @return      The object at location lco
     */
    public E get(Location loc)
    {
        return (E) grid[loc.getRow()][loc.getCol()];
    }

    /**
     * Gets all the occupied locations in this grid
     * @return  An ArrayList of Locations that are occupied in this MyArrayList
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> res = new ArrayList<Location>();
        for (int row = 0; row < grid.length; row++)
        {
            if (grid[0].length > 0)
            {
                for (int col = 0; col < grid[0].length; col++)
                {
                    if (grid[row][col] != null)
                    {
                        res.add(new Location(row, col));
                    }
                }
            }
        }
        return res;
    }
}
