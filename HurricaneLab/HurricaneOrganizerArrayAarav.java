import java.io.*;
import java.util.*;

/**
 * Models hurricane information, works with Hurricane class
 * and the user to manipulate an array of hurricane data.
 * Data came from http://www.aoml.noaa.gov/hrd/tcfaq/E23.html 
 * except for 2018. 2018 data came from 
 * https://en.wikipedia.org/wiki/2018_Atlantic_hurricane_season.
 * @author Aarav Borthakur & Susan King
 * @version 03/01/23
 */
public class HurricaneOrganizerArrayAarav
{
    private Hurricane [] hurricanes;

    /**
     * Constructs a HurricaneOrganizerArray given a 
     * file name to read data from
     * @param filename      The name of the file to read
     * from
     * @throws IOException  if file with the hurricane 
     *                      information cannot be found
     */
    public HurricaneOrganizerArrayAarav(String filename) throws IOException
    {
        readFile(filename);   
    }

    /**
     * Determines the length of a file given its
     * name
     * @param filename      The name of the file
     *                      whose length is to be determined
     * @return              The number of lines in the file
     * @throws IOException  if file with the hurricane information 
     *                      cannot be found
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
     * Sets the hurricanes instance field by reading data
     * from a given file.
     * @param filename      The name of the file to
     *                      read from
     * @throws IOException  if file with the hurricane information 
     *                      cannot be found
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
     * Gets the highest wind speed of the
     * Hurricanes in the hurricanes instance
     * field
     * @return      The greatest wind speed 
     *              in hurricanes
     */
    public int findMaxWindSpeed()
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
     * Gets the max highest pressure found in
     * hurricanes instance field
     * @return      The greatest pressure 
     *              in hurricanes
     */
    public int findMaxPressure()
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
     * Gets the lowest wind speed of the
     * Hurricanes in hurricanes 
     * @return      The lowest wind speed 
     *              in hurricanes
     */
    public int findMinWindSpeed()
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
     * Gets the minimum pressure found in
     * hurricanes
     * @return      The lowest pressure 
     *              in hurricanes
     */
    public int findMinPressure()
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
     * Gets the average wind speed of the hurricanes
     * @return      The average wind speed of hurricanes
     *              instance field
     */
    public double calculateAverageWindSpeed()
    {
        int sum = 0;
        for (Hurricane h : hurricanes)
        {
            sum += h.getSpeed();
        }
        return (double) sum / hurricanes.length;
    }

    /**
     * Gets the average pressure of the hurricanes
     * @return      The average pressure of the hurricanes
     *              instance field
     */
    public double calculateAveragePressure()
    {
        int sum = 0;
        for (Hurricane h : hurricanes)
        {
            sum += h.getPressure();
        }
        return (double) sum / hurricanes.length;
    }

    /**
     * Gets the average category of the hurricanes
     * @return      The average category of the hurricanes
     *              instance field
     */
    public double calculateAverageCategory()
    {
        int sum = 0;
        for (Hurricane h : hurricanes)
        {
            sum += h.getCategory();
        }
        return (double) sum / hurricanes.length;
    }

    /**
     * Swaps two values in an array
     * @param arr     The array containing values
     *                to be swapped
     * @param first   The index of the first value
     *                to swap
     * @param second  The index of the other value
     *                to swap
     * @postcondition arr[first] and arr[second]
     *                is swapped
     */
    private void swap(Hurricane[] arr, int first, int second)
    {
        Hurricane temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    /**
     * Gets the minimum year in hurricanes
     * @param arr   The array of hurricanes
     * @param low   The index to start
     *              searching from
     */
    private int findMinYear(Hurricane[] arr, int low)
    {
        int minIndex = low;
        for (int i = low; i < arr.length; i++)
        {
            if (arr[i].compareYearTo(arr[minIndex]) < 0)
            {
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * Sorts ascending based upon the hurricanes' years,
     * using selection sort.
     * @postcondition   The hurricanes are sorted by year
     *                  in ascending order
     */
    public void sortYears()
    {
        for (int i = 0; i < hurricanes.length - 1; i++)
        {
            swap(hurricanes, i, findMinYear(hurricanes, i));
        }
    }

    /**
     * Lexicographically sorts hurricanes in ascending order 
     * based on the hurricanes' name, using insertion sort.
     * @postcondition   The hurricanes are lexicographically sorted
     *                  in ascending order
     */
    public void sortNames()
    {
        for (int i = 1; i < hurricanes.length; i++)
        {
            Hurricane val = hurricanes[i];
            int j = i - 1;
            while (j >= 0 && hurricanes[j].compareNameTo(val) > 0)
            {
                hurricanes[j + 1] = hurricanes[j];
                j--;
            }
            hurricanes[j + 1] = val;
        }
    }

    /**
     * Gets the max category found in hurricanes
     * @param arr   The array of Hurricanes
     *              to be searched through
     * @param low   The index of arr to start 
     *              searching from
     * @return      The index of the hurricane with
     *              the highest category
     */
    private int findMaxCategory(Hurricane[] arr, int low)
    {
        int maxIndex = low;
        for (int i = low; i < arr.length; i++)
        {
            if (arr[i].compareCategoryTo(arr[maxIndex]) > 0)
            {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Sorts descending based upon the hurricanes' categories,
     * using selection sort.
     * @postcondition   The hurricanes are sorted in descending
     *                  order
     */
    public void sortCategories()
    {
        for (int i = 0; i < hurricanes.length - 1; i++)
        {
            swap(hurricanes, i, findMaxCategory(hurricanes, i + 1));
        }
    }  

    /**
     * Merges two sorted parts in in array by comparing their pressures
     * in descending order
     * @param a       The array containing the parts to merge
     * @param low     The starting index of the first part of the array
     * @param mid     The ending index of the first part of the array
     * @param high    The ending index of the second part of the array
     * @precondition  a[low]...a[mid] and a[mid + 1]...a[high] is sorted 
     *                in descending order
     * @postcondition a[low]...a[high] is sorted in descending order
     */
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
     * @postcondition   Hurricanes are sorted in descending order based
     *                  on pressure
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
     * Sorts ascending based upon wind speeds using a recursive merge sort 
     * @param low       The index to start sorting from
     * @param high      The last index to sort
     * @postcondition   hurricanes[low]...hurricanes[high] is sorted in
     *                  descending order by wind speed
     */
    public void sortWindSpeeds(int low, int high)
    {
        mergeWindSpeedsSortHelper(low, (low + high) / 2, high);
    }

    /**
     * Merges two sorted parts of an array by comparing their 
     * speeds
     * @param a         The array containing the parts to be 
     *                  merged
     * @param lowIndex  The starting index of the first part
     *                  of the array
     * @param midIndex  The ending index of the second part
     *                  of the array
     * @param highIndex The ending index of the second part 
     *                  of the array
     * @precondition    a[lowIndex]...a[midIndex] and 
     *                  a[midIndex + 1]...a[highIndex] is sorted
     *                  in ascending order
     * @postcondition   a[lowIndex]...a[highIndex] is sorted in 
     *                  ascending order
     */
    private void mergeSpeed(Hurricane[] a, int lowIndex, int midIndex, int highIndex)
    {
        Hurricane[] copy = new Hurricane[a.length];
        for (int i = lowIndex; i <= highIndex; i++)
        {
            copy[i] = a[i];
        }
        int left = lowIndex;
        int right = midIndex + 1;
        for (int i = lowIndex; i <= highIndex; i++)
        {
            if (right > highIndex ||
                (left <= midIndex && copy[left].compareSpeedTo(copy[right]) > 0))
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
     * @precondition  hurricanes[low]...arr[mid] and hurricanes[mid + 1] are 
     *                sorted ascending based upon wind speed
     * @param low     The starting index of one part of the array.
     *                This index is included in the first half.
     * @param mid     The starting index of the second part of the array.
     *                This index is included in the second half.
     * @param high    The ending index of the second part of the array.  
     *                This index is included in the merge.
     * @postcondition hurricanes[low]...hurricanes[high] is sorted in ascending
     *                order by wind speed
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
     * Sequentially searches for all the hurricanes in a given year
     * @param year  The year to search for in hurricanes
     * @return      An array of Hurricanes that occured in the year
     *              year
     */
    public Hurricane[] searchYear(int year)
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
     * Searches through binary serach for a hurricane name
     * @param name    the name to search for in hurricanes
     * @return        an array of Hurricanes with the given 
     *                name, null if there are no matches
     * @postcondition hurricanes are sorted by name in 
     *                ascending order
     */
    public Hurricane[] searchHurricaneName(String name)
    {
        sortNames();
        return searchHurricaneNameHelper(name, 0, hurricanes.length - 1);
    }

    /**
     * Recursively binary searches for a hurricane name.  This is the 
     * helper for searchHurricaneName.
     * @precondition  hurricanes is sorted by name
     * @param name    hurricane name to search for
     * @param low     the smallest index that needs to be checked
     * @param high    the highest index that needs to be checked
     * @return        a array of Hurricanes with the specified name, 
     *                null if there are no matches
     */
    private Hurricane[] searchHurricaneNameHelper(String name, int low , int high)
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
     * Helps searchHurricaneNameHelper get the full range of matches.
     * @precondition hurricanes is sorted by the name
     * @param name   hurricane name being search for
     * @param index  the index where a match was found
     * @return       a array of Hurricanes with the specified name,
     *               null if there are no matches
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
     * Prints the header of the table
     */
    public void printHeader()
    {
        System.out.println("\n\n");
        System.out.printf("%-4s %-5s %-15s %-5s %-5s %-5s \n", 
            "Year", "Mon.", "Name", "Cat.", "Knots", "Pressure");
    }

    /**
     * Prints each hurricane's feilds in the current order
     */
    public void printHurricanes()
    {
        printHurricanes(hurricanes);
    }

    /**
     * Given a list of Hurricanes, prints each Hurricane's
     * fields in the current order
     */
    public void printHurricanes(Hurricane[] hurs)
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
     * Prints a menu that allows the user to choose which
     * method to test
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
     * Prints the maximum and minimum wind speeds and 
     * pressures of the hurricanes
     */
    public void printMaxAndMin()
    {
        System.out.println("Maximum wind speed is " + 
            findMaxWindSpeed() +
            " knots and minimum wind speed is " + 
            findMinWindSpeed() + " knots.");
        System.out.println("Maximum pressure is " + 
            findMaxPressure() +
            " and minimum pressure is " + 
            findMinPressure() + ".");
    }

    /**
     * Prints the average wind speeds, pressures, and categories
     * of hurricanes
     */
    public void printAverages()
    {
        System.out.printf("Average wind speed is %5.2f knots. \n" , 
            calculateAverageWindSpeed());
        System.out.printf("Average pressure is %5.2f. \n" , 
            calculateAveragePressure());
        System.out.printf("Average category is %5.2f. \n" , 
            calculateAverageCategory());
    }

    /**
     * Asks the user for an input and runs the corresponding
     * method (or quits) given the input
     * @return  true if user quits, false otherwise
     */
    public boolean interactWithUser()
    {
        Scanner in = new Scanner(System.in);
        boolean done = false;
        printMenu();
        int choice = in.nextInt();
        // clear the input buffer
        in.nextLine();

        if (choice == 1)
        {
            printHurricanes(); 
        }
        else if (choice == 2)
        {
            printMaxAndMin();
        }
        else if (choice == 3)
        {
            printAverages();
        }
        else if(choice == 4)
        {
            sortYears();
            printHurricanes();
        }
        else if(choice == 5)
        {
            sortNames();
            printHurricanes();
        }
        else if(choice == 6)
        {
            sortCategories();
            printHurricanes();
        }
        else if(choice == 7)
        {
            sortPressures();
            printHurricanes();
        }
        else if(choice == 8)
        {
            sortWindSpeeds(0, hurricanes.length - 1);
            printHurricanes();
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
     * Prints the menu and asks for user input ot test a method
     * until the user quits
     * @param args          command line arguments passed into
     *                      the program
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public static void main(String[] args) throws IOException
    {
        HurricaneOrganizerArrayAarav cane = new HurricaneOrganizerArrayAarav("hurricanedata.txt");
        boolean areWeDoneYet = false;
        while (!areWeDoneYet)
        {
            areWeDoneYet = cane.interactWithUser();    
        }
    }
}
