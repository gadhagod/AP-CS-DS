import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * The Signatures helps process signatures of famous authors
 * and find the most likely author of a text.
 * @author  Aarav Borthakur
 * @version 5/12/23
 */
public class Signatures
{
    private static final double[] WEIGHTS = {11, 33, 50, 0.4, 4};
    private HashMap<String, double[]> sigs;

    /**
     * Constructs a Signatures object
     * @param siganturesDir The directory containing the signatures
     *                      to scan. If ./{signaturesDir} does not 
     *                      exist, a FileNotFoundException is 
     *                      thrown
     */
    public Signatures(String signaturesDir) throws FileNotFoundException
    {
        sigs = new HashMap<String, double[]>();
        File dir = new File(signaturesDir);
        File[] signatureFiles = dir.listFiles();
        if (signatureFiles == null) 
        {
            throw new FileNotFoundException("signatures files not found in " + signaturesDir);
        }
        else
        {
            for (File signatureFile : signatureFiles)
            {
                double[] stats = new double[5];
                java.util.Scanner reader = new java.util.Scanner(signatureFile);
                String name = reader.nextLine();
                for (int i = 0; i < 5; i++)
                {
                    Double stat = Double.parseDouble(reader.nextLine());
                    stats[i] = stat;
                }
                sigs.put(name, stats);
                reader.close();
            }
        } 
    }

    /**
     * Finds the author who most likely wrote a text
     * @param other     The Document whose author is to be determined
     * @return          The name of the author who most likely wrote
     *                  the text in other
     */
    public String getClosestMatch(Document other)
    {
        String closestMatch = "";
        double shortestDist = Integer.MAX_VALUE;
        for (String name : sigs.keySet())
        {
            double dist = 0;
            double[] otherStats = other.getStats();
            for (int i = 0; i < 5; i++)
            {
                dist += Math.abs(otherStats[i] - sigs.get(name)[i]) * WEIGHTS[i];
            }

            if (dist < shortestDist)
            {
                shortestDist = dist;
                closestMatch = name;
            }
        }
        return closestMatch;
    }
}