import javax.sound.sampled.*;
import javax.sound.sampled.LineEvent.Type;
import java.io.File;
import java.io.IOException;

/**
 * Listens for audio events
 * @author  Aarav Borthakur
 * @version 03/21/23
 */
class AudioListener implements LineListener 
{
    private boolean done = false;

    /*
    /**
     * Checks whether process is over
     * @param event  The event to process
     */
    public synchronized void update(LineEvent event) 
    {
        Type eventType = event.getType();
        if (eventType == Type.STOP || eventType == Type.CLOSE) 
        {
            done = true;
            notifyAll();
        }
    }

    /**
     * Stalls the Thread until the process is over
     * @throws InterruptedException     if the thread was interrupted
     */
    public synchronized void waitUntilDone() throws InterruptedException 
    {
        while (!done) 
        {
            wait();
        }
    }
}

public class Audio 
{
    /**
     * Starts the game music
     * @postcondition   music.wav is playing
     */
    private static void start() throws IOException,
            UnsupportedAudioFileException, LineUnavailableException, InterruptedException 
    {
        AudioListener listener = new AudioListener();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("music.wav"));

        try 
        {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            clip.start();
            listener.waitUntilDone();
        } 
        finally 
        {
            audioInputStream.close();
        }
    }

    /**
     * Starts the game music
     * @postcondition   music.wav is playing
     */
    public static void play() 
    {
        try 
        {
            start();
        } 
        catch (Exception err)
        {
            System.out.println(err);
            System.exit(1);
        }
    }
}
