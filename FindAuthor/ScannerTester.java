import java.util.Arrays;
import java.io.*;
/**
 * Tests the project by predicting the author of each text
 * in ./FindAuthorMaterial/MysteryText
 * 
 * @author Anu Datar, Aarav Borthakur
 * @version 05/12/2023
 */
public class ScannerTester
{
    /**
     * Prints predictions of each author of text in files in
     * ./FindAuthorMaterial/MysteryText. Throws a FileNotFoundException
     * if directory ./FindAuthorMaterial/SignatureFiles or 
     * ./FindAuthorMaterial/MysteryText is empty
     * @param str The command line arguments to pass into the program
     */
    public static void main(String[] str) throws FileNotFoundException
    {
        Signatures sigs = new Signatures("FindAuthorMaterial/SignatureFiles");
        File mysteries = new File("FindAuthorMaterial/MysteryText");
        File[] mysteryFiles = mysteries.listFiles();
        Arrays.sort(mysteryFiles);
        for (File mysteryFile : mysteryFiles)
        {
            FileReader reader = new FileReader(mysteryFile);
            Scanner scanner = new Scanner(reader);
            Document doc = new Document(scanner);
            System.out.println(mysteryFile.getName() + " was written by " + sigs.getClosestMatch(doc));
        }
    }
}