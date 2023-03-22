import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Scoreboard extends JComponent implements KeyListener
{

    private JFrame frame;
    private int score;
    private JLabel scoreText;
    private int level;
    private JLabel levelText;

    /**
     * Creates a new Scoreboard
     */
    public Scoreboard()
    {

        SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    createAndShowGUI();
                }
            }
        );

        try
        {
            while (frame == null || !frame.isVisible())
            {
                Thread.sleep(1);
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        repaint();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI()
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setTitle("Tetris Scoreboard");
        JPanel panel = new JPanel();
        scoreText = new JLabel("Score: 0");
        levelText = new JLabel("Level: 0");
        panel.add(new JLabel("        "));
        panel.add(scoreText);
        panel.add(levelText);
        panel.add(new JLabel("        "));
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Adds an amount to the score board
     * @param deltaScore    The score to add
     * @postcondition       The scoreboard is updated
     */
    public void addScore(int deltaScore)
    {
        score += deltaScore;
        scoreText.setText("Score: " + score);
        repaint();
    }

    /**
     * Gets the score of the game
     * @return  The score of the game
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Adds a level to the score board
     * @postcondition       The scoreboard is updated to show
     *                      one more level
     */
    public void addLevel()
    {
        level++;
        levelText.setText("Level: " + level);
        repaint();
    }

    /**
     * Gets the speed that the Tetrads should fall
     * at based on the level
     * @return  The speed that the Tetrads should fall
     */
    public int getSpeed()
    {
        int speed = 100 - 10 * level;
        if (speed < 500)
        {
            speed = 500;
        }
        return speed;
    }

    /**
     * The method ran when a key is typed
     * @param e     The key typed
     */
    public void keyTyped(KeyEvent e)
    {
    }

    /**
     * The method ran when a key is released
     * @param e     The key released
     */
    public void keyReleased(KeyEvent e)
    {
    }

    /**
     * The method ran when a key is pressed
     * @param e     The key pressed
     */
    public void keyPressed(KeyEvent e)
    {
    }
}
