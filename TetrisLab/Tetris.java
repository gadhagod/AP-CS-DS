public class Tetris implements ArrowListener
{
    private MyBoundedGrid<Block> grid;
    private BlockDisplay display;
    private Scoreboard scoreboard;
    private Tetrad active;

    /**
     * Constructs a Tetris game
     */
    public Tetris()
    {
        grid = new MyBoundedGrid<Block>(20, 10);
        display = new BlockDisplay(grid);
        display.setArrowListener(this);
        display.setTitle("Tetris");
        scoreboard = new Scoreboard();
        active = new Tetrad(grid);
        display.showBlocks();
        new Thread(new Runnable()
        {
            @Override
            public void run() 
            {
                Audio.play();
            }
        }).start();
        play();

    }

    /**
     * Checks whether a row in the grid is completely 
     * filled
     * @param row   The index of the row to check
     * @return      Whether the row is filled
     */
    private boolean isCompletedRow(int row)
    {
        for (int i = 0; i < 10; i++)
        {
            if (grid.get(new Location(row, i)) == null)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Clears all blocks in a row
     * @param row     The index of the row to clear
     * @postcondition The row is empty
     */
    private void clearRow(int row)
    {
        for (int i = 0; i < 10; i++)
        {
            grid.get(new Location(row, i)).removeSelfFromGrid();
        }
    }

    /**
     * Clears the completed rows and shifts all blocks
     * above completed rows down by one row
     * @return  No rows are filled; if row(s) were previously
     *          filled all blocks above the row are shifted 
     *          down
     */
    private void clearCompletedRows()
    {
        int turnScore = 0;
        for (int row = 0; row < 20; row++)
        {
            if (isCompletedRow(row))
            {
                if (turnScore == 0)
                {
                    turnScore = 40;
                } 
                else if (turnScore == 40)
                {
                    turnScore = 100;
                }
                else if (turnScore == 100)
                {
                    turnScore = 200;
                }
                else if (turnScore == 200)
                {
                    turnScore = 1200;
                }
                else
                {
                    turnScore += 1200;
                }
                clearRow(row);
                scoreboard.addLevel();
                if (grid.isValid(new Location(row - 1, 0)))
                {
                    for (int r = row - 1; r >= 0; r--)
                    {
                        for (int c = 0; c < 10; c++)
                        {
                            Block curr = grid.get(new Location(r, c));
                            if (curr != null)
                            {
                                curr.moveTo(new Location(r + 1, c));
                            }
                            display.showBlocks();
                        }
                    }
                }
            }
        }
        scoreboard.addScore(turnScore);
    }

    /**
     * Rotates the active Tetrad
     * @postcondition   This Tetrad is rotated
     */
    public void upPressed()
    {
        active.rotate();
        display.showBlocks();
    }

    /**
     * Moves the active Tetrad down by one row
     * @postcondition   The Tetrad is shifted down by one
     *                  row
     */
    public void downPressed()
    {
        active.translate(1, 0);
        display.showBlocks();
    }

    /**
     * Moves the active Tetrad left by one column
     * @postcondition   This Tetrad is shifted left by one
     *                  column
     */
    public void leftPressed()
    {
        active.translate(0, -1);
        display.showBlocks();
    }

    /**
     * Moves the active Tetrad right by one column
     * @postcondition   This Tetrad is shifted right by one
     *                  column
     */
    public void rightPressed()
    {
        active.translate(0, 1);
        display.showBlocks();
    }

    /**
     * Repeatedly shifts the active Tetrad down to simulate
     * it "dropping"
     */
    public void play()
    {
        while (true)
        {
            try
            {
                if (active.isGameOver())
                {
                    display.endGame(scoreboard.getScore());
                    return;
                }
                Thread.sleep(scoreboard.getSpeed());
                if (!active.translate(1, 0))
                {
                    active = null;
                    clearCompletedRows();
                    active = new Tetrad(grid);
                }
                display.showBlocks();
            }
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
        }
    }

    /**
     * Starts a new Tetris game by opening a new window
     * @param args  The command line arguments to pass into
     *              the program
     */
    public static void main(String[] args) 
    {
        new Tetris();
    }
}