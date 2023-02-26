import java.io.*;
import java.util.*;

/**
 * Models hurricane information, works with Hurricane class
 * and the user to manipulate an array of hurricane data.
 * 
 * Data came from http://www.aoml.noaa.gov/hrd/tcfaq/E23.html except for 2018.
 * 2018 data came from https://en.wikipedia.org/wiki/2018_Atlantic_hurricane_season.
 *
 * @author Aarav Borthakur & Susan King 
 * @version January 17, 2019
 * @version February 10, 2020 Polished code via variable names
 */
public class HurricaneOrganizerArray
{
    private Hurricane [] hurricanes;

    /**
     * Comment this constructor even though you did not write it.
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public HurricaneOrganizerArray(String filename)throws IOException
    {
        readFile(filename);   
    }

    /**
     * Comment this method even though you did not write it.
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    private static int determineFileLength(String filename) throws IOException
    {
        Scanner inFile = new Scanner(new File(filename));
        int counter = 0;

        while(inFile.hasNextLine())
        {
            counter++;
            inFile.nextLine();
        }
        inFile.close();
        return counter;
    }

    /**
     * Comment this method even though you did not write it.
     */
    public void readFile(String filename) throws IOException
    {
        hurricanes = new Hurricane [determineFileLength(filename)];
        int hurYear, hurPressure, hurSpeed;
        String hurName, hurMonth;
        Scanner inFile = new Scanner(new File(filename));

        for(int i = 0; i < hurricanes.length; i++)
        {
            hurYear = inFile.nextInt();
            hurMonth = inFile.next();
            hurPressure = inFile.nextInt();
            hurSpeed = inFile.nextInt();
            String tempName = inFile.nextLine();
            hurName = "";
            for(int k = 0; k < tempName.length(); k++)
            {
                char c = tempName.charAt(k);
                if(('a' <= c && c <= 'z') || ('A' <= c && c <='Z'))
                    hurName += c;
            }
            Hurricane h = new Hurricane(hurYear, hurMonth, hurPressure, hurSpeed, hurName);
            hurricanes [i] = h;
        }
        inFile.close();
    }

    /**
     * Comment this method.
     */
    public int findMaxWindSpeed( )
    {
        int max = hurricanes[0].getSpeed();
        for (Hurricane h : hurricanes)
        {
            if (h.getSpeed() > max)
            {
                max = h.getSpeed();
            }
        }
        return max;
    }

    /**
     * Comment this method.
     */
    public int findMaxPressure( )
    {
        int max = hurricanes[0].getPressure();
        for (Hurricane h : hurricanes)
        {
            if (h.getPressure() > max)
            {
                max = h.getPressure();
            }
        }
        return max;
    }

    /**
     * Comment this method.
     */
    public int findMinWindSpeed( )
    {
        int min = hurricanes[0].getSpeed();
        for (Hurricane h : hurricanes)
        {
            if (h.getSpeed() < min)
            {
                min = h.getSpeed();
            }
        }
        return min;
    }

    /**
     * Comment this method.
     */
    public int findMinPressure( )
    {
        int min = hurricanes[0].getPressure();
        for (Hurricane h : hurricanes)
        {
            if (h.getPressure() < min)
            {
                min = h.getPressure();
            }
        }
        return min;
    }

    /**
     * Comment this method.
     */
    public double calculateAverageWindSpeed( )
    {
        int sum = 0;
        for (Hurricane h : hurricanes)
        {
            sum += h.getSpeed();
        }
        return (double) sum / hurricanes.length;
    }

    /**
     * Comment this method.
     */
    public double calculateAveragePressure( )
    {
        int sum = 0;
        for (Hurricane h : hurricanes)
        {
            sum += h.getPressure();
        }
        return (double) sum / hurricanes.length;
    }

    /**
     * Comment this method.
     */
    public double calculateAverageCategory( )
    {
        int sum = 0;
        for (Hurricane h : hurricanes)
        {
            sum += h.getCategory();
        }
        return (double) sum / hurricanes.length;
    }

    private void swap(Hurricane[] arr, int first, int second)
    {
        Hurricane temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    private int findMinYear(Hurricane[] arr, int low)
    {
        int minIndex = low;
        for (int i = low; i < arr.length; i++)
        {
            if (arr[i].getYear() < arr[minIndex].getYear())
            {
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * Sorts ascending based upon the hurricanes' years,
     * The algorithm is selection sort.
     */
    public void sortYears()
    {
        for (int i = 0; i < hurricanes.length - 1; i++)
        {
            swap(hurricanes, i, findMinYear(hurricanes, i + 1));
        }
    }

    /**
     * Lexicographically sorts hurricanes based on the hurricanes' name, 
     * using insertion sort.
     */
    public void sortNames()
    {
        for (int i = 1; i < hurricanes.length; i++)
        {
            int j = i;
            while (j > 0 && hurricanes[j].getName().compareTo(hurricanes[j - 1].getName()) < 0)
            {
                swap(hurricanes, j, j - 1);
                j--;
            }
        }
    }

    private int findMaxCategory(Hurricane[] arr, int low)
    {
        int maxIndex = low;
        for (int i = low; i < arr.length; i++)
        {
            if (arr[i].getCategory() > arr[maxIndex].getCategory())
            {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Sorts descending based upon the hurricanes' categories,
     * using selection sort.
     */
    public void sortCategories()
    {
        for (int i = 0; i < hurricanes.length - 1; i++)
        {
            swap(hurricanes, i, findMaxCategory(hurricanes, i + 1));
        }
    }  


    private void mergePressure(Hurricane[] a, int low, int mid, int high)
    {
        Hurricane[] copy = new Hurricane[a.length];
        for (int i = low; i <= high; i++)
        {
            copy[i] = a[i];
        }
        int left = low;
        int right = mid + 1;
        for (int i = low; i <= high; i++)
        {
            if (right > high || (left <= mid && copy[left].comparePressureTo(copy[right]) > 0))
            {
                a[i] = copy[left];
                left++;
            }
            else if (i < a.length && right < copy.length)
            {
                a[i] = copy[right];
                right++;
            }
        }
    }

    /**
     * Sorts descending based upon pressures using a non-recursive merge sort.
     */
    public void sortPressures()
    {
        int diff = 0;
        int arrSizes = 2;
        while (arrSizes < hurricanes.length)
        {
            for (int i = 0; i < hurricanes.length; i += arrSizes)
            {
                int high = i + arrSizes - 1;
                if (high >= hurricanes.length)
                {
                    diff = i;
                }
                else 
                {
                    mergePressure(hurricanes, i, (i + high) / 2, high);
                }
            }
            arrSizes = arrSizes * 2;
        }
        mergePressure(hurricanes, 0, diff - 1, hurricanes.length - 1);
    }

    /**
     * Sorts ascending based upon wind speeds using a recursive merge sort. 
     */
    public void sortWindSpeeds(int low, int high)
    {
        mergeWindSpeedsSortHelper(low, (low + high) / 2, high);
    }

    private void mergeSpeed(Hurricane[] a, int lowIndex, int midIndex, int highIndex)
    {
        Hurricane[] copy = new Hurricane[a.length];
        for (int i = lowIndex; i <= highIndex; i++)
            copy[i] = a[i];
        int left = lowIndex;
        int right = midIndex + 1;
        for (int i = lowIndex; i <= highIndex; i++)
        {
            if (right > highIndex ||
                (left <= midIndex && copy[left].compareSpeedTo(copy[right]) < 0))
            {
                a[i] = copy[left];
                left++;
            }
            else
            {
                a[i] = copy[right];
                right++;
            }
        }
    }

    /**
     * Merges two consecutive parts of an array, using wind speed as a criteria
     * and a temporary array.  The merge results in an ascending sort between
     * the two given indices.
     * 
     * @precondition the two parts are sorted ascending based upon wind speed
     * 
     * @param low   the starting index of one part of the array.
     *              This index is included in the first half.
     * @param mid   the starting index of the second part of the array.
     *              This index is included in the second half.
     * @param high  the ending index of the second part of the array.  
     *              This index is included in the merge.
     */
    private void mergeWindSpeedsSortHelper(int low, int mid, int high)
    {
        if (Math.abs(low - high) == 1 && hurricanes[low].compareSpeedTo(hurricanes[high]) > 0)
        {
            swap(hurricanes, low, high);
        }
        else if (low < high)
        {
            mergeWindSpeedsSortHelper(low, (mid + low) / 2, mid);
            mergeWindSpeedsSortHelper(mid + 1, ((mid + 1) + high) / 2, high);
            mergeSpeed(hurricanes, low, mid, high);
        }
    }

    /**
     * Sequential search for all the hurricanes in a given year.
     * 
     * @param   year
     * @return  an array of objects in Hurricane that occured in
     *          the parameter year
     */
    public Hurricane [] searchYear(int year)
    {
        int counter = 0;
        for (Hurricane h : hurricanes)
        {
            if (h.getYear() == year)
            {
                counter++;
            }
        }
        Hurricane[] matches = new Hurricane[counter];
        int i = 0;
        for (Hurricane h : hurricanes)
        {
            if (h.getYear() == year)
            {
                matches[i] = h;
                i++;
            }
        }
        return matches;
    }     

    /**
     * Binary search for a hurricane name.
     * 
     * @param  name   hurricane name being search
     * @return a Hurricane array of all objects in hurricanes with specified name. 
     *         Returns null if there are no matches
     */
    public Hurricane[ ] searchHurricaneName(String name)
    {
        sortNames();
        return searchHurricaneNameHelper(name, 0, hurricanes.length - 1);
    }

    /**
     * Recursive binary search for a hurricane name.  This is the helper
     * for searchHurricaneName.
     * 
     * @precondition  the array must be presorted by the hurricane names
     * 
     * @param   name  hurricane name to search for
     * @param   low   the smallest index that needs to be checked
     * @param   high  the highest index that needs to be checked
     * @return  a Hurricane array of all Hurricane objects with a specified name. 
     *          Returns null if there are no matches
     */
    private Hurricane[ ] searchHurricaneNameHelper(String name, int low , int high)
    {
        // Test for the base case when a match is not found
        if (low > high)
        {
            return new Hurricane[0];
        }

        int mid = (low + high) / 2;
        int comparison = hurricanes[mid].getName().compareTo(name);

        // Test for match
        if (comparison == 0)
        {
            return retrieveMatchedNames(name, mid);
        }
        
        
        // Determine if the potential match is in the 
        // "first half" of the considered items in the array
        if (comparison > 0)
        {
            return searchHurricaneNameHelper(name, low, mid - 1);
        }
        
        
        // The potential match must be in the
        // "second half" of the considered items in the array
        return searchHurricaneNameHelper(name, mid + 1, high);
        
    }

    /**
     * Supports Binary Search method to get the full range of matches.
     * 
     * @precondition  the array must be presorted by the hurricane names
     * 
     * @param   name hurricane name being search for
     * @param   index  the index where a match was found
     * @return  a Hurricane array with objects from hurricanes with specified name. 
     *          Returns null if there are no matches
     */
    private Hurricane[] retrieveMatchedNames(String name, int index)
    {
        // Find the start where the matches start:
        int start = index;
        while (start > 0 && hurricanes[start - 1].getName().equals(name))
        {
            start--;
        }
        
        // Find the end of the matches:
        int end = index;
        while (end < hurricanes.length - 1 && hurricanes[end + 1].getName().equals(name))
        {
            end++;
        }
        
        // Copy the objects whose names match:
        int diff = end - start + 1;
        Hurricane[] res = new Hurricane[diff];
        int count = 0;
        for (int i = start; i <= end; i++, count++)
        {
            res[count] = hurricanes[i];
        }

        return res;
    }

    /**
     * Comment this method even though you did not write it.
     */
    public void printHeader()
    {
        System.out.println("\n\n");
        System.out.printf("%-4s %-5s %-15s %-5s %-5s %-5s \n", 
            "Year", "Mon.", "Name", "Cat.", "Knots", "Pressure");
    }

    /**
     * Comment this method even though you did not write it.
     */
    public void printHurricanes()
    {
        printHurricanes(hurricanes);
    }

    /**
     * Add comments here even though you did not write the method.
     */
    public void printHurricanes(Hurricane [] hurs)
    {
        if(hurs.length == 0)
        {
            System.out.println("\nVoid of hurricane data.");
            return;
        }
        printHeader();
        for(Hurricane h: hurs)
        {
            System.out.println(h);
        }
    }

    /**
     * Add comments here even though you did not write the method.
     */
    public void printMenu()
    {
        System.out.println("\n\nEnter option: ");
        System.out.println("\t 1 - Print all hurricane data \n" +
            "\t 2 - Print maximum and minimum data \n" +
            "\t 3 - Print averages \n" +
            "\t 4 - Sort hurricanes by year \n" +
            "\t 5 - Sort hurricanes by name \n" +
            "\t 6 - Sort hurricanes by category, descending \n" +
            "\t 7 - Sort hurricanes by pressure, descending \n" +
            "\t 8 - Sort hurricanes by speed \n" + 
            "\t 9 - Search for hurricanes for a given year \n" +
            "\t10 - Search for a given hurricane by name \n" +
            "\t11 - Quit \n");
    }

    /**
     * Add comments here even though you did not write the method.
     */
    public void printMaxAndMin( )
    {
        System.out.println("Maximum wind speed is " + 
            findMaxWindSpeed( ) +
            " knots and minimum wind speed is " + 
            findMinWindSpeed( ) + " knots.");
        System.out.println("Maximum pressure is " + 
            findMaxPressure( ) +
            " and minimum pressure is " + 
            findMinPressure( ) + ".");
    }

    /**
     * Add comments here even though you did not write the method.
     */
    public void printAverages( )
    {
        System.out.printf("Average wind speed is %5.2f knots. \n" , 
            calculateAverageWindSpeed( ));
        System.out.printf("Average pressure is %5.2f. \n" , 
            calculateAveragePressure( ));
        System.out.printf("Average category is %5.2f. \n" , 
            calculateAverageCategory( ));
    }

    /**
     * Add comments here even though you did not write the method.
     */
    public boolean interactWithUser( )
    {
        Scanner in = new Scanner(System.in);
        boolean done = false;
        printMenu();
        int choice = in.nextInt();
        // clear the input buffer
        in.nextLine();

        if(choice == 1)
        {
            printHurricanes( ); 
        }
        else if (choice == 2)
        {
            printMaxAndMin( );
        }
        else if (choice == 3)
        {
            printAverages( );
        }
        else if(choice == 4)
        {
            sortYears();
            printHurricanes( );
        }
        else if(choice == 5)
        {
            sortNames();
            printHurricanes( );
        }
        else if(choice == 6)
        {
            sortCategories();
            printHurricanes( );
        }
        else if(choice == 7)
        {
            sortPressures();
            printHurricanes( );
        }
        else if(choice == 8)
        {
            sortWindSpeeds(0, hurricanes.length - 1);
            printHurricanes( );
        }
        else if(choice == 9)
        {
            System.out.print("\n\tWhich year do you want to search for?\n\t");
            int year = in.nextInt();
            printHurricanes(searchYear(year));
        }
        else if(choice == 10)
        {
            System.out.print("\n\tWhich name do you want to search for?\n\t");
            String name = in.next();
            printHurricanes(searchHurricaneName(name));
        }
        else if (choice == 11)
        {
            done = true;
        }  
        return done;
    }

    /**
     * Comment the method even though you did not write it.
     * 
     * @param args  user's information from the command line
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public static void main (String [] args) throws IOException
    {
        HurricaneOrganizerArray cane = new HurricaneOrganizerArray("hurricanedata.txt");
        boolean areWeDoneYet = false;
        while ( ! areWeDoneYet)
        {
            areWeDoneYet = cane.interactWithUser( );    
        }
    }
}
