import java.awt.Color;
/**
 * Block encapsulates a Block abstraction which can be placed into a Gridworld style grid.
 * @author  Aarav Borthakur
 * @version 3/5/23
 */
public class Block
{
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;

    /**
     * Constructs a blue Block that's not in a grid
     */
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }

    /**
     * Gets the color of the Block
     * @return  the color of the Block
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Sets the color of the Block
     * @param newColor  The new color of the block
     * @postcondition   The block's color is set to newColor
     */
    public void setColor(Color newColor)
    {
        color = newColor;
    }
    
    /**
     * Gets the grid that this Block is in
     * @return the MyBoundedGrid that the block is in
     */
    public MyBoundedGrid<Block> getGrid()
    {
        return grid;
    }
    
    /**
     * Gets the current location of the Block
     * @return the Location of the block
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Removes this Block from the grid
     * @postcondition   This Block is disassociate from it's 
     *                  grid
     */
    public void removeSelfFromGrid()
    {
        grid.remove(location);
        location = null;
        grid = null;
    }
    
    /**
     * Puts this block in a grid
     * @param gr        The grid to place this Block into
     * @param loc       The location in the grid to place this Block into,
     *                  must be within bounds of gr
     * @postcondition   This Block is placed into a gr at location loc
     */
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
        location = loc;
        grid = gr;
        Block prev = grid.get(location);
        if (prev != null)
        {
            prev.removeSelfFromGrid();
        }
        grid.put(loc, this);
    }

    /**
     * Moves this block into a new location in its grid
     * @param newLocation   The new location to place this Block into,
     *                      must be within bounds of its grid
     * @precondition        This Block is in a grid
     * @postcondition       This Block is at newLocation in this grid
     */
    public void moveTo(Location newLocation)
    {
        MyBoundedGrid<Block> temp = grid;
        removeSelfFromGrid();
        putSelfInGrid(temp, newLocation);
    }

    /**
     * Get's the string representation of the Block
     * @return  The String representation of the Block,
     *          specifying it's location and color
     */
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}