import java.awt.Color;
import java.util.concurrent.Semaphore;

/**
 * Tetrad represents a Tetrad piece featured in the Tetris game
 * @author  Aarav Borthakur
 * @version 3/21/23
 */
public class Tetrad 
{
    private Block[] blocks;
    private MyBoundedGrid<Block> grid;
    public String shape;
    private Color color;
    private Location[] locs;
    private Semaphore lock;
    private boolean gameOver;

    /**
     * Constructs a Tetrad in a grid
     * @param grid  The grid that this Tetrad is part of
     */
    public Tetrad(MyBoundedGrid<Block> grid)
    {
        lock = new Semaphore(1, true);
        blocks = new Block[4];
        this.grid = grid;
        setShapeAndColor();
        locs = new Location[4];
        if (shape.equals("I"))
        {
            locs[0] = new Location(0, 6);
            locs[1] = new Location(0, 4);
            locs[2] = new Location(0, 5);
            locs[3] = new Location(0, 7);
        }
        else if (shape.equals("T"))
        {
            locs[0] = new Location(0, 5);
            locs[1] = new Location(0, 4);
            locs[2] = new Location(0, 6);
            locs[3] = new Location(1, 5);
        }
        else if (shape.equals("O"))
        {
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(1, 4);
            locs[3] = new Location(1, 5);
        }
        else if (shape.equals("L"))
        {
            locs[0] = new Location(0, 5);
            locs[1] = new Location(0, 4);
            locs[2] = new Location(0, 6);
            locs[3] = new Location(1, 4);
        }
        else if (shape.equals("J"))
        {
            locs[0] = new Location(0, 5);
            locs[1] = new Location(0, 4);
            locs[2] = new Location(0, 6);
            locs[3] = new Location(1, 6);
        }
        else if (shape.equals("S"))
        {
            locs[0] = new Location(0, 5);
            locs[1] = new Location(0, 6);
            locs[2] = new Location(1, 5);
            locs[3] = new Location(1, 4);
        }
        else
        {
            locs[0] = new Location(0, 5);
            locs[1] = new Location(0, 4);
            locs[2] = new Location(1, 5);
            locs[3] = new Location(1, 6);
        }
        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i] = new Block();
            blocks[i].setColor(color);
        }
        for (Location loc : locs)
        {
            if (grid.get(loc) != null)
            {
                gameOver = true;
                return;
            }
        }
        addToLocations(grid, locs);
    }

    /**
     * Removes each block of this Tetrad from teh grid
     * @return          An array of Locations that the blocks
     *                  were previously in
     * @postcondition   All blocks of this Tetrad are removed
     *                  from the grid
     */
    private Location[] removeBlocks()
    {
        Location[] copy = new Location[4];
        for (int i = 0; i < locs.length; i++)
        {
            copy[i] = locs[i];
            grid.get(locs[i]).removeSelfFromGrid();
        }
        return copy;
    }

    /**
     * Chceks whether locations of a grid are empty
     * @param grid  The grid to test 
     * @param locs  An array of Locations in the grid to test
     *              (must be within bounds of the grid)
     * @return      Whether the locations of the grid are empty
     */
    private boolean areEmpty(MyBoundedGrid<Block> grid, Location[] locs) 
    {
        for (Location loc : locs)
        {
            if (!grid.isValid(loc) || grid.get(loc) != null)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds blocks to a grid
     * @param grid      The grid to place blocks into
     * @param locs      The locations to place the blocks into
     * @postcondition   Grid positions at locs are filled with blocks
     */
    private void addToLocations(MyBoundedGrid<Block> grid, Location[] locs) 
    {
        for (int i = 0; i < locs.length; i++)
        {
            blocks[i].putSelfInGrid(grid, locs[i]);
        }
    }

    /**
     * Translates this Tetrad
     * @param deltaRow      The number of rows to move this Tetrad
     *                      by (negative for leftward)
     * @param deltaCol      The number of columns to move this Tetrad
     *                      by (negative for upward)
     * @return              Whether this Tetrad moved
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        try {
            lock.acquire();
            Location[] oldLocs = removeBlocks();
            Location[] newLocs = new Location[oldLocs.length];
            for (int i = 0; i < oldLocs.length; i++)
            {
                Location old = oldLocs[i];
                newLocs[i] = new Location(old.getRow() + deltaRow, old.getCol() + deltaCol);
                if (!grid.isValid(newLocs[i]))
                {
                    addToLocations(grid, oldLocs);
                    return false;
                }
            }
            if (areEmpty(grid, newLocs))
            {
                locs = newLocs;
                addToLocations(grid, newLocs);
                return true;
            }
            addToLocations(grid, oldLocs);
            return false;
        }
        catch (InterruptedException e)
        {
            return false;
        }
        finally 
        {
            lock.release();
        }
    }

    /**
     * Rotates this Tetrad around a single block
     * @return      Whether this Tetrad was rotated
     */
    public boolean rotate()
    {
        if (shape.equals("O")) // Don't rotate O's
        {
            return true;
        }
        Location[] oldLocs = removeBlocks();
        Location[] newLocs = new Location[oldLocs.length];
        for (int i = 0; i < oldLocs.length; i++)
        {
            Location old = oldLocs[i];
            newLocs[i] = new Location(
                locs[0].getRow() - locs[0].getCol() + old.getCol(),
                locs[0].getRow() + locs[0].getCol() - old.getRow()
            );
            if (!grid.isValid(newLocs[i]))
            {
                addToLocations(grid, oldLocs);
                return false;
            }
        }
        if (areEmpty(grid, newLocs))
        {
            locs = newLocs;
            addToLocations(grid, newLocs);
            return true;
        }
        addToLocations(grid, oldLocs);
        return false;
    }

    /**
     * Checks whether the game is over
     */
    public boolean isGameOver()
    {
        return gameOver;
    }

    /**
     * Sets the shape and color instance fields of this Tetrad
     * @postcondition shape and color instance fields are filled
     *                appropriately
     */
    private void setShapeAndColor()
    {
        int rand = (int) (Math.random() * 7);
        if (rand == 0)
        {
            shape = "I";
            color = Color.red;
        } 
        else if (rand == 1)
        {
            shape = "T";
            color = Color.gray;
        }
        else if (rand == 2)
        {
            shape = "O";
            color = Color.blue;
        }
        else if (rand == 3)
        {
            shape = "L";
            color = Color.yellow;
        }
        else if (rand == 4)
        {
            shape = "J";
            color = Color.magenta;
        }
        else if (rand == 5)
        {
            shape = "S";
            color = Color.blue;
        }
        else 
        {
            shape = "Z";
            color = Color.green;
        }
    }
}
